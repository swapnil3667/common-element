package com.apple

import java.math.BigInteger
import java.security.MessageDigest

import scala.util.Try

class BloomFilter(length: Int, numHash: Int, algorithm: String="SHA1") {

  val set = new Array[Byte](length)
  val digest = Try(MessageDigest.getInstance(algorithm))

  def addArray(elements: Array[Int]): Int = {
    elements.foreach( getSet(_).foreach(set(_) = 1))
    elements.size
  }

  def add(el: Int) = getSet(el).foreach(set(_) = 1)

  final def contains(el: Int): Boolean = !getSet(el).exists(set(_) != 1)

  import java.math.BigInteger

  private def bigIntToByteArray(i: Int) = {
    val bigInt = BigInteger.valueOf(i)
    bigInt.toByteArray
  }

  def hash(value: Int,index: Int = 0) : Int = digest.map(d => {
    d.reset
    d.update(index.toByte)
    Math.abs(new BigInteger(1, d.digest(bigIntToByteArray(value))).intValue) % (set.size -1)
  }).getOrElse(-1)

  private def getSet(el: Int): Array[Int] = {
    val newSet = new Array[Int](numHash)
    var index =  0
    while(index < newSet.size){
      newSet.update(index, hash(el,index))
      index +=1
    }
    newSet
  }

}