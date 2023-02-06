package greed.test;

import greed.algorithm.Candy;

public class CandyTest {
    public static void main(String[] args) {
        int[] ratings = new int[]{1,0,2};
        Candy candy = new Candy();
        int candy1 = candy.candy(ratings);
        System.out.println(candy1);
    }
}
