package stack.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/*
    【225 用队列实现栈】请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
                     实现 MyStack 类：
                                    void push(int x) 将元素 x 压入栈顶。
                                    int pop() 移除并返回栈顶元素。
                                    int top() 返回栈顶元素。
                                    boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
    ======================================================================================================
    【解题思路】
            1、出栈：  que1    1 2 3      que1   3      出栈 3   que1    1 2   交换两个队列指针
                      que2               que2    1 2            que2
 */
public class MyStack {
    private Queue<Integer> que;
    private Queue<Integer> temp;
    public MyStack() {
        que = new LinkedList<>();
        temp = new LinkedList<>();
    }

    public void push(int x) {
        que.offer(x);
    }

    public int pop() {
        int size = que.size();
        while(size > 1){
            temp.offer(que.poll());
            size--;
        }
        int result = que.poll();
        Queue<Integer> swap = que;
        que = temp;
        temp = swap;
        return result;
    }

    public int top() {
        int pop = this.pop();
        this.push(pop);
        return pop;
    }

    public boolean empty() {
        return que.isEmpty();
    }
}
