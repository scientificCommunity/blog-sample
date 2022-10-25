package org.baichuan.sample.algorithms.leetcode.simple

/**
 * 202. 快乐数
 * https://leetcode.cn/problems/happy-number/
 */
class IsHappy {
    /**
     * 通过hash判断重复
     */
    fun isHappy(n: Int): Boolean {
        val map: MutableMap<Int, Boolean> = HashMap()
        var x = n
        while (map[x] == null) {
            map[x] = true
            x = next(x)
            if (x == 1) {
                return true
            }
        }
        return false
    }

    /**
     * 快慢指针判断循环。在一个圆里，走的快的必然会跟走的慢的重合
     */
    fun isHappy1(n: Int): Boolean {
        var x = n
        var y = n
        while (true) {
            x = next(x)
            y = next(y)
            y = next(y)
            if (y == 1) {
                return true
            }
            if (x == y) {
                return false
            }
        }
    }

    fun next(n: Int): Int {
        var x = n
        var result = 0
        while (x > 0) {
            val y = x % 10
            result += +y * y
            x /= 10
        }
        return result
    }
}

fun main() {
    IsHappy().isHappy(19)
}