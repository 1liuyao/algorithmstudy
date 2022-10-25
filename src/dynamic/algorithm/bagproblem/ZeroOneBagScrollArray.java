package dynamic.algorithm.bagproblem;
/*
    【01背包滚动数组】把二维dp降为一维dp
        1、出现的原因：考虑dp[i][j]的迭代公式，发现dp[i-1]是不变的，在二维矩阵中下一行的计算依赖于上一行，这就出现了基于行的滚动数组
                        |-----  dp[i - 1][j] ，if(weight[i] > j) , 物品 i 由于重量大于背包容量 j 导致无法放入背包
              dp[i][j] =|
                        |-----  MAX(dp[i - 1][j] , dp[i - 1][j - weight[i]] + value[i]) , if(weight[i] <= j), 物品 i 被放入背包中
    【基于滚动数组的动态规划】
        1、确定dp数组的定义：在一维dp数组中，dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]。
        2、一维dp数组的递推公式：其实把二维数据压缩成一维数组，本质上是去掉了行影响
                        |-----  dp[j] ，if(weight[i] > j) , 物品 i 由于重量大于背包容量 j 导致无法放入背包
                dp[j] = |
                        |-----  MAX(dp[j] , dp[j - weight[i]] + value[i]) , if(weight[i] <= j), 物品 i 被放入背包中
        3、一维dp数组如何初始化
          （1）dp[0] = 0
          （2）非索引 0 位置的元素需要定义的尽量小，因为如果定义的很大，会导致MAX(dp[j] , dp[j - weight[i]] + value[i]) 永远取dp[j]
        4、一维dp数组遍历顺序：
                for(int i = 0; i < weight.size(); i++) { // 遍历物品
                    for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
                        dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
                    }
                }

          （1）由于我们压缩的是行，按照行滚动的数组，所以需要先遍历物品。
              ※如果遍历背包容量放在上一层，那么每个dp[j]就只会放入一个物品，即：背包里只放入了一个物品。
          （2）一定要倒序遍历背包容量：倒序遍历是为了保证物品i只被放入一次
              dp[1] = dp[1 - weight[0]] + value[0] = 15
              dp[2] = dp[2 - weight[0]] + value[0] = 30
              此时dp[2]就已经是30了，意味着物品0，被放入了两次，所以不能正序遍历。

              为什么倒序遍历，就可以保证物品只放入一次呢？
              倒序就是先算dp[2]
              dp[2] = dp[2 - weight[0]] + value[0] = 15 （dp数组已经都初始化为0）
              dp[1] = dp[1 - weight[0]] + value[0] = 15
              所以从后往前循环，每次取得状态不会和之前取得状态重合，这样每种物品就只取一次了。

              那么问题又来了，为什么二维dp数组历的时候不用倒序呢？
              因为对于二维dp，dp[i][j]都是通过上一层即dp[i - 1][j]计算而来，本层的dp[i][j]并不会被【覆盖】！
         5、问题:（1）为什么采用【二维】数组的时候【可以】调换 物品 和 背包容量 的遍历顺序
                （2）为什么采用【一维】数组的时候【不可以】调换 物品 和 背包容量 的遍历顺序
                （3）为什么一维数组 背包容量 要倒叙遍历，如果正确遍历会产生什么问题
            解答这些问题的根本方法，就是手动模拟计算dp数组，发现会产生什么问题
            产生这些问题的根本原因：递推公式的推导方向 + 本层是否被上层覆盖

 */

public class ZeroOneBagScrollArray {
    public static void main(String[] args) {
        // 定义物品重量
        int[] weight = {1, 3, 4};
        // 定义物品价值
        int[] value = {15, 20, 30};
        // 定义背包容量
        int bagWeight = 4;
        int[] dp = solution1(weight, value, bagWeight);
        System.out.println("背包最大价值为: " + dp[bagWeight]);
    }
    // 求解01背包问题
    public static int[] solution1(int[] weight, int[] value, int bagWeight){
        // 步骤1 ：初始化dp数组全为0
        int[] dp = new int[bagWeight + 1];
        // 定义物品i是否被选择
        int[] xi = new int[weight.length];
        // 遍历物品
        int i;
        // 遍历背包容量
        int j;

        // 迭代求解最大价值
        for ( i = 0; i < weight.length; i++) {
            for ( j = bagWeight; j >= weight[i] ; j--) {
                dp[j] =Math.max(dp[j], dp[j - weight[i]] + value[i]) ;
                if (dp[j] == dp[j - weight[i]] + value[i]){
                    xi[i] = 1;
                }
            }
            travelArray(dp);
        }
        travelArray(xi);
        return dp;
    }

    // 遍历一维数组
    public static void travelArray(int[] dp){
        for (int j = 0; j < dp.length; j++) {
            System.out.print(dp[j]);
        }
        System.out.println();
        System.out.println("---------------");
    }
}
