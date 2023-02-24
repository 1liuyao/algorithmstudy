package stack.algorithm;

import java.util.LinkedList;

/*
    【739 每日温度】给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
                  其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
                  如果气温在这之后都不会升高，请在该位置用 0 来代替。
    【示例 1】
            输入: temperatures = [73,74,75,71,69,72,76,73]
            输出: [1,1,4,2,1,1,0,0]
    【示例 2】
            输入: temperatures = [30,40,50,60]
            输出: [1,1,1,0]
    【示例 3】
            输入: temperatures = [30,60,90]
            输出: [1,1,0]
    =================================================================================
    【解题思路】
            1、单调栈适合解决什么问题？
              （1）求比当前元素 右边 大 或者 小 的第一个元素
              （2）求比当前元素 左边 大 或者 小 的第一个元素

            2、单调栈内元素规律
               永远保持栈内元素是递增或者递减的

            3、单调栈中存什么？
               如果单调栈中存储的是元素值，那么题目所有是索引差值，通过元素值找到索引位置是麻烦的，
               如果数组存在重复元素，那么根本查不到，所以单调栈中存储【索引值】

            4、如何确定单调栈是递增还是递减的  【从栈顶到栈底的方向】
              （1）求比本元素大的值，递增
              （2）求比本元素小的值，递减

            5、为什么要用栈存储
               因为当我们遍历到哪一个元素的时候，需要将它和以往遍历过的元素进行比较
               例如：遍历到 76， 76 是 75 71 69 72 的右边第一个大的元素， 这些元素都需要存储

            6、手动模拟单调栈解决问题过程
            temperatures = [73,74,75,71,69,72,76,73]      result = [0,0,0,0,0,0,0,0]
            --------------
                       0 |    首先在栈中放入 1 个元素  result = [0,0,0,0,0,0,0,0]
            --------------
            --------------
                       1 |    遍历到 74，比栈顶元素 73 大，因此 73 已经找到第一个比它大的元素，result[0] = 1 - 0 = 1
            --------------    74 入栈， 73 出栈
            --------------
                       2 |    遍历到 75，比栈顶元素 74 大，因此 73 已经找到第一个比它大的元素，result[1] = 2 - 1 = 1
            --------------    75 入栈， 74 出栈
            --------------
                     3 2 |    遍历到 71，比栈顶元素 75 小，因此 75 没有找到第一个比它大的元素，result 不更新，71 入栈
            --------------
            --------------
                   4 3 2 |    遍历到 69，比栈顶元素 71 小，因此 71 没有找到第一个比它大的元素，result 不更新，69 入栈
            --------------
            --------------
                     5 2 |    遍历到 72，比栈顶元素 69 大，因此 69 已经找到第一个比它大的元素，result[4] = 5 - 4 = 1， 69 出栈
            --------------    继续比较，比栈顶元素 71 大，因此 71 已经找到第一个比它大的元素，result[3] = 5 - 3 = 2， 71出栈，72入栈
            --------------
                       6 |    遍历到 76，比栈顶元素 72 大，因此 72 已经找到第一个比它大的元素，result[5] = 6 - 5 = 1， 72 出栈
            --------------    继续比较，比栈顶元素 75 大，因此 75 已经找到第一个比它大的元素，result[2] = 6 - 2 = 4， 75出栈，76入栈
            --------------
                     7 6 |    遍历到 73，比栈顶元素 76 小，直接入栈，遍历结束，栈内剩余元素无法找到比它大的元素
            --------------    因此 result[7] = 0; result[6] = 0;

           7、遍历过程中的入栈出栈操作
             （1）如果遍历元素比栈顶元素小，那么直接入栈
             （2）如果遍历元素和栈顶元素相等，那么直接入栈
             （3）如果遍历元素比栈顶元素大，那么和栈顶元素比较，并更新 result
                 栈顶元素出队，继续和栈顶元素比较，直到遇到栈顶元素大的情况，遍历元素才能入栈

 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        LinkedList<Integer> stack = new LinkedList<>();

        stack.offerLast(0);
        for (int i = 1; i < temperatures.length; i++) {
            if (temperatures[i] < temperatures[stack.peekLast()])
                stack.offerLast(i);
            else if (temperatures[i] == temperatures[stack.peekLast()]) {
                stack.offerLast(i);
            }else {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peekLast()]){
                    result[stack.peekLast()] = i - stack.peekLast();
                    stack.pollLast();
                }
                stack.offerLast(i);
            }
        }

        return result;
    }

    // 优化代码
    public int[] dailyTemperatures1(int[] temperatures) {
        int[] result = new int[temperatures.length];
        LinkedList<Integer> stack = new LinkedList<>();

        stack.offerLast(0);
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peekLast()]){
                result[stack.peekLast()] = i - stack.peekLast();
                stack.pollLast();
            }
                stack.offerLast(i);
        }

        return result;
    }
}
