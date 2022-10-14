package org.baichuan.sample.algorithms.leetcode.simple;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/1/27
 * 14. 最长公共前缀
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        //System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"a"}));
    }

    public String longestCommonPrefix(String[] strs) {
        int currIndex = 0;
        StringBuilder result = new StringBuilder();
        boolean goOn = true;
        Character c = null;
        while (goOn) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() <= currIndex) {
                    goOn = false;
                    break;
                }
                char c1 = strs[i].charAt(currIndex);
                if (c == null) {
                    c = c1;
                    if (i == strs.length - 1) {
                        result.append(c);
                        c = null;
                    }
                    continue;
                }
                if (c != c1) {
                    goOn = false;
                    break;
                }
                if (i == strs.length - 1) {
                    result.append(c);
                    c = null;
                }
            }
            currIndex++;
        }
        return result.toString();
    }

    public String solution2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
