package stack.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/*
    【496 下一个更大元素 I】nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
                        给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
                        对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2
                        确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
                        返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
    【示例 1】
            输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
            输出：[-1,3,-1]
            解释：nums1 中每个值的下一个更大元素如下所述：
            - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
            - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
            - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
    【示例 2】
            输入：nums1 = [2,4], nums2 = [1,2,3,4].
            输出：[3,-1]
            解释：nums1 中每个值的下一个更大元素如下所述：
            - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
            - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
    =====================================================================================================
    【解题思路】
            1、收集结果的数组应该定义成多大？
               明确要收集哪些元素：找到 nums1 中 在 nums2 出现过的元素，收集第一个比该元素大的元素
               所以结果集的大小一定是 nums1 的长度

            2、单调栈应该存储哪些元素
               应该存储 nums2 中元素，因为是在 nums2 中查找第一个大的元素

            3、什么时候收集结果
               正常 单调栈，当我们遍历 nums2 时，发现 遍历到的元素 大于 栈顶元素，那么我们会收集结果
               因为此时栈顶元素已经找到了 第一个比它大的元素。
               本题，收集结果时，需要判断栈顶元素是否在 nums1 中出现过，出现过才收集结果

            4、收集到的结果放在哪个索引位置？
               当我们收集结果时，需要看 栈顶元素 在 nums1 中处于哪个位置
               这种查询操作就需要用到 hash 表
 */
public class NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 初始化result
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);

        // 记录 nums1 元素以及索引
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            hashMap.put(nums1[i], i);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(nums2[0]);

        for (int i = 1; i < nums2.length; i++) {
            if (!stack.isEmpty() && nums2[i] <= stack.peek())
                stack.push(nums2[i]);
            else{
                while(!stack.isEmpty() && nums2[i] > stack.peek()){
                    // 通过单调栈找到第一个比栈顶大的元素后，不一定要立刻收集结果
                    // 只对 nums1 中出现过的数据收集结果
                    if (hashMap.containsKey(stack.peek())){
                        result[hashMap.get(stack.peek()).intValue()] = nums2[i];
                    }
                    stack.pop();
                }
                stack.push(nums2[i]);
            }
        }

        return result;
    }
}
