package dynamic.algorithm.rob;
/*
    【337 打家劫舍 III】小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
                      除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
                      聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
                      如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
                      给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
    【例 1】
                           3
                       2       3
                          3       1

            输入: root = [3,2,3,null,3,null,1]
            输出: 7
            解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
    【例 2】
                           3
                        4     5
                     1     3     1

            输入: root = [3,4,5,1,3,null,1]
            输出: 9
            解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
    ==================================================================================
    【解题思路】树形 dp + 状态转移
             1、定义 dp 数组：（1）dp[0]：表示不偷结点
                            （2）dp[1]: 表示偷结点
                            注意：每个结点都具有两种状态，即偷，或者不偷，在遍历的过程中

                             3  dp{3+3,3+3+1}
              dp{3,2+0} 2         3 dp{1,3+0}
                   dp{0,3} 3        1  dp{0,1}

             2、递推公式：（1）如果不偷 当前遍历 的结点： dp[0] = 当前结点左孩子 dp 数组最大值 + 当前数组右孩子 dp 数组最大值
                            注意：不偷当前结点，不代表当前结点的左右孩子一定会被偷，左右孩子是否被偷取决于左右孩子结点 dp 数组中，
                            dp[0]和dp[1]哪个更大
                        （2）如果 偷 当前遍历结点：dp[1] = 当前结点.val + 左孩子不偷dp[0] + 右孩子不偷[1]
             3、初始化：当遍历到 null 结点：dp[0] = 0, dp[1] = 1
             4、遍历顺序：后序遍历树，因为当前结点 dp 数组值 需要通过 左右孩子值 计算
             5、打印dp数组
 */
public class rob3 {
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
    public int rob(TreeNode root) {
        int[] dp = new int[2];
        dp = robTree(root);
        // 后序遍历 最值 出现在根节点
        return Math.max(dp[0], dp[1]);
    }

    private int[] robTree(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};
        int[] leftDP = robTree(root.left);
        int[] rightDP = robTree(root.right);
        // 不偷当前结点
        int dp0 = Math.max(leftDP[0], leftDP[1]) + Math.max(rightDP[0], rightDP[1]);
        // 偷当前结点
        int dp1 = root.val + leftDP[0] + rightDP[0];
        return new int[]{dp0, dp1};
    }
}
