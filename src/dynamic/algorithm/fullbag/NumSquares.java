package dynamic.algorithm.fullbag;

import java.util.Arrays;

/*
    【279 完全平方数】给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
                   完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
                   例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
    【示例 1】
            输入：n = 12
            输出：3
            解释：12 = 4 + 4 + 4
    【示例 2】
            输入：n = 13
            输出：2
            解释：13 = 4 + 9
    =====================================================================================
    【解题思路】完全背包问题
             （1）所求：装满背包容量为 n 的背包，最少使用的物品数为 dp[j] 个
             （2）物品的重量是 i * i
             （3）背包一定会被装满吗？会的，因为有 1 的存在
 */
public class NumSquares {
    public int numSquares(int n) {
        // dp[j]：表示装满背包容量为 j 的背包，物品数量最少为 dp[j] 个
        int[] dp = new int[n + 1];
        // 初始化
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // 遍历
        for (int i = 0; i <= Math.ceil(Math.sqrt(n)); i++) {
            for (int j = i * i; j <= n; j++) {
                // 如果将 coins[i] 放入背包，dp[j - coins[i]] 值为 MAX，说明不需要迭代，MAX 加 1会为负数
                if (dp[j - i * i] != Integer.MAX_VALUE )
                    dp[j] = Math.min(dp[j], dp[j - i*i] + 1);
            }
        }
        return dp[n];
    }
}
