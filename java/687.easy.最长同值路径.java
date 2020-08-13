/*
 * @lc app=leetcode.cn id=687 lang=java
 *
 * [687] 最长同值路径
 *
 * https://leetcode-cn.com/problems/longest-univalue-path/description/
 *
 * algorithms
 * Easy (40.60%)
 * Likes:    284
 * Dislikes: 0
 * Total Accepted:    18.2K
 * Total Submissions: 44.8K
 * Testcase Example:  '[5,4,5,1,1,5]'
 *
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * 
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * 
 * 示例 1:
 * 
 * 输入:
 * 
 * 
 * ⁠             5
 * ⁠            / \
 * ⁠           4   5
 * ⁠          / \   \
 * ⁠         1   1   5
 * 
 * 
 * 输出:
 * 
 * 
 * 2
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * 
 * 
 * ⁠             1
 * ⁠            / \
 * ⁠           4   5
 * ⁠          / \   \
 * ⁠         4   4   5
 * 
 * 
 * 输出:
 * 
 * 
 * 2
 * 
 * 
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int max;
    public int longestUnivaluePath(TreeNode root) {
        // Note: 不一定是从顶点开始，也可能是左子节点->顶点->右子节点
        
        if (root == null) {
            return 0;
        }

        dfs(root);

        return max;
    }

    private int dfs (TreeNode node){
        int leftLength = 0, rightLength = 0;
        
        if (node.left != null){
            int l = dfs(node.left);
            if (node.val == node.left.val) {
                leftLength++;
                leftLength += l;
            }
        }
        
        if (node.right != null) {
            int r = dfs(node.right);
            if (node.val == node.right.val) {
                rightLength++;
                rightLength += r;
            }
        }

        max = Math.max(max, leftLength + rightLength);

        return Math.max(leftLength, rightLength);
    }
}
// @lc code=end

