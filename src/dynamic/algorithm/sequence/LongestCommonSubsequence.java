package dynamic.algorithm.sequence;
/*
    【1143 最长公共子序列】给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
                       一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符
                       （也可以不删除任何字符）后组成的新字符串。
                        例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
                        两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

    【示例 1】
            输入：text1 = "abcde", text2 = "ace"
            输出：3
            解释：最长公共子序列是 "ace" ，它的长度为 3 。
    【示例 2】
            输入：text1 = "abc", text2 = "abc"
            输出：3
            解释：最长公共子序列是 "abc" ，它的长度为 3 。
    【示例 3】
            输入：text1 = "abc", text2 = "def"
            输出：0
            解释：两个字符串没有公共子序列，返回 0 。
     ========================================================================================================
     【解题思路】
            1、dp 数组含义：以 text1[i - 1]，text2[j - 1] 为结尾的子序列 的公共子序列最大长度为 dp[i][j]
            2、递推公式： 如果 text1[i - 1] == text2[j - 1]，则 dp[i][j] = dp[i - 1][j - 1] + 1
                        如果 text1[i - 1] ！= text2[j - 1]，则 dp[i][j] = max (dp[i - 1][j], dp[i][j - 1])
            3、dp 数组初始化：（1）为了解决 i - 1 需要特殊赋值的问题，多添加一行和一列

                                 a b c d e
                               0 0 0 0 0 0
                            a  0
                            c  0
                            e  0

                           （2）dp[i][j] 计算依赖于三个方向

                               dp[i - 1][j - 1]   dp[i - 1][j]
                               dp[i][j - 1]       dp[i][j]
           4、遍历顺序：从左到右，从上到下
           5、打印 dp 数组：
                                 a b c d e
                               0 0 0 0 0 0
                            a  0 1 1 1 1 1
                            c  0 1 1 2 2 2
                            e  0 1 1 2 2 3
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        // 定义dp数组：以 text1[i - 1]，text2[j - 1] 为结尾的子序列 的公共子序列最大长度为 dp[i][j]
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        // 初始化 dp 数组，d[i - 1][0] 和 dp[0][j - 1] 是 0
        // 递推：从左到右，从上到下
        for (int i = 1; i <= text1.length(); i++)
            for (int j = 1; j <= text2.length() ; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        return dp[text1.length()][text2.length()];
    }
}
