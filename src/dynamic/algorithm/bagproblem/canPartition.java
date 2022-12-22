package dynamic.algorithm.bagproblem;

import java.util.Arrays;

/*
    【416 分割等和子集】给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

    【示例 1】
            输入：nums = [1,5,11,5]
            输出：true
            解释：数组可以分割成 [1, 5, 5] 和 [11] 。
    【示例 2】
            输入：nums = [1,2,3,5]
            输出：false
            解释：数组不能分割成两个元素和相等的子集。
    ============================================================================================================
    【解题思路】套用 0 - 1 背包问题
             1、如果问题可以套用 0 - 1 背包问题建模，需要明确以下几点？
               （1）什么是物品？ nums数组中的元素
               （2）物品的重量是？ nums[i]
               （3）物品的价值是？ nums[i]
               （4）背包容量是？  nums数组元素和的一半
               （5）同一个物品是否可以多次重复的装入背包？  不可以，是 0 - 1 背包问题
               （6）什么时候是装满了背包呢？
                   当 dp[target] == target 时，说明装满背包时背包的最大价值，其实就是背包容量本身
             2、确定 dp[i,j] 含义 ： 将数组元素 nums[1]，nums[2]，..， nums[i] 以任意组合状态放入背包的和
             3、确定 递推公式：dp[i,j] = max(dp[i - 1][j] , dp[i - 1][j - nums[i]] + nums[i])
             4、确定遍历顺序：从前到后
             5、如果恰好能装满背包，则返回true
 */
public class canPartition {
    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        int[] nums1 = {1,2,5};
        canPartition(nums1);
    }
    public static boolean canPartition(int[] nums) {
        // 初始化 dp 数组
        int sums = Arrays.stream(nums).sum();
        int target;
        // 如果和的一半是奇数，则无法填满背包，返回false
        if (sums % 2 == 1)
            return false;
        else
            target = sums / 2;

        int[][] dp = new int[nums.length][target + 1];

        for (int j = 0; j <= target; j++) {
            if (nums[0] <= j)
                dp[0][j] = nums[0];
        }

        // 确定遍历顺序
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target ; j++) {
                if (nums[i] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
            }
        }
        travelArray(dp, nums.length, target + 1);
        // 满足和条件 返回 true
        if (dp[nums.length - 1][target] != target)
            return false;
        return true;
    }
    public static void travelArray(int[][] dp, int row, int column){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }
    }
}
