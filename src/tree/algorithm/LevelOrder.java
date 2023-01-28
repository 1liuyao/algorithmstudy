package tree.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    【102 二叉树的层序遍历】给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。

                                          3
                                    9           20
                               NULL   NULL    15   17

    【用例 1】
            输入：root = [3,9,20,null,null,15,7]
            输出：[[3],[9,20],[15,7]]
    【用例 2】
            输入：root = [1]
            输出：[[1]]
    【用例 3】
            输入：root = []
            输出：[]
    =============================================================================================
    【解决方案】队列 --- > 广度
             root = [3,9,20,null,null,15,7]
             ------------------------------
              3                              根结点入队  size = 1，result = [3]
             ------------------------------
              9 20                           根节点 3 【出队】，result = [[3]]，左右孩子入队，size = 2，result = [[3], [9, 20]]
             ------------------------------
              15 17                          9左右孩子为空不入队，20的左右孩子入队，size = 2, result = [[3],[9,20],[15,7]]
             ------------------------------
                                             队列为空，结束遍历过程
             ------------------------------

             1、输出结果要求收集每一层的结点：需要统计每一层的结点个数 size
             2、出队操作：根据记录的size，依次将每一层的结点出队
             3、入队操作：当出队结点的左右孩子不为空，入队

 */

public class LevelOrder {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        // 步骤1：初始化结果收集容器，初始化每层结点收集容器，初始化队列，初始化层结点个数
        ArrayList<Integer> level = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        int size;

        if (root == null)
            return result;

        // 根节点入队
        queue.offerFirst(root);
        // 步骤2：遍历树中结点，执行入队出队操作
        while (!queue.isEmpty()) {
            // 记录队列大小
            size = queue.size();
            // 出队列操作
            while (size > 0) {
                TreeNode treeNode = queue.pollLast();
                level.add(treeNode.val);
                // 如果左右孩子不为空，则入队
                if (treeNode.left != null)
                    queue.offerFirst(treeNode.left);
                if (treeNode.right != null)
                    queue.offerFirst(treeNode.right);
                size--;
            }
            // 一层遍历完收集层结果
            result.add(new ArrayList<>(level));
            level.clear();
        }
        return result;
    }

}
