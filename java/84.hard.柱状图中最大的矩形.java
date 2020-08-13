import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=84 lang=java
 *
 * [84] 柱状图中最大的矩形
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (40.90%)
 * Likes:    817
 * Dislikes: 0
 * Total Accepted:    74.5K
 * Total Submissions: 180.8K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 
 * 
 * 
 * 
 * 
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 
 * 
 * 
 * 
 * 
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * 
 */

// @lc code=start
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;

        // 单调栈
        Deque<Integer> stack = new ArrayDeque<>(len);

        int[] leftBoundIndex = new int[len];
        int[] rightBoundIndex = new int[len];

        for (int i = 0; i < len; i++) {
            int h = heights[i];
            Integer top;
            while ((top = stack.peekLast()) != null && heights[top] >= h) {
                // top位置被弹出时说明遇到了高度<=它的位置（柱子），此时可以大概确定右边界。
                // 无法求出准确的右边界（有等于的情况），但对最终的答案没有任何影响。
                // 这是因为在答案对应的矩形中，如果有若干个柱子的高度都等于矩形的高度，那么最右侧的那根柱子是可以求出正确的右边界的
                rightBoundIndex[top] = i;
                stack.pollLast();
            }
            leftBoundIndex[i] = top == null ? -1 : top;
            stack.offerLast(i);
        }

        // 在遍历结束后，栈中仍然有一些位置，这些位置对应的右边界就是位置为len的「哨兵」。
        while (!stack.isEmpty()) {
            rightBoundIndex[stack.pollLast()] = len;
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            int width = rightBoundIndex[i] - leftBoundIndex[i] - 1;
            max = Math.max(max, heights[i] * width);
        }

        return max;
    }
}
// @lc code=end

