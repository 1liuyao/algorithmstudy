package tree.algorithm;
/*
    【404 左叶子之和】给定二叉树的根节点 root ，返回所有左叶子之和。

    【示例 1】                   3
                            9      20
                                15     7
            输入: root = [3,9,20,null,null,15,7]
            输出: 24
            解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
    【示例 2】
            输入: root = [1]
            输出: 0
    ============================================================================
    【解题思路】1、确定遍历顺序：【后序】，其实和求高度是一样的，先计算左、右子树【左叶子】之和，那么根节点左叶子之和是两者的和
              2、怎么区分【左叶子】，只能通过父节点才能判断是否为左孩子，那么就涉及到遍历完左子树的回溯过程，
                 因为遍历完左，回溯到左的根，这时候就可以判断是否为左叶子结点，并保存左叶子结点值
              3、递归终止条件：
                                 3
                            9      20（3）回溯到根结点，判断 7 不是 左叶子，则认为右子树和 仍为 0，当 左叶子 15 回溯到 20，则更新和
                                15     7（2）遍历到叶子结点 7，此时不能向上层返回7，因为还没判断是否为 左叶子，所以向上层返回 0；
                            Null  Null （1）如果遍历到null，则向上层返回左叶子之和为 0
 */

public class SumOfLeftLeaves {

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

    public int sumOfLeftLeaves(TreeNode root) {
        // 步骤1：确定递归终止条件
        if (root == null)
            return 0;
        // 虽然走到了叶子结点，但是没办法判断是否为左，所以仍返回0，只有回溯到根结点，判断确实是左叶子，再记录叶子和
        if (root.left == null && root.right == null)
            return  0;

        // 计算左叶子和
        int leftSum = sumOfLeftLeaves(root.left);
        // 回溯到根节点判断是否为右孩子，是就加上左叶子的和
        if (root.left != null && root.left.left == null && root.left.right == null)
            leftSum = leftSum + root.left.val;
        int rightSum = sumOfLeftLeaves(root.right);

        int sum = leftSum + rightSum;
        return sum;
    }
}
