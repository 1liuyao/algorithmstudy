package tree.algorithm;
/*
    【98 验证二叉搜索树】给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
                     有效二叉搜索树定义如下：
                                        节点的左子树只包含 小于 当前节点的数。
                                        节点的右子树只包含 大于 当前节点的数。
                                        所有左子树和右子树自身必须也是二叉搜索树。
    【示例 1】
            输入：root = [2,1,3]
            输出：true
    【示例 2】
            输入：root = [5,1,4,null,null,3,6]
            输出：false
            解释：根节点的值是 5 ，但是右子节点的值是 4 。
    ================================================================================================
    【解题思路】1、确定遍历方式：由于二叉搜索树中序遍历有序，所以判定一个二叉树是否为搜索二叉树 <===> 中序遍历是递增序列
              2、如何判断是递增序列：（1）可以收集中序遍历结果，再对结果进一步判断是否是递增序列 --- （效率低）
                                 （2）能否在中序遍历的过程中直接比较：
                                     ① 一种是初始化最小值，遍历到的结点都比这个最小值大，每遍历一个结点就更新最小值
                                     ② 另一种是直接记录当前结点，和当前结点在中序遍历中的前驱结点，保证前驱一定小于当前结点
 */
public class IsValidBST {
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
    // 双指针：当前结点指针 + 中序遍历时当前结点的前驱指针
    // 初始化前驱指针
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        // 空结点是满二叉树、平衡二叉树、二叉搜索树
        if (root == null)
            return true;
        // 遍历左
        boolean leftIsValid = isValidBST(root.left);
        // 遍历中：判断是否满足升序规则 [2 2 2]应该返回false
        if(pre != null && pre.val >= root.val)
            return false;
        pre = root;
        // 遍历右
        boolean rightIsValid = isValidBST(root.right);
        return leftIsValid && rightIsValid;
    }
}
