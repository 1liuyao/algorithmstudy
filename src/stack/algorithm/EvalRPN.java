package stack.algorithm;

import java.util.Stack;

/*
    【150 逆波兰表达式求值】根据 逆波兰表示法，求表达式的值。有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
                        注意 两个整数之间的除法只保留整数部分。
                        可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
    【用例 1】
            输入：tokens = ["2","1","+","3","*"]
            输出：9
            解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
    【示例 2】
            输入：tokens = ["4","13","5","/","+"]
            输出：6
            解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
    【示例 3】
            输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
            输出：22
            解释：该算式转化为常见的中缀算术表达式为：
              ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
            = ((10 * (6 / (12 * -11))) + 17) + 5
            = ((10 * (6 / -132)) + 17) + 5
            = ((10 * 0) + 17) + 5
            = (0 + 17) + 5
            = 17 + 5
            = 22
     =================================================================================
     【解题思路】

     【出入栈情况】1、遍历到数值需要入栈
                2、遇到运算符连续出栈两个元素 op1 op2 ，并进行运算 op2 运算符 op1，并将运算结果入栈
                3、重复此过程直到遍历完字符串数组
 */
public class EvalRPN {
    public int evalRPN(String[] tokens) {
        // 初始化操作数
        int opt1, opt2, result = 0;
        Stack<Integer> stack = new Stack<>();
        if (tokens.length == 0)
            return result;
        // 遍历字符串数组执行响应的入栈、出栈操作
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i]) || "-".equals(tokens[i]) || "*".equals(tokens[i]) || "/".equals(tokens[i])){
                if(stack.capacity() < 2)
                    return result;
                // 遇到运算符连续出栈两个元素 op1 op2
                opt1 = stack.pop();
                opt2 = stack.pop();
                result = compute(opt1, tokens[i], opt2);
                // 将运算结果入栈
                stack.push(result);
            }else {
                // 数字压栈
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
        // 坑：这里不能 return result;
        // 因为当 token = {"18"}这种只有数字的情况，需要返回 18，然而这时候 result = 0 返回有误
    }

    public int compute(int opt1, String operator, int opt2){
        if (operator == "+")
            return opt2 + opt1;
        else if (operator == "-")
            return opt2 - opt1;
        else if (operator == "*")
            return opt2 * opt1;
        else
            return opt2 / opt1;
    }
    // 二刷
    public int evalRPN1(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < tokens.length; i++){
            if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")){
                int opt1 = Integer.valueOf(stack.pop());
                int opt2 = Integer.valueOf(stack.pop());
                // 坑：题目描述：第二个出栈元素 是 被除数
                int result = compute(opt2, opt1, tokens[i]);
                System.out.println(result);
                stack.push(result);
            }else{
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }
    public int compute(int opt1, int opt2, String opt){
        if("+".equals(opt))
            return opt1 + opt2;
        else if("-".equals(opt))
            return opt1 - opt2;
        else if("*".equals(opt))
            return opt1 * opt2;
        return opt1 / opt2;
    }
}
