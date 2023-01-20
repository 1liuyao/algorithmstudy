package linkedtable.test;

import linkedtable.algorithm.ReverseList;

public class ReverseListTest {
    public static void main(String[] args) {
        ReverseList.ListNode listNode1 = new ReverseList().new ListNode(1, null);
        ReverseList.ListNode listNode2 = new ReverseList().new ListNode(2, listNode1);
        ReverseList.ListNode listNode3 = new ReverseList().new ListNode(3, listNode2);
        ReverseList.ListNode listNode4 = new ReverseList().new ListNode(4, listNode3);
        ReverseList.ListNode listNode5 = new ReverseList().new ListNode(5, listNode4);
        ReverseList.ListNode listNode = new ReverseList().reverseList3(listNode5);

        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
