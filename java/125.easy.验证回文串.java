/*
 * @lc app=leetcode.cn id=125 lang=java
 *
 * [125] 验证回文串
 *
 * https://leetcode-cn.com/problems/valid-palindrome/description/
 *
 * algorithms
 * Easy (41.20%)
 * Likes:    122
 * Dislikes: 0
 * Total Accepted:    60.4K
 * Total Submissions: 145.9K
 * Testcase Example:  '"A man, a plan, a canal: Panama"'
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 
 * 示例 1:
 * 
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入: "race a car"
 * 输出: false
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;

        int left = 0, right = s.length() - 1;
        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if (!valid(l)){
                left++;
                continue;
            }else if(!valid(r)){
                right--;
                continue;
            }

            if (l == r || (l > 57 && r > 57 && Math.abs(l-r) == 32)) {
                left++;
                right--;
                continue;
            }
            return false;
        }
        return true;
    }

    // 0-9 : 48-57     
    // A-Z : 65-90     
    // a-z : 97-122    
    private boolean valid(char c) {
        // Character.isLetterOrDigit(c)
        if (c >= 48 && c<= 57 || c >= 65 && c <= 90 || c >= 97 && c <= 122) {
            return true;
        }
        return false;
    }
}
// @lc code=end
