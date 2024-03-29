package dynamic.algorithm.shares;
/*
    【121 买卖股票的最佳时机】给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
                          你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
                          返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    【示例 1】
            输入：[7,1,5,3,6,4]
            输出：5
            解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
                 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
    【示例 2】
            输入：prices = [7,6,4,3,1]
            输出：0
            解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     ===================================================================================================================
     【解题思路】一只股票只能买卖一次
            1、一只股票具有两种状态，持有或者不持有，因此定义二维数组 dp[i][0] dp[i][1]
              （1）dp[i][0]：表示第 i 天持有股票，注意 持有 不等于 买入，第 i 天持有可以是第 i 天买入的，也可以是第 i 天前已经买入
              （2）dp[i][1]：表示第 i 天不持有股票，注意 不持有 不等于 卖出，第 i 天不持有可以是第 i 天卖出的，也可以是第 i 天前已经卖出
            2、递推公式：
              （1）如何推出 dp[i][0]？
                  如果是第 i 天买入，则 dp[i][0] = - price[i]
                  如果是第 i 天前已经买入，但是第 i 天是持有的状态，则 dp[i][0] = dp[i - 1][0]
                  因此， dp[i][0] = max(-price[i], dp[i - 1][0])
              （2）如何推出 dp[i][1]?
                  如果是第 i 天卖出，则 dp[i][1] = dp[i - 1][0] + price[i]
                  如果是第 i 天前已经卖出，但是第 i 天是不持有状态，则 dp[i][1] = dp[i - 1][1]
                  因此，dp[i][1] = max(dp[i - 1][0] + price[i], dp[i - 1][1])
            3、初始化：
              （1）dp[0][0] : 第 0 天持有股票的最大值为 -price[0]
              （2）dp[0][1] : 第 0 天卖出股票的最大值为 0，因为还没买入，没什么可抛的
 */
public class MaxProfit1 {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = - prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(-prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        // 只有卖出股票才能获得收益
        return dp[prices.length -1][1];
    }
}
