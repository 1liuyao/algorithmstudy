package tree.algorithm;

/*
    【106 从中序与后序遍历序列构造二叉树】给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
                                    postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。

    【示例 1】
            输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
            输出：[3,9,20,null,null,15,7]
    【示例 2】
            输入：inorder = [-1], postorder = [-1]
            输出：[-1]
    ===================================================================================================
    【解题思路】第一步：如果数组大小为零的话，说明是空节点了。
              第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。
              第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点
              第四步：切割中序数组，切成中序左数组和中序右数组 （顺序别搞反了，一定是先切中序数组）
              第五步：切割后序数组，切成后序左数组和后序右数组
              第六步：递归处理左区间和右区间
                [9|3|15,20,7 ]                   选择后序数组中最后一个结点构建根结点                               3
                [9|15,7,20|3|]                   切割中序数组，获得前序左右子树序列                               /  \
               /             \                   切割后序数组，获得后序序左右子树序列                            9    20
    前序左子树 [9]           [15|20|7 ]前序右子树    根节点的左孩子在新切割后的前序左子树序列、后序左子树序列中产生           /  \
    后序左子树 [9]           [15|7|20|]后序右子树    根节点的右孩子在新切割后的前序右子树序列、后序右子树序列中产生         15   17
    当序列大小为1停止递归        /    \
                           [15]   [7]
                           [15]   [7]

              注意：1、递归终止条件：后序数组切割后只剩下一个元素
                   2、单层递归操作：构建根结点，切中序，切后序，构建左孩子，构建有孩子
                   3、为什么切数组要先切中序，再切后序？
                      因为切完中序才能获得左子树的大小，才能确定后序数组怎么切
                      切数组的过程要贯彻开闭原则
                   4、根节点的构建永远取更新后的后序遍历最后一个结点，在前序中搜索根节点的位置，左后序分割
                      这种在一个集合中查找一个元素的操作，可以用哈希表实现
                   5、构建二叉树一定要使用【前序遍历】

 */

import java.util.Arrays;
import java.util.HashMap;

public class BuildTree {
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 如果数组为空，则为空树
        if(inorder.length == 0)
            return null;
        // 取后序数组最后一个元素构建根结点【前序遍历】
        TreeNode treeNode = new TreeNode(postorder[postorder.length - 1]);
        // 确定递归终止条件
        if(postorder.length == 1)
            // 应该返回这个叶子结点，在返回前我们需要构建结点，所以将构建结点操作写在终止条件前
            return treeNode;
        // 每层递归重复操作：创建结点（已写在终止条件前） + 切前序 + 切后序 (左闭右开)
        int index = getIndex(inorder, postorder);
        int[] newInorderLeft = Arrays.copyOfRange(inorder, 0, index);
        int[] newInorderRight = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        int[] newPostorderLeft = Arrays.copyOfRange(postorder, 0, index);
        int[] newPostorderRight = Arrays.copyOfRange(postorder, index, postorder.length - 1);

        // 递归构建左右孩子结点
        treeNode.left = buildTree(newInorderLeft,newPostorderLeft);
        treeNode.right = buildTree(newInorderRight, newPostorderRight);
        return treeNode;
    }

    private int getIndex(int[] inorder, int[] postorder) {
        // 用 map 存储<inorder元素, inorder索引>
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i], i );
        }
        return hashMap.get(postorder[postorder.length - 1]);
    }
}
