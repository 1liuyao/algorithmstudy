package dynamic.algorithm.palindrome;

/*
    【647 回文子串】给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
                  回文字符串 是正着读和倒过来读一样的字符串。
                  子字符串 是字符串中的由连续字符组成的一个序列。
                  具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
    【示例 1】
            输入：s = "abc"
            输出：3
            解释：三个回文子串: "a", "b", "c"
    【示例 2】
            输入：s = "aaa"
            输出：6
            解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
    =====================================================================================
    【解题思路】
            1、dp 数组定义： 如果串 s 在左闭右闭区间 [i, j], 其中 i <= j，为回文串，则 dp[i][j] = true
            2、递推公式：      a a a a a a
                             i         j  如果 dp[i + 1][j - 1] 为 true，通过比较 s[i] == s[j]，可推出 dp[i][j]
                              i+1   j-1
                  （1）如果 s[i] == s[j] 相等:
                      ① i == j, 即只有一个 字符a，那么 dp[i][j] = true
                      ② j - i = 1, 即有两个 字符 aa，那么 dp[i][j] = true
                      ③ j - i >1, 说明有多个字符 aaaa，那么 通过 dp[i + 1][j - 1] 推出 dp[i][j]
                  （2）如果 s[i] == s[j] 不相等
                      dp[i][j] = false
            3、初始化：false
            4、遍历顺序：左下角推右上角，从下往上，从左到右
                                        dp[i][j]
                       dp[i + 1][j - 1]
            5、打印 dp 数组
 */
public class CountSubstrings {
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int result = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        result++;
                    }
                    if (j - i > 1 && dp[i + 1][j - 1] == true) {
                        dp[i][j] = true;
                        result++;
                    }
                }
            }
        }
        return result;
    }
}
