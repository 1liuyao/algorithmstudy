package greed.algorithm;

import java.util.ArrayList;
import java.util.List;
/*
    【763 划分字母区间】给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
                     注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
                     返回一个表示每个字符串片段的长度的列表。
    【示例 1】
            输入：s = "ababcbacadefegdehijhklij"
            输出：[9,7,8]
            解释：
            划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
            每个字母最多出现在一个片段中。
            像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
    【示例 2】
            输入：s = "eccbbbbdec"
            输出：[10]
     ===============================================================================================
     【解题思路】
            1、记录每个字符出现的最远位置
            a  b  a  b  c  b  a  c  a   d  e  f  e  g  d  e    h  i  j  h  k  l  i  j
            8  5  8  5  7  5  8  7  8   14 15 11 15 13 14 15   19 22 23 19 20 21 22 23
                                      |                      |
                                    分割线                  分割线
            2、如何分割
            当我们遍历到索引 0 元素时，我们至少要遍历到 索引 8 才能包含所有的 a
            当我们遍历到索引 1 元素时，我们至少要遍历到 索引 5 才能包含所有的 b
            当我们遍历到索引 4 元素时，我们至少要遍历到 索引 7 才能包含所有的 c
            综上，我们只要遍历到 索引 8，即片段[0 到 8]，就可以包含所有的 a b c

            总结，当我们遍历一个元素时，就需要记录当这个元素加入片段后，片段的最右边界
            例如，当我们遍历到 第一个 d 时，即索引 9 位置时，我们片段的最右边界是 14
            当我们遍历到 第一个 e 时，即索引 10 位置时，我们片段的最右边界是 15

            当我们遍历到的位置等于我们的 最右边界时，就找到了分割点

            3、如何计算片段长度
            头尾指针：（1）头指针记录片段开始位置，初始为 0 ，每当找到分割点后，头指针 变成 分割点 + 1
                    （2）尾指针永远记录片段的最右边界，当尾指针遍历到的元素 就是最右边界时，那么就找到了分割点
                    （3）片段长度 = 尾指针 - 头指针 + 1
            当遍历到索引 8 时，我们发现 片段最有边界和 8 相等，则 8 为分割点，此时尾指针为 8 ，头指针 为 0 ，片段长度为 9
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int[] hash = new int[26];
        // 利用 hash 表，记录每个元素出现的最远位置
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i;
        }

        // 头尾指针计算片段长度
        int front = 0;
        int rear = 0;
        for (int i = 0; i < s.length(); i++) {
            // 尾指针永远记录片段的最右边界
            rear = Math.max(hash[s.charAt(i) - 'a'], rear);
            // 找到分割点
            if (rear == i){
                result.add(rear - front + 1);
                // 找到分割点，更新新片段的头指针
                front = rear + 1;
            }
        }
        return result;
    }
}
