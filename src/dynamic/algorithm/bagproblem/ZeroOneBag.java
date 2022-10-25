package dynamic.algorithm.bagproblem;
/*
    【背包问题分类】：
            1、前提:（1）背包容量 W
                   （2）物品 i：重量 weight[i] , 价值 value[i]
            2、01背包:（1）每个物品都有两种状态，选或者不选
                     （2）优化目标：在物品 i 只能选择一次的前提下，最大化背包价值
            3、完全背包:（1）完全背包的物品数量是无限的。
                      （2）每个物品都有两种状态，选或者不选，但是选要明确对物品 i选几次
                      （3）优化目标：在物品 i 可以被多次选择的前提下，最大化背包价值

    【01背包问题完整描述】:（1）有 n 件物品和一个最多能背重量为w 的背包。
                       （2）第 i 件物品的重量是weight[i]，得到的价值是value[i] 。
                       （3）每件物品【只能用一次】，求解将哪些物品装入背包里物品价值总和最大。
    ======================================================================================
    【解决方案】

    【暴力解法】回溯法：相当于组合问题，时间复杂度 O(2的n次方)，约束条件

    【例子】：（1）背包最大重量为4。
            （2）物品为：
                        重量	价值
                        物品0	1	15
                        物品1	3	20
                        物品2	4	30
            （3）问背包能背的物品最大价值是多少？

    【动态规划】：
            1、确定dp数组以及下标的含义：dp[i][j] 表示对下标为[0-i]的物品任意取后，放进容量为j的背包，价值总和最大是多少。
            2、确定递推公式：可以有两个方向推出来dp[i][j]（dp[i][j]是基于[0 - i-1]的物品价值推出来的）
              （1）不放物品i：由dp[i - 1][j]推出，即背包容量为j，里面不放物品i的最大价值，此时dp[i][j]就是dp[i - 1][j]。
                  (其实就是当物品i的重量大于背包j的重量时，物品i无法放进背包中，所以被背包内的价值依然和前面相同。)
              （2）放物品i：由dp[i - 1][j - weight[i]]推出，dp[i - 1][j - weight[i]] 为背包容量为j - weight[i]的时候不放物品i的最大价值，
                  那么dp[i - 1][j - weight[i]] + value[i] （物品i的价值），就是背包放物品i得到的最大价值
              （3）总结：
                            |-----  dp[i - 1][j] ，if(weight[i] > j) , 物品 i 由于重量大于背包容量 j 导致无法放入背包
                  dp[i][j] =|
                            |-----  MAX(dp[i - 1][j] , dp[i - 1][j - weight[i]] + value[i]) , if(weight[i] <= j), 物品 i 被放入背包中

            3、dp数组如何初始化：
            （1）通过递推公式可知：dp[i][j]是根据二维矩阵左上方的数据迭代计算而来，因此需要初始化第一行第一列
            （2）第一列数据初始化：for(i = 0; i < n; i++){
                                    d[i][0] = 0;
                               }
            （3）第一行数据初始化：for(j = 1; j <= W; j++){
                                   if (j < weight[i])
                                      d[0][j] = 0;
                                   else
                                      d[0][j] = value[i];
                               }
                                        背包容量 j
                                    0   1   2   3   4
                  dp[i][j]   物品 0  0  15  15  15  15
                             物品 1  0   0   0   0   0
                             物品 2  0   0   0   0   0
            （4）非第一行和第一列数据初始化，dp[i][j]可以取任意值，因为已经初始化了左上角的元素，随着迭代的进行dp[i][j]一定会被更新
           4、确定遍历顺序：先遍历 物品 还是先遍历 背包重量 都可以
                for(int i = 1; i < weight.size(); i++) { // 遍历物品
                    for(int j = 0; j <= W; j++) { // 遍历背包容量
                            迭代公式;
                    }
                }
           5、举例推导dp数组：最终结果就是dp[2][4]
                                       背包容量 j
                                    0   1   2   3   4
                  dp[i][j]   物品 0  0  15  15  15  15
                             物品 1  0  15  15  20  35
                             物品 2  0  15  15  20  35
           6、问题：（1）为什么先遍历物品和先遍历背包容量是一样的？
                       因为dp[i][j]的迭代计算依赖于左上角的元素，无论是先遍历行还是先遍历列，只要保证左上角元素在，就可以完成迭代计算
 */
public class ZeroOneBag {
    public static void main(String[] args) {
        // 定义物品重量
        int[] weight = {1, 3, 4};
        // 定义物品价值
        int[] value = {15, 20, 30};
        // 定义背包容量
        int bagWeight = 4;
        int[][] dp = solution(weight, value, bagWeight);
        travelArray(dp, weight.length,bagWeight + 1);
        System.out.println("背包最大价值为: " + dp[weight.length-1][bagWeight]);
    }
    // 求解01背包问题
    public static int[][] solution(int[] weight, int[] value, int bagWeight){
        // 步骤1 ：初始化dp数组
        int[][] dp = new int[weight.length][bagWeight + 1];
        // 定义物品i是否被选择
        int[] xi = new int[weight.length];
        // 遍历物品
        int i;
        // 遍历背包容量
        int j;
        for (j = 0; j <= bagWeight ; j++) {
            if (j >= weight[0]) {
                dp[0][j] = value[0];
                xi[0] = 1;
            }
        }
        // 迭代求解最大价值
        for ( i = 1; i < weight.length; i++) {
            for ( j = 1; j <= bagWeight ; j++) {
                // 物品 i 不放进背包
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                    xi[i] = 0;
                }else {
                    // 物品 i 放进背包
                    dp[i][j] =Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]) ;
                    if (dp[i][j] == dp[i - 1][j - weight[i]] + value[i]){
                        xi[i] = 1;
                    }
                }
            }
        }
        travelArray1(xi);
        return dp;
    }

    // 遍历二维数组
    public static void travelArray(int[][] dp, int row, int column){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }
    }
    // 遍历一维数组
    public static void travelArray1(int[] dp){
        for (int j = 0; j < dp.length; j++) {
            System.out.print(dp[j]);
        }
        System.out.println();
        System.out.println("==============");
    }
}
