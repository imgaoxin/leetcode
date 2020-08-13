/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 *
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 *
 * algorithms
 * Easy (51.46%)
 * Likes:    2096
 * Dislikes: 0
 * Total Accepted:    262.1K
 * Total Submissions: 509.3K
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 示例:
 * 
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 
 * 
 * 进阶:
 * 
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * 
 */

// @lc code=start
class Solution {
    public int maxSubArray(int[] nums) {
        return dp(nums, nums.length);
        //return divideAndConquer(nums, 0, nums.length - 1)[3];
    }

    private int[] divideAndConquer(int[] nums, int left, int right) {
        // 1记录左端最大和，2记录右端最大和，3记录连续子数组最大和, 4记录数组和 
        // 0标记连续子数组最大和是数组和（v=1）或不是数组和（v=0）
        // 数组最大和是数组和时，左端最大和==右端最大和==连续子数组最大和==数组和 
        int[] struct = new int[5];
        
        if(left == right) {
            struct[1] = nums[left];
            struct[2] = nums[left];
            struct[3] = nums[left];
            struct[4] = nums[left];
            struct[0] = 1;
            return struct;
        }

        int mid = left + ((right - left) >> 2);
        int[] a = divideAndConquer(nums, left, mid);
        int[] b = divideAndConquer(nums, mid + 1, right);

        struct[1] = Math.max(a[1], a[4] + b[1]);

        struct[2] = Math.max(b[2], b[4] +a[2]);

        struct[4] = a[4] + b[4];

        struct[3] = Math.max(a[3], b[3]);
        struct[3] = Math.max(struct[3], struct[1]);
        struct[3] = Math.max(struct[3], struct[2]);
        struct[3] = Math.max(struct[3], a[2] + b[1]);

        if (struct[3] <= struct[4]) {
            struct[3] = struct[4];
            struct[0] = 1;
        }

        return struct;
    }

    private int dp(int[] nums, int len) {
        int max = nums[0];
        int pre = 0;
        for (int i : nums) {
            pre = Math.max(pre + i, i);
            max = Math.max(max, pre);
        }
        return max;
    }
}
// @lc code=end

