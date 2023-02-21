package greed.algorithm;

/*
    【968 监控二叉树】给定一个二叉树，我们在树的节点上安装摄像头。
                   节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
                   计算监控树的所有节点所需的最小摄像头数量。
    【示例 1】
            输入：[0,0,null,0,0]

                        0
                     0       ----在这个位置安装一个摄像头
                  0    0

            输出：1
            解释：如图所示，一台摄像头足以监控所有节点。
    【示例 2】
            输入：[0,0,null,0,null,0,null,null,0]
                        0
                     0   ------- 在这个位置安装一个摄像头
                  0
               0  ------- 在这个位置安装一个摄像头
                  0
            输出：2
            解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
     ===============================================================================
    【解题思路】
            1、如何贪心的放置摄像头？
               叶子结点个数相对于根节点呈现指数级增长，所以尽可能保证叶子结点被监控到，因此遍历顺序从下往上

                        父节点 有覆盖
                   父节点  加摄像头
               叶子   无覆盖
            空结点  有覆盖

            【注意】 null 结点需要设置成有覆盖，这样才能保证叶子结点的父节点安装摄像头
                   才能保证距离叶子结点更近的结点安装上摄像头，这样才能保证总体的摄像头安装数量最少

            2、结点状态： 0 ： 无覆盖
                        1 ： 有摄像头
                        2 ： 有覆盖

            3、从低向上更新 结点状态【依次考虑孩子结点存在 0 1 2 状态时，父节点的状态】
            （1）左右都有覆盖，父节点更新状态是无覆盖
                    0
                  2   2
            （2）左右至少有一个无覆盖，父节点需要安装摄像头
                    1
                  0   x
            （3）左右至少有一个有摄像头，父节点更新状态是有覆盖
                    2
                  1   x
            （4）根结点是无覆盖状态，需要安装摄像头

                         父节点  无覆盖         此节点为根节点，但是按照更新规则，根结点会变成无覆盖
                       父节点 有覆盖            所以根结点需要最后从无覆盖更新成有摄像头的状态
                   父节点  加摄像头
               叶子   无覆盖
            空结点  有覆盖

            【注意】针对根结点的特殊处理，写在主函数中，当从底向上遍历完后，
                  根节点的状态被赋值成 0，此时需要更新根结点状态为 1，并安装摄像头
 */
public class MinCameraCover {
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
    int result = 0;
    public int minCameraCover(TreeNode root) {
        if (root == null)
            return result;


        int rootState = changeState(root);
        if (rootState == 0)
            result++;
        return result;
    }

    public int changeState(TreeNode root) {
        if (root == null)
            return 2;

        // 后序遍历
        int left = changeState(root.left);
        int right = changeState(root.right);

        if (left == 2 && right == 2)
            return 0;
        if (left == 0 || right == 0) {
            result++;
            return 1;
        }
        if (left == 1 || right == 1){
            return 2;
        }
        return -1;
    }
}
