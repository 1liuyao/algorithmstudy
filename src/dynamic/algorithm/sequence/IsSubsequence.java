package dynamic.algorithm.sequence;
/*
    【392 判断子序列】给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
                   字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
                   （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
    【示例 1】
            输入：s = "abc", t = "ahbgdc"
            输出：true
    【示例 2】
            输入：s = "axc", t = "ahbgdc"
            输出：false
     ===============================================================================================
     【解题思路】
            1、确定 dp[i][j] 数组含义：以 s[i - 1] t[j - 1] 为结尾的序列的最长公共子序列长度
            2、递推公式：（1）if (s[i - 1] == t[j - 1])，t中找到了一个字符在s中也出现了，dp[i][j] = dp[i - 1][j - 1] + 1;
                       （2）if (s[i - 1] != t[j - 1])，相当于t要删除元素，继续匹配，
                           那么dp[i][j] 的数值就是 看s[i - 1]与 t[j - 2]的比较结果了，即：dp[i][j] = dp[i][j - 1];
            3、初始化 dp 数组：dp[i][j] 计算依赖于两个方向：
                            dp[i - 1][j - 1]
                            dp[i][j - 1]       dp[i][j]

                            初始化 dp[i][0] = 0
            4、确定遍历顺序：从做到右，从上到下
            5、打印 dp 数组
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        // 确定 dp[i][j] 数组含义：以 s[i - 1] t[j - 1] 为结尾的序列的最长公共子序列长度
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        // 初始化 dp 数组，dp[i][0] = 0
        // 确定遍历顺序
        for (int i = 1; i <= s.length() ; i++) {
            for (int j = 1; j <= t.length() ; j++) {
                if(s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = dp[i][j - 1];
            }
        }

        // 如果最长公共子序列就是 子串 s，则说明 t 包含 s
        return s.length() == dp[s.length()][t.length()];
    }
}
