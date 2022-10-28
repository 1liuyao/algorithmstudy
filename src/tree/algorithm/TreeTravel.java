package tree.algorithm;

import java.util.ArrayList;
import java.util.List;

/*
    【144 145 94 二叉树的前序、中序、后序遍历】
    【递归遍历】：1、确定递归函数的参数和返回值
               2、确定递归停止条件
               3、确定单层递归逻辑
 */
public class TreeTravel {

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


    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }
    public void preorder(TreeNode root, ArrayList<Integer> result){
        if (root == null){
            return;
        }else {
            result.add(root.val);
            preorder(root.left, result);
            preorder(root.right, result);
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    public void postorder(TreeNode root, ArrayList<Integer> result){
        if (root == null){
            return;
        }else {
            postorder(root.left, result);
            postorder(root.right, result);
            result.add(root.val);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    public void inorder(TreeNode root, ArrayList<Integer> result) {
        if (root == null){
            return;
        }else {
            inorder(root.left, result);
            result.add(root.val);
            inorder(root.right, result);
        }
    }
}
