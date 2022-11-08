package tree.algorithm;
/*
    【701 二叉搜索树中的插入操作】给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。
                             返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
                             注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
    【示例 1】
            输入：root = [4,2,7,1,3], val = 5
            输出：[4,2,7,1,3,5]
    【示例 2】
            输入：root = [40,20,60,10,30,50,70], val = 25
            输出：[40,20,60,10,30,50,70,null,null,25]
    【示例 3】
            输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
            输出：[4,2,7,1,3,5]
    ===========================================================================================================
    【解题思路】1、确定 新结点 插入位置：针对二叉搜索树，只要满足中序遍历是递增序列，插入到哪里都可以，但是简单的想法，是插在叶子结点位置
              2、确定遍历顺序：利用二叉搜索树搜索特性，选择中序遍历

              4                  4       5>4，向4的右子树搜索                                4
           2      7    ==>   2       7   7>4, 向7的左子树搜索                    ==>     2       7
        1    3             1    3  5     7的左子树为空，走到叶子结点，找到插入位置         1    3   5   将5返回给7的左子树，连接5和7
 */
public class InsertIntoBST {
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
    // 递归法
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 终止条件，找到了插入位置
        if (root == null){
            TreeNode treeNode = new TreeNode(val);
            return treeNode;
        }
        // 未找到插入位置，向左搜索，连接插入结点和根结点
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        // 未找到插入位置，向右搜索，连接插入结点和根结点
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        return root;
    }
    // 迭代法
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        TreeNode treeNode = new TreeNode(val);
        if(root == null)
            root = treeNode;
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null){
            if(cur.val > val){
                pre = cur;
                cur = cur.left;
            }else{
                pre = cur;
                cur = cur.right;
            }
        }
        if (pre.val > val)
            pre.left = treeNode;
        if (pre.val < val)
            pre.right = treeNode;
        return root;
    }
}
