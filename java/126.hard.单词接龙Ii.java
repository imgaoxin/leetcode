import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

/*
 * @lc app=leetcode.cn id=126 lang=java
 *
 * [126] 单词接龙 II
 *
 * https://leetcode-cn.com/problems/word-ladder-ii/description/
 *
 * algorithms
 * Hard (38.42%)
 * Likes:    294
 * Dislikes: 0
 * Total Accepted:    21.2K
 * Total Submissions: 55.1K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord
 * 的最短转换序列。转换需遵循如下规则：
 * 
 * 
 * 每次转换只能改变一个字母。
 * 转换后得到的单词必须是字典中的单词。
 * 
 * 
 * 说明:
 * 
 * 
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出:
 * [
 * ⁠ ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: []
 * 
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 * 
 */

// @lc code=start
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new LinkedList<>();
        if (!wordList.contains(endWord)) return res;

        // 预处理
        Map<String, List<String>> map = new HashMap<>();
        genKV(beginWord, map);
        for (String word : wordList) {
            genKV(word, map);
        }

        // bfs
        Deque<List<String>> queue = new ArrayDeque<>();
        List<String> begin = new LinkedList<>();
        begin.add(beginWord);
        queue.offerLast(begin);

        Set<String> mark = new HashSet<String>();
        mark.add(beginWord);

        boolean finalFlag = false;

        while (!queue.isEmpty()) {
            int len = queue.size();
            Set<String> subMark = new HashSet<String>();

            while (len-- > 0) {
                List<String> list = queue.pollFirst();
                String word = list.get(list.size() - 1);

                for (int i = 0; i < word.length(); i++) {
                    String key = word.substring(0, i) + "*" + word.substring(i + 1);
                    List<String> value = map.get(key);
                    for (String str : value) {
                        if (str.equals(endWord)) {
                            finalFlag = true;
                            List<String> end = new LinkedList<>(list);
                            end.add(str);
                            res.add(end);
                            continue;
                        }
                        if (!mark.contains(str)) {
                            subMark.add(str);
                            List<String> mid = new LinkedList<>(list);
                            mid.add(str);
                            queue.offerLast(mid);
                        }
                    }
                }
            }

            mark.addAll(subMark);
            if (finalFlag) break;
        }

        return res;
    }

    void genKV(String word, Map<String, List<String>> map) {
        for (int i = 0; i < word.length(); i++) {
            String key = word.substring(0, i) + "*" + word.substring(i + 1);
            List<String> value = map.getOrDefault(key, new LinkedList<String>());
            value.add(word);
            map.put(key, value);
        }
    }
}
// @lc code=end

