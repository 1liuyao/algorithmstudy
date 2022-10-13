package linkedtable.algorithm;
/*
    【707 设计链表】：设计链表的实现。您可以选择使用单链表或双链表。
                    单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。
                    如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
    在链表类中实现这些功能：
        get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
        addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
        addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
        addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。
                               如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
        deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
    【用例】：
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        linkedList.get(1);            //返回3
 */
public class MyLinkedList {
    // 定义链表属性
    public ListNode dummyHead;
    public int listLen ;

    // 定义单链表结点类（内部类）
    public class ListNode{
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public MyLinkedList() {
        // 初始化虚拟头节点
        dummyHead = new ListNode(-1, null);
        listLen=0;
    }

    // 获取链表中第 index 个节点的值。如果索引无效，则返回-1
    // 注意：index从0开始
    public int get(int index) {
        // 步骤1：判断索引是否有效
        if (index < 0 || index > listLen-1) {
            return -1;
        }
        // 步骤2：查找第 index 个节点的值
        // 注意：为了后续可以从头遍历链表，所以头结点指针不可以移动，使用临时指针遍历链表
        ListNode cur = dummyHead;
        while (index >= 0){
            cur =cur.next;
            index--;
        }
        return cur.val;
    }

    // 【头插法】在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
    public void addAtHead(int val) {
        // 步骤1：构造新结点
        ListNode newNode = new ListNode(val, null);

        // 步骤2：头插法指针修改
        newNode.next = dummyHead.next;
        dummyHead.next = newNode;

        // 步骤3：更新链表长度
        listLen++;
    }

    // 【尾插法】将值为 val 的节点追加到链表的最后一个元素。
    // 更好地解决方案：是增加尾指针
    public void addAtTail(int val) {
        // 步骤1：构造新结点
        ListNode newNode = new ListNode(val, null);

        // 步骤2：找到链表的最后一个元素
        ListNode cur = dummyHead;
        int len = listLen;
        while (len > 0){
            cur =cur.next;
            len--;
        }
        // 步骤3：在最后一个结点尾部插入元素
        cur.next = newNode;

        // 步骤4：更新链表长度
        listLen++;
    }

    // 在链表中的第 index 个节点之前添加值为 val  的节点。
    // 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
    // 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
    public void addAtIndex(int index, int val) {
        // 步骤1：构造新结点
        ListNode newNode = new ListNode(val, null);

        // 步骤2：判断index合理性
        if (index > listLen)
            return;

        if (index < 0) {
            addAtHead(val);
            return;
        }

        if(index == listLen) {
            addAtTail(val);
            return;
        }
        // 步骤3：找到第index结点，并记录前驱
        ListNode cur = dummyHead;
        while (index > 0){
            cur =cur.next;
            index--;
        }
        newNode.next = cur.next;
        cur.next = newNode;

        // 步骤4：更新链表长度
        listLen++;
    }

    // 如果索引 index 有效，则删除链表中的第 index 个节点
    public void deleteAtIndex(int index) {
        // 步骤1：判断索引是否有效
        if (index < 0 || index > listLen-1) {
            return ;
        }

        // 步骤2：找到第index个结点，并记录前驱
        ListNode cur = dummyHead;
        while (index > 0){
            cur =cur.next;
            index--;
        }
        cur.next = cur.next.next;

        // 步骤3：缩短链表长度
        listLen--;
    }

    // 遍历链表
    public void travel(){
        for (int m = 0; m < listLen; m++) {
            System.out.print(get(m)+" ");
        }
        System.out.println();
    }

}
