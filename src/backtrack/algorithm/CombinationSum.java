package backtrack.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
    【39 组合总和】给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
                 找出 candidates 中可以使数字和为目标数 target 的所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
                 candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
                 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
    【用例1】
            输入：candidates = [2,3,6,7], target = 7
            输出：[[2,2,3],[7]]
            解释：
            2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
            7 也是一个候选， 7 = 7 。
            仅有这两种组合。
    【用例2】
            输入: candidates = [2,3,5], target = 8
            输出: [[2,2,2,2],[2,3,3],[3,5]]
    【用例3】
            输入: candidates = [2], target = 1
            输出: []
     ========================================================================================
    【回溯法】例如：candidates = [2,3,5], target = 8
     =================================================================================================================
                                                                   [2 3 5]
                                    取2                               取3                            取5
                                 计算sum=0+2                       计算sum=0+3                   计算sum=0+5
     =================================================================================================================
                                  [2 3 5]                             [3 5]                      [5]
                        取2          取3           取5              取3      取5                   取5
                    sum=2+2      sum=2+3        sum=2+5        sum=3+3    sum=3+5               sum=5+5
                                                                            √(sum = target)      ×(sum > target停止递归)                      ×
     =================================================================================================================
                     [2 3 5]        [3 5]         [5]            [3 5]   收集结果[3 5]
                  取2   取3   取5    取3 取5        取5          取3    取5
               sum(6)sum(7)sum(9)  sum(8)sum(10) sum(12)    sum(9)  sum(11)
                             ×       √      ×      ×           ×      ×
     =================================================================================================================
             [2 3 5]   [3 5]      收集[2 3 3]
        取2   取3   取5 取3 取5
    sum(8)sum(10)sum(14)sum(10)sum(12)
     √      ×     ×      ×      ×
     =================================================================================================================
     收集[2 2 2 2]

            1、树的深度递归层数是 和 条件控制的：sum == target
            2、由于元素可以重复取，所以从上层到下层，结点集合的起始位置是 i
            3、【剪枝优化】：由于题目中 candidates 是无序的，考虑如果candidates是有序的，
                          例如针对根结点[2 3 5]，考虑如果 target = 2，当取3时，sum > target，
                          因此后面取5操作是没必要执行的，因为 sum 一定会大于 target
               ※所以针对每一个结点 for 横向遍历时，只要当前孩子分支sum + path[i] > target，那么后面的孩子也就没必要存在了
                 当前前提一定是【candidates是有序的】
 */
public class CombinationSum {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int startIndex = 0;
        int sum = 0;
        backTracking(candidates, target, sum, startIndex);
        return result;
    }

    private void backTracking(int[] candidates, int target, int sum, int startIndex) {
        // 确定回溯终止条件
        // 坑：第一个终止条件必须写，因为如果不添加，那么在sum == target相等后，sum > target会一直成立，递归是无法停止的
        //    题目并没有限定结果集内元素个数，所以没了这个限制，很容易就栈溢出
        if (sum > target){
            return;
        }
        if (sum == target) {
            result.add(new LinkedList<>(path));
            return;
        }
        // 确定回溯函数单层处理逻辑
        for (int i = startIndex; i < candidates.length; i++) {
            path.offerLast(candidates[i]);
            sum = sum + candidates[i];
            backTracking(candidates, target, sum, i);
            sum = sum - candidates[i];
            path.removeLast();
        }
    }

    // 剪枝优化：candidates有序 + 横向剪枝
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        int startIndex = 0;
        int sum = 0;
        // 剪枝优化：candidates有序
        Arrays.sort(candidates);
        backTracking1(candidates,target, sum, startIndex);
        return result;
    }

    private void backTracking1(int[] candidates, int target, int sum, int startIndex) {
        if (sum > target){
            return;
        }
        if (sum == target) {
            result.add(new LinkedList<>(path));
            return;
        }
        // 剪枝优化 横向剪枝
//        for (int i = startIndex; (i < candidates.length) && ((sum + candidates[i]) <= target); i++) {
//            path.offerLast(candidates[i]);
//            sum = sum + candidates[i];
//            backTracking1(candidates, target, sum, i);
//            sum = sum - candidates[i];
//            path.removeLast();
//        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target)
                break;
            path.offerLast(candidates[i]);
            sum = sum + candidates[i];
            backTracking1(candidates, target, sum, i);
            sum = sum - candidates[i];
            path.removeLast();
        }
    }

}
