package stack.test;

import stack.algorithm.EvalRPN;

public class EvalRPNTest {
    public static void main(String[] args) {
        // String[] tokens =  {"2","1","+","3","*"};
        // String[] tokens =  {"4","13","5","/","+"};
        String[] tokens =  {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        EvalRPN evalRPN = new EvalRPN();
        int result = evalRPN.evalRPN(tokens);
        System.out.println(result);
    }
}
