/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 *
 * https://leetcode-cn.com/problems/move-zeroes/description/
 *
 * algorithms
 * Easy (57.16%)
 * Likes:    429
 * Dislikes: 0
 * Total Accepted:    80.3K
 * Total Submissions: 139.6K
 * Testcase Example:  '[0,1,0,3,12]'
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 
 * 示例:
 * 
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 
 * 说明:
 * 
 * 
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * 
 * 
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        /* // 标记0的个数
        int zeroCounts = 0;
        // 通过替换数字和0的位置（第一个0的位置），扫描过的位置出现的0被排列到一起
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCounts++;
            } else if (zeroCounts > 0) {
                // 0是连续的，替换时，向前移动zeroCounts个位置即可
                nums[i - zeroCounts] = nums[i];
                nums[i] = 0;
            }
        } */

        /* int zeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[zeroIndex] = nums[i];
                zeroIndex++;
            }
        }
        for (int i = zeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        } */

        int zeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[zeroIndex] = nums[i] +  nums[zeroIndex] - (nums[i] = nums[zeroIndex]);
                zeroIndex++;
            }
        }
    }
}
// @lc code=end
