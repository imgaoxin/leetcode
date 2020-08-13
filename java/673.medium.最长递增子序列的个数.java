/*
 * @lc app=leetcode.cn id=673 lang=java
 *
 * [673] 最长递增子序列的个数
 *
 * https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (31.09%)
 * Likes:    157
 * Dislikes: 0
 * Total Accepted:    9.1K
 * Total Submissions: 25.7K
 * Testcase Example:  '[1,3,5,4,7]'
 *
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * 
 * 示例 1:
 * 
 * 
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 
 * 
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 * 
 */

// @lc code=start
class Solution {
    // Note: 注意结果的划分不是按照元素值，而是按照不同元素，值相等的不同元素得到的结果不算重复结果
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;

        if (len <= 1) {
            return len;
        }

        // 记录当前位置元素结尾的最长递增序列长度
        int[] maxLen = new int[len];
        // 记录对应元素结尾的最长递增序列（相同长度）的个数
        int[] counter = new int[len];
        // 第一个元素结尾的最长递增序列长度为1
        maxLen[0] = 1;
        counter[0] = 1; 

        for (int i = 1; i < len; i++) {
            // arr[0 ... i]的最长递增序列长度是
            // 所有“arr[0 ... 前面值比其小的元素位置]的最长递增序列长度 + 1”
            // 和arr[0 ... 前面值与其相等的元素位置]的最长递增序列长度
            // 中的最大值
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    if(maxLen[j] + 1 > maxLen[i]){ // 最优子结构
                        maxLen[i] = maxLen[j] + 1;
                        counter[i] = counter[j]; // 长度重新赋值，个数重新赋值为最优子结构的个数
                    } else if (maxLen[j] + 1 == maxLen[i]) {
                        counter[i] += counter[j]; // 前面元素值小且长度小于1（最优子结构）的个数累加
                    }
                } else if (nums[i] == nums[j]) {
                    if(maxLen[j] > maxLen[i]) { // 共用最优子结构
                        maxLen[i] = maxLen[j];
                        counter[i] = counter[j]; // 长度重新赋值，个数重新赋值
                    } else if (maxLen[j] == maxLen[i]) {
                        counter[i] += counter[j]; // 前面元素值相等且长度相等（重复子问题但结果不重复）的个数累加
                    }
                    // 遇到相等的结尾元素，不需要继续向前比较
                    break;
                }
            }

            // 以自己为结尾且只有自己一个元素的最长递增子序列长度是1，个数是1
            if (maxLen[i] == 0) {
                maxLen[i] = 1;
                counter[i] = 1;
            }
        }

        int length = 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if(maxLen[i] > length){
                length = maxLen[i];
                count = counter[i];
            }else if(maxLen[i] == length) {
                count += counter[i];
            }
        }

        return count;
    }
}
// @lc code=end

