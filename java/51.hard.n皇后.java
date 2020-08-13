import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N皇后
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (70.01%)
 * Likes:    435
 * Dislikes: 0
 * Total Accepted:    45.5K
 * Total Submissions: 65K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 
 * 
 * 上图为 8 皇后问题的一种解法。
 * 
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 
 * 示例:
 * 
 * 输入: 4
 * 输出: [
 * ⁠[".Q..",  // 解法 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 * 
 * ⁠["..Q.",  // 解法 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。（引用自
 * 百度百科 - 皇后 ）
 * 
 * 
 */

// @lc code=start
class Solution {
    List<List<String>> res = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        solveN(0, n, new ArrayList<String>(n));
        return res;
    }

    private void solveN(int row, int n, List<String> list) {
        if (row == n) {
            res.add(list);
        }

        for (int i = 0; i < n; i++) {
            if (valid(i, list)) {
                List<String> newList = new ArrayList<String>(list);
                newList.add(createQString(i, n));
                solveN(row + 1, n, newList);
            }
        }
    }

    private boolean valid(int index, List<String> list) {
        if (list.isEmpty()) return true;
        int offset = 1;
        for (int i = list.size() - 1; i >= 0; i--) {
            String s = list.get(i);
            if (s.charAt(index) == 'Q') {
                return false;
            }
            int left = index - offset, right = index + offset;
            if (left >= 0 && s.charAt(left) == 'Q') {
                return false;
            }
            if (right < s.length() && s.charAt(right) == 'Q') {
                return false;
            }
            offset++;
        }

        return true;
    }

    private String createQString(int qIndex, int len) {
        char[] ch = new char[len];
        Arrays.fill(ch, '.');
        ch[qIndex] = 'Q';
        return new String(ch);
    }
}
// @lc code=end

