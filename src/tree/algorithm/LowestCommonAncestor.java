package tree.algorithm;
/*
    【236 二叉树的最近公共祖先】给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
                           百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
                           满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
                           提示：（1）树中节点数目在范围 [2, 105] 内。
                                （2）-109 <= Node.val <= 109
                                （3）所有 Node.val 互不相同 。
                                （4）p != q
                                （5）p 和 q 均存在于给定的二叉树中。
    【示例 1】                     3
                             5        1
                         6      2   0    8
                              7   4
            输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
            输出：3
            解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
    【示例 2】
            输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
            输出：5
            解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
    【示例 3】                 1
                          2
            输入：root = [1,2], p = 1, q = 2
            输出：1
    ==============================================================================================
    【解题思路】1、确定遍历顺序：后序遍历（如果 pq 存在于 root 的左右子树中，那么 root 就是最近公共祖先）
              2、随着后序遍历，逐步判断是否等于pq，如果存在pq，则返回给根节点，直到遍历到某一个结点时，既收集到了q，也收集到p，则遍历结束
                                    3
                       返回5      /     \     返回1
                               5         1
                       null  /   \ null /  \ null   结点 5 和 结点 1 下的子树返回的都是null
       p = 5, q = 1        6      2    0    8       遍历到 5，等于 p ，则向 3 返回 5
                                 / \                遍历到 1，等于 q ，则向 3 返回 1
                               7    4

 */
public class LowestCommonAncestor {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        //当前层判断
        if ( root.val == p.val || root.val == q.val)
            return root;
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);
        // 回溯到根节点时，已经获得了左右子树的查询结果，那么对结果进行判定，root结点现在已经不匹配pq，那么它要向上层返回什么
        if (leftResult != null && rightResult == null)
            return leftResult;
        if (leftResult == null && rightResult != null)
            return rightResult;
        if (leftResult == null && rightResult == null)
            return null;
        // q和q都找到，证明 root 就是最近公共祖先
        return root;
    }
}
