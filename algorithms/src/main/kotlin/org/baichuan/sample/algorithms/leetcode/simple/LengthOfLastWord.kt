package org.baichuan.sample.algorithms.leetcode.simple

/**
 * https://leetcode.cn/problems/length-of-last-word/
 * 58. 最后一个单词的长度
 */
class LengthOfLastWord {
    fun lengthOfLastWord(s: String): Int {
        val cs = s.toCharArray()
        var i = cs.size - 1
        var result = 0
        while (i != -1) {
            if (cs[i] == ' ') {
                if (result > 0) {
                    return result
                }
            } else {
                result++
            }
            i--
        }
        return result
    }
}