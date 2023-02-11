package bit.test;

import bit.algorithm.HammingWeight;

public class HammingWeightTest {
    public static void main(String[] args) {
        int n = 11;
        HammingWeight hammingWeight = new HammingWeight();
        hammingWeight.hammingWeight1(n);
        System.out.println(11 ^ 1);
    }
}
