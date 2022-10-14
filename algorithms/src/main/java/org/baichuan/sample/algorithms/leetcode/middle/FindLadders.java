package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.*;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/16
 * 面试题 17.22. 单词转换
 * https://leetcode-cn.com/problems/word-transformer-lcci/
 * 解法：DFS
 * 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
 * <p>
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出:
 * ["hit","hot","dot","lot","log","cog"]
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: []
 * <p>
 * 解释:endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-transformer-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindLadders {
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> ans = new ArrayList<>();
        ans.add(beginWord);
        Set<Integer> visited = new HashSet<>();
        doFind(beginWord, endWord, wordList, ans, visited);
        if (ans.size() == 1) {
            ans.remove(0);
        }
        return ans;
    }

    private boolean doFind(String beginWord, String endWord, List<String> wordList, List<String> ans, Set<Integer> visited) {
        if (beginWord.equals(endWord)) {
            return true;
        }
        for (int i = 0; i < wordList.size(); i++) {
            if (visited.contains(i)) {
                continue;
            }

            if (canTransfer(beginWord, wordList.get(i))) {
                ans.add(wordList.get(i));
                visited.add(i);
                if (doFind(wordList.get(i), endWord, wordList, ans, visited)) {
                    return true;
                }
                ans.remove(ans.size() - 1);
            }
        }
        return false;
    }

    private boolean canTransfer(String beginWord, String word) {
        int diff2Begin = 0;
        for (int j = 0; j < word.length(); j++) {
            if (word.charAt(j) != beginWord.charAt(j)) {
                if (diff2Begin == 1) {
                    return false;
                }
                diff2Begin++;
            }
        }
        return diff2Begin == 1;
    }

    public static void main(String[] args) {
        //case 1
//        List<String> strings = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
//        String startWords = "hit";
//        String endWords = "cog";

        //case 2
//        List<String> strings = Arrays.asList("a", "b", "c");
//        String startWords = "a";
//        String endWords = "c";

        //case 3
//        List<String> strings = Arrays.asList("lest","leet","lose","code","lode","robe","lost");
//        String startWords = "leet";
//        String endWords = "code";

        //case 4
        List<String> strings = Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye");
        String startWords = "qa";
        String endWords = "sq";
        new FindLadders().findLadders(startWords, endWords, strings);
    }
}
