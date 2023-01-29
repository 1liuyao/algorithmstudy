package backtrack.algorithm;

import java.util.*;

/*
    【47 全排列】给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

    【用例 1】
            输入：nums = [1,1,2]
            输出：[[1,1,2], [1,2,1], [2,1,1]]
    【用例 2】
            输入：nums = [1,2,3]
            输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    ====================================================================
    【解决方案】【回溯法】
            1、排列问题：从索引 0 开始遍历，used[i] = 0 时，未被选择的元素才能参与排列
            2、去重策略：（1）明确什么时重复：nums[i] == nums[i - 1]
                       （2）树层去重：nums[i - 1] = 0，此时证明以 nums[i - 1] 为开头的排列已经都找到了，
                                   等广度遍历到 nums[i] 时， 已经回溯完了nums[i - 1]，因此nums[i - 1] 从 1 变成了0
                       （3）树枝不需要去重，因为虽然值，一样但是代表了不同索引位置上的元素
 */
public class PermuteUnique {
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTracking(nums);
        return result;
    }

    public void backTracking(int[] nums) {
        // 确定回溯终止条件
        if (path.size() == nums.length){
            result.add(new LinkedList<>(path));
            return;
        }
        // 广度遍历，确定单层递归逻辑
        for (int i = 0; i < nums.length; i++) {
            // 需要去重的情况：【取过的元素不能取】 + 【树层需要去重】
            if (used[i] || (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]))
                continue;
            path.offerFirst(nums[i]);
            used[i] = true;
            backTracking(nums);
            used[i] = false;
            path.pollFirst();
        }
    }

    public void backTracking1(int[] nums) {
        // 确定回溯终止条件
        if (path.size() == nums.length){
            result.add(new LinkedList<>(path));
            return;
        }
        Set<Integer> set = new HashSet<>();
        // 广度遍历，确定单层递归逻辑
        for (int i = 0; i < nums.length; i++) {
            // 需要去重的情况：【取过的元素不能取】 + 【树层需要去重】
            if(used[i] || set.contains(nums[i]))
                continue;

            set.add(nums[i]);
            path.offerFirst(nums[i]);
            used[i] = true;
            backTracking(nums);
            used[i] = false;
            path.pollFirst();
        }
    }
}
