package hashtable.algorithm;
/*
    【18 四数之和】给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
                 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]
                 （若两个四元组元素一一对应，则认为两个四元组重复）：
                （1）0 <= a, b, c, d < n
                （2）a、b、c 和 d 互不相同
                （3）nums[a] + nums[b] + nums[c] + nums[d] == target
                （4）你可以按 任意顺序 返回答案 。
    【用例1】
            输入：nums = [1,0,-1,0,-2,2], target = 0
            输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    【用例2】
            输入：nums = [2,2,2,2,2], target = 8
            输出：[[2,2,2,2]]
    =========================================================================================
    【解题思路】
    【双指针法】和三数之和类似，只是多固定了一个数
            举例：nums = [-1,0,1,2,-1,-4,2] 排序后为：[-4,-1,-1,0,1,2 2]
                       ---------------------------------------------------------
                                [-5 -4  -1  -1  0  1  2  2]
                                  i  j left            right
                                  a  b  c                d
                       ---------------------------------------------------------
              1、相当于固定了 i j 通过移动 left 和 right 指针满足 四元组的和 = target
              2、在处理四元组中的每一个元素时，需要考虑 【剪枝】 + 【去重】 操作
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<Integer> four = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int left ;
        int right;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] > target)
                return result;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                // 如果nums[i]为负数，nums[i] > target无法剪枝，因为nums[i]的下一个元素可能还是负数，加上后可能 < target
                if (nums[i] + nums[j] > 0 && nums[i] + nums[j] > target)
                    break;
                // 对 a 去重
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                else {
                    left = j + 1;
                    right = nums.length - 1;
                    // 寻找以 nums[i] 为 a 的符合 和条件 的三元组
                    while (left < right) {
                        if ((nums[i] + nums[j] + nums[left] + nums[right]) > target) {
                            right--;
                        } else if ((nums[i] + nums[j] + nums[left] + nums[right]) < target) {
                            left++;
                        } else { // 当找到符合条件的三元组，则收集结果
                            four.add(nums[i]);
                            four.add(nums[j]);
                            four.add(nums[left]);
                            four.add(nums[right]);
                            result.add(new ArrayList<>(four));
                            four.clear();
                            // 对 b 和 c 去重
                            while (left < nums.length - 1 && nums[left] == nums[left + 1])
                                left++;
                            while (right > 0 && nums[right] == nums[right - 1])
                                right--;
                            // 如果没有重复元素，继续寻找下一个以 nums[i]为a 的三元组
                            // 以下判断不可缺少，否则当无重复元素，left 和 right 又不更新，那么会死循环
                            if (left < nums.length - 1 && nums[left] != nums[left + 1]) {
                                left++;
                            }
                            if (right > 0 && nums[right] != nums[right - 1]) {
                                right--;
                            }
                        }

                    }
                }
            }
        }
        return result;
    }
}
