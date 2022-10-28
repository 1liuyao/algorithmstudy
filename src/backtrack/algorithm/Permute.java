package backtrack.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*
    【46 全排列】给定一个【不含重复】数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

    【用例 1】
            输入：nums = [1,2,3]
            输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    【用例 2】
            输入：nums = [0,1]
            输出：[[0,1],[1,0]]
    【用例 3】
            输入：nums = [1]
            输出：[[1]]
    =====================================================================================
    【解题思路】回溯法
        1、used数组：被取出的元素为 1，代表使用过，未被取出参与排列的为 0
        2、广度遍历：从 used 为 0中取元素，相当于 used 为 1 的分支被砍掉了
        3、和组合的区别：永远从 0 位置开始，组合需要去重 [1 2 ] 和 [2 1] 是一个元素，需要从startIndex开始广度遍历
 */

public class Permute {
    boolean[] used;
    LinkedList<Integer> path =  new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0)
            return result;
        used = new boolean[nums.length];
        backTracking(nums);
        return result;
    }

    private void backTracking(int[] nums) {
        // 走到叶子结点回溯终止
        if (path.size() == nums.length){
            result.add(new LinkedList<>(path));
            return;
        }
        // 单层递归逻辑：【取数】 + 配置 used 数组
        for (int i = 0; i < nums.length; i++) {
            // 如果数被取过，则不取了，取下一个
            if (used[i])
                continue;
            path.offerFirst(nums[i]);
            used[i] = true;
            backTracking(nums);
            // 回溯撤销操作
            used[i] = false;
            path.pollFirst();
        }
    }
}
