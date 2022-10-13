package stack.structure;

import java.util.Iterator;
/*
    【链栈数据结构和基本操作实现】

    【概念】：栈是一种基于先进后出(FILO)的数据结构，是一种只能在一端进行插入和删除操作的特殊线性表。它按照先进后出
            的原则存储数据，先进入的数据被压入栈底，最后的数据在栈顶，需要读数据的时候从栈顶开始弹出数据（最后一
            个数据被第一个读出来）。我们称数据进入到栈的动作为压栈，数据从栈中出去的动作为弹栈。

    【类名】：Stack

    【成员变量】：
            1.private Node head:记录首结点
            2.private int N:当前栈的元素个数

    【构造方法】：Stack：创建Stack对象

    【成员方法】：
            1.public boolean isEmpty()：判断栈是否为空，是返回true，否返回false
            2.public int size():获取栈中元素的个数
            3.public T pop():弹出栈顶元素
            4.public void push(T t)：向栈中压入元素t
 */

public class Stack<T> implements Iterable<T>{
    //记录首结点
    private Node head;
    //栈中元素的个数
    private int N;

    private class Node{
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public Stack() {
        this.head = new Node(null,null);
        this.N=0;
    }

    //判断当前栈中元素个数是否为0
    public boolean isEmpty(){
        return N==0;
    }

    //获取栈中元素的个数
    public int size(){
        return N;
    }

    //把t元素压入栈
    public void push(T t){
        //找到首结点指向的第一个结点
        Node oldFirst = head.next;
        //创建新结点
        Node newNode = new Node(t, null);
        //让首结点指向新结点
        head.next = newNode;
        //让新结点指向原来的第一个结点
        newNode.next=oldFirst;
        //元素个数+1；
        N++;
    }

    //弹出栈顶元素
    public T pop(){
        //找到首结点指向的第一个结点
        Node oldFirst = head.next;
        if (oldFirst==null){
            return null;
        }
        //让首结点指向原来第一个结点的下一个结点
        head.next=oldFirst.next;
        //元素个数-1；
        N--;
        return oldFirst.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator{
        private Node n;

        public SIterator(){
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

}

