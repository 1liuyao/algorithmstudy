package tree.test;

import tree.algorithm.LevelOrder;

import java.util.List;

public class LevelOrderTest {
    public static void main(String[] args) {
        LevelOrder levelOrder = new LevelOrder();
        LevelOrder.TreeNode treeNode4 = levelOrder.new TreeNode(15, null, null);
        LevelOrder.TreeNode treeNode5 = levelOrder.new TreeNode(17, null, null);
        LevelOrder.TreeNode treeNode3 = levelOrder.new TreeNode(20, treeNode4, treeNode5);
        LevelOrder.TreeNode treeNode2 = levelOrder.new TreeNode(9, null, null);
        LevelOrder.TreeNode treeNode1 = levelOrder.new TreeNode(3, treeNode2, treeNode3);

        List<List<Integer>> lists = levelOrder.levelOrder(treeNode1);
        for (List<Integer> list:
             lists) {
            System.out.println(list);
        }
    }
}
