package org.baichuan.sample.algorithms.leetcode.simple;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2022/1/20
 * 面试题 16.15. 珠玑妙算
 * https://leetcode-cn.com/problems/master-mind-lcci/
 */
public class MasterMind {
    public static void main(String[] args) {
        //"RGRB"
        //"BBBY"
        for (int i: new MasterMind().masterMind("RGRB","BBBY")){
            System.out.println(i);
        }
    }
    public int[] masterMind(String solution, String guess) {
        short[] shorts = new short[24];
        int real = 0;
        int fake = 0;
        for (int i = 0; i < 4; i++) {
            char s = solution.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                real++;
                continue;
            }
            if (shorts[s - 'B'] > 0) {
                fake++;
            }
            shorts[s - 'B']--;

            if (shorts[g - 'B'] < 0) {
                fake++;
            }
            shorts[g - 'B']++;
        }
        return new int[]{real, fake};
    }
}
