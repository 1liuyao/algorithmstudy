package greed.algorithm;

import java.util.Arrays;

/*
    【135 分发糖果】n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
                  你需要按照以下要求，给这些孩子分发糖果：
                  每个孩子至少分配到 1 个糖果。
                  相邻两个孩子评分更高的孩子会获得更多的糖果。
                  请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
    【示例 1】
            输入：ratings = [1,0,2]
            输出：5
            解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
    【示例 2】
            输入：ratings = [1,2,2]
            输出：4
            解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
                 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
    ==================================================================================================
    【解题思路】如何解决【相邻两个孩子评分更高的孩子会获得更多的糖果】
            1、如果孩子在 i 位置，那么需要与 i - 1 位置和 i + 1 位置的孩子进行比较
               针对这种比较标准不唯一的问题，需要先确定一个标准，再比较另一个标准
            2、先按照右孩子大标准发放饼干
       ratings 1   0   2        i 从索引 1 开始，比较 ratings[i] 和 ratings[i - 1]
       candy   1   1   2

            3、再按照左孩子大标准发放饼干
       ratings 1   0   2        i 从索引 ratings.length - 2 开始，比较 ratings[i] 和 ratings[i + 1]
       candy   1   1   2
       candy   2   1   2

            4、左右孩子中取较大者，并求饼干总数
 */
public class Candy {
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1);
        // 如果右孩子大，那么得分应该比相邻的左孩子高
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1])
                candy[i] = candy[i - 1] + 1;
        }
        // 如果左孩子大，那么得分应该比相邻的右孩子高
        for (int i = candy.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
        }

        return Arrays.stream(candy).sum();
    }
}
