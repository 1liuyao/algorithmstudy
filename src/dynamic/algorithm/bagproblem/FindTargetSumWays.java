package dynamic.algorithm.bagproblem;

import java.util.Arrays;

/*
    【494 目标和】给你一个整数数组 nums 和一个整数 target 。向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个表达式 ：
                例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
                返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
    【示例 1】
            输入：nums = [1,1,1,1,1], target = 3
            输出：5
            解释：一共有 5 种方法让最终目标和为 3 。
            -1 + 1 + 1 + 1 + 1 = 3
            +1 - 1 + 1 + 1 + 1 = 3
            +1 + 1 - 1 + 1 + 1 = 3
            +1 + 1 + 1 - 1 + 1 = 3
            +1 + 1 + 1 + 1 - 1 = 3
    【示例 2】
            输入：nums = [1], target = 1
            输出：1
    ======================================================================================================
    【解题思路】
            1、'+' 或 '-' 将数组划分成两堆，想套用背包思想，最终要的就是要找到 和 不变量，即背包容量，物品 i 的重量和价值
            2、假设 '+' 元素和为 left，'-'元素和为 right
               left - right = target;
               left + right = Arrays.stream(nums).sum();
            => left = (target + Arrays.stream(nums).sum()) / 2
            3、问题转化：将 nums 元素放入背包容量为 (target + Arrays.stream(nums).sum()) / 2 的背包中，【装满】背包有几种方法？
            4、数学归纳过程：
              （1）定义 dp[j] 表示装满背包容量为 j 的背包有 dp[j] 种方法
              （2）把 物品 0 放入背包后，有 dp[j - nums[0]] 种方法可以凑成 dp[j]
                  把 物品 1 放入背包后，有 dp[j - nums[1]] 种方法可以凑成 dp[j]
                  把 物品 2 放入背包后，有 dp[j - nums[2]] 种方法可以凑成 dp[j]
                                   ......
                  把 物品 i 放入背包后，有 dp[j - nums[i]] 种方法可以凑成 dp[j]

                  注意：这里 物品 i 的重量就是 nums[i]，若把 nums[i] 放入背包，则背包剩余容量就是 j - nums[i]，
                       那么问题就转化成 装满 j - nums[i] 有 dp[j - nums[i]] 种方法
             5、迭代公式：dp[j] = dp[j] + dp[ j-nums[i] ]
             6、初始化 ：dp[0] = 1
             注意：left = (target + Arrays.stream(nums).sum()) / 2 如果结果不能整除，则一定找不到任何方法可以装满背包
                  可以采用反证法证明，举一个奇数和偶数背包容量，发现不能整除，则一定装不满背包
 */
public class FindTargetSumWays {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        int temp = target + Arrays.stream(nums).sum();
        // 判断背包容量是否合法
        if(temp % 2 != 0)
            return 0;
        int bagWeight = (temp) / 2;
        if (bagWeight <0)
            bagWeight = -bagWeight;
        int[] dp = new int[bagWeight + 1];
        // 初始化 dp
        dp[0] = 1;
        // 确定遍历顺序
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagWeight; j >= nums[i]; j--){
                // 确定迭代公式
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[bagWeight];
    }
}
