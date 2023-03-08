package stack.algorithm;

import java.util.Arrays;
import java.util.Stack;

/*
    【84 柱状图中最大的矩形】给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
                         求在该柱状图中，能够勾勒出来的矩形的最大面积。
    【示例 1】
            输入：heights = [2,1,5,6,2,3]
            输出：10
            解释：最大的矩形为图中红色区域，面积为 10
    【示例 2】
            输入： heights = [2,4]
            输出： 4
    =========================================================================================

 */
public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int[] newHeights = new int[heights.length + 2];
        int result = 0;
        // 首尾补零
        for (int h = 0; h < heights.length; h++) {
            newHeights[h + 1] = heights[h];
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        // 单调递减栈
        for (int i = 1; i < newHeights.length; i++) {
            if (newHeights[i] > newHeights[stack.peek()]){
                stack.push(i);
            } else if (newHeights[i] == newHeights[stack.peek()]) {
                stack.pop();
                stack.push(i);
            }else{
                while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]){
                    int midHeight = newHeights[stack.pop()];

                    if (!stack.isEmpty()){
                        // 计算面积
                        int rightIndex = i;
                        int leftIndex = stack.peek();
                        int area = midHeight * (rightIndex - leftIndex - 1);
                        result = Math.max(result, area);
                    }
                }
                stack.push(i);
            }
        }

        return result;
    }
}
