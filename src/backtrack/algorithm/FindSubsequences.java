package backtrack.algorithm;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/*
    【491 递增子序列】给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有【两个】元素 。
                   你可以按 任意顺序 返回答案。
                   数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
    【示例 1】
            输入：nums = [4,6,7,7]
            输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
    【示例 2】
            输入：nums = [4,4,3,2,1]
            输出：[[4,4]]
    ================================================================================================
    【解题思路】回溯法
        1、求至少含有两个元素：那就类似子集问题，收获结果不是只收获叶子结点
        2、如何去重呢？例如：针对索引 2 的 [4, 7] 和针对索引 3 的[4, 7]这两个元素本质上是重复元素
          （1）之前组合的去重考虑 树层去重 + 树枝去重 ： 本题需要考虑树层去重
          （2）之前去重我们将重复的元素通过【排序】变成相邻的元素，即使用 nums[i - 1] = nums[i] && used[i - 1] = 0
          （3）本题能采用以上方式 先排序，再去重？ 不可以，因为一旦排序，那整个集合的所有元素数大于 2 的子集都成了 合法的
              例如：若对[ 4 7 6 7 ]排序 ---> [ 4 6 7 7 ]  那么[ 4 6 7 7 ]就变成了合法的，但是从[ 4 7 6 7 ]无法得到这个结果
          （4）树层去重策略：采用 hashSet 数据结构去重，如果 nums[i] 在集合中出现了，则应该别剪枝
          （5）递增去重策略：每层广度遍历取元素时，要保证 所取元素 nums[i] 加入 path 后保证 path 是递增的，
                          所以 nums[i] > path最后一个元素

                         举例：nums = [1,2,2]
                                   空集 path = []
                                     [4 7 6 7]                                    访问根节点 path 为 [], 所以根节点代表空集
                 取4                取7            取6            取7
               path[4]           path[7]        path[6]        path[7]
================================================================================
             剩余[7 6 7]          剩余[6 7]       剩余[7]       重复不收获（数层剪枝）×
           取7    取6     取7     取6    取7         取7
      path[47]√ path[46]√重复×   不递增×  [77]√      [67]√
================================================================================
        剩余[6 7]   剩余[7]
       取6    取7      取7
      不递增× path[477]√path[467]√
================================================================================



 */
public class FindSubsequences {
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();
    // 注意本题的去重，不使用used数组，因为used数组记录了被path收集的元素是否被使用过
    // 记录了整棵树元素的使用情况
    // 但是本题只需要树层去重，记录本层元素如果被使用过，则剪枝
    public List<List<Integer>> findSubsequences(int[] nums) {
        int startIndex = 0;
        backTracking(nums, startIndex);
        return result;
    }

    public void backTracking(int[] nums, int startIndex) {
        // 子集问题，收集结果不只是在叶子结点，只要满足 递增 + 元素个数 > 2 的条件，只要进入递归结点就应该收集结果
        if(path.size() > 1) {
            result.add(new LinkedList<>(path));
        }
        // 确定回溯终止条件，其实针对 子集问题 ，终止条件已经包含在 for 的广度遍历中
        // 即每一层都是遍历到结点数组的末尾
        if (startIndex >= nums.length)
            return;
        // 存储每一层元素是否被选择过，做树层去重，进入到每一层时都需要清空
        HashSet<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            // 剪枝策略 ： 递增 + 去重 注意索引越界
            if((!path.isEmpty() && nums[i] < path.peekLast()) || set.contains(Integer.valueOf(nums[i])))
                continue;
            set.add(nums[i]);
            path.offerLast((nums[i]));
            backTracking(nums, i + 1);
            path.pollLast();
            // 注意：这里回溯操作不回溯set，因为set只对本层去重，与下层无关
        }
    }
}
