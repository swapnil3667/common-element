package com.apple.config

case class BloomFilterConfig(length: Int, numHash: Int, algorithm: String="SHA1")
