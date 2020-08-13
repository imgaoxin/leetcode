import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 *
 * https://leetcode-cn.com/problems/3sum/description/
 *
 * algorithms
 * Medium (24.17%)
 * Likes:    1504
 * Dislikes: 0
 * Total Accepted:    115.3K
 * Total Submissions: 473K
 * Testcase Example:  '[-1,0,1,2,-1,-4]'
 *
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
 * ？找出所有满足条件且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组。
 * 
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 
 * 满足要求的三元组集合为：
 * [
 * ⁠ [-1, 0, 1],
 * ⁠ [-1, -1, 2]
 * ]
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }

            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];
            int right = nums.length - 1;

            for (int left = i + 1; left < right; left++) {
                if (left != i + 1 && nums[left] == nums[left - 1]) {
                    continue;
                }
                
                while (left < right && nums[left] + nums[right] > target) {
                    right--;
                }
    
                if (left < right && nums[left] + nums[right] == target) {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                }
            }
        }

        return result;
    }
}
// @lc code=end
