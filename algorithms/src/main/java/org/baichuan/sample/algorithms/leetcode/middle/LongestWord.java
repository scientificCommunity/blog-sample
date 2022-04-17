package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/17
 * 面试题 17.15. 最长单词
 * https://leetcode-cn.com/problems/longest-word-lcci/
 * 解法：字典树+DPS
 *
 * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
 *
 * 示例：
 *
 * 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
 * 输出： "dogwalker"
 * 解释： "dogwalker"可由"dog"和"walker"组成。
 * 提示：
 *
 * 0 <= len(words) <= 200
 * 1 <= len(words[i]) <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestWord {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word = null;
    }

    private final TrieNode root = new TrieNode();

    public String longestWord(String[] words) {
        String ans = "";
        if (words.length == 0) {
            return ans;
        }

        constructTrieNode(words);
        int maxLen = 0;
        char startLetter = 'z';
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            if (find(word, 0)) {
                if (word.length() > maxLen || (word.length() == maxLen && word.charAt(0) < startLetter)) {
                    ans = word;
                    maxLen = word.length();
                    startLetter = word.charAt(0);
                }
            }
        }
        return ans;
    }

    private void constructTrieNode(String[] words) {
        for (String s : words) {
            TrieNode node = root;
            for (Character c : s.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
            }
            node.word = s;
        }
    }

    // TODO: 2022/4/17 剪枝
    private boolean find(String word, int begin) {
        TrieNode node = root;
        for (int i = begin; i < word.length(); i++) {
            if (node.next[word.charAt(i) - 'a'] == null) {
                return false;
            }
            node = node.next[word.charAt(i) - 'a'];

            if (node.word != null && !node.word.equals(word)) {
                if (find(word, i + 1)) {
                    return true;
                }
            }
        }

        return node.word != null && !node.word.equals(word);
    }

    public static void main(String[] args) {
        //case 1
        //System.out.println(new LongestWord().longestWord(new String[]{""}));

        //case 2
        //System.out.println(new LongestWord().longestWord(new String[]{"ttaaaa", "pp", "tpa", "kpaqkt", "tktpqq", "aqppatp"}));

        //case 3
        //System.out.println(new LongestWord().longestWord(new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"}));

        //case 4
        System.out.println(new LongestWord().longestWord(new String[]{"pg", "ptgt", "tgpppttg", "tptttgg", "pgttggtpt", "t", "ptg", "ppgp", "g", "ptgpptpgg"}));
    }
}
