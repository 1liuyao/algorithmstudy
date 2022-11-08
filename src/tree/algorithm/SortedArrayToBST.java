package tree.algorithm;
/*
    【108 将有序数组转换为二叉搜索树】给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
                                高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
    【示例 1】
            输入：nums = [-10,-3,0,5,9]
            输出：[0,-3,9,-10,null,5]
            解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
    【示例 2】
            输入：nums = [1,3]
            输出：[3,1]
            解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
     ====================================================================================================
     【解题思路】 1、如何保证平衡：每次取数组中的 中间结点
                2、如何定义 中间 ：明确是左闭右闭，还是左闭右开，奇偶情况的讨论
 */
public class SortedArrayToBST {
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

    public TreeNode sortedArrayToBST(int[] nums) {
        // 根据 左闭右闭 原则 初始化区间
        int left = 0;
        int right = nums.length - 1;
        TreeNode root = createTree(nums, left, right);
        return root;
    }

    public TreeNode createTree(int[] nums, int left, int right) {
        // 终止条件 ：左闭右闭区间
        // 当 left > right 是非法区间，此时不能 return nums[left], 因为在走到这个终止条件前，nums[left] 已经在 mid == left时求过了
        if (left > right)
            return null;
        // 为了保持平衡，选中间结点作为 root
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 递归构建二叉树
        root.left = createTree(nums, left, mid - 1);
        root.right = createTree(nums, mid + 1, right);

        return root;
    }
}
