package dynamic.algorithm.baseproblem;
/*
    【746 使用最小花费爬楼梯】给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，
                          即可选择向上爬一个或者两个台阶。
                          你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
                          请你计算并返回达到楼梯顶部的最低花费。
    【示例 1】
            输入：cost = [10,15,20]
            输出：15
            解释：你将从下标为 1 的台阶开始。
            - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
            总花费为 15 。

    【示例 2】
            输入：cost = [1,100,1,1,1,100,1,1,100,1]
            输出：6
            解释：你将从下标为 0 的台阶开始。
            - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
            - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
            - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
            - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
            - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
            - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
            总花费为 6 。
      =========================================================================
      【解决方案】动态规划：
                1、明确哪个位置代表楼梯顶：nums.length
                2、明确 dp[i] 含义：代表到达第 i 阶 需要的最小花费
                3、如果认为第 1 步【需要】花费，那么到达楼顶的最后一步是【不需要】花费的
                  （1）递推公式：dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
                  （2）初始条件： dp[0] = cost[0];
                                dp[1] = cost[1];
                  （3）达到楼顶的最小花费 = Math.min(dp[cost.length - 2], dp[cost.length - 1])

                  如果认为第 1 步【不需要】花费，那么到达楼顶的最后一步是【需要】花费的
                  （1）递推公式：dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]) ;
                  （2）初始条件：dp[0] = 0;
                               dp[1] = 0;
 */

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        // 步骤1：初始化dp数组：dp数组表示到达第i阶的最低花费
        // 假设第一步是需要花费的， 走到楼梯顶的那一步是不需要花费的
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        // 遍历dp数组
        for(int i = 2; i < cost.length; i++){
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length - 2], dp[cost.length - 1]);
    }

    public int minCostClimbingStairs1(int[] cost) {
        // 步骤1：初始化dp数组：dp数组表示到达第i阶的最低花费
        // 假设第一步是【不需要】花费的， 走到楼梯顶 cost.length 的那一步是需要花费的
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        // 步骤2：遍历dp数组 根据递推公式
        for(int i = 2; i <= cost.length; i++){
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]) ;
        }
        return dp[cost.length];
    }
}
