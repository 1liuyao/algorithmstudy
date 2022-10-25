package stack.test;

public class MaxSlidingWindowTest {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        stack.algorithm.MaxSlidingWindow maxSlidingWindow = new stack.algorithm.MaxSlidingWindow();
        int[] ints = maxSlidingWindow.maxSlidingWindow(nums, k);
        for (Integer a:
             ints) {
            System.out.println(a);
        }
    }
}
