package stack.test;

import stack.algorithm.NextGreaterElement;

public class NextGreaterElementTest {
    public static void main(String[] args) {
        int[] nums1 = {1,3,5,2,4};
        int[] nums2 = {6,5,4,3,2,1,7};

        NextGreaterElement nextGreaterElement = new NextGreaterElement();

        nextGreaterElement.nextGreaterElement(nums1, nums2);
    }
}
