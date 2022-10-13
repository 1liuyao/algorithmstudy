package linkedtable.test;

import linkedtable.algorithm.MyLinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();

        int val = myLinkedList.get(1);
        System.out.println(val);
        myLinkedList.travel();

        myLinkedList.addAtHead(1);
        myLinkedList.travel();

        myLinkedList.addAtTail(3);
        myLinkedList.travel();

        myLinkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        myLinkedList.travel();

        int val1 = myLinkedList.get(1);            //返回2
        System.out.println(val1);
        myLinkedList.travel();

        myLinkedList.deleteAtIndex(1);  //现在链表是1-> 3
        myLinkedList.travel();

        int val2 = myLinkedList.get(1);
        System.out.println(val2);
        myLinkedList.travel();
    }
}
