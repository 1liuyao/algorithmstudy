package tree.test;

import tree.algorithm.GetMinimumDifference;

public class GetMinimumDifferenceTest {

    public static void main(String[] args) {
        GetMinimumDifference getMinimumDifference= new GetMinimumDifference();
        GetMinimumDifference.TreeNode treeNode1 = getMinimumDifference.new TreeNode(1, null, null);
        GetMinimumDifference.TreeNode treeNode3 = getMinimumDifference.new TreeNode(3, null, null);
        GetMinimumDifference.TreeNode treeNode2 = getMinimumDifference.new TreeNode(2, treeNode1, treeNode3);
        GetMinimumDifference.TreeNode treeNode6 = getMinimumDifference.new TreeNode(6, null, null);
        GetMinimumDifference.TreeNode treeNode4 = getMinimumDifference.new TreeNode(1, treeNode2, treeNode6);
        int minimumDifference1 = getMinimumDifference.getMinimumDifference1(treeNode4);
    }
}
