package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.Arrays;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/17
 * 面试题 17.15. 最长单词
 * https://leetcode-cn.com/problems/longest-word-lcci/
 * 解法：字典树+DFS
 * 相对于{@link LongestWord} 该解法先对word排序，然后直接遍历找到第一个能由其他word组成的word即可
 * <p>
 * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
 * <p>
 * 示例：
 * <p>
 * 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
 * 输出： "dogwalker"
 * 解释： "dogwalker"可由"dog"和"walker"组成。
 * 提示：
 * <p>
 * 0 <= len(words) <= 200
 * 1 <= len(words[i]) <= 100
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestWord2 {
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
        //优先按长度从大到小排序，长度相等则按所有字母的字典顺序排序
        Arrays.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            if (find2(word, 0, 0)) {
                return word;
            }
        }
        return ans;
    }

    private void constructTrieNode(String[] words) {
        for (String s : words) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                int index = c - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new TrieNode();
                }
                node = node.next[index];
            }
            node.word = s;
        }
    }

    // TODO: 2022/4/17 剪枝
    private boolean find(String word, int begin) {
        TrieNode node = root;
        for (int i = begin; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.next[index] == null) {
                return false;
            }
            node = node.next[index];

            if (node.word != null && !node.word.equals(word)) {
                if (find(word, i + 1)) {
                    return true;
                }
            }
        }

        return node.word != null && !node.word.equals(word);
    }

    /**
     * @param begin     本次迭代遍历word字符时的起始偏移量
     * @param wordCount 已跟word匹配的单词数量，如果最终word=1，表明只有word本身与其匹配，此时返回false。即return wordCount>1
     * @return true: word由其他单词组成
     */
    private boolean find2(String word, int begin, int wordCount) {
        TrieNode node = root;
        for (int i = begin; i < word.length(); i++) {
            if (node.next[word.charAt(i) - 'a'] == null) {
                return false;
            }
            node = node.next[word.charAt(i) - 'a'];

            if (node.word != null) {
                if (word.length() > i + 1) {
                    if (find2(word, i + 1, wordCount + 1)) {
                        return true;
                    }
                } else {
                    wordCount++;
                }
            }
        }

        return node.word != null && wordCount > 1;
    }

    public static void main(String[] args) {
        //case 1
        //System.out.println(new LongestWord().longestWord(new String[]{""}));

        //case 2
        //System.out.println(new LongestWord().longestWord(new String[]{"ttaaaa", "pp", "tpa", "kpaqkt", "tktpqq", "aqppatp"}));

        //case 3
        //System.out.println(new LongestWord().longestWord(new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"}));

        //case 4
        //System.out.println(new LongestWord().longestWord(new String[]{"pg", "ptgt", "tgpppttg", "tptttgg", "pgttggtpt", "t", "ptg", "ppgp", "g", "ptgpptpgg"}));
        System.out.println(new LongestWord().longestWord(new String[]{"bbac", "bbab", "bbaa", "babb", "baba", "baaa", "ptg", "ppgp", "g", "ptgpptpgg"}));
    }
}
