package linkedtable.algorithm;

/*
    【24 两两交换链表中的节点】给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
                          你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
    【用例1】
            输入：head = [1,2,3,4]
            输出：[2,1,4,3]
    【用例2】
            输入：head = []
            输出：[]
    【用例3】
            输入：head = [1]
            输出：[1]
     ==========================================================================
     【解题思路】交换两个结点本质上是指针指向的修改，当需要交换两个相邻结点时，我们需要找到这两个结点的前驱进行指针修改
            1、采用虚拟头节点
            dummyHead  head
               -1 -->  1  -->  2  -->  3  -->  4  -->  5  -->  null
                       |--交换--|       |--交换--|
               cur             cur            cur
            2、交换后，指针指向变换
            dummyHead  head
               -1 -->  1  -->  2  -->  3  -->  4  -->  5  -->  null
                       |--交换--|       |--交换--|

               cur     |--------------->
               -1      1 <-----2       3
                |------------->
                      suc1            suc2
            3、引入cur的后继指针，和后继的后继指针
            因为一旦-1指向2，则cur指向后继的next指针被修改，则将无法再访问到cur原始后继1
            一旦2指向1，则2的原始后继3将无法再被访问到，所以需要进行指针修改操作前，需要暂存3
 */
public class SwapPairs {
    public class ListNode {
        public int val;
        public ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        // 步骤1：初始化虚拟头节点和遍历指针
        ListNode dummyHead = new ListNode(-1, head);
        ListNode cur = dummyHead;
        ListNode suc1;
        ListNode suc2;
        // 步骤2：当cur后有两个结点时才能交换，不足两个结点就无需交换了
        while (cur.next != null && cur.next.next != null) {
            // 步骤3：为了后续可以访问到后继结点，暂存后继结点
            suc1 = cur.next;
            suc2 = cur.next.next.next;

            // 步骤4：交换结点，进行修改指针操作
            cur.next = cur.next.next;
            cur.next.next = suc1;
            suc1.next = suc2;

            System.out.println(cur.val + " " + cur.next.val + " " + cur.next.next.val);
            // 步骤5：移动cur，进行下一组交换
            cur = cur.next.next;
        }
        return dummyHead.next;
    }

}
