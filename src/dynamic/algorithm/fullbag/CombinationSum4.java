package dynamic.algorithm.fullbag;
/*
    【377 组合总和 Ⅳ】给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
                    请你从 nums 中找出并返回总和为 target 的元素组合的个数。
                    题目数据保证答案符合 32 位整数范围。
    【示例 1】
            输入：nums = [1,2,3], target = 4
            输出：7
            解释：
            所有可能的组合为：
            (1, 1, 1, 1)
            (1, 1, 2)
            (1, 2, 1)
            (1, 3)
            (2, 1, 1)
            (2, 2)
            (3, 1)
            请注意，顺序不同的序列被视作不同的组合。
    【示例 2】
            输入：nums = [9], target = 3
            输出：0
     ===============================================================================
     【解题思路】动态规划求排列数
              （1）判断是 0 1 背包 还是 完全背包：一个物品可以不止一次被放入背包 属于 完全背包问题
              （2）求装满背包有多少种方法递推公式采用累加式：dp[j] = dp[j] + dp[j - nums[i]]
              （3）求排列数遍历顺序：先遍历背包容量后遍历物品
 */
public class CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        // 定义 dp 数组：装满背包容量为 j 的背包共有 dp[j] 种物品排列方法
        int[] dp = new int[target + 1];
        // 初始化
        dp[0] = 1;
        // 遍历
        for (int j = 0; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                // 正序遍历，当物品 i 可以放进背包才更新迭代，若放不进去，取dp[j]
                if (j >= nums[i])
                    dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
