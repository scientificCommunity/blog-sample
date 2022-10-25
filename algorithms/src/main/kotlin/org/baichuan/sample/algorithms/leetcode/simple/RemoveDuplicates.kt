package org.baichuan.sample.algorithms.leetcode.simple

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 * 26. 删除有序数组中的重复项
 */
class RemoveDuplicates {
    fun removeDuplicates(nums: IntArray): Int {
        var pre: Int? = null
        var end = 0
        var i = 0
        while (true) {
            if (i > nums.size - 1 - end) {
                break
            }
            if (nums[i] == pre) {
                reRange(i, nums, end)
                end++
                i--
            } else {
                pre = nums[i]
            }
            i++
        }
        return nums.size - end
    }

    private fun reRange(start: Int, nums: IntArray, end: Int) {
        for (i in start until nums.size - end - 1) {
            nums[i] = nums[i + 1]
        }
    }
}

fun main() {
    RemoveDuplicates().removeDuplicates(arrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4).toIntArray())
}