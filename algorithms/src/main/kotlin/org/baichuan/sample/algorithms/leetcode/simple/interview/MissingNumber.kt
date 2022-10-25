package org.baichuan.sample.algorithms.leetcode.simple.interview

/**
 * https://leetcode.cn/problems/missing-number-lcci/
 * 面试题 17.04. 消失的数字
 */
class MissingNumber {
    fun missingNumber(nums: IntArray): Int {
        val sum = (nums.size + 1) * nums.size / 2
        var realSum = 0
        for (num in nums) {
            realSum += num
        }
        return sum - realSum
    }
}