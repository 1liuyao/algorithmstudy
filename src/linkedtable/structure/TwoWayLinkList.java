package linkedtable.structure;

import java.util.Iterator;
/*
    【双向链表数据结构及基本操作实现】

    【概念】：双向链表也叫双向表，是链表的一种，它由多个结点组成，每个结点都由一个数据域和两个指针域组成，数据域用
            来存储数据，其中一个指针域用来指向其后继结点，另一个指针域用来指向前驱结点。链表的头结点的数据域不存
            储数据，指向前驱结点的指针域值为null，指向后继结点的指针域指向第一个真正存储数据的结点。

    【类名】：TowWayLinkList

    【成员变量】：
            1.private Node first:记录首结点
            2.private Node last:记录尾结点
            2.private int N:记录链表的长度

    【成员内部类】：也就是结点类，既除头节点外真正存储数据的结点。
    1.类名：    private class Node
    2.构造方法： Node(T t,Node pre,Node next)：创建Node对象
    3.成员变量： T item:存储数据
               Node next：指向下一个结点
               Node pre:指向上一个结点

    【构造方法】：TowWayLinkList()：创建TowWayLinkList对象
    【成员方法】：
            1.public void clear()：空置线性表
            2.public boolean isEmpty()：判断线性表是否为空，是返回true，否返回false
            3.public int length():获取线性表中元素的个数
            4.public T get(int i):读取并返回线性表中的第i个元素的值
            5.public void insert(T t)：往线性表中添加一个元素；
            6.public void insert(int i,T t)：在线性表的第i个元素之前插入一个值为t的数据元素。
            7.public T remove(int i):删除并返回线性表中第i个数据元素。
            8.public int indexOf(T t):返回线性表中首次出现的指定的数据元素的位序号，若不存在，则返回-1。
            9.public T getFirst():获取第一个元素
            10.public T getLast():获取最后一个元素
 */

public class TwoWayLinkList<T> implements Iterable<T> {
    //首结点
    private Node head;
    //最后一个结点
    private Node last;

    //链表的长度
    private int N;

    //结点类
    private class Node{
        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }

        //存储数据
        public T item;
        //指向上一个结点
        public Node pre;
        //指向下一个结点
        public Node next;
    }

    public TwoWayLinkList() {
        //初始化头结点和尾结点
        this.head = new Node(null,null,null);
        this.last=null;
        //初始化元素个数
        this.N=0;
    }

    //清空链表
    public void clear(){
        this.head.next=null;
        this.head.pre=null;
        this.head.item=null;
        this.last=null;
        this.N=0;
    }

    //获取链表长度
    public int length(){
        return N;
    }

    //判断链表是否为空
    public boolean isEmpty(){
        return N==0;
    }

    //获取第一个元素
    public T getFirst(){
        if (isEmpty()){
            return null;
        }
        return head.next.item;
    }

    //获取最后一个元素
    public T getLast(){
        if (isEmpty()){
            return null;
        }
        return last.item;
    }

    //插入元素t
    public void insert(T t){

        if (isEmpty()){
            //如果链表为空：

            //创建新的结点
            Node newNode = new Node(t,head, null);
            //让新结点称为尾结点
            last=newNode;
            //让头结点指向尾结点
            head.next=last;
        }else {
            //如果链表不为空
            Node oldLast = last;

            //创建新的结点
            Node newNode = new Node(t, oldLast, null);

            //让当前的尾结点指向新结点
            oldLast.next=newNode;
            //让新结点称为尾结点
            last = newNode;
        }

        //元素个数+1
        N++;

    }

    //向指定位置i处插入元素t
    public void insert(int i,T t){
        //找到i位置的前一个结点
        Node pre = head;
        for(int index=0;index<i;index++){
            pre=pre.next;
        }
        //找到i位置的结点
        Node curr = pre.next;
        //创建新结点
        Node newNode = new Node(t, pre, curr);
        //让i位置的前一个结点的下一个结点变为新结点
        pre.next=newNode;
        //让i位置的前一个结点变为新结点
        curr.pre=newNode;
        //元素个数+1
        N++;
    }

    //获取指定位置i处的元素
    public T get(int i){
        Node n = head.next;
        for(int index=0;index<i;index++){
            n=n.next;
        }
        return n.item;
    }

    //找到元素t在链表中第一次出现的位置
    public int indexOf(T t){
        Node n = head;
        for(int i=0;n.next!=null;i++){
            n=n.next;
            if (n.next.equals(t)){
                return i;
            }
        }
        return -1;
    }

    //删除位置i处的元素，并返回该元素
    public T remove(int i){
        //找到i位置的前一个结点
        Node pre = head;
        for(int index=0;index<i;index++){
            pre=pre.next;
        }
        //找到i位置的结点
        Node curr = pre.next;
        //找到i位置的下一个结点
        Node nextNode= curr.next;
        //让i位置的前一个结点的下一个结点变为i位置的下一个结点
        pre.next=nextNode;
        //让i位置的下一个结点的上一个结点变为i位置的前一个结点
        nextNode.pre=pre;
        //元素的个数-1
        N--;
        return curr.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new TIterator();
    }

    private class TIterator implements Iterator{
        private Node n;
        public TIterator(){
            this.n=head;
        }
        @Override
        public boolean hasNext() {
            return n.next!=null;
        }

        @Override
        public Object next() {
            n=n.next;
            return n.item;
        }
    }

}