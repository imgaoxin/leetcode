import java.util.Stack;

/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 *
 * https://leetcode-cn.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (41.81%)
 * Likes:    1673
 * Dislikes: 0
 * Total Accepted:    321.1K
 * Total Submissions: 765.7K
 * Testcase Example:  '"()"'
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 
 * 
 * 注意空字符串可被认为是有效字符串。
 * 
 * 示例 1:
 * 
 * 输入: "()"
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入: "()[]{}"
 * 输出: true
 * 
 * 
 * 示例 3:
 * 
 * 输入: "(]"
 * 输出: false
 * 
 * 
 * 示例 4:
 * 
 * 输入: "([)]"
 * 输出: false
 * 
 * 
 * 示例 5:
 * 
 * 输入: "{[]}"
 * 输出: true
 * 
 */

// @lc code=start
class Solution {
    public boolean isValid(String s) {
        /* if (s == null || s.length() < 1) {
            return true;
        } */

        // 奇数长度无法完全匹配
        /* if (s.length() % 2 == 1) {
            return false;
        } */

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            // 栈里面存储左括号元素，如果超过总字符长度的一半无法完全匹配
            /* if (stack.size() > s.length() / 2) {
                return false;
            } */

            Character c = s.charAt(i);

            if (c.equals('(') || c.equals('[') || c.equals('{')) {
                stack.push(c);
                continue;
            }

            if (stack.empty()) {
                return false;
            }

            Character t = stack.pop();

            if (t.equals('(') && c.equals(')') 
                || t.equals('[') && c.equals(']') 
                || t.equals('{') && c.equals('}')) {
                 continue;   
            }

            return false;
        }

        return stack.empty();
    }
}
// @lc code=end

