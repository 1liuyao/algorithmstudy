package tree.test;

import tree.algorithm.FindMode;

public class FindModeTest {
    public static void main(String[] args) {
        FindMode findMode = new FindMode();
        FindMode.TreeNode treeNode = findMode.new TreeNode(0, null, null);
        int[] mode = findMode.findMode(treeNode);
        for (int i = 0; i < mode.length; i++) {
            System.out.println(mode[i]);
        }
    }
}
