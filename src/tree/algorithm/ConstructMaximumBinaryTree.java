package tree.algorithm;

import java.util.Arrays;
import java.util.HashMap;

/*
    【654 最大二叉树】 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
                    创建一个根节点，其值为 nums 中的最大值。
                    递归地在最大值 左边 的 子数组前缀上 构建左子树。
                    递归地在最大值 右边 的 子数组后缀上 构建右子树。
                    返回 nums 构建的 最大二叉树 。
    【示例 1】
            输入：nums = [3,2,1,6,0,5]
            输出：[6,3,5,null,2,0,null,null,1]
            解释：递归调用如下所示：
            - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
                - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
                    - 空数组，无子节点。
                    - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
                        - 空数组，无子节点。
                        - 只有一个元素，所以子节点是一个值为 1 的节点。
                - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
                    - 只有一个元素，所以子节点是一个值为 0 的节点。
                    - 空数组，无子节点。
    【示例 2】
            输入：nums = [3,2,1]
            输出：[3,null,2,null,1]
    ===============================================================================
    【解题思路】：解题思路和【中序、后序】构造二叉树的题目是一样的：【构建二叉树选择前序遍历】，只不过本题对构建结点的要求是最大
                    [3 2 1|6|0 5]           选取数组中最大值作为根结点                     6
                    /           \           根据最大值位置将数组拆分成左、右区间           /   \
                [3|2 1]        [0|5]        在左区间选取最大值，构建左孩子               3     5
                    \            /          在右区间选取最大值，构建右孩子                \   /
                    [2|1]     [0]           继续划分区间                               2  0
                        \                                                             \
                        [1]                                                            1

                1、明确递归终止条件：当左右区间长度为1，则为叶子结点
                2、明确本层递归操作：选最大值构建结点 + 切合左右区间 + 递归生成左右孩子
                3、进入递归前是否一定要进行判断？
                   取决于【终止条件】，本题在递归前加入【区间长度至少为1】的判断，因为在切割过程中，很有可能左右区间中的某一个区间长度为0
                   就像 5 的右区间长度为0，代表没有右孩子，此时应该剪枝不应该再进行递归了。
 */
public class ConstructMaximumBinaryTree {

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

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 前序遍历选择最大值构建结点
        int[] max = getMax(nums);
        TreeNode treeNode = new TreeNode(max[0]);
        // 递归终止条件
        if (nums.length == 1)
            return treeNode;
        // 以最大值为界限，切割左右子树序列区间
        int[] leftNums = Arrays.copyOfRange(nums, 0, max[1]);
        int[] rightNums = Arrays.copyOfRange(nums, max[1] + 1, nums.length);
        // 递归构建左右孩子结点
        // 注意：一定要左判断，否则遇到切割后左或右区间为空，仍会进入递归，进入后仍会选择最大值，此时，max = nums[0]会报数组越界
        if (leftNums.length > 0)
            treeNode.left = constructMaximumBinaryTree(leftNums);

        if (rightNums.length > 0)
            treeNode.right = constructMaximumBinaryTree(rightNums);
        return treeNode;
    }
    private int[] getMax(int[] nums) {
        int max = nums[0];
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max){
                max = nums[i];
                maxIndex = i;
            }
        }
        int[] maxResult = {max, maxIndex};
        return maxResult;
    }
}
