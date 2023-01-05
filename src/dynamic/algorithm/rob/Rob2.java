package dynamic.algorithm.rob;

import java.util.Arrays;

/*
    【213 打家劫舍 II】你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
                     这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
                     同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
                     给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
    【示例 1】
            输入：nums = [2,3,2]
            输出：3
            解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
    【示例 2】
            输入：nums = [1,2,3,1]
            输出：4
            解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
                 偷窃到的最高金额 = 1 + 3 = 4 。
    【示例 3】
            输入：nums = [1,2,3]
            输出：3
    ======================================================================================================
    【解题思路】环形数组的处理
            1、环形数组：[2 3 2] 在第一个元素和最后一个元素不能同时取的前提下，可以划分为三种情况：
            （1）打家劫舍的数组范围取中段 ：[3]
            （2）打家劫舍的数组范围取第一个元素 + 中段 ：[2 3]
            （3）打家劫舍的数组范围取中段 + 最后一个元素：[3 2]
            其中，情况2，包含了情况1，数组的范围只是表明了计算最值的范围，在范围内的房间并不一定会偷
 */
public class Rob2 {
    public int rob2(int[] nums) {
        // nums 数组长度合法性判断
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int[] nums1 = Arrays.copyOfRange(nums,0, nums.length - 1);
        int[] nums2 = Arrays.copyOfRange(nums, 1, nums.length);
        int result1 = rob1(nums1);
        int result2 = rob1(nums2);
        return Math.max(result1,result2);
    }
    public int rob1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
