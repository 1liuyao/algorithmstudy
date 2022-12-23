package stack.algorithm;

import stack.structure.Stack;

/*
    薯队长写了一篇笔记草稿，请你帮忙输出最后内容。
    1.输入字符包括，"("    ,    ")"    和    "<"和其他字符。
    2.其他字符表示笔记内容。
    3.()之间表示注释内容，任何字符都无效。    括号保证成对出现。
    4."<"表示退格,    删去前面一个笔记内容字符。括号不受"<"影响    。
 */
public class StringSolve {
    public static void main(String[] args) {
        String s ="Corona(Trump)USA<<<Virus";
        String result = solve(s);
        System.out.println(result);
    }

    private static String solve(String s) {
        // 对 s 进行合法判断
        if (s == null || s.length() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 入栈策略：如果是数字或者 ( ，则入栈
            if (chars[i] != ')' && chars[i] != '<')
                sb.append(chars[i]);
            if (chars[i] == ')') {
                //出栈直到遇到 (
                while(sb.charAt(sb.length() - 1) != '('){
                    sb.deleteCharAt(sb.length() - 1);
                }
                // 出栈 '('
                sb.deleteCharAt(sb.length() - 1);
            }
            if (chars[i] == '<'){
                // 出栈
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }
}
