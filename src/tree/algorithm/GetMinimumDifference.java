package tree.algorithm;
/*
    【530 二叉搜索树的最小绝对差】给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
                             差值是一个正数，其数值等于两值之差的绝对值。
                             提示：
                                    树中节点的数目范围是 [2, 104]
                                    0 <= Node.val <= 10的5次方
    【示例 1】                     4
                              2       6
                          1      3
            输入：root = [4,2,6,1,3]
            输出：1
    【示例 2】
            输入：root = [1,0,48,null,null,12,49]
            输出：1
     =============================================================================================
     【解题思路】双指针法
              1、二叉搜索树中任意两不同节点值之间的最小差值  《==》 中序序列相邻两个结点差值最小
              2、双指针：一个记录当前遍历结点
                       一个记录当前结点中序序列的前驱
 */
public class GetMinimumDifference {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    // 双指针法
    // 保存最小值
    int min = 100001;
    // 定义前驱指针
    TreeNode pre = null;
    public int getMinimumDifference(TreeNode root) {
        backTracking(root);
        return min;
    }

    private void backTracking(TreeNode cur) {
        if(cur == null)
            return;
        // 左
        backTracking(cur.left);
        // 中：取中序相邻结点最小值差值 + 更新前驱
        if(pre != null)
            min = Math.min(min, cur.val - pre.val);
        pre = cur;
        // 右
        backTracking(cur.right);
    }

    TreeNode pre1 = null;
    int result = 100000000;
    public int getMinimumDifference1(TreeNode root) {
        if (root == null)
            return result;
        // 遍历左
        int leftResult = getMinimumDifference1(root.left);
        // 遍历中：计算差值，更新最小插值
        if(pre1 != null)
            // 坑：如果加 return，那么根节点下的右子树就不会被遍历，结果只找到了左子树的最小值
            // return result = Math.min(result, root.val - pre1.val);
            result = Math.min(result, root.val - pre1.val);
        pre1 = root;
        // 遍历右
        int rightResult = getMinimumDifference1(root.right);
        return Math.min(leftResult, rightResult);
    }
}
