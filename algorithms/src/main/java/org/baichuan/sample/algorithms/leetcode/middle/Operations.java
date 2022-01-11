package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/10
 * https://leetcode-cn.com/problems/operations-lcci/
 * 面试题 16.09. 运算
 */
public class Operations {
    static final int NEGATIVE_UNIT = -1;
    static final int POSITIVE_UNIT = 1;

    public static void main(String[] args) {
        System.out.println(new Operations().minus(2, 1));
        System.out.println(new Operations().multiply(0, -5));
        System.out.println(new Operations().divide(2, 2));
    }

    public Operations() {

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
            while (a >= positiveB) {
                positiveB += positiveB;
                result += NEGATIVE_UNIT;
            }
        } else {
            int negativeB = getNegative(b);
            while (a <= negativeB) {
                negativeB += negativeB;
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
        int result = 0;
        while (x < 0) {
            x += POSITIVE_UNIT;
            result += POSITIVE_UNIT;
        }
        return result;
    }
}
