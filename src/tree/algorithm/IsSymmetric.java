package tree.algorithm;
/*
    【101 对称二叉树】给你一个二叉树的根节点 root ， 检查它是否轴对称。

    【用例1】                 1
                      2            2
                   4     3      3     4
            输入：root = [1,2,2,3,4,4,3]
            输出：true

    【用例2】                 1
                      2            2
                          3              3
            输入：root = [1,2,2,null,3,null,3]
            输出：false
    =================================================================================
    【解题思路】

                  |     左子树     1      右子树    |     （1）左子树进行翻转就变成了右子树，右子树进行翻转就变成了左子树
                  |       2       |        2      |     （2）左子树的外侧结点值和右子树外侧结点值相等
    【对称情况】    |    3     4    |     4     3   |     （3）左子树的内测结点和右子树的内测结点相等
                  | 5   6   7   8 |   8   7  6   5|     （4）当遍历完左子树，遍历完右子树，内外侧结点值相等，根节点就认为左右子树对称
               外侧|           内侧|内侧            | 外侧 （5）确定遍历方式：【后序遍历】：因为根节点必须知道它的左右子树互相可以翻转

               1、什么时候选择后序遍历？
                 根节点需要知道全部孩子结点的信息，才能做出判断，这个时候就需要后序遍历。
                 在二叉树的题目中首先需要确定遍历顺序，这一点很重要
               2、上一层是否需要下一层的信息，是则需要在本层递归逻辑中添加 return，把孩子结点的信息返回给根结点

    【对称情况讨论】：    左子树           右子树     是否对称
                        空               空       对称
                       非空              空       非对称
                        空              非空      非对称
                       非空  内外侧值不等  非空      非对称
                       非空  内外侧值相等  非空      对称
 */
public class IsSymmetric {

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

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return false;
        boolean result = compare(root.left, root.right);
        return result;
    }

    public boolean compare(TreeNode left, TreeNode right) {
        // 对称情况讨论
        if (left == null && right == null)
            return true;
        if (left != null && right == null)
            return false;
        if (left == null && right != null)
            return false;
        // 左右子树都不为空，但是值不相等
        if (left.val != right.val)
            return false;
        // 左右子树都不为空，但是值相等
        // 获外侧比较结果
        boolean out = compare(left.left, right.right);
        // 获内侧比较结果
        boolean in = compare(left.right, right.left);
        // 返回给上层 ，下层左右孩子是否为对称的
        return out && in;
    }
}
