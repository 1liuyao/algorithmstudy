package hashtable.algorithm;

import java.util.HashMap;

/*
    【454 四数相加 II】给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
                    （1）0 <= i, j, k, l < n
                    （2）nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
    【用例1】
            输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
            输出：2
            解释：
            两个元组如下：
            1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
            2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
    【用例2】
            输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
            输出：1
    ==============================================================================================================
    【解题思路】

    【暴力法】（1）4个 for 遍历数组，找到所有的元素组合，如果和为 0 ，那么计数 count ++
            （2）时间复杂度 O(n的四次方)

    【哈希表法】1、为什么使用哈希表法？
                这个题目虽然是分别从四个数组中挑选元素相加求和，本质上，可以看成两数相加求和的题目
                举例：target = 0
                     数组 A [1,2]       A + B       将 A + B 存储在哈希表中后，只需要查找 target - (C + D) 是否在表中存在
                     数组 B [-2,-1]                 这种查找过程就正好对应哈希表的结构
                     数组 C [-1,2]      C + D       这个过程将时间负责度降为 O(2*(n的平方))
                     数组 D [0,2]
             2、哈希表选择那种数据结构？
               （1）首先 A + B 的值需要存储
               （2）其次需要记录 A + B 出现的次数，因为题目最后需要返回符合 和为0 条件的元素组合数目
               （3）选择 map <A + B的值 , A + B 出现的次数>
             3、当存在 3 种组合使得 A + B的值为 5 ，那么如果在 C + D 中找到 -5，那么符合 和条件为0 的组合数需要 +3
                因此，在对符合 和条件 的组合结果进行计数时，需要不断加上map中的value，而不是单纯的++

 */
public class FourSumCount {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 步骤1：初始化哈希表、计数器
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = 0;
        int target = 0;
        // 步骤2：将 A 数组 + B 数组的值存储在哈希表中，并记录和出现的次数
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (hashMap.containsKey(nums1[i] + nums2[j])) {
                    hashMap.replace(nums1[i] + nums2[j],hashMap.get(nums1[i] + nums2[j])+1);
                }else {
                    hashMap.put(nums1[i] + nums2[j], 1);
                }
            }
        }
        // 步骤3：将 C 数组 + D 如果 target - (C + D)在哈希表中存在，则更新计数器
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                if (hashMap.containsKey(target - (nums3[i] + nums4[j]))) {
                    count = count + hashMap.get(target - (nums3[i] + nums4[j]));
                }
            }
        }
        return count;
    }
}
