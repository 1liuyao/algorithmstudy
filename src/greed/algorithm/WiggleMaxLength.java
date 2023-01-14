package greed.algorithm;
/*
    【376 摆动序列】如果连续数字之间的 差 严格地在正数和负数之间交替，则数字序列称为 摆动序列 。
                  第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
                  例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
                  相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，
                  第二个序列是因为它的最后一个差值为零。子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
                  给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
    【示例 1】
            输入：nums = [1,7,4,9,2,5]
            输出：6
            解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
    【示例 2】
            输入：nums = [1,17,5,10,13,15,10,5,16,8]
            输出：7
            解释：这个序列包含几个长度为 7 摆动序列。
            其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
    【示例 3】
            输入：nums = [1,2,3,4,5,6,7,8,9]
            输出：2
    ===================================================================================================================
    【解题思路】把摆动模拟成一种 上山下乡
             1、只有一个元素：  1        摆动序列最小长度 1
             2、两个元素：（1）     2        （2） 1   1       （3）1
                            1                                         2
                         针对两个元素，相当于前面 补一个同等元素，例如针对（1）
                             2                         2
                         1           转换成   1    1    curDiff = 2 - 1 = 1   代表出现一个峰值，摆动序列最小长度 2
                                             preDiff = 1 - 1 = 0
                         所以针对两个元素的情况，只需要把 preDiff 初始化成 0 即可，就可以和多个元素情况合在一起
             3、多个元素：（1）         2
                            1    1               preDiff = 0   curDiff = 1   代表出现一个峰值，摆动序列最小长度 2

                        （2）     2       4
                             1       3       5        result 初始成 1
                             preDiff = 0              curDiff = 2 - 1 = 1    一个波峰，result = 1 + 1 = 2
                             preDiff = curDiff = 1    curDiff = 2 - 3 = -1   result = 3
                             preDiff = curDiff = -1   curDiff = 4 - 3 = 1    result = 4
                             preDiff = curDiff = 1    curDiff = 4 - 5 = -1   result = 5
                        （3）    2   2
                            1           1
                             preDiff = 0              curDiff = 2 - 1 = 1    result = 1 + 1 = 2
                             preDiff = curDiff = 1    curDiff = 2 - 2 = 0    result = 3   错 不成立，没有形成波峰
                             preDiff = curDiff = 1    curDiff = 1 - 2 = -1    result = 3
                        （4）             3
                                   2  2
                               1
                            preDiff = 0              curDiff = 2 - 1 = 1    result = 1+1
                            preDiff = 1              curDiff = 2 - 2 = 0    不成立

                            这步出错，因为，上一步不成立，没有出现波峰，就不应该更新preDiff
                            preDiff = curDiff = 0    curDiff = 3 - 2 = 1    result = 3
                            应该为 preDiff = 1        curDiff = 3 - 2 = 1    不成立 ，result 仍为 2

                            preDiff =  1    curDiff = 3 - 3 = 0    不成立  result 仍为 2

            4、峰值出现条件：if ((curDiff > 0 && preDiff <= 0) || (preDiff >= 0 && curDiff < 0))
            5、如何更新 result 、preDiff、curDiff：一定是在峰值出现的时候才更新，并不是在遍历数组的过程中一直更新
 */
public class WiggleMaxLength {
    // 完全无法处理[0 0] [3 3 3 2 5] 这种情况
//    public int wiggleMaxLength(int[] nums) {
//        if(nums.length == 1)
//            return 1;
//        boolean[] result = new boolean[nums.length - 1];
//
//
//        for(int i = 0; i < nums.length - 1; i++){
//            if(nums[i + 1] >= nums[i])
//                result[i] = true;
//        }
//        int resultLen = result.length;
//        if (resultLen == 1)
//            return resultLen;
//        for(int i = 0; i < result.length - 1; i++){
//            if(result[i+1] == result[i])
//                resultLen--;
//        }
//        return resultLen + 1;
//    }
    public int wiggleMaxLength(int[] nums) {
        int preDiff = 0;
        int curDiff = 0;
        int result = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            // 判断是否存在摆动
            curDiff = nums[i + 1] - nums[i];
            if ((preDiff >= 0 && curDiff < 0) || (preDiff <= 0 && curDiff > 0)){
                result++;
                preDiff = curDiff;
            }
        }
        return result;
    }
}
