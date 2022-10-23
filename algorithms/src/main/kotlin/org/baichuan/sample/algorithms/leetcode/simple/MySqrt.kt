package org.baichuan.sample.algorithms.leetcode.simple

/**
 * 69. x 的平方根
 * https://leetcode.cn/problems/sqrtx/
 */
class MySqrt {
    fun mySqrt(x: Int): Int {
        var l = 0
        var r = Math.min(x + 1 / 2, 46340)
        while (r > l) {
            val i = l / 2 + r / 2
            if (i * i > x) {
                r = i - 1
            } else {
                l = i + 1
            }
        }
        if (l * l > x) {
            return l - 1
        }
        return l
    }
}

fun main() {
    println(MySqrt().mySqrt(2147395599))
}