package dynamic.test;

import dynamic.algorithm.baseproblem.Fib;

public class FibTest {
    public static void main(String[] args) {
        Fib fib = new Fib();
        int n = 2;
        int result = fib.fib(n);
        System.out.println(result);
    }
}
