/*
 * @lc app=leetcode.cn id=21 lang=java
 *
 * [21] 合并两个有序链表
 *
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
 *
 * algorithms
 * Easy (57.70%)
 * Likes:    691
 * Dislikes: 0
 * Total Accepted:    134.8K
 * Total Submissions: 231.9K
 * Testcase Example:  '[1,2,4]\n[1,3,4]'
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 
 * 示例：
 * 
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 存在任一空链表，则返回非空链表
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 定义合并链表的头结点，通过.next返回最终合并排序的结果
        ListNode mergeNode = new ListNode(0);
        ListNode tempNode = mergeNode;

        // 遍历比较两个有序链表的节点大小，小值节点将被插入合并链表的尾部
        do {
            if (l1.val <= l2.val) {
                tempNode.next = l1;
                l1 = l1.next;
            } else {
                tempNode.next = l2;
                l2 = l2.next;
            }
            tempNode = tempNode.next;
        } while (l1 != null && l2 != null);

        // 此时至少一个链表为空，将非空链表剩余元素插入合并链表尾部即可
        tempNode.next = l1 == null ? l2 : l1;
        return mergeNode.next;
    }
}
// @lc code=end