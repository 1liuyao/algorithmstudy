package listtable.algorithm;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
    【209长度最小的子数组】给定一个含有n个正整数的数组和一个正整数 target 。找出该数组中满足其和 ≥ target 的长度最小的
                       连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。

    【用例1】：
        输入：target = 7, nums = [2,3,1,2,4,3]
        输出：2
        解释：子数组[4,3]是该条件下的长度最小的子数组。

    【用例2】：
        输入：target = 4, nums = [1,4,4]
        输出：1

    【用例3】：
        输入：target = 11, nums = [1,1,1,1,1,1,1,1]
        输出：0
    ===================================================================
    【解题思路】：【滑动窗口】通过滑动窗口去截取数组中的子数组，并根据题目条件进行筛选
            1、滑动窗口尾指针移动策略：遍历数组，算出[数组起始位置,尾指针]区间内元素和
            2、滑动窗口头指针移动策略：如果[数组起始位置,尾指针]区间内元素和 ≥ target ，说明是符合元素和条件的子数组，
                                  但是并不一定是最短的，此时需要移动头指针。在满足和条件的前提下，向尾指针方向移动，
                                  缩短子数组长度，【直到找到】长度最短，且满组和条件的子数组。
 */
public class MinSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        // 步骤1：初始化最短子数组长度、滑动窗口首尾指针、子数组元素和
        // 子数组长度的搜索是从大到小逼近的，所以初始化为最大值，即数组长度
        int minLength = nums.length;
        int winLength;
        int winRear;
        int winFront = 0;
        int sumSubArray = 0;

        // 如果整个数组元素和都小于target，则不存在满足和条件的最短子数组
        if (Arrays.stream(nums).sum() < target)
            return 0;
        // 步骤2：移动滑动窗口尾指针，寻求满足和 ≥ target的子数组
        for (winRear = 0; winRear < nums.length; winRear++){
            sumSubArray = sumSubArray +nums[winRear];

            //if (sumSubArray >= target){
            // 步骤3：满足和条件后，需移动头指针，逐渐缩短子数组长度
            //       更新滑动窗口元素和，后续如果仍满足和条件，则继续移动头指针，
            //       【直到】不满足和条件，则停止移动头指针
            // 注意：头指针需要在满足和条件时，不断移动，缩短子数组长度，所以用while
            while (sumSubArray >= target){
                // 获得当前满足和条件的子数组长度，即滑动窗口长度
                winLength = winRear-winFront+1;
                // 更新子数组最短长度
                minLength = minLength < winLength ? minLength : winLength;
                sumSubArray = sumSubArray - nums[winFront];
                winFront++;
            }
        }
        return minLength;
    }
}
