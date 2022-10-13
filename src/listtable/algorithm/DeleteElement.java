package listtable.algorithm;
/*
    【27删除数组中元素】：给你一个数组 nums和一个值 val，你需要原地移除所有数值等于val的元素，并返回移除后数组的新长度。
                      不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
                      元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
    【用例】
        输入：nums = [3,2,2,3], val = 3
        输出：2, nums = [2,2]
    ========================================================
    【解题思路】
    【方案一】：【暴力方法】：遍历符合条件的元素，执行覆盖操作
            1、外层循环遍历数组
            2、内层循环执行覆盖元素操作

    【方案二】：【快慢指针法】：把符合条件的新数组元素放在正确的新数组位置上
            1、新数组：认为删除了所有数值【等于】val的元素后形成的数组为新数组
            2、快指针：遍历整个数组，筛选出所有【不等于】val的新数组元素
            3、慢指针：指向快指针元素应该在新数组中的位置
 */

import com.sun.istack.internal.NotNull;

public class DeleteElement {
    // 暴力解法
    public int deleteElement(int[] nums, int val){
        int size=nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val) {
                // 用i+1的元素覆盖i位置元素，i+2覆盖i+1位置元素,...,n-1覆盖n-2位置元素
                // j指向需要被移动的元素
                for (int j = i + 1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                // 由于i位置被i+1元素替换，所以需要i位置被覆盖上了新元素，然而这个新元素也需要被比较
                i--;
                size--;
            }
        }
        return size;
    }

    public int deleteElement2(int[] nums, int val){
        // 步骤1：初始化快慢指针
        int fast = 0;
        int slow = 0;
        for ( fast = 0; fast < nums.length; fast++) {

            // 步骤2：筛选新数组元素
            if(nums[fast] != val){
                //步骤3：将符合筛选条件的元素插入新数组对应位置
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
