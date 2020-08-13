/*
 * @lc app=leetcode.cn id=357 lang=java
 *
 * [357] 计算各个位数不同的数字个数
 *
 * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/description/
 *
 * algorithms
 * Medium (51.15%)
 * Likes:    68
 * Dislikes: 0
 * Total Accepted:    10.3K
 * Total Submissions: 20.1K
 * Testcase Example:  '2'
 *
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10^n 。
 * 
 * 示例:
 * 
 * 输入: 2
 * 输出: 91 
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        // 0-9共10个数字，首位不能为0共9种选择，其后依次少1种选择
        int base = 9;
        int sum = 10;
        for (int i = 2; i <= n; i++) {
            base = base * (10 - i + 1);
            sum += base;
        }
        return n > 0 ? sum : 1;
    }
}
// @lc code=end

