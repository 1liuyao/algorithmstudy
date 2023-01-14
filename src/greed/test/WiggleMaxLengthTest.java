package greed.test;

import greed.algorithm.WiggleMaxLength;

public class WiggleMaxLengthTest {
    public static void main(String[] args) {
        int[] nums ={1,7,4,9,2,5};
        WiggleMaxLength wiggleMaxLength = new WiggleMaxLength();
        int i = wiggleMaxLength.wiggleMaxLength(nums);
        System.out.println(i);
    }
}
