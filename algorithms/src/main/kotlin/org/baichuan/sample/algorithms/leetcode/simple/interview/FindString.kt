package org.baichuan.sample.algorithms.leetcode.simple.interview

/**
 * 面试题 10.05. 稀疏数组搜索
 * https://leetcode.cn/problems/sparse-array-search-lcci/
 */
class FindString {
    fun findString(words: Array<String>, s: String): Int {
        var l = 0
        var r = words.size - 1
        while (l <= r) {
            var i = (l + r) / 2
            var wc = words[i].toCharArray()
            while (wc.isEmpty()) {
                if (l > r) {
                    return -1
                }
                i = l
                l++
                wc = words[i].toCharArray()
            }
            val sc = s.toCharArray()

            var flag = true
            for (j in sc.indices) {
                if (sc[j] != wc[j]) {
                    if (s[j] > wc[j]) {
                        l = i + 1
                    } else {
                        r = i - 1
                    }
                    flag = false
                    break
                }
                if (j == wc.size - 1 && j < sc.size - 1) {
                    l = i + 1
                    flag = false
                    break
                }
            }
            if (flag && wc.size == sc.size) {
                return i
            }
        }
        return -1
    }
}

fun main() {
    println(
        FindString().findString(
            arrayOf(
                "DirNnILhARNS hOYIFB",
                "SM ",
                "YSPBaovrZBS",
                "evMMBOf",
                "mCrS",
                "oRJfjw gwuo",
                "xOpSEXvfI"
            ), "mCrS"
        )
    )
}