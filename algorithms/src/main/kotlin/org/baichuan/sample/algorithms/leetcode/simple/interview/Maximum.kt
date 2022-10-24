package org.baichuan.sample.algorithms.leetcode.simple.interview

/**
 * 面试题 16.07. 最大数值
 * https://leetcode.cn/problems/maximum-lcci/
 */
class Maximum {
    fun maximum(a: Int, b: Int): Int {
        val k = ((a.toLong() - b.toLong()) ushr 63).toInt()
        return a * (k xor 1) + b * k
    }
}

fun main() {
    println(Maximum().maximum(2147483647, -2147483648))
    println((1 - 99) shr 30)
    println(Int.MIN_VALUE ushr 31)
}