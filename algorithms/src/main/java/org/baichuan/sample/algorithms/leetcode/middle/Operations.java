package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/11
 * https://leetcode-cn.com/problems/operations-lcci/
 * 面试题 16.09. 运算
 */
public class Operations {
    long[] negatives = new long[32];
    long[] positives = new long[32];
    long[] cacheA = new long[32];
    long[] cacheB = new long[32];
    static final int NEGATIVE_UNIT = -1;

    public static void main(String[] args) {
        System.out.println((Integer.MAX_VALUE + 1) * 2 + 1);
        System.out.println(new Operations().multiply(-1, -2147483647));
        System.out.println(new Operations().multiply(-100, 21474836));
        System.out.println(new Operations().divide(-2147483648, 1));
    }

    public Operations() {
        negatives[0] = NEGATIVE_UNIT;
        positives[0] = 1;
        for (int i = 1; i < 32; i++) {
            negatives[i] = negatives[i + NEGATIVE_UNIT] + negatives[i + NEGATIVE_UNIT];
            positives[i] = positives[i + NEGATIVE_UNIT] + positives[i + NEGATIVE_UNIT];
        }
    }

    public int minus(int a, int b) {
        if (a == b) {
            return 0;
        }
        int index = 31;
        while (index >= 0) {
            if (b > 0) {
                if (b >= positives[index]) {
                    b += negatives[index];
                    a += negatives[index];
                } else {
                    index += NEGATIVE_UNIT;
                }
            } else {
                if (b <= negatives[index]) {
                    b += positives[index];
                    a += positives[index];
                } else {
                    index += NEGATIVE_UNIT;
                }
            }
        }
        return a;
    }

    public int multiply(int a, int b) {
        if (a == 0 || b == 0) return 0;
        if (a == 1) return b;
        if (b == 1) return a;
        if (a == NEGATIVE_UNIT) return minus(0, b);
        if (b == NEGATIVE_UNIT) return minus(0, a);
        boolean aSign = a > 0;
        //最终结果的正负，true：正 false：负
        boolean resultSign = (a > 0 && b > 0) || (a < 0 && b < 0);

        /**
         * 转负数是为了简化代码
         * 为什么不转正数？因为-2147483648无法转成有效的4字节正数
         */
        if (a > 0) {
            a = minus(0, a);
        }
        if (b > 0) {
            b = minus(0, b);
        }

        cacheA[0] = a;
        for (int i = 1; i < 31; i++) {
            cacheA[i] = cacheA[i + NEGATIVE_UNIT] + cacheA[i + NEGATIVE_UNIT];
        }

        int result = 0;
        int index = 31;
        while (index >= 0) {
            if (b <= negatives[index]) {
                b += positives[index];
                result += cacheA[index];
            }
            index += NEGATIVE_UNIT;
        }

        //原始结果符号，用来判断计算结果是否溢出
        boolean originalResultSign = result > 0;
        //确保溢出的情况不会影响结果的正负
        if (originalResultSign != aSign) {
            result = minus(0, result);
        }

        //确定最终结果的正负
        if ((resultSign && result < 0) || (!resultSign && result > 0)) {
            result = minus(0, result);
        }

        return result;
    }

    public int divide(int a, int b) {
        if (a == 0 || (a > 0 && a < b) || (a < 0 && a > b)) return 0;
        int result = 0;

        boolean resultSign = (a > 0 && b > 0) || (a < 0 && b < 0);

        if (a > 0) {
            a = minus(0, a);
        }
        if (b > 0) {
            b = minus(0, b);
        }

        //cacheB[0] = b* 2^0
        cacheB[0] = b;
        for (int i = 1; i < 32; i++) {
            cacheB[i] = cacheB[i + NEGATIVE_UNIT] + cacheB[i + NEGATIVE_UNIT];
        }

        int index = 31;
        long preDividedB = 0;
        while (index >= 0) {
            if (a <= cacheB[index] + preDividedB) {
                preDividedB += cacheB[index];
                result += positives[index];
            }
            index += NEGATIVE_UNIT;
        }

        //确定最终结果的正负
        if ((resultSign && result < 0) || (!resultSign && result > 0)) {
            result = minus(0, result);
        }
        return result;
    }

}
