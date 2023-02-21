package greed.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    【56 合并区间】以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
                 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

    【示例 1】
            输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
            输出：[[1,6],[8,10],[15,18]]
            解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    【示例 2】
            输入：intervals = [[1,4],[4,5]]
            输出：[[1,5]]
            解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
    ===========================================================================================
    【解题思路】
            1、所有元素按照左区间升序排列

              1-----3                               收集结果 [1 3]
                 2------6                           [2 6] 和 [1 3] 比较，区间重叠，更新区间为 [1 6] , 结果集更新为[1 6]
                           8----10                  [8 10] 和 [1 6] 比较，区间不重叠，结果集更新为 [1 6] [8 10]
                                     15----18       [15 18] 和 [8 10] 比较，区间不重叠，结果集更新为 [1 6] [8 10] [15 18]
            2、如何判断区间重叠
               intervals[i] 和 结果集 result 最后一个元素比较，if intervals[i][0] <= end[1]
               即，intervals[i]的左区间小于等于 result 最后一个元素的右区间
            3、如何更新区间
               如果不重叠，则直接加入结果集，不需要更新区间
               如果重叠，则 更新最小左边界，最大右边界，删除结果集最后一个元素，并将更新的区间加入结果集
 */
public class Merge {
    public int[][] merge(int[][] intervals) {
        // 按照左边界升序排列
        Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            if (result.isEmpty())
                // 第一个元素直接加入结果集
                result.add(intervals[i]);
            else{
                // 获取结果集最后一个元素
                int[] end = result.get(result.size() - 1);
                // 如果 intervals[i] 和 结果集最后一个元素重叠
                if (intervals[i][0] <= end[1]){
                    int[] temp = new int[2];
                    // 更新重叠后的区间
                    // 左边界其实不用更新，一定是 end[0] 更小，因为初始已经按照左边界升序排列了
                    temp[0] = Math.min(intervals[i][0], end[0]);
                    temp[1] = Math.max(intervals[i][1], end[1]);
                    // 删除结果集末尾元素
                    result.remove(result.size() - 1);
                    // 将更新区间加入结果集
                    result.add(temp);
                }else {
                    // 如果不重叠直接加入结果集
                    result.add(intervals[i]);
                }
            }
        }
        return result.toArray(new int[0][]);
    }
}
