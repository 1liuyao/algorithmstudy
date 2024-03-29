package linkedtable.structure;

import java.util.Iterator;
/*
    【单链表数据结构及基本操作实现】

    【概念】：单向链表是链表的一种，它由多个结点组成，每个结点都由一个数据域和一个指针域组成，数据域用来存储数据，
            指针域用来指向其后继结点。链表的头结点的数据域不存储数据，指针域指向第一个真正存储数据的结点。

    【类名】：LinkList

    【成员变量】：
            1.private Node head:记录首结点
            2.private int N:记录链表的长度

    【成员内部类】:也就是结点类，既除头节点外真正存储数据的结点。
            1.类名：    private class Node
            2.构造方法： Node(T t,Node next)：创建Node对象
            3.成员变量： T item:存储数据
                       Node next：指向下一个结点

    【构造方法】：LinkList()：创建LinkList对象，初始化头结点和链表长度.

    【成员方法】：
            1.public void clear()：空置线性表
            2.public boolean isEmpty()：判断线性表是否为空，是返回true，否返回false
            3.public int length():获取线性表中元素的个数
            4.public T get(int i):读取并返回线性表中的第i个元素的值
            5.public void insert(T t)：往线性表中添加一个元素；
            6.public void insert(int i,T t)：在线性表的第i个元素之前插入一个值为t的数据元素。
            7.public T remove(int i):删除并返回线性表中第i个数据元素。
            8.public int indexOf(T t):返回线性表中首次出现的指定的数据元素的位序号，若不存在，则返回-1。
 */

public class LinkList<T> implements Iterable<T>{
    //记录头结点
    private Node head;
    //记录链表的长度
    private int N;

    //结点类
    private class Node {
        //存储数据
        T item;
        //下一个结点
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public LinkList() {
        //初始化头结点
        this.head = new Node(null,null);
        //初始化元素个数
        this.N=0;
    }

    //清空链表
    public void clear() {
        head.next=null;
        this.N=0;
    }

    //获取链表的长度
    public int length() {
        return N;
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return N==0;
    }

    //获取指定位置i出的元素
    public T get(int i) {

        //通过循环,从头结点开始往后找，依次找i次，就可以找到对应的元素
        Node n = head.next;
        for(int index=0;index<i;index++){
            n=n.next;
        }

        return n.item;
    }

    //向链表中添加元素t
    public void insert(T t) {
        //找到当前最后一个结点

        Node n = head;
        while(n.next!=null){
            n=n.next;
        }

        //创建新结点，保存元素t
        Node newNode = new Node(t, null);
        //让当前最后一个结点指向新结点
        n.next=newNode;
        //元素的个数+1
        N++;
    }

    //向指定位置i出，添加元素t
    public void insert(int i, T t) {
        //找到i位置前一个结点
        Node pre = head;
        for(int index=0;index<=i-1;index++){
            pre=pre.next;
        }

        //找到i位置的结点
        Node curr = pre.next;
        //创建新结点，并且新结点需要指向原来i位置的结点
        Node newNode = new Node(t, curr);
        //原来i位置的前一个节点指向新结点即可
        pre.next=newNode;
        //元素的个数+1
        N++;
    }

    //删除指定位置i处的元素，并返回被删除的元素
    public T remove(int i) {
        //找到i位置的前一个节点
        Node pre = head;
        for(int index=0;index<=i-1;i++){
            pre=pre.next;
        }
        //要找到i位置的结点
        Node curr = pre.next;
        //找到i位置的下一个结点
        Node nextNode = curr.next;
        //前一个结点指向下一个结点
        pre.next=nextNode;
        //元素个数-1
        N--;
        return curr.item;
    }

    //查找元素t在链表中第一次出现的位置
    public int indexOf(T t) {
        //从头结点开始，依次找到每一个结点，取出item，和t比较，如果相同，就找到了
        Node n = head;
        for(int i=0;n.next!=null;i++){
            n=n.next;
            if (n.item.equals(t)){
                return i;
            }
        }
        return -1;
    }


    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    private class LIterator implements Iterator{
        private Node n;
        public LIterator(){
            this.n=head;
        }

        @Override
        public boolean hasNext() {
            return n.next!=null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }

    //用来反转整个链表
    public void reverse(){

        //判断当前链表是否为空链表，如果是空链表，则结束运行，如果不是，则调用重载的reverse方法完成反转
        if (isEmpty()){
            return;
        }

        reverse(head.next);
    }

    //反转指定的结点curr，并把反转后的结点返回
    public Node reverse(Node curr){
        if (curr.next==null){
            head.next=curr;
            return curr;
        }
        //递归的反转当前结点curr的下一个结点；返回值就是链表反转后，当前结点的上一个结点
        Node pre = reverse(curr.next);
        //让返回的结点的下一个结点变为当前结点curr；
        pre.next=curr;
        //把当前结点的下一个结点变为null
        curr.next=null;
        return curr;
    }


}
