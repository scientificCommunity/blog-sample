package org.baichuan.sample.algorithms.leetcode.middle

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 * 33. 搜索旋转排序数组
 */
class Search {
    fun search(nums: IntArray, target: Int): Int {
        val first = nums[0]
        if (target == first) {
            return 0
        }
        var i = nums.size / 2
        var left = 0
        var right = nums.size - 1
        while (right > left) {
            if (target == nums[i]) {
                return i
            }
            if (target > nums[i]) {
                if (nums[i] < first && target > first) {
                    right = i
                    i = (right + left) / 2
                } else {
                    if (i == nums.size - 1) return -1
                    left = i + 1
                    i = (right + left) / 2
                }
            } else {
                if (nums[i] > first) {
                    if (target > first) {
                        right = i
                        i = (right + left) / 2
                    } else {
                        if (i == nums.size - 1) return -1
                        left = i + 1
                        i = (right + left) / 2
                    }
                } else {
                    right = i
                    i = (right + left) / 2
                }
            }
        }
        if (nums[left] == target) {
            return left
        }
        return -1
    }
}

fun main() {
    Search().search(arrayOf(8, 9, 2, 3, 4).toIntArray(), 9)
}