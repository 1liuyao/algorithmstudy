package stack.test;

import java.util.LinkedList;
import java.util.Queue;

public class MaxSlidingWindowTest {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,1,2,0,5};
        int k = 3;
        stack.algorithm.MaxSlidingWindow maxSlidingWindow = new stack.algorithm.MaxSlidingWindow();
        int[] ints = maxSlidingWindow.maxSlidingWindow(nums, k);
        for (Integer a:
             ints) {
            System.out.println(a);
        }
    }

}
