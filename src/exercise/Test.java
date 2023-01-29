package exercise;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        integerBreak(10);
    }
    public static int integerBreak(int n) {
        // dp[i] : 表示拆分 i ，可获得的乘积最大值
        int[] dp = new int[n + 1];

        dp[2] = 1;

        for(int i = 3; i <= n; i++){
            for(int j = 1; j <= i/2; j++){
                dp[i] = Math.max(dp[i], j * (i - j));
                dp[i] = Math.max(dp[i], j * dp[i - j]);
                System.out.print(dp[i] + " ");
            }
            System.out.println("=============");
        }

        return dp[n];
    }

    public static void print(int[][] dp){
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }
        System.out.println("==================");
    }
}
