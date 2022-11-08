package tree.algorithm;
/*
    【617 合并二叉树】给你两棵二叉树： root1 和 root2 。想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
                   你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；
                   否则，不为 null 的节点将直接作为新二叉树的节点。
                   返回合并后的二叉树。
                   注意: 合并过程必须从两个树的根节点开始。
    【示例 1】              1                   2                                  3
                       3      2            1      3         ==》              4       5
                     5                        4      7                     5    4         7
            输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
            输出：[3,4,5,5,4,null,7]
    【示例 2】
            输入：root1 = [1], root2 = [1,2]
            输出：[2,2]
     ================================================================================================================
     【解题思路】1、确定遍历顺序：（1）需要同时同步的遍历两棵二叉树，因此需要两个变量，从两棵树的根节点开始同步的遍历两个树
                             （2）需要构建二叉树：可以选择在第一个二叉树上左修改，也可以重新new结点，构建新的二叉树，所以选择先序遍历
               2、确定单层递归逻辑：计算两个树同步遍历到的结点的值的和
 */
public class MergeTrees {
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
    // 直接在第一个树root1上修改
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 如果root1遍历的结点为空，则返回root2遍历的结点
        if (root1 == null)
            return root2;
        // 如果root2遍历的结点为空，则返回root1遍历的结点
        if (root2 == null)
            return root1;
        // 如果两棵树同步遍历的当前结点不为 null，进入单层递归逻辑
        root1.val = root1.val + root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
}
