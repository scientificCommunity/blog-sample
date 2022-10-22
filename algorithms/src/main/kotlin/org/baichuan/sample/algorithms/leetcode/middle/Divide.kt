package org.baichuan.sample.algorithms.leetcode.middle

/**
 * https://leetcode.cn/problems/divide-two-integers/
 * 两数相除
 * 核心思路： 38/3 = 3*2 + 32 = 3*2*2+26= 3*2*2*2 + 14 = 3*2*2*2 + 3*2*2 +2 = 3(2*2*2 + 2*2) +2 = 3 * 12 + 2 = 12
 */
class Divide {
    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == 0) {
            return 0
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        if (divisor == 1) {
            return dividend
        }
        if (divisor == -1) {
            return -dividend
        }
        var result = 1
        var sign = 0
        var divided1 = if (dividend > 0) {
            sign++
            -dividend
        } else {
            sign--
            dividend
        }
        var divisor1 = if (divisor > 0) {
            sign++
            -divisor
        } else {
            sign--
            divisor
        }
        val divisor2 = divisor1

        if (divided1 > divisor1) {
            return 0
        }

        if (divisor1 == divided1) {
            return if (sign == 0) -result else result
        }

        while (divided1 - divisor1 <= divisor1) {
            divisor1 = divisor1 shl 1
            result += result
        }

        divided1 -= divisor1
        var tmp1 = result
        while (divided1 < divisor2) {
            divisor1 = divisor1 shr 1
            tmp1 = tmp1 shr 1
            if (divided1 - divisor1 <= 0) {
                divided1 -= divisor1
                result += tmp1
            }
        }
        if (divided1 == divisor2) {
            result += 1
        }
        return if (sign == 0) -result else result
    }
}

fun main() {
    println(Divide().divide(2147483647, 1))
    println(-10 shr 1)
}