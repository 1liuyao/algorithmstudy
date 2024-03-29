package dynamic.algorithm.shares;
/*
    【188 买卖股票的最佳时机 IV】给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
                             设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
                             注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    【示例 1】
            输入：k = 2, prices = [2,4,1]
            输出：2
            解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2。
    【示例 2】
            输入：k = 2, prices = [3,2,6,5,0,3]
            输出：7
            解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4。
                 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3。
     ==============================================================================================================
     【解题思路】一只股票可以至多买卖 k 次
             1、至多可以卖两次
             （1）dp数组的定义:（1）dp[i][0]: 表示第 i 天无操作
                          （2）dp[i][1]: 表示第 i 天第一次持有股票
                          （3）dp[i][2]: 表示第 i 天第一次不持有股票
                          （4）dp[i][3]: 表示第 i 天第二次持有股票
                          （5）dp[i][4]: 表示第 i 天第二次不持有股票
                          （6）注意：第 i 天持有或者不持有并不代表第 i 天会卖出或者买入，还有可能第 i 天之前就已经进行了买入或卖出的操作
                                   第 i 天仅仅是维持了第 i - 1 天的状态
            （2）递推公式： （1）dp[i][0] = dp[i - 1][0]
                        （2）dp[i][1] = max(dp[i - 1][1], dp[i][0] - prices[i])
                        （3）dp[i][2] = max(dp[i - 1][2], dp[i][1] + prices[i])
                        （4）dp[i][3] = max(dp[i - 1][3], dp[i][2] - prices[i])
                        （5）dp[i][4] = max(dp[i - 1][4], dp[i][3] + prices[i])
                        （6）注意：我们可以发现这个状态转换过程中，后一个状态是在前一个状态的基础上做出改变
            （3）初始化：（1）dp[0][0]: 0
                     （2）dp[0][1]: 第 0 天 第一次持有，即利润为：- prices[0]
                     （3）dp[0][2]: 第 0 天 买了又卖了，即利润为： 0
                     （4）dp[0][3]: 第 0 天 买了又卖了，又买了，即利润为：- prices[0]
                     （5）dp[0][4]: 第 0 天 买了又卖了，买了又卖了，即利润为： 0
            （4）遍历顺序：第 i 天的利润状态依赖于 第 i - 1 天的利润状态
            （5）打印 dp 数组
            （6）所有目标：利润最大一定在卖出时产生，所以可以比较第一次不持有和第二次不持有的最大值，但是其实第二次已经包含了第一次卖出的最大值
                       如果真的是在第一次不持有中产生的最大值，那么第二次也只是当天买当天卖，即包含了第一次的最值
           2、至多可以卖 k 次：只是股票的状态变成了 2k + 1，其中 dp[i][0] 站一次，至多卖 k 次，则具备 2k 个状态
           （1）dp数组的定义:（1）dp[i][0]: 表示第 i 天无操作
                          （2）dp[i][1]: 表示第 i 天第一次持有股票
                          （3）dp[i][2]: 表示第 i 天第一次不持有股票
                          （4）dp[i][3]: 表示第 i 天第二次持有股票
                                        ......
                          （5）dp[i][2k - 1]: 表示第 i 天第 k 次不持有股票
                          （6）dp[i][2k]: 表示第 i 天第 k 次不持有股票
                          （7）注意：第 i 天持有或者不持有并不代表第 i 天会卖出或者买入，还有可能第 i 天之前就已经进行了买入或卖出的操作
                                   第 i 天仅仅是维持了第 i - 1 天的状态
            （2）递推公式：（1）dp[i][0] = dp[i - 1][0]
                        （2）dp[i][1] = max(dp[i - 1][1], dp[i][0] - prices[i])
                        （3）dp[i][2] = max(dp[i - 1][2], dp[i][1] + prices[i])
                        （4）dp[i][3] = max(dp[i - 1][3], dp[i][2] - prices[i])
                        （5）dp[i][4] = max(dp[i - 1][4], dp[i][3] + prices[i])
                                       ......
                            第 i 天，第 k 次持有股票
                        （6）dp[i][j 从 0 开始循环，j 为 奇数] = max(dp[i - 1][j + 1], dp[i][j] - prices[i])
                            第 i 天，第 k 次不持有股票
                        （7）dp[i][j 从 0 开始循环，j 为 偶数] = max(dp[i - 1][j + 2], dp[i][j + 1] + prices[i])
                        （8）注意：我们可以发现这个状态转换过程中，后一个状态是在前一个状态的基础上做出改变
            （3）初始化：（1）dp[0][0]: 0
                     （2）dp[0][1]: 第 0 天 第一次持有，即利润为：- prices[0]
                     （3）dp[0][2]: 第 0 天 买了又卖了，即利润为： 0
                     （4）dp[0][3]: 第 0 天 买了又卖了，又买了，即利润为：- prices[0]
                     （5）dp[0][4]: 第 0 天 买了又卖了，买了又卖了，即利润为： 0
                                       ......
                     （6）dp[0][j 从 1 开始，j 为奇数]: 第 0 天 第 j 次持有股票，即利润为：- prices[0]
                     （7）dp[0][j 从 1 开始，j 为偶数]: 第 0 天 第 j 次持有股票，即利润为： 0
            （4）遍历顺序：第 i 天的利润状态依赖于 第 i - 1 天的利润状态
            （5）打印 dp 数组
            （6）所有目标：利润最大一定在卖出时产生，所以可以比较第一次不持有和第二次不持有的最大值，但是其实第二次已经包含了第一次卖出的最大值
                       如果真的是在第一次不持有中产生的最大值，那么第二次也只是当天买当天卖，即包含了第一次的最值
 */
public class MaxProfit4 {
    public int maxProfit(int k, int[] prices) {
        int[][] dp = new int[prices.length][2 * k + 1];
        dp[0][0] = 0;
        for (int j = 1; j < 2 * k; j = j + 2) {
            dp[0][j] = - prices[0];
            dp[0][j + 1] = 0;
        }
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i - 1][0];
            for (int j = 0; j < 2 * k; j = j + 2) {
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i][j] - prices[i]);
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i][j + 1] + prices[i]);
            }
        }
        return dp[prices.length - 1][2 * k];
    }
}
