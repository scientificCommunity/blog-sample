package org.baichuan.sample.algorithms.leetcode.simple

/**
 * 面试题 05.07. 配对交换
 * https://leetcode.cn/problems/exchange-lcci/
 */
class ExchangeBits {
    fun exchangeBits(num: Int): Int {
        //取奇数位
        val x = 1431655765
        //取偶数位
        val y = 715827882

        val xx = num and x shl 1
        val yy = num and y shr 1

        return xx xor yy
    }
}

fun main() {
    ExchangeBits().exchangeBits(1)
    var i = 0
    var result = 0.0
    while (i < 31) {
        result += Math.pow(2.0, i.toDouble())
        i += 2
    }
    println(result.toInt())
}