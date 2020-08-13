/*
 * @lc app=leetcode.cn id=42 lang=java
 *
 * [42] 接雨水
 *
 * https://leetcode-cn.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (46.65%)
 * Likes:    657
 * Dislikes: 0
 * Total Accepted:    36.3K
 * Total Submissions: 77.4K
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 
 * 
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢
 * Marcos 贡献此图。
 * 
 * 示例:
 * 
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * 
 */

// @lc code=start
class Solution {
    // public int trap(int[] height) {
    //     /**
    //      * 思路1：使用首尾指针向中间收敛，每次移动高度小的一侧。
    //      * 当前首尾距离(包括柱子宽度)乘以较小高度（与上一次统计高度的差）是当前层的面积，
    //      * 然后移动低的一侧，高的一侧不动，然后再统计下一层的面积，累加。
    //      * 最后减去不高于最后统计高度的柱子面积（柱子宽度1）就是可以接雨水的面积。
    //      */
    //     int area = 0;
    //     int start = 0;
    //     int end = height.length - 1;
    //     int h = 0;
    //     while (start < end) {
    //         int left = height[start];
    //         int right = height[end];
    //         int down = end - start + 1;
    //         int lit;
    //         if (left <= right) {
    //             lit = left;
    //             start++;
    //         } else {
    //             lit = right;
    //             end--;
    //         }

    //         if (lit > h) {
    //             area += (lit - h) * down;
    //             h = lit;
    //         }
    //     }

    //     int lose = 0;
    //     for (int i : height) {
    //         lose += (i < h ? i : h); 
    //     }
    //     return area - lose;
    // }

    public int trap(int[] height) {
        /**
         * 思路2：使用首尾指针向中间收敛，每次移动高度小的一侧。
         * 当前首尾距离（不包括柱子宽度）乘以较小高度（与上一次统计高度的差）是当前层的面积，
         * 然后移动低的一侧，高的一侧不动，然后再统计下一层的面积，累加。
         * 移动某侧时，发现下一个柱子小于等于当前高度需要把这个柱子面积减去，如果大，就减去当前高度的面积。
         */
        int area = 0;
        int start = 0;
        int end = height.length - 1;
        int h = 0;
        while (start < end) {
            int down = end - start - 1;
            int lit;
            if (height[start] <= height[end]) {
                lit = height[start];
                start++;
            } else {
                lit = height[end];
                end--;
            }

            if (lit > h) {
                area -= h;
                area += (lit - h) * down;
                h = lit;
            } else {
                area -= lit;
            }
        }

        return area;
    }
}
// @lc code=end

