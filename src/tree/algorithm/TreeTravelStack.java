package tree.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
    【非递归前序遍历二叉树】：二叉树的递归遍历本质也是在调用系统栈
                                    1
                                 2     3                  前序遍历结果：1 2 4 5 3
                              4    5
                         ------------------------------
                         | 1                              根结点入栈  result = [1]
                         ------------------------------
                         | 3 2                            根节点【出栈】，右孩子入栈，左孩子入栈（前序根左右，栈后入先出）
                         ------------------------------
                         | 3 5 4                          左孩子【出栈】，左孩子的右孩子入栈，左孩子的左孩子入栈（左孩子相当于新的根）
                         ------------------------------   result = [1 2]
                         | 3 5                            根节点【出栈】, 左右孩子都是空，无入栈操作  result = [1 2 4]
                         ------------------------------
                         | 3                              根节点【出栈】, 左右孩子都是空，无入栈操作  result = [1 2 4 5]
                         ------------------------------
                         |                                根节点【出栈】, 左右孩子都是空，无入栈操作  result = [1 2 4 5 3]
                         ------------------------------
    【非递归后序遍历二叉树】：相当于在【非递归前序遍历二叉树】基础上，
                       1、将左右子孩子入栈顺序调换，从 根左右 变成 根右左
                       2、将得到的遍历顺序整体反转，得到 左右根

    【非递归中序遍历二叉树】：问题：访问结点的顺序和处理结点的顺序不一致，增加指针遍历结点，遍历到特定位置，执行处理操作（如收集元素值）
                                    1
                                 2     3                  中序遍历结果：4 2 5 1 3   增加遍历指针，cur
                              4    5
                         ------------------------------
                         | 1 2 4                          cur一直遍历左子树，将访问到的结点入栈，直到访问到null，处理结点
                         ------------------------------
                         | 1 2                            cur指向 4 的左孩子，为空，则 4 出栈，cur指向4 result = [4]
                         ------------------------------
                         | 1                              遍历 4 的右孩子，为空，2出栈，cur指向2 result = [4 2]
                         ------------------------------
                         | 1 5                            遍历 2 的右孩子，入栈，cur指向5
                         ------------------------------
                         | 1                              5的左孩子为空，5出栈，result = [4 2 5]
                         ------------------------------
                         |                                5的右孩子为空，则1出栈 cur指向 1 ，result = [4 2 5 1]
                         ------------------------------
                         |3                               1的有孩子为3，入栈，3的左右孩子都为空，则出栈 result = [4 2 5 1 3]
                         ------------------------------
         1、入栈操作：（1）只要左孩子【不为空】，访问的结点就不是要操作的结点，需要一直入栈
                    （2）若有孩子不为空，继续遍历有孩子，把右孩子的左子树继续访问入栈
         2、出栈操作：（1）只要左孩子为空，就要【出栈 + 更新cur + 处理结点】
                    （2）只要右孩子为空，就出栈，此时出栈的是其父结点【出栈 + 更新cur + 处理结点】
         3、栈空不是循环的结束条件，只有当栈为空且cur也为空才结束循环
 */

public class TreeTravelStack {
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

    // 前序遍历非递归
    public List<Integer> preorderTraversal(TreeNode root) {
        // 步骤1：初始化栈，和结果收集容器
        LinkedList<TreeNode> stack = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        // 步骤2：根节点入栈
        stack.offerLast(root);
        // 步骤3：在栈不空的情况下，重复执行出栈，入栈操作
        while (!stack.isEmpty()) {
            // 根节点出栈
            TreeNode treeNode = stack.pollLast();
            // 左右孩子入栈
            // 注意：入栈的时候，只要根节点存在，他的左右孩子就会入栈，即使孩子为null，所以出栈做非空判断，如果为空就跳过循环
            if (treeNode != null) {
                // 收集结果
                result.add(treeNode.val);
                // 栈先入后出，为了保证前序根左右的顺序，将右孩子先入栈
                stack.offerLast(treeNode.right);
                stack.offerLast(treeNode.left);
            } else
                continue;
        }
        return result;
    }

    // 后序遍历非递归
    public List<Integer> postorderTraversal(TreeNode root) {
        // 步骤1：初始化栈，和结果收集容器
        LinkedList<TreeNode> stack = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        // 步骤2：根节点入栈
        stack.offerLast(root);
        // 步骤3：在栈不空的情况下，重复执行出栈，入栈操作
        while (!stack.isEmpty()) {
            // 根节点出栈
            TreeNode treeNode = stack.pollLast();
            // 左右孩子入栈
            // 注意：入栈的时候，只要根节点存在，他的左右孩子就会入栈，即使孩子为null，所以出栈做非空判断，如果为空就跳过循环
            if (treeNode != null) {
                // 收集结果
                result.add(treeNode.val);
                // 调换顺序 根左右 -- > 根右左
                stack.offerLast(treeNode.left);
                stack.offerLast(treeNode.right);
            } else
                continue;
        }
        // 对结果进行反转，变成 根右左 --> 左右根
        Collections.reverse(result);
        return result;
    }

    // 中序遍历非递归
    public List<Integer> inorderTraversal(TreeNode root) {
        // 步骤1：初始化栈，和结果收集容器、遍历指针
        LinkedList<TreeNode> stack = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        if (root == null)
            return result;
        // 步骤2：确定循环条件，终止条件是stack.isEmpty() &&  cur == null
        while(!stack.isEmpty() || cur != null){
            // 步骤3：确定入栈逻辑
            if(cur != null){
                stack.offerLast(cur);
                cur = cur.left;
            }else{
                // 步骤4：确定出栈逻辑
                cur = stack.pollLast();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return  result;
    }
}


