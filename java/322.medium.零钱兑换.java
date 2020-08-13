/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (35.95%)
 * Likes:    648
 * Dislikes: 0
 * Total Accepted:    93.9K
 * Total Submissions: 235.2K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * 给定不同面额的硬币 coins 和一个总金额
 * amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3 
 * 解释: 11 = 5 + 5 + 1
 * 
 * 示例 2:
 * 
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 
 * 
 * 
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * 
 */

// @lc code=start
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        /* if (amount < 0) {
            return -1;
        } */

        /* int[] v = new int[amount + 1];
        return minCount(coins, amount, v); */

        //return minCount2(coins, amount);

        return minCount3(coins, amount);
    }

    private int minCount3(int[] coins, int amount) {
        /* for (int coin : coins) {
            if (coin == amount) {
                return 1;
            }
        } */

        int max = amount + 1;
        int[] v = new int[max];
        Arrays.fill(v, max);
        v[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    v[i] = v[i - coin] + 1 < v[i] ? v[i - coin] + 1 : v[i];
                }
            }
        }

        return v[amount] > amount ? -1 : v[amount];
    }

    private int minCount2(int[] coins, int amount) {
        int[] v = new int[amount + 1];

        for (int coin : coins) {
            if (coin == amount) {
                return 1;
            } else if (coin < amount) {
                v[coin] = 1;
            }
        }

        int nextV;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                nextV = coin + i; 
                // coin = Integer.MaxValue ?
                // nextV > 0
                if (v[i] > 0 && nextV > 0 && nextV <= amount) {
                    if (v[nextV] != 0) {
                        v[nextV] = v[nextV] <= v[i] + 1 ? v[nextV] : v[i] + 1;
                    } else {
                        v[nextV] = v[i] + 1;
                    }
                }
            }
        }

        return v[amount] == 0 ? -1 : v[amount];
    }

    // 递归 + 备忘录，
    private int minCount(int[] coins, int amount, int[] v) {
        if (v[amount] != 0) {
            return v[amount];
        }

        int max = amount + 1;
        int min = max;

        int tmp;
        for (int coin : coins) {
            if (amount == coin) {
                return 1;
            } else if (amount < coin) {
                continue;
            }

            tmp = minCount(coins, amount - coin, v);

            if (tmp == -1) {
                continue;
            }

            min = min <= tmp + 1 ? min : tmp + 1;
        }

        min = min == max ? -1 : min;

        v[amount] = min;

        return min;
    }
}
// @lc code=end
