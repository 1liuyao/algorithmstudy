package backtrack.algorithm;

import java.util.*;

/*
    【90 子集 II】给你一个整数数组 nums ，其中可能包含【重复元素】，请你返回该数组所有可能的子集（幂集）。
                解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
    【用例 1】
            输入：nums = [1,2,2]
            输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
    【用例 2】
            输入：nums = [0]
            输出：[[],[0]]
    ====================================================================================
    【解题思路】回溯法【去重】+【收获结点为树中全部结点】
                  举例：nums = [1,2,2]
                                空集 path = []
                                   [1 2 2]                                    访问根节点 path 为 [], 所以根节点代表空集
                  取1                 取2                取2
             used=[1 0 0]        used=[0 1 0]        used=[0 0 1]
        ====================================================================
               收获[1]               收获[2]            重复不收获（数层剪枝）×
               剩余[2 2]             剩余[2]              空
            取2        取2         used=[0 1 1]
      used=[1 1 0]   used=[1 0 1]
        ====================================================================
          收获[1 2] 不收获（树层剪枝）× 收获[2 2]
           剩余[2]     空              空
           取2
        used=[1 1 1]
        ====================================================================
          收获[1 2 2]

         1、什么样的情况代表重复？
           集合中含有重复的元素，但是他们的下标不同，其实是不同的 2，获得结果中，2 的个数不能超过 nums中个数
         2、去重的前提：保证数组是有序的，让重复的元素变成相邻的元素，那么判断是否重复的条件是 nums[i] = nums[i - 1]
         3、去重策略：（1）树层去重：针对[1 2 2]，当我们取了 1 位置的2，进行深度遍历时，已经包含了从 2 位置的 2 开始遍历的结果
                                所以树层需要去重，当 nums[i] = nums[i - 1] 相等时，需要判断 used 数组中 i - 1 位置是否为 0
                                如果为 0，就需要剪枝

                                为什么 used 数组 i - 1 位置为 0，才代表和 nums[i] 重复，因为遍历到 nums[i - 1] 时，深度递归找到了 从
                                i - 1 开始的所有组合，但深度遍历完成会经历回溯过程，回溯完 nums[i - 1] 进入广度遍历 nums[i] 时，u
                                sed数组 i - 1 位置从 1 变成 0， 所以used数组 i - 1 位置为 0 恰恰说明以 nums[i - 1] 开始的子树已经遍历完成
                     （2）树枝去重：当前层 nums[i - 1] 和 下一层 nums[i] 相等，此时 used 数组 i - 1 位置为 1。
                                 这种情况是无需剪枝的，因为 nums[i - 1] 和  nums[i] 虽然值相等，但是他们本质是不同位置上的元素
 */
public class SubsetsWithDup {
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();
    boolean[] used;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int startIndex = 0;
        used = new boolean[nums.length];
        // 数组必须排序，让重复的元素变成相邻的元素，方便去重
        Arrays.sort(nums);
        backTracking(nums, startIndex);
        return result;
    }

    // 确定回溯函数的参数和返回值
    public void backTracking(int[] nums, int startIndex) {
        // 收获结果
           result.add(new LinkedList<>(path));
        // 确定回溯函数终止条件
        if(startIndex == nums.length)
            return;
        // 确定单层回溯逻辑
        for (int i = startIndex; i < nums.length; i++) {
            // 树层去重
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false)
                continue;
            // 取元素
            path.offerLast(nums[i]);
            // 设置 used 数组
            used[i] = true;
            backTracking(nums, i + 1);
            // 回溯撤销操作
            used[i] = false;
            path.pollLast();
        }
    }

    // 树层去重不使用 used, 使用 startIndex
    public void backTracking1(int[] nums, int startIndex) {
        // 收获结果
        result.add(new LinkedList<>(path));
        // 确定回溯函数终止条件
        if(startIndex == nums.length)
            return;
        // 确定单层回溯逻辑
        for (int i = startIndex; i < nums.length; i++) {
            // 树层去重
            if (i > startIndex && nums[i] == nums[i - 1] ) {
                continue;
            }

            // 取元素
            path.offerLast(nums[i]);

            backTracking(nums, i + 1);

            path.pollLast();
        }
    }

    // Set去重
    public void backTracking2(int[] nums, int startIndex) {
        result.add(new LinkedList(path));

        if(startIndex == nums.length)
            return;

        // 记录每一层的结点，每一层都有一个新的 Set 容器， 每进入一层，set 需要 清空，仅记录当前层的结点
        Set<Integer> set = new HashSet<>();
        for(int i = startIndex; i < nums.length; i++){
            // 不适用 used 数组去重，使用 startIndex
            if(set.contains(Integer.valueOf(nums[i])))
                continue;
            // 不需要回溯 ：因为每一层递归都重新定义个一个空的 set，
            // 并不是像 path 一样，对于整棵树是唯一的，从下层到上层就需要回溯，否则遍历到本层时，如果不回溯，path 就 记录了整棵树的值
            set.add(nums[i]);
            path.offerLast(nums[i]);

            backTracking(nums, i + 1);

            path.pollLast();;
        }
    }
}
