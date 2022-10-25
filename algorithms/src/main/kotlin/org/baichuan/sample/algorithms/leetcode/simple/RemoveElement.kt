package org.baichuan.sample.algorithms.leetcode.simple

/**
 * https://leetcode.cn/problems/remove-element/
 * 27. 移除元素
 */
class RemoveElement {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var removed = 0
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            if (nums[left] == `val`) {
                nums[left] = nums[right]
                removed++
                right--
            } else {
                left++
            }
        }
        return nums.size - removed
    }
}