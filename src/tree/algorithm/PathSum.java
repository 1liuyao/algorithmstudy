package tree.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    【113 路径总和 II】给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
                     叶子节点 是指没有子节点的节点。
    【示例 1】                       5
                              4         8
                          11    null  13   4
                        7   2                 1
            输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
            输出：[[5,4,11,2],[5,8,4,5]]
    【示例 2】
            输入：root = [1,2,3], targetSum = 5
            输出：[]
    【示例 3】
            输入：root = [1,2], targetSum = 0
            输出：[]
     ==================================================================================================
     【解题思路】：1、递归结束：走到叶子节点 + 满足和条件
                2、每层递归执行操作：减去当前结点值
                3、需要一个变量 path 收集符合和条件的路径
                4、需要结果变量，保存path结果
                5、什么时候递归前要加判断：
                如果让空节点（空指针）进入递归，就不加if，如果不让空节点进入递归，就加if限制一下， 终止条件也会相应的调整。
                例如：（1）终止条件是走到 叶子结点，那么通常加 if 判断 left 或者 right 不为空才进入递归，当然也可以不加判断
                         只不过多了走了一层递归而已。
                     （2）终止条件是 结点为 null，所以进入递归不需要判断是否为null，因为 null 进入递归后，会走到终止条件停止
 */
public class PathSum {

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

    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root == null)
            return result;
        path.offerLast(root.val);
        targetSum = targetSum - root.val;
        pathSumBackTracking(root,targetSum);
        return result;
    }

    public void pathSumBackTracking(TreeNode root, int targetSum) {
        if(root.left == null && root.right == null){
            if (targetSum == 0)
                result.add(new LinkedList<>(path));
            return;
        }
        if (root.left != null){
            targetSum = targetSum - root.left.val;
            path.offerLast(root.left.val);
            pathSumBackTracking(root.left, targetSum);
            targetSum = targetSum + root.left.val;
            path.pollLast();
        }
        if (root.right != null){
            targetSum = targetSum - root.right.val;
            path.offerLast(root.right.val);
            pathSumBackTracking(root.right, targetSum);
            targetSum = targetSum + root.right.val;
            path.pollLast();
        }
    }
}
