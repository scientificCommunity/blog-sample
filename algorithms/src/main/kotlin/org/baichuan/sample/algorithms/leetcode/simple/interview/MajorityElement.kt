package org.baichuan.sample.algorithms.leetcode.simple.interview

/**
 * 面试题 17.10. 主要元素
 * https://leetcode.cn/problems/find-majority-element-lcci/
 */
class MajorityElement {
    /**
     * 摩尔投票解法
     * 遍历，并对不同的元素进行抵消。
     * 核心思路是：
     *    1. **如果y的个数超过数组一半大小**，则经过抵消后剩下的数一定是y。
     *    2. 如果个数超过一半大小的数不存在。则抵消后最后剩下的数可能是数组中任何一个数。因为抵消是随机的
     *
     * 所以最后要对这个剩下的数的数目进行验证，就可以判断是否存在满足条件的数以及这个数的值
     */
    fun majorityElement(nums: IntArray): Int {
        var x = 0
        var y = 0
        for (num in nums) {
            if (x == 0) {
                y = num
                x = 1
            } else {
                x += if (y == num) 1 else -1
            }
        }
        x = 0
        for (num in nums) if (y == num) x++
        return if (nums.size / 2 < x) y else -1
    }
}