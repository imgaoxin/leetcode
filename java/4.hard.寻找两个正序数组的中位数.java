/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个正序数组的中位数
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (38.31%)
 * Likes:    2796
 * Dislikes: 0
 * Total Accepted:    212.9K
 * Total Submissions: 556K
 * Testcase Example:  '[1,3]\n[2]'
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 
 * 
 * 
 * 示例 1:
 * 
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * 则中位数是 2.0
 * 
 * 
 * 示例 2:
 * 
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * 则中位数是 (2 + 3)/2 = 2.5
 * 
 * 
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length - 1, l2 = nums2.length - 1;
        int len = l1 + l2 + 2;
        // 将找中位数问题转化为找第k小的数
        // 偶数长度需要获取中间两数的均值，奇数长度需要获取中间数
        int k = len / 2;
        boolean needNext = true;
        if (len % 2 == 1) {
            k += 1;
            needNext = false;
        }

        int moveIndex, index1, index2;
        int start1 = 0, start2 = 0;
        double mid = 0;
        // k减到1（可以通过比较获得结果）或某个数组元素减为为空，循环结束
        while (start1 <= l1 && start2 <= l2) {
            if (k == 1) {
                k--;
                mid = nums1[start1] <= nums2[start2] ? nums1[start1++] : nums2[start2++];
                break;
            }

            // 需要移动的索引或尾索引中小的，避免越界
            moveIndex = k / 2 - 1;
            index1 = Math.min(l1, start1 + moveIndex);
            index2 = Math.min(l2, start2 + moveIndex);

            // 每次减少k/2个较小元素或某个数组所有元素中小的，避免越界
            if (nums1[index1] <= nums2[index2]) {
                k -= index1 - start1 + 1;
                start1 = index1 + 1;
            } else {
                k -= index2 - start2 + 1;
                start2 = index2 + 1;
            }
        }
        
        if (k == 0) {
            // 情况1 通过k==1跳出（已经获取一个中位数）且不需要在获取下个元素，直接返回
            if (!needNext) {
                return mid;
            }
             // 情况2 还需要再获取下一个小值来做平均
            if (start1 <= l1 && start2 <= l2) {
                mid += nums1[start1] <= nums2[start2] ? nums1[start1] : nums2[start2];
            } else if (start1 > l1) {
                mid += nums2[start2];
            } else {
                mid += nums1[start1];
            }
            mid /= 2.0;
        } else {
            // 情况3、4 因某一数组元素减为空而跳出循环
            if (start1 > l1) {
                mid = needNext ? (nums2[start2 + k - 1] + nums2[start2 + k]) / 2.0 : nums2[start2 + k - 1];
            } else {
                mid = needNext ? (nums1[start1 + k - 1] + nums1[start1 + k]) / 2.0 : nums1[start1 + k - 1];
            }
        }
        
        return mid;
    }
}
// @lc code=end
