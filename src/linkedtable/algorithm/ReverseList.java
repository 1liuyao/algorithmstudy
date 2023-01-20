package linkedtable.algorithm;
/*
    【206 反转链表】给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

    【用例1】
        输入：head = [1,2,3,4,5]
        输出：[5,4,3,2,1]

    【用例2】
        输入：head = [1,2]
        输出：[2,1]

    【用例3】
        输入：head = []
        输出：[]
    ==============================================================
    【解题思路】

    【方案一】双指针法（前驱后继指针）：反转链表就是将链表中每一个结点的next指针从指向其【后继】改为指向其【前驱】
        pre    cur    temp ......          pre    cur
                1  ->  2  ->  3  ->  4  ->  5  -> NULL
        NULL <- 1      2
             head
        1、cur：临时指针，遍历整个链表
        2、pre：指向cur的前驱，后续将cur的next指向其前驱，实现链表翻转
        3、temp：记录cur的后继
        【?】思考一个问题，当cur的next指向pre时，如果继续遍历链表，发现无法遍历了，所以需要先保存下cur的后继

    【方案二】递归：根据双指针法修改
        1、递归函数形参的确定 == 循环过程的增量 不断发生变化或者移动的量（pre，cur）
        2、主函数递归的初始值 == pre 和 cur的初始值设置
        3、递归的终止条件 == 循环的终止条件
        4、递归的更新条件 == 循环中pre 和 cur变化规律

    【迭代和递归】其实是两种方案之间的转化
    迭代：每一次对过程的重复称作一次迭代，而每一次迭代【得到的结果】会作为【下一次迭代的初始值】。（即迭代过程中就会得到结果）
    递归：解决问题的某一步骤中，又产生新的子问题，并且子问题的解决方式同原来的问题的解决步骤相同。
         当新的子问题得到解决时，再回过头来继续解决原来的问题，则会发现问题迎刃而解。（即递归过程中不会得到结果，只有回溯的时候才会得到结果）

 */
public class ReverseList {
    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 方案1：双指针法
    public ListNode reverseList(ListNode head) {
        // 步骤1：初始化指针
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;
        // 步骤2：确定双指针终止条件，当cur指向空位置时，移动结束；
        // 因为当cur指向链表尾结点时，仍然需要修改next指向前驱
        while (cur != null){
            // 步骤3：记录cur的后继，便于cur继续遍历链表
            temp = cur.next;

            // 步骤4：使cur指向前驱实现链表反转
            cur.next = pre;

            // 步骤5：更新双指针
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    // 方案二：递归
    public ListNode reverseList1(ListNode head) {
        // 确定递归初始值
        ListNode pre = null;
        ListNode cur = head;
        return reverseList2(pre, cur);
    }
    // 步骤1：确定递归形参
    public ListNode reverseList2(ListNode pre, ListNode cur){
        // 步骤2：确定递归终止条件
        if (cur == null) {
            // 当cur为空时，pre指向的是反转后的头节点
            return pre;
        }
        // 步骤3：确定下一次递归的形参值
        ListNode temp = cur.next;
        cur.next = pre;
        return reverseList2(cur,temp);
    }
    public ListNode reverseList3(ListNode head) {
        if(head == null)
            return null;

        ListNode pre = null;
        ListNode cur = head;
        // 若在这个位置初始化，cur进行 cur = temp;
        // 发生变化后，由于 cur 指向最后一个元素，temp 已经为空，cur = temp; 后，cur为null空指针
        // 所以temp = cur.next;报空指针异常，需要引入非空判断
        ListNode temp = cur.next;

        while(cur != null){
            cur.next = pre;
            pre = cur;
            cur = temp;
            if (cur != null)
                temp = cur.next;
        }

        return pre;
    }
}
