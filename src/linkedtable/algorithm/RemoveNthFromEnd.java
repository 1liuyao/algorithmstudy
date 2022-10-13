package linkedtable.algorithm;
/*
    【19 删除链表的倒数第 N 个结点】给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

    【用例1】
        输入：head = [1,2,3,4,5], n = 2
        输出：[1,2,3,5]
    【示例2】
        输入：head = [1], n = 1
        输出：[]
    【用例3】
        输入：head = [1,2], n = 1
        输出：[1]
    =======================================================
    【解题思路】：【快慢指针】要删除倒数第n个结点，需要提前记录
            1、快指针：遍历链表，找到正数第n个结点
            2、慢指针：指向倒数第n个结点的前驱
            因此慢指针需要移动n下才能撵上快指针，例如：n = 2
       dummyHead     head
              -1  --> 1 --> 2 --> 3 --> 4 --> 5 --> null
              fast
              slow
              ------------------------------------------
                           fast
              slow
              ------------------------------------------
                                 fast
                    slow
              ------------------------------------------
                                       fast
                          slow
              ------------------------------------------
                                            fast  fast.next == null
                                 slow
              ------------------------------------------
 */
public class RemoveNthFromEnd {
    public class ListNode {
        public int val;
        public ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 步骤1：初始化快慢指针，构造虚拟头结点
        ListNode dummyHead = new ListNode(-1, head);
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;
        // 步骤2：找到快指针移动n步的位置
        while (n > 0 && fast.next != null){
            fast = fast.next;
            n--;
        }
        // 步骤3：同时移动快慢指针，保持快慢指针相差n个元素
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        // 那么循环结束后，慢指针指向倒数第n个结点的前驱
        // 步骤4：执行删除结点操作
        slow.next = slow.next.next;

        return dummyHead.next;
    }
}
