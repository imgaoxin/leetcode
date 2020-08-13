import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=698 lang=java
 *
 * [698] 划分为k个相等的子集
 *
 * https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/description/
 *
 * algorithms
 * Medium (41.93%)
 * Likes:    177
 * Dislikes: 0
 * Total Accepted:    11.6K
 * Total Submissions: 27.6K
 * Testcase Example:  '[4,3,2,3,5,2,1]\n4'
 *
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 
 * 示例 1：
 * 
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
     
        return func1(nums, k);
    }

    private boolean func1(int[] nums, int k) {
        // 求数组和
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        // 数组和可以被k整除，才能划分
        if (sum % k != 0) {
            return false;
        }
        int avg = sum / k;

        // 从小到大排序，从较大元素开始放置，更快
        Arrays.sort(nums);
        
        // 如果数组最大元素大于avg，则不能划分
        int endIndex = nums.length - 1;
        if (nums[endIndex] > avg) {
            return false;
        }

        // 处理可以直接划分的情况
        while (endIndex >= 0 && nums[endIndex] == avg) {
            endIndex--;
            k--;
        }
        
        return search(nums, endIndex, new int[k], avg);
    }

    private boolean search(int[] nums, int endIndex, int[] group, int avg) {
        // 所有元素成功放置
        if (endIndex < 0) {
            return true;
        }

        for (int i = 0; i < group.length; i++) {
            if (group[i] + nums[endIndex] <= avg) {
                group[i] += nums[endIndex];
                if (search(nums, endIndex - 1, group, avg)) {
                    return true;
                }
                group[i] -= nums[endIndex];
            }
            // 某组初始为0的情况都不能放置当前元素，那么所有组都不可能成功放置
            if (group[i] == 0) {
                break;
            }
        }

        return false;
    }
}
// @lc code=end

