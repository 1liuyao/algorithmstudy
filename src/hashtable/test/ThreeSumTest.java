package hashtable.test;

import hashtable.algorithm.ThreeSum;

import java.util.List;

public class ThreeSumTest {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(nums);
        for (List<Integer> a : lists) {
            for (Integer b : a) {
                System.out.print(b + " ");
            }
            System.out.println("-----------");
        }
        Object[] objects = lists.toArray();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            if (i == 0)
                s.append("[");
            s.append(objects[i]);
            if (i != objects.length - 1) {
                s.append(",");
            }else {
                s.append("]");
            }
        }
        System.out.println(s.toString());

        lists.stream().forEach(System.out::println);
    }
}
