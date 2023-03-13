package extra;

import java.util.Arrays;

/*
    【1365 有多少小于当前数字的数字】给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
                                换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
                                以数组形式返回答案。
    【示例 1】
            输入：nums = [8,1,2,2,3]
            输出：[4,0,1,1,3]
            解释：
            对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
            对于 nums[1]=1 不存在比它小的数字。
            对于 nums[2]=2 存在一个比它小的数字：（1）。
            对于 nums[3]=2 存在一个比它小的数字：（1）。
            对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
    【示例 2】
            输入：nums = [6,5,4,8]
            输出：[2,1,0,3]
    【示例 3】
            输入：nums = [7,7,7,7]
            输出：[0,0,0,0]
    =================================================================================================================
    【解题思路】
            1、排序
            元素   8  1  2  2  3         排序后    1  2  2  3  8
            索引   0  1  2  3  4                  0  1  2  3  4

            2、发现规律：（1）比元素 8 小的元素个数是恰好是排序后的索引 4
                       （2）当元素值相等时，技巧出现，需要从后向前遍历
                           例如：从 后 向 前 遍历，第一个 2，索引是 2，遍历到第二个 2，索引是 1，正好覆盖
            3、如何根据元素获得索引：哈希表
              （1）哈希表结构：元素值有限 [0, 100]，哈希表采用数组，长度是101
              （2）从后向前遍历排序后的数组构建hash表
                  0  1  2  3  4  5  6  7  8
                        1  3              4
            4、输出结果：正向遍历 未排序 数组 查询哈希表 获得索引值，即为比当前元素值小的元素个数
 */
public class SmallerNumbersThanCurrent {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] hash = new int[101];

        int[] temp = Arrays.copyOfRange(nums, 0, nums.length);

        int[] result = new int[nums.length];

        Arrays.sort(temp);

        for (int i = temp.length - 1; i >= 0; i--) {
            hash[temp[i]] = i;
        }

        for (int i = 0; i < nums.length; i++) {
            result[i] = hash[nums[i]];
        }

        return result;
    }
}
