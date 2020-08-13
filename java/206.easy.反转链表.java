/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 *
 * https://leetcode-cn.com/problems/reverse-linked-list/description/
 *
 * algorithms
 * Easy (69.43%)
 * Likes:    1026
 * Dislikes: 0
 * Total Accepted:    260.9K
 * Total Submissions: 375.8K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 反转一个单链表。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // return loop(head);
        return recursive(head, null);
    }

    private ListNode recursive(ListNode head, ListNode next) {
        if (head == null) {
            return next;
        }

        ListNode tmp = head;
        head = head.next;
        tmp.next = next;

        return recursive(head, tmp);
    }

    private ListNode loop(ListNode head) {
        ListNode tmp = null;
        ListNode rev = null;

        while (head != null) {
            tmp = head;
            head = head.next;
            tmp.next = rev;
            rev = tmp;
        }

        return head == null ? tmp : head;
    }
}
// @lc code=end
