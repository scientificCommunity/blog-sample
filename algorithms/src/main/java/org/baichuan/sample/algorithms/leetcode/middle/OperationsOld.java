package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/10
 * https://leetcode-cn.com/problems/operations-lcci/
 * 面试题 16.09. 运算
 * 最开始的思路，没完全实现，基本case都没完全通过
 * 废弃版
 */
@Deprecated
public class OperationsOld {
    static final int NEGATIVE_UNIT = -1;
    static final int POSITIVE_UNIT = 1;

    public static void main(String[] args) {
        System.out.println(new OperationsOld().minus(0, -2147483647));
        System.out.println(new OperationsOld().minus(-1, 2147483647));
        System.out.println(new OperationsOld().multiply(-1, -2147483647));
        System.out.println(new OperationsOld().multiply(-100, 21474836));
        System.out.println(new OperationsOld().divide(2147483647, -1));
        System.out.println(new OperationsOld().divide(-2147483648, 1));
    }

    public OperationsOld() {

    }

    public int minus(int a, int b) {
        if (b < 0 && a > 0 || a < 0 && b > 0) {
            return a + b;
        }

        int tmp;

        if (b > 0) {
            tmp = NEGATIVE_UNIT;
            while (b + tmp > 0) {
                tmp += NEGATIVE_UNIT;
            }
        } else {
            tmp = POSITIVE_UNIT;
            while (b + tmp < 0) {
                tmp += POSITIVE_UNIT;
            }
        }
        return a + tmp;
    }

    public int multiply(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        int originalA = a;
        int originalB = b;
        //乘积为正数
        if (b > 0 && a > 0) {
            while (b > 1) {
                a += originalA;
                b += NEGATIVE_UNIT;
            }
            return a;
        }
        if (a < 0 && b < 0) {
            //求出a对应的正数值
            int negativeA = getPositive(a);
            originalA = negativeA;
            while (b < -1) {
                negativeA += originalA;
                b += POSITIVE_UNIT;
            }
            return negativeA;
        }

        //乘积为负数
        if (a < 0) {
            while (b > 1) {
                a += originalA;
                b += NEGATIVE_UNIT;
            }
            return a;
        } else {
            while (a > 1) {
                b += originalB;
                a += NEGATIVE_UNIT;
            }
            return b;
        }
    }

    public int divide(int a, int b) {
        if (a == 0 || getPositive(a) < getPositive(b)) {
            return 0;
        }

        int result = 0;
        if (a > 0 && b > 0) {
            while (a >= b) {
                b += b;
                result++;
            }
        } else if (a < 0 && b < 0) {
            while (a <= b) {
                b += b;
                result++;
            }
        } else if (a > 0) {
            int positiveB = getPositive(b);
            long originalPositiveB = positiveB;

            //翻倍，方便后面做2的指数运算
            positiveB += positiveB;
            if (a >= positiveB) {
                result = 2;
            } else {
                result = 1;
            }
            while (a >= positiveB) {
                positiveB += positiveB;
                result += result;
            }
        } else {
            int negativeB = getNegative(b);
            long originalNegativeB = negativeB;
            while (a <= negativeB) {
                negativeB += originalNegativeB;
                result += NEGATIVE_UNIT;
            }
        }

        return result;
    }

    /**
     * 获取x对应的负数
     * x>0
     *
     * @param x
     * @return -x
     */
    private int getNegative(int x) {
        int result = 0;
        while (x > 0) {
            x += NEGATIVE_UNIT;
            result += NEGATIVE_UNIT;
        }
        return result;
    }

    /**
     * 获取x对应的正数
     * x<0
     *
     * @param x
     * @return -x
     */
    public int getPositive(int x) {
        if (x > 0) {
            return x;
        }

        int result = 0;
        while (x < 0) {
            x += POSITIVE_UNIT;
            result += POSITIVE_UNIT;
        }
        return result;
    }
}
