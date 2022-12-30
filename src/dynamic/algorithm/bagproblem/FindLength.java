package dynamic.algorithm.bagproblem;
/*
    【718 最长重复子数组】给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
    【示例 1】
            输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
            输出：3
            解释：长度最长的公共子数组是 [3,2,1] 。
    【示例 2】
            输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
            输出：5
     ===================================================================================
    【解题思路】二维 dp 数组

            方案一： dp[i][j] 数组含义：表示以 nums1[i] 结尾， 以 nums2[j] 结尾的公共子数组最长长度
            方案二： dp[i][j] 数组含义：表示以 nums1[i - 1] 结尾， 以 nums2[j - 1] 结尾的公共子数组最长长度
 */
public class FindLength {
    public int findLength(int[] nums1, int[] nums2) {
        // dp[i][j] 表示：表示以 nums1[i] 结尾， 以 nums2[j] 结尾的公共子数组最长长度
        // 初始化dp数组
        int result = 0;
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j])
                    dp[i][j] = 1;
                if (result < dp[i][j])
                    result = dp[i][j];
            }
        }
        // 迭代
        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                if (result < dp[i][j])
                    result = dp[i][j];
            }
        }
        return result;
    }

    // 问题： 由于 dp[i][j] 是通过 dp[i - 1][j - 1] 推出来的，考虑 i ， j 为 0 的时候，数组越界问题
    //       因此针对第一行和第一列的数据初始化就需要特殊考虑
    public int findLength1(int[] nums1, int[] nums2) {
        // dp[i][j] 数组含义：表示以 nums1[i - 1] 结尾， 以 nums2[j - 1] 结尾的公共子数组最长长度
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        // dp数组 初始化为 0
        int result = 0;
        // 迭代
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                if (result < dp[i][j])
                    result = dp[i][j];
            }
        }
        return result;
    }
    // 使用第二种方式：可以简化dp初始化过程，同时通过增加一行和一列，规避了 i - 1 ，数组越界的问题，但是迭代公式判断的是前一位
//          3  2  1  4  7
//       0  0  0  0  0  0
//    1  0  0  0  1  0  0
//    2  0  0  1  0  0  0
//    3  0  1  0  0  0  0
//    2  0  0  2  0  0  0
//    1  0  0  0  3  0  0
}
