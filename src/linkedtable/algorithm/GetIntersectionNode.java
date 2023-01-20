package linkedtable.algorithm;
/*
    【160 相交链表】给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。

    【用例 1】
                   4 --> 1 -->
                               8 --> 4 --> 5
             5 --> 6 --> 1 -->
    输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
    输出：Intersected at '8'
    解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
         从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
         在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
         请注意相交节点的值不为 1，因为在链表 A 和链表 B 之中值为 1 的节点 (A 中第二个节点和 B 中第三个节点) 是不同的节点。
         换句话说，它们在内存中指向两个不同的位置，而链表 A 和链表 B 中值为 8 的节点 (A 中第三个节点，B 中第四个节点) 在内存中指向相同的位置。
    ===================================================================================================================
    【解题思路】
             1、搞懂什么是相交？ 不是两个结点的值相同，而是在遍历两个链表，指针指向某一结点时，相同了
             2、步骤：（1）长度长的链表先走 n 步，使得两个链表可以同起点遍历，过程类似 【删除倒数 第 k 个结点】中 快指针先走 n 步的过程
                         其中，n 表示 两个链表的长度差
                     （2）遍历两个链表的指针同步移动，如果在移动过程中，指针相同，那么说明找到相交点，
                         如果遍历完链表，依然没有相等，那么说明两个链表不相交
 */
public class GetIntersectionNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 初始化
        ListNode curA = headA;
        ListNode curB = headB;
        // 统计链表长度
        int lenA = 0;
        int lenB = 0;
        while(curA != null){
            lenA++;
            curA = curA.next;
        }
        while(curB != null){
            lenB++;
            curB = curB.next;
        }
        // 重置
        curA = headA;
        curB = headB;
        // 让 lenA 和 curA 表示最长链表
        if (lenB > lenA){
            int tempLen = lenA;
            lenA = lenB;
            lenB = tempLen;

            ListNode tempNode = curA;
            curA = curB;
            curB = tempNode;
        }
        // 长链表先遍历 n 步 和 短链表对齐
        int n = lenA - lenB;
        while (n > 0){
            curA = curA.next;
            n--;
        }
        // 两链表对其后，一起移动，若所指地址相同，则说明相交
        while (curA != null){
            if (curA == curB){
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
}
