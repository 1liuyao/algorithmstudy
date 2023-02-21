package stack.algorithm;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
    【239 滑动窗口最大值】给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
                      你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
                      返回 滑动窗口中的最大值 。
    【用例 1】
            输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
            输出：[3,3,5,5,6,7]
            解释：
                    滑动窗口的位置                最大值
                    ---------------               -----
                    [1  3  -1] -3  5  3  6  7       3
                     1 [3  -1  -3] 5  3  6  7       3
                     1  3 [-1  -3  5] 3  6  7       5
                     1  3  -1 [-3  5  3] 6  7       5
                     1  3  -1  -3 [5  3  6] 7       6
                     1  3  -1  -3  5 [3  6  7]      7
    【用例 2】
            输入：nums = [1], k = 1
            输出：[1]
     =======================================================================
    【解题思路】1、用队列保存滑动窗口的值
              2、队列返回滑动窗口中的最大值
              3、队列如何维护滑动窗口中的最大值呢?
                单调队列：peek 和 pop操作永远返回的是队列中的最大值，并将最大元素永远的维持在【队列出口】
              举例：[1,3,-1,-3,5,3,6,7]
                  -------------------
                  1 3                       当前滑动窗口 [1,3,-1]，push(1)，push(3): 1出队，这样就维持队口元素最大
                  -------------------
                  3 -1                      push(-1)，-1 < 3 不违反队口元素最大的原则
                  -------------------       result = [3]
                  3,-1,-3                   移动滑动窗口 [3,-1,-3]，push(-3), pop(1):此时 1 已经在push(3)时出队，无需pop处理
                  -------------------       result = [3, 3]
                  5                         移动滑动窗口 [-1,-3,5], push(5),-1 -3 小于 5 ，则都出队
                  -------------------       pop(3),此时要出队的队列中的最大值，需要手动pop处理, result = [3, 3, 5]
                  5 3                       移动滑动窗口 [-3,5,3], push(3), pop(-1):此时 -1 已经在push(5)时出队，无需pop处理
                  -------------------       result = [3, 3, 5, 5]
                  6                         移动滑动窗口 [5,3,6], push(6): 5 3 小于 6 ，则都出队
                  -------------------       pop(-3):此时 -3 已经在push(5)时出队，无需pop处理，result = [3, 3, 5, 5，6]
                  7                         移动滑动窗口 [3,6,7]，push(7)：6 小于 7 ，则都出队 pop(5)：已经在push(6)时出队，
                  -------------------       无需pop处理：result = [3, 3, 5, 5，6，7]
             （1）滑动窗口每移动一个单位，就要单调队列执行响应的 pop 和 push 操作
             （2）push 操作：将比入队元素小的元素出栈
             （3）pop 操作：当随着滑动窗口移动需要移除的值为队列出口元素（即最大值元素），需要在队列中手动pop
                          那对于那些【不等于】队列出口元素，怎么出队的呢？
                          例如：滑动窗口从 [1,3,-1] 到 [3,-1,-3]，元素 1 需要出队，
                          但是这个操作在 3 入队的时候就已经实现了，所以无需手动pop
             （4）peek操作：直接读取队头元素，即为滑动窗口中的最大值，每移动一次滑动窗口，就需要收集一次最大值
 */
class MyQueue {
    Deque<Integer> deque = new LinkedList<>();
    //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
    //同时判断队列当前是否为空
    void poll(int val) {
        if (!deque.isEmpty() && val == deque.peek().intValue()) {
            deque.poll();
        }
    }
    //添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
    //保证队列元素单调递减
    //比如此时队列元素3,1，2将要入队，比1大，所以1弹出，此时队列：3,2
    void add(int val) {
        while (!deque.isEmpty() && val > deque.getLast().intValue()) {
            deque.removeLast();
        }
        deque.add(Integer.valueOf(val));
    }
    //队列队顶元素始终为最大值
    int peek() {
        return deque.peek();
    }
}

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        int len = nums.length - k + 1;
        //存放结果元素的数组
        int[] res = new int[len];
        int num = 0;
        //自定义队列
        MyQueue myQueue = new MyQueue();
        //先将前k的元素放入队列
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        res[num++] = myQueue.peek();
        for (int i = k; i < nums.length; i++) {
            //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
            myQueue.poll(nums[i - k]);
            //滑动窗口加入最后面的元素
            myQueue.add(nums[i]);
            //记录对应的最大值
            res[num++] = myQueue.peek();
        }
        return res;
    }
}

