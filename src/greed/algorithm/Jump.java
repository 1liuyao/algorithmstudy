package greed.algorithm;
/*
    【45 跳跃游戏 II】给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
                    每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
                    0 <= j <= nums[i]
                    i + j < n
                    返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。

    【示例 1】
            输入: nums = [2,3,1,1,4]
            输出: 2
            解释: 跳到最后一个位置的最小跳跃数是 2。
                 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
    【示例 2】
            输入: nums = [2,3,0,1,4]
            输出: 2
    ==================================================================================================================
    【解题思路】
                    2   3   1   1   4
                    | cover                      当前处于位置 0，当前覆盖范围 cover = 0，下一跳覆盖范围 next = 2
                    |-------| next
                    2   3   1   1   4            发现在当前 cover 覆盖范围内，发现走不到数组末尾，所以扩大当前覆盖范围 cover = next;
                    |-------| cover              走到索引位置 1，更新下一跳覆盖范围 next = 3，跳数++;
                        |-----------| next       走到索引位置 2，已到达当前 cover 覆盖范围最远处，但仍未走到数组尾部，更新 cover = next
                                                 跳数++，发现 next 恰好是数组尾部，所以可以通过 2 跳到达数组末尾



 */
public class Jump {
    public int jump(int[] nums) {
        int cover = 0;
        int count = 0;
        int next = 0;

        for (int i = 0; i < nums.length; i++) {
            // 注意：next 永远保持下一跳能到达的最远范围
            next = Math.max(next, i + nums[i]);
            // 走到当前范围末端
            if (i == cover){
                // 是否当前范围就已经覆盖了数组末尾
                if (cover != nums.length - 1){
                    // 可以扩展当前覆盖范围为下一跳覆盖范围
                    cover = next;
                    count++;
                    // 如果下一跳已经到达数组末尾，则已经找到最小步数，无需再循环
                    if (next == nums.length - 1){
                        break;
                    }
                }else{
                    break;
                }
            }
        }

        return count;
    }
}
