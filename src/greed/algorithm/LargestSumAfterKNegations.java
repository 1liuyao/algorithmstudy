package greed.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/*
    【1005 K 次取反后最大化的数组和】给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
                                选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
                                重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
                                以这种方式修改数组后，返回数组 可能的最大和 。
    【示例 1】
            输入：nums = [4,2,3], k = 1
            输出：5
            解释：选择下标 1 ，nums 变为 [4,-2,3] 。
    【示例 2】
            输入：nums = [3,-1,0,2], k = 3
            输出：6
            解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
    【示例 3】
            输入：nums = [2,-3,-1,5,-4], k = 2
            输出：13
            解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
    ======================================================================================
    【解题思路】贪什么？

    【方案一】要求和最大，永远 减去 最小值
            问题：求最小值复杂度高，同时如果 k 是偶数，其实对数组和是没有影响的

    【方案二】利用 “-”, 将绝对值大的 负数 变成正数，当数组中只剩下正数的时候，并且 k 是奇数，只需要减去数组中的最小值

 */
public class LargestSumAfterKNegations {
    // 方案一
    public int largestSumAfterKNegations(int[] nums, int k) {
        int result = 0;

        while(k > 0){
            int[] min = getMin(nums);
            nums[min[1]] = -min[0];
            result = Arrays.stream(nums).sum();
            k--;
        }
        return result;
    }

    public int[] getMin(int[] nums){
        int minIndex = 0;
        int min = 101;
        for (int i = 0; i < nums.length; i++) {
            if(min > nums[i]){
                min = nums[i];
                minIndex = i;
            }
        }
        return new int[]{min, minIndex};
    }

    // 方案二
    public int largestSumAfterKNegations1(int[] nums, int k) {
        int sum = 0;
        Integer[] integers = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Arrays.sort(integers, (o1, o2) -> Math.abs((Integer) o2) - Math.abs((Integer) o1));

        for (int i = 0; i < integers.length; i++) {
            if (integers[i] < 0 && k > 0){
                k--;
                integers[i] = - integers[i];
            }
        }
        sum = Arrays.stream(integers).mapToInt(Integer :: intValue).sum();
        if (k % 2 == 1){
            // 最小值就是按绝对值排序后的最后一个元素
             sum = sum - 2 * integers[nums.length - 1];
        }

        return sum;
    }
}
