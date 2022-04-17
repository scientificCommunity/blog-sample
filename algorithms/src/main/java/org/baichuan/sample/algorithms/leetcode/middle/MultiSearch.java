package org.baichuan.sample.algorithms.leetcode.middle;

import java.util.*;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/17
 * 面试题 17.17. 多次搜索
 * https://leetcode-cn.com/problems/multi-search-lcci/
 */
public class MultiSearch {
    class TrieNode {
        //本质上是把small中的所有字符拿出来，按small字符串每个字符的顺序组成了一个树，有相同字符顺序的字符串或者子串，其在树中的路径也会有相应的重合。
        //这样通过big对树进行后缀搜索时，
        // 每一次对树的访问都是常数级的时间复杂度（构造树的复杂度是len(small)，搜索树的时间复杂度是len(big)，极端情况是len(big)^2）。
        // 同时也不会有任何遗漏。
        TrieNode[] next = new TrieNode[26];
        String word = null;
    }

    TrieNode root = new TrieNode();

    public int[][] multiSearch(String big, String[] smalls) {
        constructTrieNode(smalls);
        Map<String, List<Integer>> hit = new HashMap<>();
        for (int i = 0; i < big.length(); i++) {
            List<String> matches = search(big, i);
            for (String match : matches) {
                if (!hit.containsKey(match)) {
                    ArrayList<Integer> positions = new ArrayList<>();
                    positions.add(i);
                    hit.put(match, positions);
                    continue;
                }
                hit.get(match).add(i);
            }
        }
        int[][] result = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            if (hit.containsKey(smalls[i])) {
                result[i] = hit.get(smalls[i]).stream().mapToInt(Integer::intValue).toArray();
            } else {
                result[i] = new int[0];
            }
        }
        return result;
    }

    /**
     * 构建字典树
     *
     * @param smalls
     */
    private void constructTrieNode(String[] smalls) {
        for (String s : smalls) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
            }
            node.word = s;
        }
    }

    private List<String> search(String big, int beginIndex) {
        List<String> result = new ArrayList<>();

        char[] chars = big.toCharArray();
        TrieNode node = root;
        for (int i = beginIndex; i < chars.length; i++) {
            //对big进行遍历时，如果能在next中一直找到对应字符，则说明有相应的序列。如果next的word不为空，则说明匹配到了small中的一个完整字符串
            //如果某一个字符不在next中，则说明small中不存在big中从beginIndex到i的序列（大于i的自然更不可能存在），则跳出循环
            if (node.next[chars[i] - 'a'] == null) {
                return result;
            }
            //如果存在，则继续往下找
            node = node.next[chars[i] - 'a'];
            //如果word不为空，说明找到了一个small中的完整的序列
            if (node.word != null) {
                result.add(node.word);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String big = "mississippi";
        String[] smalls = {"is", "ppi", "hi", "sis", "i", "ssippi"};
        int[][] ints = new MultiSearch().multiSearch(big, smalls);
        System.out.println(ints);
    }
}
