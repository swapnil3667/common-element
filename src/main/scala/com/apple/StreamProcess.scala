package com.apple

import java.nio.file.Paths

import cats.effect._
import com.apple.config.BloomFilterConfig
import fs2._

class StreamProcess(firstFilePath: String,
                    secondFilePath: String,
                    outputFilePath: String,
                    bloomFilterConfig: BloomFilterConfig) {

  implicit val ioContextShift: ContextShift[IO] =
    IO.contextShift(scala.concurrent.ExecutionContext.Implicits.global)

  val filter = new BloomFilter(bloomFilterConfig.length, bloomFilterConfig.numHash, bloomFilterConfig.algorithm)


  def populateBloomFilter()  =
    Stream.resource(Blocker[IO]).flatMap { blocker =>
      io.file.readAll[IO](Paths.get(firstFilePath), blocker, 4096)
        .through(text.utf8Decode)
        .through(text.lines)
        .evalTap(x => IO(filter.add(x.toInt)))
    }


  def processAndWrite(): Stream[IO, Unit] =

    Stream.resource(Blocker[IO]).flatMap { blocker =>

      io.file.readAll[IO](Paths.get(secondFilePath), blocker, 4096)
        .through(text.utf8Decode)
        .through(text.lines)
        .map(x => x.toInt)
        .filter(x => filter.contains(x))
        .map(x => x.toString())
        .intersperse("\n")
        .through(text.utf8Encode)
        .through(io.file.writeAll(Paths.get(outputFilePath),blocker))
    }

  def run() ={
    populateBloomFilter().compile.drain.unsafeRunSync()
    processAndWrite().compile.drain.unsafeRunSync()
  }

}
