package linkedtable.test;

import linkedtable.algorithm.MyLinkedList;
import linkedtable.algorithm.SwapPairs;

public class SwapPairsTest {
    public static void main(String[] args) {
        SwapPairs.ListNode listNode5 = new SwapPairs().new ListNode(5, null);
        SwapPairs.ListNode listNode4 = new SwapPairs().new ListNode(4, listNode5    );
        SwapPairs.ListNode listNode3 = new SwapPairs().new ListNode(3, listNode4);
        SwapPairs.ListNode listNode2 = new SwapPairs().new ListNode(2, listNode3);
        SwapPairs.ListNode head = new SwapPairs().new ListNode(1, listNode2);

        SwapPairs swapPairs = new SwapPairs();
        swapPairs.swapPairs(head);
    }
}
