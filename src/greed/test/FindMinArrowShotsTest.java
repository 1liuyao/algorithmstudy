package greed.test;

import greed.algorithm.FindMinArrowShots;

public class FindMinArrowShotsTest {
    public static void main(String[] args) {
        int[][] nums = {{-2147483646,-2147483645},{2147483646,2147483647}};

        FindMinArrowShots findMinArrowShots = new FindMinArrowShots();
        int minArrowShots = findMinArrowShots.findMinArrowShots(nums);
        System.out.println(minArrowShots);
    }
}
