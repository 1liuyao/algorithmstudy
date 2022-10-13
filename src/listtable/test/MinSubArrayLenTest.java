package listtable.test;

import listtable.algorithm.MinSubArrayLen;

public class MinSubArrayLenTest {
    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        int result = minSubArrayLen.minSubArrayLen(target,nums);
        System.out.println(result);
        System.out.println("===========");

        int[] nums1 = {1,1,1,1,1,1,1,1};
        int target1 = 11;
        int result1 = minSubArrayLen.minSubArrayLen(target1,nums1);
        System.out.println(result1);

    }
}
