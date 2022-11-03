package tree.algorithm;
/*
    【112 路径总和】给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
                  判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
                  如果存在，返回 true ；否则，返回 false 。
                  叶子节点 是指没有子节点的节点。
    【示例 1】
                                   5
                              4         8
                          11    null  13   4
                        7   2                 1
            输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
            输出：true
            解释：路径为：5 -> 4 -> 11 -> 2
    【示例 2】
            输入：root = [1,2,3], targetSum = 5
            输出：false
            解释：树中存在两条根节点到叶子节点的路径：
            (1 --> 2): 和为 3
            (1 --> 3): 和为 4
            不存在 sum = 5 的根节点到叶子节点的路径。
    【示例 3】
            输入：root = [], targetSum = 0
            输出：false
            解释：由于树是空的，所以不存在根节点到叶子节点的路径。
     ==============================================================================================
     【解题思路】1、终止条件：遍历到叶子结点，和减为0
               2、每层重复的操作是：减去结点的值
               注意：（1）明确递归的开始究竟是从根节点开始，还是从根结点的左右孩子开始，这决定了【初始值的设置】
                    （2）明确什么样的变量需要设置成成员变量：哪些变量随着整棵树的遍历在不断的改变，
                                                     当然也可以作为递归参数传递，但是会导致递归参数过多
                    （3）明确什么样的变量需要回溯：
                        ①先明确变量的作用域
                        ②再明确在【单层递归】中，这个操作是否在本层产生变化，如果产生变化是否影响其他递归操作，如果影响就需要回溯
                        ③最好的方法，就是手动执行依次递归，看看是否符合预期
 */
public class HasPathSum {
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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        // 遍历到叶子结点时，已经总和已经减去了叶子结点值，写在终止条件前
        targetSum = targetSum - root.val;
        // 遍历到叶子结点结束
        if (root.left == null && root.right == null) {
            if (targetSum == 0)
                return true;
            else
                return false;
        }
        if (root.left != null) {
            boolean leftResult = hasPathSum(root.left, targetSum);
            //targetSum = targetSum + root.val;
            // 不需要回溯，因为targetSum的减操作没有放在if中，作用域从定义位置开始到整个函数结束都有效
            // 所以此时rightResult = hasPathSum(root.right, targetSum);已经记录了本层的targetSum，并没有发生变化，不需要回溯
            if (leftResult)
                return true;

        }
        if (root.right != null) {
            boolean rightResult = hasPathSum(root.right, targetSum);
            //targetSum = targetSum + root.val;
            if (rightResult)
                return true;
        }
        return false;
    }
}
