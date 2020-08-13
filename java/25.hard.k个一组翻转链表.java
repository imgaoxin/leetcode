/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] K 个一组翻转链表
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (61.35%)
 * Likes:    643
 * Dislikes: 0
 * Total Accepted:    84.7K
 * Total Submissions: 136.6K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * 
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 
 * 
 * 
 * 示例：
 * 
 * 给你这个链表：1->2->3->4->5
 * 
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 
 * 
 * 
 * 说明：
 * 
 * 
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    // public ListNode reverseKGroup(ListNode head, int k) {
    //     if (head == null || head.next == null || k == 1) {
    //         return head;
    //     }

    //     // 虚拟节点，模拟‘上个子链表反转前的头结点’，也就是翻转后的尾节点
    //     ListNode fake = new ListNode(-1);
    //     ListNode link = fake;

    //     /**
    //      * 思路：遍历的同时反转链表，每k个节点反转后的头节点对接到link（上个子链表翻转后的尾节点）后，
    //      * 如果最后一次遍历不足k个节点，由于已经反转过，需要再反转回去
    //      */
    //     while (head != null) {
    //         ListNode pre = head;
    //         ListNode tail = head.next;
    //         ListNode tmp;
    //         // k个节点需要改变k-1次
    //         int count = k - 1;
    //         while (tail != null && count-- > 0) {
    //             tmp = tail.next;
    //             tail.next = pre;
    //             pre = tail;
    //             tail = tmp;
    //         }

    //         if (count > 0) {
    //             // 不足k个，需要反转回去
    //             int i = k - count;
    //             while (i-- > 0) {
    //                 tmp = pre;
    //                 pre = pre.next;
    //                 tmp.next = tail;
    //                 tail = tmp;
    //             }
    //             // 此时tail为不足k个元素的子链表的头结点
    //             link.next = tail;
    //             break;
    //         } else {
    //             // 循环只能通过两种方式结束，后面不足k个节点走上面的分支跳出，
    //             // 或者正好遍历k个后结束，此时需要把link的next置为null，否则死循环
    //             link.next = pre;
    //             link = head;
    //             link.next = null;
    //             head = tail;
    //         }
    //     }

    //     return fake.next;
    // }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (/* head == null || head.next == null ||  */k < 2) {
            return head;
        }
        
        /**
         * 思路：移动k个，记录头和尾，然后翻转;由一个虚拟节点模拟上一个子链表翻转后的尾节点，开始进行拼接
         */
        ListNode fake = new ListNode(-1);
        ListNode pre = fake;
        pre.next = head;

        while (head != null) {
            ListNode tail = head;
            for (int i = 1; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    // 说明剩下的不足k个，不需要翻转可以直接返回
                    return fake.next;
                }
                
            }
            // 翻转链表
            reverseList(head, tail);
            pre.next = tail;
            pre = head;
            head = pre.next;
        }

        return fake.next;
    }

    public void reverseList(ListNode head, ListNode tail) {
        ListNode pre = tail.next;
        ListNode tmp;
        while (pre != tail) {
            tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
    }
}
// @lc code=end

