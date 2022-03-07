package com.apple

import com.apple.config.{ArgumentConfig, BloomFilterConfig}

object CommonElementsDriver {

  def main(args: Array[String]): Unit = {

    val argConfig = ArgumentConfig.parse(args)
    val commonElements = new StreamProcess(argConfig.firstFilePath,
      argConfig.secondFilePath,
      argConfig.outputFilePath,
      BloomFilterConfig(argConfig.bloomFilterLength,argConfig.bloomFilterNumHash,argConfig.bloomFilterAlgorithm)
    )
    commonElements.run()
  }

}
