package com.apple

import org.scalactic.source.Position
import org.scalatest.{BeforeAndAfter, FlatSpec}

class BloomFilterSpec extends FlatSpec with BeforeAndAfter {

  "BloomFilter" should "returns expected result" in {
    val filter = new BloomFilter(100, 100, "SHA")
    val newValues = Array[Int](57, 97, 91, 23, 67,33)
    filter.addArray(newValues)
    assert(filter.contains(57) == true)
    assert(filter.contains(5) == false)
    assert(filter.contains(22) == false)
    assert(filter.contains(67) == true)
  }
}
