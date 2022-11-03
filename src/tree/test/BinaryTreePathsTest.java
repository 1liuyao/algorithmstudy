package tree.test;

import tree.algorithm.BinaryTreePaths;

import java.util.List;

public class BinaryTreePathsTest {
    public static void main(String[] args) {
        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();
        BinaryTreePaths.TreeNode treeNode4 = binaryTreePaths.new TreeNode(5, null, null);
        BinaryTreePaths.TreeNode treeNode5 = binaryTreePaths.new TreeNode(3, null, null);
        BinaryTreePaths.TreeNode treeNode3 = binaryTreePaths.new TreeNode(2, null, treeNode4);
        BinaryTreePaths.TreeNode treeNode2 = binaryTreePaths.new TreeNode(1, treeNode3, treeNode5);

        List<String> strings = binaryTreePaths.binaryTreePaths(treeNode2);
        for (String s:
             strings) {
            System.out.println(s);
        }
    }
}
