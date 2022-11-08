package tree.test;

import tree.algorithm.SortedArrayToBST;

public class SortedArrayToBSTTest {
    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        SortedArrayToBST.TreeNode treeNode = sortedArrayToBST.sortedArrayToBST(nums);
    }
}
