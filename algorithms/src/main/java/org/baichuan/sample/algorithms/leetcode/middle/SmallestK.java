package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/14
 * 面试题 17.14. 最小K个数
 * https://leetcode-cn.com/problems/smallest-k-lcci/
 */
public class SmallestK {
    public static void main(String[] args) {
        //[1,3,5,7,2,4,6,8]
        //4
        for (int x : new SmallestK().smallestK(new int[]{1, 3, 5, 7, 2, 4, 6, 8}, 4)) {
            System.out.println(x);
        }
    }

    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[k];
        }
        int[] result = new int[k];
        int preMax = 0;
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
            if (arr[i] > preMax) {
                preMax = arr[i];
            }
        }

        for (int i = k; i < arr.length; i++) {
            preMax = findMax(result, arr[i], preMax);
        }
        return result;
    }

    private int findMax(int[] arrK, int n, int preMax) {
        if (n >= preMax) {
            return preMax;
        }
        int re = n;
        boolean replaced = false;
        for (int i = 0; i < arrK.length; i++) {
            if (arrK[i] > re) {
                if (arrK[i] == preMax && !replaced) {
                    arrK[i] = n;
                    replaced = true;
                } else {
                    re = arrK[i];
                }
            }
        }
        return re;
    }
}
