package tree.algorithm;

import exercise.Test;

public class CreateTree {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(){}

        TreeNode(int val){
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        Integer[] nums = {1,2,3,null,4,5,null};
        TreeNode tree = createTree(nums, 0);
        pre(tree);
    }

    public static TreeNode createTree(Integer[] nums, int index) {
        if(index > nums.length - 1 || nums[index] == null)
            return null;

        TreeNode root = new TreeNode(nums[index].intValue());

        root.left = createTree(nums, 2 * index + 1);
        root.right = createTree(nums, 2 * index + 2);

        return root;
    }

    public static void pre(TreeNode root){
        if (root == null)
            return;

        pre(root.left);
        System.out.println(root.val);
        pre(root.right);
    }
}
