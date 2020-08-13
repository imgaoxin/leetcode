/*
 * @lc app=leetcode.cn id=61 lang=java
 *
 * [61] 旋转链表
 *
 * https://leetcode-cn.com/problems/rotate-list/description/
 *
 * algorithms
 * Medium (40.39%)
 * Likes:    276
 * Dislikes: 0
 * Total Accepted:    67.2K
 * Total Submissions: 166.3K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 
 * 示例 1:
 * 
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 
 * 
 * 示例 2:
 * 
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode tail = head;
        while (tail != null && tail.next != null) {
            len++;
            tail = tail.next;
        }
        len++;
        
        // Note: 想象成一个环（循环链表），移动就是旋转，就是头节点的变动
        // 需要从尾部移动的元素个数，若k % len == 0，移动后仍是原链表
        int move;
        if(len > 1 && (move = k % len) != 0) {
            // 找到断链的位置
            int step = len - move;

            // 变成循环链表
            tail.next = head;

            while (step > 0) {
                tail = head;
                head = head.next;
                step--;
            }
            tail.next = null;
        }

        return head;
    }
}
// @lc code=end
