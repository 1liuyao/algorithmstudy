package tree.algorithm;
/*
    【111 二叉树的最小深度】给定一个二叉树，找出其最小深度。
                        最小深度是从根节点到【最近叶子节点】的最短路径上的节点数量。
                        说明：叶子节点是指没有子节点的节点。
    【用例1】                        3
                              9          20
                                     15      7
            输入：root = [3,9,20,null,null,15,7]
            输出：2
    【用例2】                        2
                              null      3
                                  null     4
                                       null   5
                                          null   6
            输入：root = [2,null,3,null,4,null,5,null,6]
            输出：5
     =========================================================================
     【解题思路】                     2
                              null      3
                                  null     4
                                       null   5
                                          null   6
              1、明确最小深度：根结点到最近【叶子结点】的距离，2的左孩子是 null ，并不是叶子结点
                           （1）当根节点左子树为空，则最小深度在右子树中产生
                           （2）当根结点右子树为空，则最小深度在左子树中产生
              2、通过高度求深度：当根节点左子树、右子树【均不为空】，最小高度 == 最小深度
                            【后序遍历】从左右子树中选择高度最小的 + 1

 */
public class MinDepth {
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

    // 后序遍历求最小高度
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        int leftLength = minDepth(root.left);
        int rightLength = minDepth(root.right);
        int rootLength = 0;
        //对结点左右孩子为 null 的情况做排序，因为他们并不是叶子结点
        // 当根节点左子树为空，则最小深度在右子树中产生
        if (root.left == null && root.right != null)
            rootLength = rightLength + 1;
        // 当根结点右子树为空，则最小深度在左子树中产生
        else if (root.right == null && root.left != null)
            rootLength = leftLength + 1;
        // 当根节点左子树、右子树【均不为空】，最小高度 == 最小深度
        else
            rootLength = Math.min(leftLength, rightLength) + 1;
        return rootLength;
    }
}
