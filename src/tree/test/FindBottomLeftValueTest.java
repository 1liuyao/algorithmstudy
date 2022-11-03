package tree.test;

import tree.algorithm.FindBottomLeftValue;

public class FindBottomLeftValueTest {
    public static void main(String[] args) {
        FindBottomLeftValue findBottomLeftValue = new FindBottomLeftValue();
        FindBottomLeftValue.TreeNode treeNode1 = findBottomLeftValue.new TreeNode(3,null,null);
        FindBottomLeftValue.TreeNode treeNode2 = findBottomLeftValue.new TreeNode(2,null,null);
        FindBottomLeftValue.TreeNode treeNode3 = findBottomLeftValue.new TreeNode(1,treeNode1,treeNode2);

        int bottomLeftValue = findBottomLeftValue.findBottomLeftValue(treeNode3);
        System.out.println(bottomLeftValue);
    }
}
