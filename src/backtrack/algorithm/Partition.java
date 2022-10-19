package backtrack.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    【131 分割回文串】给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串。返回 s 所有可能的分割方案。
                   回文串 是正着读和反着读都一样的字符串。

    【用例1】
            输入：s = "aab"
            输出：[["a","a","b"],["aa","b"]]
    【用例2】
            输入：s = "a"
            输出：[["a"]]
    ===========================================================================================
    【解题思路】回溯法：【难点】确定切割位置
              举例：s = "aab"
     =================================================================================================================
                                                                   a a b
                                    取a                              取a                            取b
                                 分割成 a|ab                      分割成 aa|b                      分割成 aab|
     =================================================================================================================
                                    a b                              b                              空
                            取a              取b                     取b
                        分割成 a|a|b      分割成 a|ab|             分割成 aa|b|
     =================================================================================================================
                            b                ×（不是回文）             空
                           取b
                        分割成 a|a|b|
     =================================================================================================================
                           空
     =================================================================================================================
             1、”分割线“怎么描述：（1）”分割线“本质就是一个位置，当我们从串中取一个元素，就在这个元素后加 ”分割线“，
                               （2）站在深度递归的角度，取元素是从startIndex位置取的，所以”分割线“的位置就是startIndex
             2、子串区间：（1）从串的起始位置到”分割线“位置就是一个子串，如果符合回文要求那么就需要被path收集
                        （2）针对广度的for遍历，每个子串的起始位置和终止位置（左闭右闭）为[startIndex, i]
                        （3）举例：针对根结点，startIndex = 0
                                 取 a 后：切出子串 a ，索引区间[0, 0]，此时 i = 0
                                 取 a 后：切出子串 aa ，索引区间[0, 1]，此时 i = 1
                                 取 b 后：切出子串 aab ，索引区间[0, 2]，此时 i = 2
             2、什么时候回溯结束：回溯递归结束，都是走到了叶子结点，我们需要在叶子结点上找规律
                              发现深度递归的过程中当startIndex走到 串.length，就没有可取元素了，接下来结点就为空
 */
public class Partition {
    LinkedList<String> path = new LinkedList<>();
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> partition(String s) {
        int startIndex = 0;
        backtracking(s, startIndex);
        return result;
    }

    public void backtracking(String s, int startIndex) {
        // 确定回溯终止条件
        if(startIndex == s.length()){
            result.add(new LinkedList<>(path));
            return;
        }
        // 确定回溯单层操作逻辑：取字符 + 分割 + 判断回文
        for (int i = startIndex; i < s.length(); i++) {
            // 注意：substring(start, end)：截取区间是左闭右开的 [start, end)，而我们需要的是[startIndex , i]右闭，所以需要+1
            String subString = s.substring(startIndex, i+1);
            if (isPalindrome(subString)) {
                path.offerLast(subString);
            }else{
                continue;
            }
            backtracking(s, i + 1);
            path.pollLast();
        }
    }

    // 判断字符串是否是回文字符串
    public boolean isPalindrome(String s){
        int start = 0;
        int end = s.length() - 1;
        for (; start < end; start++,end--){
            if (s.charAt(start) != s.charAt(end)){
                return false;
            }
        }
        return true;
    }
}
