package hashtable.algorithm;

import java.util.HashSet;
import java.util.Set;

/*
    【349 两个数组的交集】：给定两个数组 nums1 和 nums2 ，返回 它们的交集。
                        输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序。
                        提示：
                            1 <= nums1.length, nums2.length <= 1000
                            0 <= nums1[i], nums2[i] <= 1000
    【用例1】
            输入：nums1 = [1,2,2,1], nums2 = [2,2]
            输出：[2]
    【用例2】
            输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
            输出：[9,4]
            解释：[4,9] 也是可通过的
    ============================================================================
    【解决方案】

    【哈希表法】判断数组交集其实就是看一个数组中的元素是否在另一个数组中出现过，当这种查找情景出现时，就要考虑使用哈希表法
        1、考虑哈希表应该选取哪种结构：（1）数组是可以的，因为题目限制数组长度是1000
                                 （2）set也可以，并且具备【去重】的效果
        2、遍历 nums1 ，将元素放入哈希表
        3、遍历 nums2 判断是否在hash表中出现过，如果出现就收集结果，不出现就跳过判断下一个

 */
public class Intersection {
    // 哈希表采用set数据结构
    public int[] intersection(int[] nums1, int[] nums2) {
        // 步骤1：初始化hash表、结果收集容器
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        // 步骤2：遍历 nums1 ，将元素放入哈希表
        for (int i = 0; i < nums1.length; i++) {
            hashSet.add(nums1[i]);
        }
        // 步骤3：遍历 nums2 判断是否在hash表中出现过
        for (int i = 0; i < nums2.length; i++) {
            if (hashSet.contains(nums2[i])) {
                result.add(nums2[i]);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // 哈希表采用数组
    public int[] intersection1(int[] nums1, int[] nums2) {
        // 步骤1：初始化hash表、结果收集容器
        int[] hashSet = new int[1001];
        HashSet<Integer> result = new HashSet<>();
        // 步骤2：遍历 nums1 ，将元素放入哈希表
        for (int i = 0; i < nums1.length; i++) {
            if (hashSet[nums1[i]] != 1)
                hashSet[nums1[i]] = 1;
        }
        // 步骤3：遍历 nums2 判断是否在hash表中出现过
        for (int i = 0; i < nums2.length; i++) {
            if (hashSet[nums2[i]] == 1) {
                result.add(nums2[i]);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
