import java.util.Random;

/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (62.73%)
 * Likes:    513
 * Dislikes: 0
 * Total Accepted:    129.9K
 * Total Submissions: 207.1K
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 
 * 示例 1:
 * 
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 
 * 
 * 示例 2:
 * 
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 
 * 说明: 
 * 
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * 
 */

// @lc code=start
class Solution {
    Random random = new Random();
    public int findKthLargest(int[] nums, int k) {

        int start = 0, end = nums.length - 1;
        int index = randomPartition(nums, start, end);
        while (nums.length - index != k) {
            if (nums.length - index < k) {
                end = index - 1;
            } else {
                start = index + 1;
            }           
            index = randomPartition(nums, start, end);
        }
        return nums[index];
    }

    private int randomPartition(int[] nums, int start, int end) {
        int i = random.nextInt(end - start + 1) + start;
        int tmp = nums[end];
        nums[end] = nums[i];
        nums[i] = tmp;
        return partition(nums, start, end);
    }

    private int partition(int[] nums, int start, int end) {
        int value = nums[end];
        while (start < end) {
            while (start < end && nums[start] <= value) {
                start++;
            }
            nums[end] = nums[start];
            while(start < end && nums[end] > value){
                end--;
            }
            nums[start] = nums[end];
        }
        nums[end] = value;
        return end;
    }
}
// @lc code=end

