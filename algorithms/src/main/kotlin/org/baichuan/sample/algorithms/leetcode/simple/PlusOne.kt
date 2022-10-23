package org.baichuan.sample.algorithms.leetcode.simple

/**
 * https://leetcode.cn/problems/plus-one/
 * 66. 加一
 */
class PlusOne {
    fun plusOne(digits: IntArray): IntArray {
        var len = digits.size - 1
        while (len > -1) {
            if (digits[len] < 9) {
                digits[len] = digits[len] + 1
                return digits
            }
            digits[len] = 0
            len--
        }
        val result = IntArray(digits.size + 1)
        result[0] = 1
        return result
    }
}