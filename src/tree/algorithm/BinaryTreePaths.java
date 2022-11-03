package tree.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*
    【257 二叉树的所有路径】给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
                        叶子节点 是指没有子节点的节点。
    【示例 1】
            输入：root = [1,2,3,null,5]
            输出：["1->2->5","1->3"]
    【示例 2】
            输入：root = [1]
            输出：["1"]
    ===============================================================================
    【解决方案】
        1、确定遍历方式： 前序遍历，因为前序遍历从根一直走到叶子结点
        2、递归终止条件：（1） if(node == null){    //代表走到叶子结点【下一层】NULL结点结束
                                收集结果；
                                return；
                            }
                              执行单层递归逻辑；   //这层逻辑其实并没有被 null层结点执行到

                                   1
                                2      3
                            null        null  ---> 走到此处结束 此时null以上的所有层都被收集了结果

                      （2）    执行单层递归逻辑；   // 只能写在这个位置，如果写在叶子结点递归终止条件后，会导致叶子结点收集不到
                            if(root.left == null && root.right == null){      代表走到【叶子结点】结束
                                收集结果；
                                return；
                            }
        3、回溯过程：path 通过前序遍历游历整棵二叉树，左移树收集完后，退回父节点时，记录的路径应该回退到root 到 父节点的路径
                   在此基础上去遍历 右子树

           backTracking(TreeNode root){                           backTracking(TreeNode root){
               path.push(node);                                         if（左子树不为空）{
                                                                            path.push(node);
               if（左子树不为空）{                                             backTracking(root.left);
                  backTracking(root.left);        等价于                     path.pop;
                  path.pop;                   <============>            }
               }                             左侧相当于把左右子树都需要的共性操作提取出来了
               if（右子树不为空）{                                          if（右子树不为空）{
                                                                            path.push(node);
                  backTracking(root.right);                                 backTracking(root.right);
                  path.pop;                                                 path.pop;
               }                                                         }
           }                                                      }

 */
public class BinaryTreePaths {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

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

    List<String> result = new ArrayList<>();
    // 注意：这里不要 选择StringBuilder，因为如果树中结点的值是两位数，例如：12  在添加符号的时候，会变成 1->2
    LinkedList<Integer> path = new LinkedList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null)
            return result;
        savePath(root);
        return result;
    }

    public void savePath(TreeNode root) {
        // 为什么要在这里收获结果，因为在遍历到叶子结点时，需要先记录到叶子结点的路径，再收获结果
        path.offerLast(root.val);
        // 遍历到叶子结点结束
        // 终止条件不能是 root == null，因为如果 根节点 只有右子树，左子树为空，遇到空就返回，那右子树遍历结果就丢失了
        if (root.left == null && root.right == null) {
            result.add(formatPath(path));
            return;
        }
        // 如果path记录路径写在这里，则叶子结点就收集不到了，因为已经被return了
        if(root.left != null){
            savePath(root.left);
            // 回溯过程
            path.pollLast();
        }
        if(root.right != null){
            savePath(root.right);
            // 回溯过程
            path.pollLast();
        }
    }

    // 处理箭头问题
    public String formatPath(LinkedList<Integer> path){
        String formatPath = "";
        for (int i = 0; i < path.size() - 1; i++) {
            formatPath = formatPath + path.get(i) + "->";
        }
        formatPath = formatPath + path.get(path.size() - 1);
        return formatPath;
    }
}
