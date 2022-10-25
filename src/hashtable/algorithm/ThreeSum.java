package hashtable.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    【15 三数之和】给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
                满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
                请你返回所有和为 0 且不重复的三元组。
                注意：答案中不可以包含重复的三元组。
    【用例1】
            输入：nums = [-1,0,1,2,-1,-4]
            输出：[[-1,-1,2],[-1,0,1]]
            解释：
            nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
            nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
            nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
            不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
            注意，输出的顺序和三元组的顺序并不重要。
    【用例2】
            输入：nums = [0,1,1]
            输出：[]
            解释：唯一可能的三元组和不为 0 。
    【用例3】
            输入：nums = [0,0,0]
            输出：[[0,0,0]]
            解释：唯一可能的三元组和为 0 。
    ===========================================================================
    【解题思路】：当然仍然可以相两数之和一样，采用哈希表法，先计算 a+b 然后 判断 target- c是否在哈希表中存在
               但是去重很难

    【双指针法】：1、【前提】是数组已经排好序了，这么做是为了方便去重
               2、考虑怎样表示三元组[a, b, c]
               举例：nums = [-1,0,1,2,-1,-4,2] 排序后为：[-4,-1,-1,0,1,2 2]
               ---------------------------------------------------------
                            -4  -1  -1  0  1  2  2]
                             i left            right
                             a  b                c
               ---------------------------------------------------------
               3、如何找到符合 和为0 条件的三元组
               （1）if遍历到 a 时，a > 0， 由于数组是有序的，那么b 和 c 一定比 0 大，三个大于0的数是不可能相加为 0 的，那么直接结束搜寻过程
               （2）if遍历到 a 时，a <= 0, 那么接下来需要寻找符合 和条件 的 b 和 c
                   if a + b + c > 0 , 那说明选取的数值过大了，所以需要 right--
                   if a + b + c < 0 , 那说明选取的数值过小了，所以需要 left++
                   if a + b + c = 0 , 那就需要收集结果了
               4、如何对三元组[a, b, c]进行去重
               （1）对 a 进行去重：对 a 的选取其实是遍历数组的过程，那么考虑例子 nums[1] = nums[2] = -1，
                                当 a = nums[1] = -1 时，则说明以 -1 开头符合 和条件 的三元组已经被找到，
                                那么，当a = nums[2] = -1 时，无需再次寻找（注意：数组是有序的）
               （2）对 b，c 去重：当我们收集结果后，发现下一个 left 和 right 对应的元素和上一个相同，那么这样的 b 和 c 就是重复的
              举例：- 2   0   0   0   1   1   2   2   2
                    i   left                       right
                   a=-2  b=0                        c=2   在a=-2时，成功收集结果[-2 0 2 ]
                         |----------->   < ----------|    发现 nums[left] = nums[left + 1]
                                   left right             发现 nums[right] = nums[right - 1]
                                                          以上这样的 b 和 c 都是重复的，因此需要不断移动 left 和 right
                                                          直到找到不重复的 b 和 c，再寻找新的以 a=-2 为开始 满足和条件的三元组
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> triple = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int left ;
        int right;
        // 步骤1：取a元素，并去重
        for (int i = 0; i < nums.length; i++) {
            // 由于数组是有序的，那么b 和 c 一定比 a 大，三个大于0的数是不可能相加为 0 的，那么直接结束搜寻过程
            if (nums[i] > 0)
                return result;
            // 对 a 去重
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            else {
                left = i + 1;
                right = nums.length -1;
                // 寻找以 nums[i] 为 a 的符合 和条件 的三元组
                while (left < right){
                    if ((nums[i] + nums[left] + nums[right] )> 0){
                        right --;
                    }else if ((nums[i] + nums[left] + nums[right]) < 0){
                        left ++;
                    }else { // 当找到符合条件的三元组，则收集结果
                        triple.add(nums[i]);
                        triple.add(nums[left]);
                        triple.add(nums[right]);
                        result.add(new ArrayList<>(triple));
                        triple.clear();
                        // 对 b 和 c 去重
                        while (left < nums.length - 1 && nums[left] == nums[left + 1])
                            left++;
                        while (right > 0 && nums[right] == nums[right - 1])
                            right--;
                        // 如果没有重复元素，继续寻找下一个以 nums[i]为a 的三元组
                        // 以下判断不可缺少，否则当无重复元素，left 和 right 又不更新，那么会死循环
                        if(left < nums.length - 1 && nums[left] != nums[left + 1]){
                            left++;
                        }
                        if(right > 0 && nums[right] != nums[right - 1]){
                            right--;
                        }
                    }

                }
            }
        }
        return result;
    }
}
