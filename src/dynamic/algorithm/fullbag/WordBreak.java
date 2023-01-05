package dynamic.algorithm.fullbag;

import java.util.List;
/*
    【139 单词拆分】给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
                  注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
    【示例 1】
            输入: s = "leetcode", wordDict = ["leet", "code"]
            输出: true
            解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
    【示例 2】
            输入: s = "applepenapple", wordDict = ["apple", "pen"]
            输出: true
            解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
            注意，你可以重复使用字典中的单词。
    【示例 3】
            输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
            输出: false
     ===============================================================================================
     【解题思路】串 s 就相当于是一个背包，wordDict集合中的每一个元素就相当于一个物品
              1、如何描述把物品放入背包的过程？
                 如果物品能放入背包，则 wordDict 中的字符串长度一定小于s串，并且 s 串中的某一部分一定能和物品匹配
              2、是组合还是排列？
                 wordDict 中的元素是有顺序的，按照一定的顺序拼接成串 s
              3、dp[i] 数组含义: 串 s 长度为 j 时，如果 wordDict 中元素能拼接成长度为 j 的串s，则 dp[i] = true;
              4、递推公式：问什么时候 dp[i] 能为 true，那一定是在 dp[j] 为 true 的基础上，
                         如果串 s 在 j 到 i 范围内能匹配上 wordDict 中元素，则 dp[j] 也为 true
                         if(dp[i] == true && s.subString(j, i))
                            dp[j] = true
              5、初始化：从递归公式中可以看出，dp[i] 的状态依靠 dp[j]是否为true，那么dp[0]就是递归的根基，dp[0]一定要为true，
                       否则递归下去后面都都是false了。
                       非 0 下表为 false
              6、遍历顺序：组合问题：先遍历背包 后 遍历物品
              7、打印 dp 数组
              （1）i 为 1， s = "l" , dp[0] = true, s 不包含 "leet" , 则 dp[1] = false
              （2）i 为 2， s = "le", dp[0] = true, dp[1] = false, s 不包含 "leet" , 则 dp[2] = false
              （3）i 为 3， s = "lee", dp[0] = true, dp[1] = false,dp[2] = false, s 不包含 "leet" , 则 dp[3] = false
              （4）i 为 4， s = "leet", dp[0] = true, dp[1] = false,dp[2] = false, dp[3] = false, s 包含 "leet" , 则 dp[4] = true
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // s 长度为 0 是空串
        // 排列问题：先遍历背包
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String ss = s.substring(j,i);
                boolean contains = wordDict.contains(ss);
                if (dp[j] && contains)
                    dp[i] = true;
            }
        }
        return dp[s.length()];
    }
}
