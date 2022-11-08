package tree.algorithm;

/*
    【235 二叉搜索树的最近公共祖先】给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
                                百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
                                满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
    【示例 1】
            输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
            输出: 6
            解释: 节点 2 和节点 8 的最近公共祖先是 6。
    【示例 2】
            输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
            输出: 2
            解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     ======================================================================================================
     【解题思路】充分利用二叉搜索树的特性，如果不是二叉搜索树我们需要遍历整棵树去寻找，
               但是基于二叉搜索树搜索时，只要 当前结点 处于 q 和 p 之间，则这个点就是最近的公共祖先
               如果 当前结点 > q 且 大于 q ，则目标结点一定在 当前结点的左子树
               如果 当前结点 < q 且 小于 q ，则目标结点一定在 当前结点的右子树

 */

public class LowestCommonAncestorSort {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    // 迭代法
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null){
            // 当前结点值大于当前值，则向左继续搜索
            if (root.val > p.val && root.val > q.val){
                root = root.left;
                // 当前结点值小于当前值，则向左继续搜索
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
                // 当前结点值 等于p或q，或者 p < 当前结点值 < q，或者 p > 当前结点值 > q 都是合法情况，最近公共祖先就是 当前结点
            } else
                return root;
        }
        return root;
    }

    // 递归法
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return root;
        // 向左搜索逻辑
        if (root.val > p.val && root.val > q.val){
            TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
            if (leftNode != null)
                return leftNode;
        }
        // 向右搜索逻辑
        if (root.val < p.val && root.val < q.val){
            TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
            if (rightNode != null)
                return rightNode;
        }
        return root;
    }
}
