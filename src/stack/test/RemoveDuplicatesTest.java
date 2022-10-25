package stack.test;

import stack.algorithm.RemoveDuplicates;

public class RemoveDuplicatesTest {
    public static void main(String[] args) {
        String s = "abbaca";
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        String s1 = removeDuplicates.removeDuplicates(s);
        System.out.println(s1);
    }
}
