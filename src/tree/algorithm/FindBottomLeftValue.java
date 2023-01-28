package tree.algorithm;
/*
    【513 找树左下角的值】给定一个二叉树的 根节点 root，请找出该二叉树的 【最底层】 【最左边】 节点的值。
                      假设二叉树中至少有一个节点。
    【示例 1】
            输入: root = [2,1,3]
            输出: 1
    【示例 2】
            输入: [1,2,3,4,null,5,6,null,null,7]
            输出: 7
     ====================================================================================
     【解题思路】：1、明确 【最底层】，最【左边】的结点   <===>  在遍历过程中，变量更新深度最深的【叶子结点值】
                           1
                         2   3    最底层最左边的结点是左孩子
                           1
                              2   最底层最左边的结点是右孩子
                2、遍历顺序：为什么最深的【叶子结点值】就是最左边的呢？
                （1）如果多个叶子结点都在最低层，但是无论前中后序，其实都是先遍历左，后遍历右，
                    因此遍历过程中，变量先存储的是最左侧叶子结点，即使后来右侧结点和它同深度，但是不比他大，变量也不会更新。
                （2）跟深度相关，最好使用前序遍历：但是需要注意根节点，深度的回溯过程
                （3）层序遍历：在层序遍历过程中，我们记录了每一层的叶子结点值，最后一层第一个元素就是所求的值

 if(root.left != null){                                              if(root.left != null){
        depth ++;
        computeLeftSum(root.left, depth,result,maxDepth);  【等价于】        computeLeftSum(root.left, depth + 1,result,maxDepth);
        depth --;
 }                                                                   }
    由于在本层中depth++，改变了depth的值，比如：depth= 1                   这种写法将加操作写在形参位置，本层depth值并没有改变
    那么到右子树递归时，如果不做回溯 depth --，会导致                        隐藏了回溯的过程
    computeReftSum(root.left, depth,result,maxDepth)变成
    computeReftSum(root.left, 2,result,maxDepth)
    但是本层右孩子根节点和做还在是一层的，应为depth= 1，所以在递归下需要回溯
 */
public class FindBottomLeftValue {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 坑：将 maxDepth 和 result 定义在形参上，同时操作写在了终止条件内，
    // 同时本层又没有对 maxDepth 和 result 做计算更新操作，导致，递归每一层 maxDepth 和 result 永远都是初始值
//    public int findBottomLeftValue(TreeNode root) {
//        // 二叉树至少有一个结点，也可以不判断
//        if(root == null)
//            return 0;
//        // 根结点深度是1
//        int maxDepth = 1;
//        int depth = 1;
//        // 用于记录深度最深的叶子结点的值
//        int result = 0;
//        computeLeftSum(root, depth, result,maxDepth);
//        return result;
//    }
//
//    public  void  computeLeftSum(TreeNode root, int depth, int result,int maxDepth) {
//        // 终止条件：遍历到叶子结点
//        if(root.left == null && root.right == null){
//            if(maxDepth < depth){
//                maxDepth = depth;
//                result = root.val;
//            }
//            return;
//        }
//        // 这部分根前序遍历求最大深度是一样的
//        if(root.left != null){
//            depth ++;
//            computeLeftSum(root.left, depth,result,maxDepth);
//            depth --;
//        }
//        if(root.right != null){
//            depth ++;
//            computeLeftSum(root.right, depth,result,maxDepth);
//            depth --;
//        }
//    }


    // 这两个变量在遍历整棵树的过程中都在变化，但是更新操作发生在终止条件内，没发生在单层递归中
    // 只能声明成成员变量，否则，每一层递归，这两个值都不会改变，永远为初始值
    Integer result = 0;
    Integer maxDepth = 1;
    public int findBottomLeftValue(TreeNode root) {
        // 二叉树至少有一个结点，也可以不判断
        if(root == null)
            return 0;
        // 根结点深度是1
        result = root.val;
        int depth = 1;
        // 用于记录深度最深的叶子结点的值
        computeLeftSum(root, depth);
        return result;
    }

    public  void computeLeftSum(TreeNode root, int depth) {
        // 终止条件：遍历到叶子结点
        if(root.left == null && root.right == null){
            if(maxDepth < depth){
                maxDepth = depth;
                result = root.val;
            }
            maxDepth = Integer.valueOf(maxDepth) > depth ? Integer.valueOf(maxDepth) : depth;
            return;
        }
        // 这部分根前序遍历求最大深度是一样的
        if(root.left != null){
            depth ++;
            computeLeftSum(root.left, depth);
            depth --;
        }
        if(root.right != null){
            depth ++;
            computeLeftSum(root.right, depth);
            depth --;
        }
    }

}
