package org.baichuan.sample.algorithms.leetcode.middle

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/10
 *
 * leetcode 11 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
class MaxArea {
    /**
     * 解题思路(双指针法思路浅析)：
     * 题目要求找出盛水最多的容器，那么哪些因素会影响到盛水量呢？首先，盛水量完全取决于底*min(左高, 右高)，
     * 所以，我们可以从底最大的时候开始遍历数组进行计算，这样的好处是当我们从"两边往中间靠的过程"中就不用考虑存在更大的底而对计算结果造成影响了。
     * 接下来是应该怎么往中间靠？答案是哪边"更低"哪边往中间靠，为什么呢？因为从高度上来说，决定盛水量的是最低的那一边。只有这一边变大才有可能使得容器的盛水量增加。
     * 最后就是循环这个往中间靠的过程了。
     */
    fun maxArea(height: IntArray): Int {
        var result = 0
        var left = 0
        var right = height.size - 1
        var i = 1
        while (left < right) {
            val tmp = if (height[left] > height[right]) {
                right--
                height[right] * ((height.size - i))
            } else {
                left++
                height[left] * ((height.size - i))
            }
            result = tmp.coerceAtLeast(result)
            i++
        }

        return result
    }
}