package dynamic.test;

import dynamic.algorithm.fullbag.CoinChange;

public class CoinChangeTest {
    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        CoinChange coinChange = new CoinChange();
        int i = coinChange.coinChange(coins, amount);
        System.out.println(i);
        System.out.println(Integer.MAX_VALUE + 1);
    }
}
