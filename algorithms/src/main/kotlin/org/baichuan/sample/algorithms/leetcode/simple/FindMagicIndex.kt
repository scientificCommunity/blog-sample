package org.baichuan.sample.algorithms.leetcode.simple

/**
 * 面试题 08.03. 魔术索引
 * https://leetcode.cn/problems/magic-index-lcci/
 */
class FindMagicIndex {
    fun findMagicIndex(nums: IntArray): Int {
        var i = 0
        while (i < nums.size) {
            val minus = nums[i] - i
            if (minus == 0) {
                return i
            }
            //如果数组元素更大，则直接跳到以元素值为下标的索引下
            if (minus > 0) {
                i = nums[i]
            } else {
                i++
            }
        }
        return -1
    }
}