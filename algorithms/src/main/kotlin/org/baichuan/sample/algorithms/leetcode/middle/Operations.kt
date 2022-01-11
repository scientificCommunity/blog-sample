package org.baichuan.sample.algorithms.leetcode.middle

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/11
 */
class Operations {
    var negatives = LongArray(32)
    var positives = LongArray(32)
    var cacheA = LongArray(32)
    var cacheB = LongArray(32)
    val NEGATIVE_UNIT = -1

    fun Operations() {
        negatives[0] = NEGATIVE_UNIT.toLong()
        positives[0] = 1
        for (i in 1..31) {
            negatives[i] = negatives[i + NEGATIVE_UNIT] + negatives[i + NEGATIVE_UNIT]
            positives[i] = positives[i + NEGATIVE_UNIT] + positives[i + NEGATIVE_UNIT]
        }
    }

    fun minus(a: Int, b: Int): Int {
        var aVar = a
        var bVar = b
        if (aVar == bVar) {
            return 0
        }
        var index = 31
        while (index >= 0) {
            if (bVar > 0) {
                if (bVar >= positives[index]) {
                    bVar += (negatives[index]).toInt()
                    aVar += (negatives[index]).toInt()
                } else {
                    index += NEGATIVE_UNIT
                }
            } else {
                if (bVar <= negatives[index]) {
                    bVar += (positives[index]).toInt()
                    aVar += (positives[index]).toInt()
                } else {
                    index += NEGATIVE_UNIT
                }
            }
        }
        return aVar
    }

    fun multiply(a: Int, b: Int): Int {
        var aVar = a
        var bVar = b
        if (aVar == 0 || bVar == 0) return 0
        if (aVar == 1) return bVar
        if (bVar == 1) return aVar
        if (aVar == NEGATIVE_UNIT) return minus(0, bVar)
        if (bVar == NEGATIVE_UNIT) return minus(0, aVar)
        val aSign = aVar > 0
        //最终结果的正负，true：正 false：负
        val resultSign = aVar > 0 && bVar > 0 || aVar < 0 && bVar < 0
        /**
         * 转负数是为了简化代码
         * 为什么不转正数？因为-2147483648无法转成有效的4字节正数
         */
        if (aVar > 0) {
            aVar = minus(0, aVar)
        }
        if (bVar > 0) {
            bVar = minus(0, bVar)
        }
        cacheA[0] = aVar.toLong()
        for (i in 1..30) {
            cacheA[i] = cacheA[i + NEGATIVE_UNIT] + cacheA[i + NEGATIVE_UNIT]
        }
        var result = 0
        var index = 31
        while (index >= 0) {
            if (bVar <= negatives[index]) {
                bVar += (positives[index]).toInt()
                result += (cacheA[index]).toInt()
            }
            index += NEGATIVE_UNIT
        }

        //原始结果符号，用来判断计算结果是否溢出
        val originalResultSign = result > 0
        //确保溢出的情况不会影响结果的正负
        if (originalResultSign != aSign) {
            result = minus(0, result)
        }

        //确定最终结果的正负
        if (resultSign && result < 0 || !resultSign && result > 0) {
            result = minus(0, result)
        }
        return result
    }

    fun divide(a: Int, b: Int): Int {
        var aVar = a
        var bVar = b
        if (aVar == 0 || aVar > 0 && aVar < bVar || aVar < 0 && aVar > bVar) return 0
        var result = 0
        val resultSign = aVar > 0 && bVar > 0 || aVar < 0 && bVar < 0
        if (aVar > 0) {
            aVar = minus(0, aVar)
        }
        if (bVar > 0) {
            bVar = minus(0, bVar)
        }

        //cacheB[0] = b* 2^0
        cacheB[0] = bVar.toLong()
        for (i in 1..31) {
            cacheB[i] = cacheB[i + NEGATIVE_UNIT] + cacheB[i + NEGATIVE_UNIT]
        }
        var index = 31
        var preDividedB: Long = 0
        while (index >= 0) {
            if (aVar <= cacheB[index] + preDividedB) {
                preDividedB += cacheB[index]
                result += (positives[index]).toInt()
            }
            index += NEGATIVE_UNIT
        }

        //确定最终结果的正负
        if (resultSign && result < 0 || !resultSign && result > 0) {
            result = minus(0, result)
        }
        return result
    }
}