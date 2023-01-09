package dynamic.algorithm.shares;
/*
    【309 最佳买卖股票时机含冷冻期】给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格。
                              设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
                              卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
                              注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    【示例 1】
            输入: prices = [1,2,3,0,2]
            输出: 3
            解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
    【示例 2】
            输入: prices = [1]
            输出: 0
    ==============================================================================================================
    【解题思路】一只股票可以买卖多次，但是有冷冻期
                     第   1    2     3     4      5     6     7  天
            【操作序列1】  买入 卖出 冷冻期 保持卖出  保持卖出 买入   卖出
                     第   1    2     3    4      5     6  天
            【操作序列2】  买入 卖出 冷冻期  保持卖出 买入   卖出
                            |-----|
            【状态转换图】     -持有<-|  ----------> 卖出
                               ^     ^             |
                               |       |           |
                               |          |        |
                               |             |     |
                               |                |  V
                            保持卖出<--------------冷冻期

            1、dp 数组定义：考虑股票的状态
              （1）dp[i][0]: 持有股票状态(买入 + )
              （2）dp[i][1]: 保持卖出股票的状态（两天前就卖出了股票，度过一天冷冻期。或者是前一天就是卖出股票状态，一直没操作）
              （3）dp[i][2]: 在第 i 天卖出股票
              （4）dp[i][3]: 处于冷冻期，但是冷冻期只有一天，即前一天一定是卖出状态
              注意：dp[i][1] 和 dp[i][2] 可以合并成一个状态，就是不持有股票的状态。
                   因为本题我们有冷冻期，而冷冻期的前一天，只能是 「今天卖出股票」状态，
                   如果是 「不持有股票状态」那么就很模糊，因为不一定是 卖出股票的操作。
            2、递推公式：
              （1）dp[i][0]: max(dp[i - 1][0], dp[i - 1][3] - prices[i], dp[i - 1][1] - prices[i])
                           【前一天是持有 + 前一天是卖出状态下买入了+前一天是冷冻期，过了冷冻期后买入】
              （2）dp[i][1]: max(dp[i - 1][1], dp[i - 1][3])【前一天是卖出，或者前一天是冷冻期】
              （3）dp[i][2]: dp[i - 1][0] + prices[i]【卖出前一定是持有状态】
              （4）dp[i][3]: dp[i - 1][2]【冷冻期前一天一定是卖出了股票】
            3、初始化：
              （1）dp[0][0]: 第 0 天买入，= - prices
              （2）dp[0][1]: 第 0 天处于卖出状态，只能是当天买当天卖， = 0
              （3）dp[0][2]: 第 0 天处于卖出股票，只能是当天卖当天卖， = 0
              （4）dp[0][3]: 第 0 天就处于冷冻期，是非法状态，=0
              注意：针对非法或者不具备含义的状态，需要通过对递推公式赋初始值，来完成初始化推导
            4、遍历顺序
              第 i 天的状态通过第 i - 1 天的状态决定，所以需要从前往后遍历
            5、打印 dp 数组
 */
public class MaxProfit5 {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][4];

        dp[0][0] = - prices[0];

        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j <= 3; j++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][3] - prices[i]);
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] - prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
                dp[i][2] = dp[i][0] + prices[i];
                dp[i][3] = dp[i - 1][2];
             }
        }

        return Math.max(Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]), dp[prices.length - 1][3]);
    }
}