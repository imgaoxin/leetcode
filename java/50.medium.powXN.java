/*
 * @lc app=leetcode.cn id=50 lang=java
 *
 * [50] Pow(x, n)
 *
 * https://leetcode-cn.com/problems/powx-n/description/
 *
 * algorithms
 * Medium (35.95%)
 * Likes:    438
 * Dislikes: 0
 * Total Accepted:    106.1K
 * Total Submissions: 294.5K
 * Testcase Example:  '2.00000\n10'
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 
 * 示例 1:
 * 
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 
 * 
 * 示例 2:
 * 
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 
 * 
 * 示例 3:
 * 
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2^-2 = 1/2^2 = 1/4 = 0.25
 * 
 * 说明:
 * 
 * 
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 * 
 * 
 */

// @lc code=start
class Solution {
    public double corePow(double x, long n) { // n >= 0
        if (n == 0 || x == 1) {
            return 1.0;
        }

        /* 
        if (x == 0) {
            return 0;
        }

        if (x == -1) {
            return n % 2 == 0 ? 1 : -1;
        } */

        /* 
            x * x^4 * x^8 * x^64 恰好等于 x^77。
            它们都是 2 的幂次，这些指数 1，4，8 和 64，恰好就对应了77的二进制表示 (1001101) 中的每个 1。
        */

        double res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res *= x;
            }
            x *= x;
            n /= 2;
        }

        return res;
    }

    public double myPow(double x, int n) {
        long N = n;
        return n >= 0 ? corePow(x, N) : 1.0 / corePow(x, -N);
    }

}
// @lc code=end

