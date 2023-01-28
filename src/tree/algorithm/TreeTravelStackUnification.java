package tree.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
    【前中后序 栈迭代遍历统一写法】

    （1）使用栈结构，那么针对前序遍历需要按照 右左根入栈
                  中序遍历需要按照 右根左入栈
                  后续遍历需要按照 根右左入栈
    （2）标记访问结点：也就是标记在某种遍历顺序下，应该被访问的结点
                    注意：在某种遍历顺序下应该被访问的结点，不一定是遍历指针正在指向的结点
                    标记方式是在被访问结点后加 null
 */
public class TreeTravelStackUnification {
    class TreeNode{
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

    public List<Integer> preOrder(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root == null)
            return result;

        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            stack.pop();
            if (node != null){                    //未遇到标记点，入栈
                if (node.right != null)            // 放右
                    stack.push(node.right);

                if (node.left != null)             // 放左
                    stack.push(node.left);

                stack.push(node);                  // 放被访问结点，并标记
                stack.push(null);
            }else {                                 // 遇到标记点，访问元素
                TreeNode pop = stack.pop();
                result.add(pop.val);
            }
        }

        return result;
    }

    public List<Integer> inOrder(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root == null)
            return result;

        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            stack.pop();
            if (node != null){                    //未遇到标记点，入栈
                if (node.right != null)            // 放右
                    stack.push(node.right);

                stack.push(node);                  // 放被访问结点，并标记
                stack.push(null);

                if (node.left != null)             // 放左
                    stack.push(node.left);
            }else {                                 // 遇到标记点，访问元素
                TreeNode pop = stack.pop();
                result.add(pop.val);
            }
        }

        return result;
    }

    public List<Integer> postOrder(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root == null)
            return result;

        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            stack.pop();
            if (node != null){                    //未遇到标记点，入栈
                stack.push(node);                  // 放被访问结点，并标记
                stack.push(null);

                if (node.right != null)            // 放右
                    stack.push(node.right);

                if (node.left != null)             // 放左
                    stack.push(node.left);
            }else {                                 // 遇到标记点，访问元素
                TreeNode pop = stack.pop();
                result.add(pop.val);
            }
        }

        return result;
    }

}
