package stack.algorithm;

import java.util.Stack;

/*
    【20 有效的括号】给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
                  有效字符串需满足：
                                左括号必须用相同类型的右括号闭合。
                                左括号必须以正确的顺序闭合。
                                每个右括号都有一个对应的相同类型的左括号。
    【用例 1】
            输入：s = "()"
            输出：true
    【示例 2】
            输入：s = "()[]{}"
            输出：true
    【示例 3】
            输入：s = "(]"
            输出：false
    ======================================================================================
    【解题思路】：1、哪些情况代表字符串【无效】（排除了无效情况剩下的就是有效情况）
                 （1）左括号多了：( {} [] ( )
                 （2）右括号多了: () {} ]
                 （3）左括号个数和右括号个数相等，但是类型不匹配：[ { ( } )]
               2、当遇到左括号[ '(' '{' '[' ]时，将对应的【右括号】压入栈，这样在出栈的时候可以减少判断
               3、当遇到右括号时，执行出栈判断
                 （1）左括号多了：出现元素和右括号依次相等，但是全部出栈操作执行完，栈不为空
                 （2）右括号多了：遇到第一个多出来的右括号时，栈已经空了
                 （3）左括号个数和右括号个数相等，但是类型不匹配：出栈元素和当前右括号不匹配，不是一种类型

 */
public class IsValid {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        // 步骤1：遍历字符串执行入栈和出栈操作
        for (int i = 0; i < s.length(); i++) {
            // 步骤2：遇到左括号入栈
            if (s.charAt(i) == '{')
                stack.push('}');
            else if (s.charAt(i) == '[') {
                stack.push(']');
            } else if (s.charAt(i) == '(') {
                stack.push(')');
            } else {// 遇到右括号，无效情况处理
                // 右括号多时栈为空
                if (stack.isEmpty())
                    return false;
                char c = stack.peek().charValue();
                // 左括号个数和右括号个数相等，但是类型不匹配
                boolean b = (!stack.isEmpty()) && (c != s.charAt(i));
                if (b)
                    return false;
                // 遇到右括号，有效情况直接出栈
                stack.pop();
            }
        }
        // 左括号多了，栈不为空
        return stack.isEmpty();
    }
    // 二刷
    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(')');
            else if (s.charAt(i) == '{')
                stack.push('}');
            else if (s.charAt(i) == '[')
                stack.push(']');
            else {
                // 右括号多了或者不相等
                if (stack.isEmpty() || s.charAt(i) != stack.pop())
                    return false;
            }
        }
        // 做括号多了
        return stack.isEmpty();
    }
}
