package com.apple.config

case class ArgumentConfig(firstFilePath: String,
                          secondFilePath: String,
                          outputFilePath: String,
                          bloomFilterLength: Int,
                          bloomFilterNumHash: Int,
                          bloomFilterAlgorithm: String
                         )


object ArgumentConfig {
  def parse(args: Array[String]): ArgumentConfig = {
    val parser = new scopt.OptionParser[ArgumentConfig]("Driver") {
      override def errorOnUnknownArgument = false

      head("Common Elements Driver arguments")

      opt[String]('c', "first-file-path")
        .required()
        .action((s, da) => da.copy(firstFilePath = s))
        .text("first file Path is required")

      help("help").text("prints this usage text")

      opt[String]('c', "second-file-path")
        .required()
        .action((s, da) => da.copy(secondFilePath = s))
        .text("second file Path is required")

      opt[String]('c', "output-file-path")
        .required()
        .action((s, da) => da.copy(outputFilePath = s))
        .text("output file Path is required")

      opt[Int]("bloom-filter-length")
        .required()
        .action((s, da) => da.copy(bloomFilterLength = s))
        .text("bloom-filter-length is required")

      opt[Int]("bloom-filter-num-hash")
        .required()
        .action((s, da) => da.copy(bloomFilterNumHash = s))
        .text("bloom-filter-num-hash is required")

      opt[String]('c', "bloom-filter-algorithm")
        .required()
        .action((s, da) => da.copy(bloomFilterAlgorithm = s))
        .text("bloom-filter-algorithm is required")

    }

    parser.parse(args, ArgumentConfig(null, null,null,100,100,null))
      .getOrElse(throw new IllegalArgumentException(parser.usage))
  }
}