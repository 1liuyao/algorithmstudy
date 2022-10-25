package stack.algorithm;

import java.util.*;

/*
    【347 前 K 个高频元素】给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。
                        你可以按 任意顺序 返回答案。
                   进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
    【用例1】
            输入: nums = [1,1,1,2,2,3], k = 2
            输出: [1,2]
    【用例2】
            输入: nums = [1], k = 1
            输出: [1]
    ==================================================================================
    【解决方案】
            1、统计数组中每一个元素出现的次数，数据结构选择 map <数组元素值，该元素出现的频率>
            2、对频率进行排序，取前 k 个最大值
            3、排序应该选择那种方式，快速排序时间复杂度O(n log n)，当n的规模很大时，log n 的性能就大幅下降
               但是我们只需要前 k 个最大值，此时选择堆排序，对应java的对象是 PriorityQueue 优先级队列
            4、那我们是选择大顶堆还是小顶堆进行排序呢？
              （1）堆内最大只维护 k 个元素
              （2）考虑使用大顶堆，当我们加入一个元素时，就需要取出栈顶元素，此时较大元素被剔除，没有被保留在堆中，
                  所以采用小顶堆，此时堆中以从小到大的顺序保存这前 k 个高频元素，
                  如果需要按照从高到低的顺序输出，则需要对堆取出的元素进行 reverse 输出
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // 遍历数组，统计数据元素和其出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i]))
                map.put(nums[i], 1);
            else
                map.put(nums[i], map.get(nums[i]) + 1);
        }

        // 遍历 map 集合，依次加入小顶堆，维护 k 个高频元素
        // 用一个数组存储键值，这样方便取出元素的值
        PriorityQueue<int[]> queue = new PriorityQueue<>((s1,s2)->(s1[1] - s2[1]));

        for (Map.Entry<Integer, Integer> q: map.entrySet()) {
            // 如果当前堆元素小于 k 个，那么直接添加元素就可以
            if(queue.size() < k) {
                queue.add(new int[]{q.getKey(), q.getValue()});
            }else {
                // 如果要插入的元素比栈定元素小，那么就没必要插入了
                if(queue.peek() != null && q.getValue() > queue.peek()[1]){
                    queue.add(new int[]{q.getKey(), q.getValue()});
                    queue.poll();
                }
            }

        }

        // 将优先队列中的值读取到结果集
        int[] result = new int[k];

//        for (int ki = k - 1; ki >= 0; ki--) {
//            result[ki] = queue.poll()[0];
//        }

        Iterator<int[]> iterator = queue.iterator();
        while(iterator.hasNext()){
            result[ k -1 ] = iterator.next()[0];
            k--;
        }
        return result;
    }
}
