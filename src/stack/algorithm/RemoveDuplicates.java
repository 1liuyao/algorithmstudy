package stack.algorithm;

import java.util.LinkedList;
import java.util.Stack;

/*
    【1047 删除字符串中的所有相邻重复项】给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
                                   在 S 上反复执行重复项删除操作，直到无法继续删除。
                                   在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
    【用例1】
            输入："abbaca"
            输出："ca"
            解释：在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
                 之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
    ===================================================================================================
    【解题思路】1、遍历串 s ，如果栈空，则入栈
              2、遍历串 s ，如果元素和栈定元素不相等，则入栈
              3、遍历串 s ，如果元素和栈定元素相等，则出栈
              4、串遍历完，输出栈内元素，即为符合要求无相邻相的串

 */
public class RemoveDuplicates {
    public String removeDuplicates(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int end = -1;
        // 判断s非法情况
        if (s == null || s == "")
            return s;
        // 入栈情况
        for (int i = 0; i < s.length(); i++) {
            if (stringBuilder.length() == 0) {
                stringBuilder.append(s.charAt(i));
                end++;
                continue;
            }
            if (stringBuilder.charAt(end) != s.charAt(i)) {
                stringBuilder.append(s.charAt(i));
                end++;
            } else {
                stringBuilder.deleteCharAt(end);
                end--;
            }
        }
        if (stringBuilder.length() == 0)
            return null;
        return stringBuilder.toString();
    }
}
