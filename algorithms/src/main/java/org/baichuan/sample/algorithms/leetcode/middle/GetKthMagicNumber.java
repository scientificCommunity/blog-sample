package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/18
 * 面试题 17.09. 第 k 个数
 * https://leetcode-cn.com/problems/get-kth-magic-number-lcci/
 */
public class GetKthMagicNumber {
    public int getKthMagicNumber(int k) {
        int[] nums = new int[k];
        nums[0] = 1;
        int p1 = 0, p2 = 0, p3 = 0;
        for (int i = 1; i < k; i++) {
            int pn1 = nums[p1] * 3;
            int pn2 = nums[p2] * 5;
            int pn3 = nums[p3] * 7;
            if (pn1 <= pn2 && pn1 <= pn3) {
                nums[i] = pn1;
                p1++;
            }
            //三个条件同时进行，跳过重合的值
            if (pn2 <= pn1 && pn2 <= pn3) {
                nums[i] = pn2;
                p2++;
            }

            if (pn3 <= pn1 && pn3 <= pn2) {
                nums[i] = pn3;
                p3++;
            }
        }
        return nums[k - 1];
    }

    public static void main(String[] args) {
        System.out.println(new GetKthMagicNumber().getKthMagicNumber(7));
    }
}
