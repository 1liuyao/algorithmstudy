package dynamic.algorithm.sequence;
/*
    【115 不同的子序列】给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
                     字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
                     （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
    【示例 1】
            输入：s = "rabbbit", t = "rabbit"
            输出：3
            解释：
                如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
                ra bbit
                rab bit
                rabb it
    【示例 2】
            输入：s = "babgbag", t = "bag"
            输出：5
            解释：
                如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
                ba g
                b    ag
                ba    g
                    bag
                  b  ag
   =================================================================================================
   【解题思路】本质上是通过对 串 s 进行删除操作，判断是否包含串 t
            1、dp[i][j] 数组含义：以 i-1 为结尾的 s 子序列中出现以 j-1 为结尾的t的个数为 dp[i][j]
            2、递推公式:（1）s[i - 1] 与 t[j - 1]相等
                          ① 用s[i - 1]来匹配，那么个数为dp[i - 1][j - 1]
                          ② 不用s[i - 1]来匹配，个数为dp[i - 1][j]
                          所以当s[i - 1] 与 t[j - 1]相等时，dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];

                          【举例】bagg：  s[3] 和 t[2] 相等，当使用 s[3],则个数为 bag 是否包含 ba 个数
                                 bag：   s[3] 和 t[2] 相等，当不使用 s[3],则个数为 bagg 是否包含 bag 个数
                      （2）s[i - 1] 与 t[j - 1] 不相等
                          dp[i][j]只有一部分组成，不用s[i - 1]来匹配，即：dp[i - 1][j]
                          所以递推公式为：dp[i][j] = dp[i - 1][j];
            3、初始化 dp 数组：（1）dp[i][0]：t 是空串，那么 s 包含空串得个数是 1
                            （2）dp[0][j]： s 是空串，那么 空串 s 包含不了任何串，所以个数是 0
                            （3）重叠部分dp[0][0]: s 是空串，t 也是空串，那么空串包含空串，个数是 1
            4、遍历顺序：从左到右，从上到下
                           dp[i - 1][j - 1]         dp[i - 1][j]
                                                    dp[i][j]
            5、打印 dp 数组

            【我的疑惑】为啥当 s[i - 1] 与 t[j - 1] 相等，用 s[i - 1] 来匹配，dp[i][j] = dp[i - 1][j - 1] + 1
                      为啥个数不需要 加1 呢？
 */
public class NumDistinct {
    public int numDistinct(String s, String t) {
        // sdp[i][j] 数组含义：以 i-1 为结尾的 s 子序列中出现以 j-1 为结尾的t的个数为 dp[i][j]
        int[][] dp = new int[s.length() + 1][t.length() + 1];
//        dp 数组初始化
//        dp[i][0]：t 是空串，那么 s 包含空串得个数是 1
//        dp[0][j]： s 是空串，那么 空串 s 包含不了任何串，所以个数是 0
//        重叠部分dp[0][0]: s 是空串，t 也是空串，那么空串包含空串，个数是 1
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }
        // 遍历
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length() ; j++) {
                // 考虑是否使用 s[i - 1]
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[s.length()][t.length()];
    }
}
