package queue.algorithm;

import java.util.Stack;

/*
    【232. 用栈实现队列】请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
                      实现 MyQueue 类：
                                    void push(int x) 将元素 x 推到队列的末尾
                                    int pop() 从队列的开头移除并返回元素
                                    int peek() 返回队列开头的元素
                                    boolean empty() 如果队列为空，返回 true ；否则，返回 false
    【用例】
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
    ===========================================================================
    【解题思路】采用两个队列实现栈，一个栈用于入队，一个栈用于出队
              入队顺序 1 2 3，出队顺序应为 1 2 3
                         --------------
              stackIn    |1  2  3          push(1) push(2) push(3)
                         --------------
                                           pop(3) pop(2) pop(1)
                         --------------
              stackOut        1  2  3 |    push(3) push(2) push(1)
                         --------------
              ※注意：当stackOut为空时，将stackIn【全部】元素加载到stackOut，如果只加载一部分则后续出队会乱序

 */
public class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;
    public MyQueue() {
        stackIn = new Stack<Integer>();
        stackOut = new Stack<Integer>();
    }

    public void push(int x) {
        // 不用考虑栈满的问题，题目的前提所有操作是合法的，不考虑栈上溢和下溢
        stackIn.push(x);
    }

    public int pop() {
        // 出队时，如果stackOut不是空，那就直接出队
        // 注意不用考虑空的情况，因为题目的前提所有操作是合法的
        Integer item;
        if (!stackOut.empty()) {
            item = stackOut.pop();
        }
        // 如果stackOut是空，那么需要把stackIn中的元素全部加入到stackOut中，执行pop
        else {
            stackIntoOut(stackIn, stackOut);
            item = stackOut.pop();
        }
        return item;
    }

    public int peek() {
        int pop = this.pop();
        // peek()和pop()的区别是，peek只读栈定元素，不删除
        // 所以peek()和pop()大部分操作是一致的，只是要把pop删的元素重新入栈，注意此时需要入的栈是stackOut
        stackOut.push(pop);
        return pop;
    }

    public boolean empty() {
        if (stackOut.empty() && stackIn.empty()) {
            return true;
        }
        return false;
    }

    private void stackIntoOut(Stack stackIn, Stack stackOut) {
        while (!stackIn.empty()){
            Object item = stackIn.pop();
            stackOut.push(item);
        }
    }
}
