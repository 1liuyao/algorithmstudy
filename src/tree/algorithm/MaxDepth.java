package tree.algorithm;
/*
    【104 二叉树的最大深度】给定一个二叉树，找出其最大深度。
                        二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
                        说明: 叶子节点是指没有子节点的节点。

    【用例1】给定二叉树 [3,9,20,null,null,15,7]，
                                        3
                                       / \
                                      9  20
                                        /  \
                                       15   7
           返回它的最大深度 3 。
     ==============================================================================
     【解题思路】                    3       3       1
                                          / \
       任意结点到叶子结点的距离：【高度】2      9  20    2  【深度】：任意结点到根节点的距离
                                        /   /  \
                                   1   6   15   7  3
       1、最大高度 = 最大深度
       2、求高度：先知道左右孩子的高度，在根据左右孩子的高度，计算根节点的高度，所以使用【后序】遍历，并且需要向根节点返回值
       3、求深度：根节点深度1，左右孩子在根节点的基础上加1，使用【前序遍历】
 */
public class MaxDepth {

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
    // 而根节点的高度 == 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        // 确定递归终止条件，叶子结点下的null结点的高度是 0
        if (root == null)
            return 0;
        // 后序遍历过程中，计算左右子树高度，并将最大值返回给根节点
        int leftLength = maxDepth(root.left);
        int rightLength = maxDepth(root.right);
        int rootLength = Math.max(leftLength, rightLength) + 1;
        return rootLength;
    }
}
