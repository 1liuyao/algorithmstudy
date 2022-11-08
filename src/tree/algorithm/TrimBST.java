package tree.algorithm;
/*
    【669 修剪二叉搜索树】给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。
                      通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
                      修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。
                      可以证明，存在 唯一的答案 。
                      所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
    【示例 1】
            输入：root = [1,0,2], low = 1, high = 2
            输出：[1,null,2]
    【示例 2】
            输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
            输出：[3,2,null,1]
    ===================================================================================================
    【解题思路】1、明确 需要被删除的元素 被删除后，谁来替代这个位置
              2、调整二叉树结构的题目：找到目标结点（搜索过程利用搜索二叉树的特性） + 构建新的二叉树（root.left = ? root.right = ?）
            [1 3]
              3        （1）3 符合范围 ，那么 3.left 应该连接被修建改过的 左子树；3.right 应该连接被修建改过的 右子树
            /   \      （2）0 < 1, 比给定范围小，那么 0 的左一定全部不符合要求，替代 0 位置的 新的根 只能在被修建过的右子树中产生
           0     4     （3）0 的右子树 2 和 1 都符合要求，则 null 返给 1，1 返回给 2，2 返回给 0，0 将 2返回给3
            \          （4）3.left 处理完后，需要处理 3.right，4 > 3，则说明 4.right 子树都不符合条件，只能在修剪过的 4 的左子树中产生
             2         （5）4.left = null ，则返回 null，4 将 null 返回给3
            /
           1
            【规律】1、遇到不符合 范围 的结点，应该根据二叉树搜索规律，从【被修建】过的左子树或者右子树中找到根节点 填补位置
                     不能直接选则 左右孩子，比如：0 不符合要求，那么不能选择 0 的右孩子 2 充当新的根，因为右子树中可能存在不符合的结点
                   2、构建二叉树：是下层向上层 return 逐步构建的

 */
public class TrimBST {
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

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null)
            return null;
        // root 的左一定全部不符合要求，只能从被修建过的右子树找到新的根节点替换这个位置
        if (root.val < low){
            TreeNode right = trimBST(root.right, low, high);
            return right;
        }
        // root 的右一定全部不符合要求，只能从被修建过的左子树找到新的根节点替换这个位置
        if (root.val > high){
            TreeNode left = trimBST(root.left, low, high);
            return left;
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
