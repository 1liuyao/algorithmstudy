package dynamic.algorithm.palindrome;
/*
    【516 最长回文子序列】给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
                       子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
    【示例 1】
            输入：s = "bbbab"
            输出：4
            解释：一个可能的最长回文子序列为 "bbbb" 。
    【示例 2】
            输入：s = "cbbd"
            输出：2
            解释：一个可能的最长回文子序列为 "bb" 。
    ==================================================================================================
    【解题思路】
             1、定义 dp[i][j] 数组：在串 s 的左闭右闭区间 [i, j] 上, 其中 i <= j, 最大回文序列的长度为 dp[i][j]
                         |---|-----------|---|
                       s[i] s[i+1]   s[j-1]s[j]
                         |---------------| 删s[j]
                      删s[i] |---------------|
             2、递推公式：（1）如果 s[i] == s[j]：dp[i][j] = dp[i - 1][j - 1] + 2
                        （2）如果 s[i] != s[j]：① 删除 s[i]: dp[i][j] = dp[i + 1][j]
                                              ② 删除 s[j]: dp[i][j] = dp[i][j - 1]
                            dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
             3、初始化： 当 i == j，则说明只有一个字符，是回文串，因此，dp[i][j] = 1
                       其余下表初始化成 0 ，最终会被迭代覆盖
             4、遍历顺序：     dp[i][j - 1]         dp[i][j]
                             dp[i + 1][j - 1]     dp[i + 1][j]
                      从左到右，从下到上
             5、打印 dp 数组
                       
 */
public class LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][s.length() - 1];
    }
}
