package extra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
    【1207 独一无二的出现次数】给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
                           如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
    【示例 1】
            输入：arr = [1,2,2,1,1,3]
            输出：true
            解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
    【示例 2】
            输入：arr = [1,2]
            输出：false
    【示例 3】
            输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
            输出：true
    ======================================================================================
    【解题思路】两次哈希查找：统计每个数字出现的次数用 map
                         统计次数是否重复，即遍历 map，判断元素次数是否在 set 中出现，出现则返回false，否则加入hash表
 */
public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        // 统计每个元素出现的次数
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        // 为次数去重
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                hashMap.put(arr[i], hashMap.get(arr[i]) + 1);
            } else {
                hashMap.put(arr[i], 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        for (Map.Entry<Integer, Integer> e :
                entries) {
            if (hashSet.contains(e.getValue())) {
                return false;
            } else {
                hashSet.add(e.getValue());
            }
        }

        return true;
    }
}
