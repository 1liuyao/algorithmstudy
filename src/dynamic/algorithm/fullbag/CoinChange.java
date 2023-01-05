package dynamic.algorithm.fullbag;

import java.util.Arrays;
import java.util.OptionalInt;

/*
    【322 零钱兑换】给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
                  计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
                  你可以认为每种硬币的数量是无限的。
    【示例 1】
            输入：coins = [1, 2, 5], amount = 11
            输出：3
            解释：11 = 5 + 5 + 1
    【示例 2】
            输入：coins = [2], amount = 3
            输出：-1
    【示例 3】
            输入：coins = [1], amount = 0
            输出：0
     =========================================================================================
     【解题思路】
              1、是 01 背包，还是完全背包？一个物品可以被放入背包多次，是完全背包问题
              2、是组合问题还是排列问题？既可以是组合也可以是排列，因为无论什么方式我们要求的是最少物品数量
              3、所求：装满背包最少能放多少物品
              4、dp[j]：表示装满背包容量为 j 的背包，物品数量最少为 dp[j] 个
              5、递推公式：dp[j] = min(dp[j], dp[j - coins[i]] + 1)
              6、初始化：（1）dp[0] = 0
                       （2）由于求最小值，所以非 0 下表初始化为 int 最大值
              7、遍历顺序：先遍历哪一个都行
              8、打印 dp 数组
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        // 判断 dp 合法性
        if (amount == 0)
            return 0;
        if (Arrays.stream(coins).min().getAsInt() > amount)
            return -1;
        // dp[j]：表示装满背包容量为 j 的背包，物品数量最少为 dp[j] 个
        int[] dp = new int[amount + 1];
        // 初始化
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // 遍历
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // 如果将 coins[i] 放入背包，dp[j - coins[i]] 值为 MAX，说明不需要迭代，MAX 加 1会为负数
                if (dp[j - coins[i]] != Integer.MAX_VALUE )
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        if (dp[amount] == Integer.MAX_VALUE)
            return -1;
        return dp[amount];
    }
}
