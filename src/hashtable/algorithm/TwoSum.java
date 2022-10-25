package hashtable.algorithm;

import java.util.HashMap;

/*
    【1 两数之和】给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
                你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
                你可以按任意顺序返回答案。
    【用例1】
            输入：nums = [2,7,11,15], target = 9
            输出：[0,1]
            解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
    【用例2】
            输入：nums = [3,2,4], target = 6
            输出：[1,2]
    【用例3】
            输入：nums = [3,3], target = 6
            输出：[0,1]
    ===================================================================================================================
    【解题思路】

    【哈希表法】：1、为什么想到哈希表法？
                  举例：nums = [2,7,11,15], target = 9，当我们遍历到元素 2 时，希望在表里找到 7，这样就可以满足和为target
                  也就是说，当我们遍历 nums 元素时，我们需要【查找】target - nums[i]是否出现过，这样的查找过程就需要用到哈希表
               2、哈希表应该选择什么样的数据结构？
                 （1）我们需要按照数组元素的值进行查找
                 （2）题目需要返回符合条件元素的【下标】
                 （3）选择 map 存储 <元素值，下标 >
               3、map的作用是什么？
                  存储我们在数组中遍历过的元素
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        // 步骤一：定义存储 <元素值，索引> 的哈希表
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        // 步骤二：遍历数组，将遍历到的元素存储到哈希表中
        for (int i = 0; i < nums.length; i++) {
            // 遍历到元素 i 时，查找 target - nums[i] 是否在哈希表中存在
            // 注意：题目假设每种输入只会对应一个答案
            if (hashMap.containsKey(target - nums[i])) {
                result[0] = i;
                result[1] = hashMap.get(target - nums[i]);
            }
            hashMap.put(nums[i],i);
        }
        return result;
    }
}
