package org.baichuan.sample.algorithms.leetcode.middle

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * 28. 找出字符串中第一个匹配项的下标
 */
class StrStr {
    fun strStr(haystack: String, needle: String): Int {
        val hLen = haystack.length
        val nLen = needle.length
        val hChars = haystack.toCharArray()
        val nChars = needle.toCharArray()

        for (i in 0 until hLen - nLen + 1) {
            var k = i
            for (j in 0 until nLen) {
                if (hChars[k] != nChars[j]) {
                    break
                }
                k++
            }
            if ((k - i) == nLen) {
                return i
            }
        }
        return -1
    }
}

fun main() {
    StrStr().strStr("hello", "ll")
}