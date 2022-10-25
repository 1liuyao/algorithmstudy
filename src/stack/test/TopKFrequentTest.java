package stack.test;

import stack.algorithm.TopKFrequent;

public class TopKFrequentTest {
    public static void main(String[] args) {
        int[] nums ={1,1,1,2,2,3};
        int k = 2;
        TopKFrequent topKFrequent = new TopKFrequent();
        int[] ints = topKFrequent.topKFrequent(nums, k);
        for (Integer a: ints) {
            System.out.println(a);
        }

    }
}
