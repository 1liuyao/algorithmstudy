package backtrack.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    【78 子集】给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
             解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

    【用例 1】
            输入：nums = [1,2,3]
            输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    【用例 2】
            输入：nums = [0]
            输出：[[],[0]]
    ==============================================================================
    【解题思路】回溯法：本题收获的结点是树中所有结点，而非只是叶子结点（※※※）
              举例：nums = [1,2,3]

                                   [1 2 3]                                    访问根节点 path 为 [], 所以根节点代表空集
                  取1                 取2                取3
        ====================================================================
               收获[1]               收获[2]            收获[3]
               剩余[2 3]             剩余[3]              空
               取2    取3             取3
        ====================================================================
          收获[1 2]   收获[1 3]      收获[2 3]
           剩余[3]     空              空
           取3
        ====================================================================
          收获[1 2 3]
 */
public class Subsets {
    LinkedList<Integer> path= new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        int startIndex = 0;
        backTracking(nums, startIndex);
        // 加入空集
        result.add(new LinkedList<>());
        return result;
    }

    // 步骤1：确定回溯函数参数和返回值
    private void backTracking(int[] nums, int startIndex) {

        // 注意：在此时收获结果也是可以的，先当于递归进入下一层的时候，收获上一层的结果
        // 这样写有一个好处，访问数层第一层根节点，此时 path 为 [] ,这样就不用调用函数对空集情况做处理，默认根节点就对应空集
        // result.add(new LinkedList<>(path));

        // 步骤2：确定回溯函数终止条件
        // 注意这个终止条件可以省略，因为当startIndex == nums.length无法进入循环，则走到函数右大括号结束了函数
        if (startIndex == nums.length)
            return;
        // 步骤3：确定单层递归逻辑
        for (int i = startIndex; i < nums.length; i++) {
            path.offerLast(nums[i]);
            result.add(new LinkedList<>(path));
            backTracking(nums, i+1);
            path.pollLast();
        }
    }
}
