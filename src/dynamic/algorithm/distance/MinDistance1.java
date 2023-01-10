package dynamic.algorithm.distance;
/*
    【72 编辑距离】给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
                 你可以对一个单词进行如下三种操作：
                                            插入一个字符
                                            删除一个字符
                                            替换一个字符
    【示例 1】
            输入：word1 = "horse", word2 = "ros"
            输出：3
            解释：
            horse -> rorse (将 'h' 替换为 'r')
            rorse -> rose (删除 'r')
            rose -> ros (删除 'e')
    【示例 2】
            输入：word1 = "intention", word2 = "execution"
            输出：5
            解释：
            intention -> inention (删除 't')
            inention -> enention (将 'i' 替换为 'e')
            enention -> exention (将 'n' 替换为 'x')
            exention -> exection (将 'n' 替换为 'c')
            exection -> execution (插入 'u')
    ====================================================================================
    【解题思路】两个字符串的删除 + 替换操作
            1、定义 dp[i][j] 数组：以 i - 1 结尾的 word1 和以 j - 1 为结尾的 word2，执行最少 dp[i][j] 次操作使得两者相同
            2、递推公式：
                （1）如果 word1[i - 1] 和 word2[j - 1] 相同，那么
                    dp[i][j] = dp[i - 1][j - 1]  【因为相同是不需要任何操作的，所以对串的操作次数是不会发生改变的】
                （2）如果 word1[i - 1] 和 word2[j - 1] 不相同，那么
                    ① 采取 删除 word1 策略：dp[i][j] = dp[i - 1][j] + 1
                    ② 采取 删除 word2 策略：dp[i][j] = dp[i][j - 1] + 1
                    ③ 采取 添加 策略：添加 其实是删除的反向操作，所以 添加 策略和 删除 策略 在操作数上是一样的，
                       最少的增加 就是 最少的删除，所以删除策略就包含了添加策略
                    ④ 采取 替换 策略：dp[i][j] = dp[i - 1][j - 1] + 1

                    dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1);

            3、初始化： dp[i - 1][j - 1]    dp[i - 1][j]
                      dp[i][j - 1]        dp[i - 1][j - 1]
                     （1）初始化第一行：dp[0][j] = j  【当 word1 为空时，word2 要操作多少次，能和 word1 相同】
                     （2）初始化第一列：dp[i][0] = i  【当 word2 为空时，word1 要操作多少次，能和 word2 相同】
            4、遍历顺序：dp[i][j] 的计算 依赖于 dp[i - 1][j - 1] dp[i - 1][j] dp[i][j - 1]
            5、打印 dp 数组
 */
public class MinDistance1 {
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
                    // 删 word1 + 删 word2 + 替换
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
