package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/16
 * 17. 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * 递归解法
 */
public class LetterCombinations {
    private static final String[] LETTER_MAP = {
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>(0);
        }
        ArrayList<String> result = new ArrayList<>();
        doCombination(digits, 0, "", result);
        return result;
    }

    private void doCombination(String digits, int index, String s, List<String> result) {
        if (index == digits.length()) {
            result.add(s);
            return;
        }
        String letters = LETTER_MAP[digits.charAt(index) - '2'];
        for (int i = 0; i < letters.length(); i++) {
            doCombination(digits, index + 1, s + letters.charAt(i), result);
        }
    }
}
