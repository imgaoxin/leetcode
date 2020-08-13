import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javafx.util.Pair;

/*
 * @lc app=leetcode.cn id=127 lang=java
 *
 * [127] 单词接龙
 *
 * https://leetcode-cn.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (42.49%)
 * Likes:    378
 * Dislikes: 0
 * Total Accepted:    49.7K
 * Total Submissions: 116K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
 * 的最短转换序列的长度。转换需遵循如下规则：
 * 
 * 
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 
 * 
 * 说明:
 * 
 * 
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出: 5
 * 
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * ⁠    返回它的长度 5。
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: 0
 * 
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * 
 */

// @lc code=start
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        
        // 构图  预处理，把能映射的关系存储起来
        Map<String, List<String>> map = new HashMap<>();
        int len = beginWord.length();
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                String common = word.substring(0, i) + "*" + word.substring(i + 1, len);
                List<String> list = map.getOrDefault(common, new LinkedList<>());
                list.add(word);
                map.put(common, list);
            }
            
        }

        // BFS 队列数据为单词和对应层次 记录已经转换过的词，避免重复路径的耗时
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<String,Integer>(beginWord, 1));
        Set<String> set = new HashSet<>();
        set.add(beginWord);

        Pair<String, Integer> curr;
        while ((curr = queue.poll()) != null) {
            String word = curr.getKey();
            int level = curr.getValue();
            for (int i = 0; i < len; i++) {
                String common = word.substring(0, i) + "*" + word.substring(i + 1, len);
                List<String> list = map.getOrDefault(common, new LinkedList<>());
                for (String w : list) {
                    if (w.equals(endWord)) {
                        return level + 1;
                    }
                    if (!set.contains(w)) {
                        set.add(w);
                        queue.offer(new Pair<String,Integer>(w, level + 1));
                    }
                }
            }
        }

        return 0;
    }

}
// @lc code=end

