package dynamic.test;

import dynamic.algorithm.sequence.FindLengthOfLCIS;

public class FindLengthOfLCISTest {
    public static void main(String[] args) {
        int[] nums = {1,3,5,4,7};
        FindLengthOfLCIS findLengthOfLCIS = new FindLengthOfLCIS();
        int lengthOfLCIS = findLengthOfLCIS.findLengthOfLCIS(nums);
        System.out.println(lengthOfLCIS);
    }
}
