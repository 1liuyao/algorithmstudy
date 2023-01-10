package dynamic.algorithm.distance;
/*
    【583 两个字符串的删除操作】给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
                           每步 可以删除任意一个字符串中的一个字符。
    【示例 1】
            输入: word1 = "sea", word2 = "eat"
            输出: 2
            解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
    【示例 2】
            输入：word1 = "leetcode", word2 = "etco"
            输出：4
     ===========================================================================================
     【解题思路】
             1、涉及两个字符串的操作 dp数组是二维的：
                    表示以 i - 1 为结尾的word1，以 j - 1 为结尾word2，删除最少 dp[i][j] 个元素使得 word1 和 word2 相同
             2、递推公式：
                （1）如果 word1[i - 1] 和 word2[j - 1] 相同，说明不需要删除操作:
                       dp[i][j] = dp[i - 1][j - 1]
                （2）如果 word1[i - 1] 和 word2[j - 1] 不相同，说明要么 word1 需要被删除，要么word2需要被删除，要么都需要被删除
                       dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + 2)
             3、初始化：
                       dp[i - 1][j - 1]    dp[i - 1][j]
                       dp[i][j - 1]        dp[i][j]
                （1）初始化第一行 dp[0][j]: 当word1是空串，word2 至少要删除 word2.length() 次，即 j，才能保证word2是空串和word1相同
                （2）初始化第一列 dp[i][0]: 当word2是空串，word1 至少要删除 word1.length() 次，即 i，才能保证word1是空串和word2相同
             4、遍历顺序从左到右，从上到下
             5、打印 dp 数组
 */
public class MinDistance {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length() ; i++) {
            for (int j = 1; j <= word2.length() ; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 2);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
