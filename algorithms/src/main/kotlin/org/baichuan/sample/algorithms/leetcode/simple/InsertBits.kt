package org.baichuan.sample.algorithms.leetcode.simple

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/10/30
 * 面试题 05.01. 插入
 * https://leetcode.cn/problems/insert-into-bits-lcci/
 */
class InsertBits {
    fun insertBits(N: Int, M: Int, i: Int, j: Int): Int {
        var left = N ushr j ushr 1
        left = left shl j shl 1
        val mid = M shl i
        val right = N and ((1 shl i) - 1)
        return left or mid or right
    }
}

fun main() {
    InsertBits().insertBits(
        1024,
        19,
        2,
        6
    )
}