package tree.algorithm;
/*
    【222 完全二叉树的节点个数】给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
                           完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
                           并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2的h次方 个节点。

    【示例 1】
            输入：root = [1,2,3,4,5,6]
            输出：6
    【示例 2】
            输入：root = []
            输出：0
    【示例 3】
            输入：root = [1]
            输出：1
    ==========================================================================================================
    【解决方案】：
          1、明确什么是完全二叉树
                                  1
                              2       3         【√】是完全二叉树
                           4     5  6

                                  1
                              2       3         【×】不是完全二叉树
                           4     5        6


         2、明确什么是满二叉树：
                                                    1
                                         ----------   -----------
            【满二叉树】                   |    2    |  |     3   |   【不是满二叉树】
          高度为2，结点个数为（2的2次方 -1）  | 4     5 |  | 6       |   注意：单结点是满二叉树，例如：叶子结点6，是满二叉树，
                                         ----------   -----------        高度为1，结点个树 = 2 的 1次方 - 1

        3、【普通遍历法】：（1）把完全二叉树当成一个普通的二叉树
                        （2）采用任何一种遍历方式，遍历到结点，个数就加1
                        （3）这种方式需要遍历树种所有结点

        4、【利用满二叉树特性统计结点个数】：
           （1）确定遍历顺序：当根节点知道左子树或者右子树的结点个数，那么 树中结点总数 = 左子树结点个数 + 右子树结点个数 + 1
           （2）那么如何求左右子树结点个数？
               如果左、右子树是满二叉树，统计树高，可以通过  2的树高次方 - 1 计算子树结点个数
               如果左、右子树不是满二叉树，则将树继续拆分，直到遇到满二叉树（叶子结点就是满二叉树）
                                              1
                                         2          3
                                      4     5    6     7
                                   7    8 9
           ① 根结点 1 的左子树 2 不是满二叉树，所以分别统计 2 的左子树、右子树结点个数          -----------------| return 6 + 3 + 1
           ② 2 的左子树 4 是满二叉树 结点个数 =  2 的 2 次方 - 1 = 3                    <---|  return 3 + 2 + 1
           ③ 2 的右子树 5 不是满二叉树，所以分别统计 5 的左子树、右子树结点个数              <---|  return 1 + 1
           ④ 5 的左子树 9 是叶子结点，是满二叉树，结点个数 =  2 的 1 次方 - 1 = 1   <---| return 1
           ⑤ 根节点 1 的右子树是满二叉树：结点个数 = 2 的 2 次方 - 1                        -----------------| return 3

           （3）如何判断树是满二叉树？
               因为题目中的树是完全二叉树，当 树的左外侧深度和树的右外侧深度一样，那就是满二叉树
                                             1
                    左侧深度 = 2         2         3      右侧深度 = 2
           （4）递归终止条件：① 如果树为空，则结点个数为0
                           ② 如果树的 左外侧深度 == 右外侧深度，则是满二叉树，直接根据满二叉树计算结点数公式，返回给根结点
           （5）此方法只遍历了树中部分结点

 */
public class CountNodes {
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

//    public int countNodes(TreeNode root) {
//        // 步骤1 ：确定递归终止条件
//        if (root == null)
//            return 0;
//        // 如果以 root 为根的左右子树是满二叉树，则直接根据 2的 深度 次方 - 1 计算
//        int leftLength = 0;
//        int rightLength = 0;
//        // 计算左外侧深度
//        while (root.left != null){    // 这种写法不可取，因为root一直在改变，等遍历到右的时候，根就已经变了
//            leftLength++;             // 必须先暂存根节点，然后声明左右外侧遍历指针，遍历
//        }
//        while (root.right != null){
//            rightLength++;
//        }
//        if (leftLength == rightLength)
//            return 2 << leftLength - 1;
//
//        int countLeftNodes =  countNodes(root.left);
//        int countRightNodes = countNodes(root.right);
//        int countRootNodes = countLeftNodes + countRightNodes + 1;
//        return countRootNodes;
//    }
    public int countNodes(TreeNode root) {
        // 步骤1 ：确定递归终止条件
        if (root == null)
            return 0;
        // 如果以 root 为根的左右子树是满二叉树，则直接根据 2的 深度 次方 - 1 计算
        int leftLength = 0;
        int rightLength = 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 计算左外侧深度
        while (left != null){
            leftLength++;
            left = left.left;
        }
        while (right != null){
            rightLength++;
            right=right.right;
        }
        if (leftLength == rightLength)
            return (2 << leftLength) - 1;

        int countLeftNodes =  countNodes(root.left);
        int countRightNodes = countNodes(root.right);
        int countRootNodes = countLeftNodes + countRightNodes + 1;
        return countRootNodes;
    }
}
