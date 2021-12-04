package org.baichuan.sample.algorithms.leetcode.middle

import org.baichuan.sample.algorithms.leetcode.middle.FindNthDigit.solution

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/12/3
 */
object FindNthDigit {
    fun solution(n: Int): Int {
        val a = 9
        var b = 1
        val c = 10
        var d = 1
        var index = 0
        var result = 0
        var n1 = n
        while (n1 > 0) {
            val i = n1 - (a * b * d)
            if (i > 0) {
                index += (a * b)
            } else {
                index += (n1 / d)
                var x = n1 % d
                if (x > 0) {
                    index += 1
                    while (x > 1) {
                        index %= b
                        x--
                        b /= 10
                    }
                    result = index / b
                } else {
                    while (b > 1) {
                        index %= b
                        b /= 10
                    }
                    result = index
                }
            }
            n1 = i
            b *= c
            d++
        }
        return result
    }
}

fun main() {
    println(solution(11))
}