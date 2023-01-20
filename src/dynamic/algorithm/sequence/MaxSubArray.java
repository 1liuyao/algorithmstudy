package dynamic.algorithm.sequence;
/*
    【53 最大子数组和】给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
                    子数组 是数组中的一个连续部分。
    【示例 1】
            输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
            输出：6
            解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
    【示例 2】
            输入：nums = [1]
            输出：1
    【示例 3】
            输入：nums = [5,4,-1,7,8]
            输出：23
    ===================================================================================================
    【解题思路】动态规划 或 贪心
    【动态规划策略】
            1、确定 dp 数组含义：表示以 nums[i] 为结尾的连续子序列 的 最大和
            2、确定递推公式：（1）以 nums[i] 为结尾的连续子序列要么是在 以 nums[i - 1] 为结尾的连续子序列和上 加 nums [i]。
                          （2）要么是以 nums[i] 为起始，重新计算 连续子序列和
                          dp[i] = max(dp[i - 1] + nums[i], nums[i])
            3、初始化 dp 数组：dp[0] = nums[0]，因为后续要求最大值，防止最大值被覆盖，所以将其他位置初始化为 0
            4、确定遍历顺序：dp[i] 需要通过 dp[i - 1] 推出来，所以从前向后遍历
            5、打印 dp 数组调试

    【贪心策略】1、只要截止到 nums[i] 的连续子序列和 是 负数，那么加上 nums[i] 和一定会减少，还不如从，nums[i] 开始重新计算
              2、统计到nums[i] 的和，如果大于 0 ，加上 nums[i], 如果和为负数，那么和更新为 nums[i]

                    -2   1   -3   4   -1   2   1   -5   4
                sum -2   1   -2   4    3   5   6    1   5
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        // 确定 dp 数组含义：表示以 nums[i] 为结尾的连续子序列 的 最大和
        int[] dp = new int[nums.length];
        // 初始化 dp 数组：dp[0] = nums[0]，因为后续要求最大值，防止最大值被覆盖，所以将其他位置初始化为 0
        dp[0] = nums[0];
        int result = nums[0];
        // 确定遍历顺序：dp[i] 需要通过 dp[i - 1] 推出来，所以从前向后遍历
        for (int i = 1; i < nums.length; i++) {
            // 迭代
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            // 最大值不一定出现在 dp[nums.length - 1]
            if (result < dp[i])
                result = dp[i];
        }
        return result;
    }
    public int maxSubArray1(int[] nums) {
        int result = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 当连续和为负数时，我们选择下一个数作为起点
            if (sum < 0)
                sum = nums[i];
            else{
                sum = nums[i] + sum;
            }
            // 坑：这个判断不能和 else 放在一起，因为针对 [-2,1] 这样的组合，在 if 判断中 sum 就已经更新了，所以result也需要更新
            result = sum > result ? sum : result;
        }
        return result;
    }
}
