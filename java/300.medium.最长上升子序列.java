/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长上升子序列
 *
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (43.24%)
 * Likes:    768
 * Dislikes: 0
 * Total Accepted:    107.8K
 * Total Submissions: 241.8K
 * Testcase Example:  '[10,9,2,5,3,7,101,18]'
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 
 * 示例:
 * 
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4 
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 
 * 说明:
 * 
 * 
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n^2) 。
 * 
 * 
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * 
 */

// @lc code=start
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int[] minEndValue = new int[nums.length + 1];
        Arrays.fill(minEndValue, Integer.MAX_VALUE);

        // 记录上升子序列最大长度
        int maxLen = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < minEndValue.length; j++) {
                if(j > maxLen) {
                    maxLen = j;
                }
                
                if (nums[i] <= minEndValue[j]) {
                    minEndValue[j] = nums[i];
                    break; 
                } 
            }
        }

        return maxLen;
    }
}
// @lc code=end

