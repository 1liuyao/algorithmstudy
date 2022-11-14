package dynamic.algorithm.baseproblem;
/*
    【343 整数拆分】给定一个正整数 n ，将其拆分为 k 个 【正整数】 的和（ k >= 2 ），并使这些整数的乘积最大化。
                  返回 你可以获得的最大乘积 。
    【示例 1】
            输入: n = 2
            输出: 1
            解释: 2 = 1 + 1, 1 × 1 = 1。
    【示例 2】
            输入: n = 10
            输出: 36
            解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
    ===========================================================================================
    【解题思路】
            1、确定 dp[i] 含义： 将正数 i 拆分成 多个正整数，最大乘积是 dp[i]
            2、确定 递推公式：（1）i 可以拆成两个数相乘：j * (j - i)
                           （2）i 可以拆成多个数相乘：j * dp[j - i]
                           （3）dp[i] = max(dp[i], max(j * (j - i), j * dp[j - i])）
                           注意：一定要在 max 中加入 dp[i]，因为随着 j 的变化（ 1<= j <= i -1）,
                               dp[i]是在不断被迭代计算的，可能最大值出现在中间某一次迭代，而不是出现在最后
                               仅仅 dp[i] = max(j * (j - i), j * dp[j - i]) 可能错过最大值
            3、初始化 dp 数组：（1）dp[0] 和 dp[1]是没有办法被拆分成两个正整数的
                             （2）dp[2] = 1
            4、确定遍历顺序：从前往后
 */
public class IntegerBreak {
    public static int integerBreak(int n) {
        // 网格dp[i],：无法将 0 或 1 拆分成两个正整数的和，从2开始计算
        int[] dp = new int[n + 1];
        // 初始化
        dp[2] = 1;
        //确定遍历顺序，dp[3]= 1 * 2  或者 1 * dp[2](相当于多个数相乘)
        for(int i = 3; i <= n; i++)
            for(int j = 1; j <= i - 1; j++) {
                //填格
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
                System.out.println(dp[i]);
            }
        return dp[n];
    }

    public static void main(String[] args) {
        integerBreak(10);
    }
}
