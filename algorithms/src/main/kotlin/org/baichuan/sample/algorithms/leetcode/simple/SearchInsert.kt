package org.baichuan.sample.algorithms.leetcode.simple

/**
 * https://leetcode.cn/problems/search-insert-position/
 * 35. 搜索插入位置
 */
class SearchInsert {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            val i = (left + right) / 2
            if (nums[i] == target) {
                return i
            }
            if (nums[i] > target) {
                right = i - 1
            } else {
                left = i + 1
            }
        }
        if (nums[left] < target) {
            return left + 1
        }
        return left
    }
}

fun main() {
    SearchInsert().searchInsert(arrayOf(1, 3).toIntArray(), 4)
}