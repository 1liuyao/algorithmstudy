package greed.algorithm;

import java.util.Arrays;

/*
    【435 无重叠区间】给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。
                   返回 需要移除区间的最小数量，使剩余区间互不重叠 。

    【示例 1】
            输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
            输出: 1
            解释: 移除 [1,3] 后，剩下的区间没有重叠。
    【示例 2】
            输入: intervals = [ [1,2], [1,2], [1,2] ]
            输出: 2
            解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
    【示例 3】
            输入: intervals = [ [1,2], [2,3] ]
            输出: 0
            解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
    ======================================================================================================
    【解题思路】
            1、怎么判断区间重叠？
               if(intervals[i][0] < intervals[i - 1][1])
            2、怎么判断区间不重叠？
               if(intervals[i][0] >= intervals[i - 1][1])
            3、所求：重叠区间个数
 */
public class EraseOverlapIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 按照区间左边界排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        int count = 0;

        for (int i = 1; i < intervals.length; i++) {
            // 计算重叠区间个数
            if (intervals[i][0] < intervals[i - 1][1]){
                count++;
                // 更新最小右边界
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            }
        }

        return count;
    }
}
