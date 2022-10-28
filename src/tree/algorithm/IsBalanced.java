package tree.algorithm;

/*
    【110 平衡二叉树】给定一个二叉树，判断它是否是高度平衡的二叉树。
                   本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
    【示例 1】
            输入：root = [3,9,20,null,null,15,7]
            输出：true
    【示例 2】
            输入：root = [1,2,2,3,3,null,null,4,4]
            输出：false
    【示例 3】
            输入：root = []
            输出：true
    =========================================================================================
    【解题思路】
            1、明确什么是平衡二叉树：树中每一个结点的左右子树【高度差的绝对值】< = 1
            2、需要明确树中每一个结点的左右子树高度差，那么就需要孩子结点告知根节点子树的高度，所需要使用后序遍历，return
            3、只要树中有一个结点不满足高度差的条件， 则整个树被判定不满足平衡二叉树
            4、递归终止条件：遍历到叶子结点下的 null 指针，认为高度为 0 ，return 0;
  */

public class IsBalanced {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}

        public TreeNode(int val){
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public boolean isBalanced(TreeNode root) {

        int result = computeHigh(root);
        if (result == -1)
            return false;
        return true;
    }

    public int computeHigh(TreeNode root) {
        // 步骤1：确定递归终止条件：遍历到叶子结点下的 null 结点，高度为0
        if (root == null)
            return 0;

        // 步骤2：计算左子树高度
        int leftHigh = computeHigh(root.left);
        // 步骤3：计算右子树高度
        int rightHigh = computeHigh(root.right);
        // 如果左右子树中有不平衡的子树，则直接返回整棵树是不平衡的
        if (leftHigh == -1 || rightHigh == -1)
            return -1;
        // 步骤4：计算根结点左右子树高度差
        int rootHigh = Math.abs(leftHigh - rightHigh);
        if (rootHigh > 1)
            return -1;
        // 计算树高度，返回给根结点
        int result = Math.max(leftHigh,rightHigh) + 1;
        return result;
    }

}
