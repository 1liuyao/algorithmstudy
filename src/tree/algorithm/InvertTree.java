package tree.algorithm;
/*
    【226 翻转二叉树】给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。

    【用例1】
            输入：root = [2,1,3]          2      ---->      2
            输出：[2,3,1]             1       3         3        1
    【示例2】
            输入：root = []
            输出：[]
    ===========================================================================
    【解题思路】1、明确交换的含义：是将 结点 的左右指针变换，并不是交换左右孩子的值，是让左指针指向有孩子，右指针指向左孩子
              2、所有二叉树的题都需要明确遍历顺序，本题前、中、后、层次遍历都可以实现
              3、画图模拟交换的过程
              4、在中序遍历中，当左子树翻转完，回溯到根节点时，此时交换左右孩子，那么已经翻转完成的左孩子就变成了右孩子，
                 此时还没有进行翻转的右子树变成了左子树，按照中序遍历的顺序会继续遍历右，那么这棵还未进行翻转的左子树将永远不会被翻转

 */

import java.util.LinkedList;

public class InvertTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 前序遍历实现二叉树翻转
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        swap(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // 后序遍历实现二叉树翻转
    public TreeNode invertTree1(TreeNode root) {
        if (root == null)
            return null;
        invertTree(root.left);
        invertTree(root.right);
        swap(root);
        return root;
    }

    // 中序遍历实现二叉树翻转
    public TreeNode invertTree2(TreeNode root) {
        if (root == null)
            return null;
        invertTree(root.left);
        swap(root);
        // 注意：需要遍历的是回溯到根节点后，被交换的左子树（原来的右子树）
        invertTree(root.left);
        return root;
    }
    // 层次遍历实现二叉树翻转
    public TreeNode invertTree3(TreeNode root) {
        if (root == null)
            return null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        int size;
        queue.offerFirst(root);
        while (!queue.isEmpty()){
            size = queue.size();
            while (size > 0){
                TreeNode treeNode = queue.pollLast();
                // 按照层序遍历到每一个结点时，交换其左右孩子
                swap(treeNode);
                if(treeNode.left != null)
                    queue.offerFirst(treeNode.left);
                if (treeNode.right != null)
                    queue.offerFirst(treeNode.right);
                size--;
            }
        }
        return root;
    }

    private void swap(TreeNode root) {
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
