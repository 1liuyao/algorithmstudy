package listtable.algorithm;
/*
    【704二分查找法】：给定一个n个元素有序的（升序）整型数组nums 和一个目标值target，
                    写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
    【示例1】
            输入: nums = [-1,0,3,5,9,12], target = 9
            输出: 4
            解释: 9 出现在 nums 中并且下标为 4
    【示例2】:
            输入: nums = [-1,0,3,5,9,12], target = 2
            输出: -1
            解释: 2 不存在 nums 中因此返回 -1
    【提示】：
            你可以假设 nums中的所有元素是不重复的。
            n将在[1, 10000]之间。
            nums的每个元素都将在[-9999, 9999]之间。
    =========================================================================
    【解法思路】

    【搜索区间】：即满足搜索条件的区间，可分为【左闭右闭】和【左闭右开】区间
            1、左闭右闭：[1,2]说明在这个区间上右端点是【可以】取到的，因此[1,1]可以存在，代表索引为1的元素
            2、左闭右开：[1,2)说明在这个区间上右端点是【不可以】取到的，因此[1,1)不可能存在，在这样的区间上是不会有元素存在的

    【举例】：当我们要对nums = [-1,0,3,5,9,12]进行遍历时，索引index的范围可以有两种写法：
            1、左闭右闭：[0,nums.length-1]
            2、左闭右开：[0,nums.length)
 */

public class BinarySearch {

    // 方式一：搜索区间是左闭右闭的
    public int search(int[] nums, int target) {
        // 步骤1：初始化搜索区间的指针，搜索范围是整个数组
        int left=0;
        // 注意：我们要检索的元素范围是数组内的所有元素，下标从 0到数组长度减1，所以初始搜索范围为[0,numSize-1]
        int numSize=nums.length;
        int right=numSize-1;
        int mid;
        // 步骤2：进入【合法】搜索区间搜索关键字target,由于是左闭右闭的，所以left=right是合法区间
        while(left<=right){

            // 步骤3：选出与关键字target进行比较的数组元素
            mid=(left+right)/2;

            // 步骤4：对搜索结果进行判断
            if(nums[mid]>target)

                // 关键字小，去左半区间搜索，更新区间右端点，由于mid所指元素与关键字不匹配，
                // 则搜索区间更新后应不包含mid所指元素，搜索区间更新为[left,mid-1]
                right=mid-1;
            else if (nums[mid]<target)
                // 关键字小，去右半区间搜索，更新区间左端点
                left=mid+1;
            else
                // nums[mid]=target时，代表查找成功
                return mid;
        }
        // 如果在合法搜索区间内没有搜索到与关键字target相等的元素，则查找失败，返回-1
        return -1;
    }

    //方式2：搜索区间是左闭右开的
    public int search2(int[] nums, int target) {
        //步骤1：初始化搜索区间的指针，搜索范围是整个数组
        int left=0;
        //注意：我们要检索的元素范围是数组内的所有元素，下标从 0到数组长度减1
        int numSize=nums.length;
        //注意：由于区间右端点取不到，所以当遍历到数组的最后一个元素时，right值为numSize，也就是数组下标的取值范围为[0,numSize)
        int right=numSize;
        int mid;
        //步骤2：进入【合法】搜索区间搜索关键字target,由于是左闭右开的，所以left=right是非合法区间
        while(left<right){

            //步骤3：选出与关键字target进行比较的数组元素
            mid=(left+right)/2;

            //步骤4：对搜索结果进行判断
            if(nums[mid]>target)

                //关键字小，去左半区间搜索，更新区间右端点，由于右端点本身取不到，所以区间更新为[left,mid)等价于[left,mid-1]
                right=mid;
            else if (nums[mid]<target)
                //关键字小，去右半区间搜索，更新区间左端点
                left=mid+1;
            else
                //nums[mid]=target时，代表查找成功
                return mid;
        }
        //如果在合法搜索区间内没有搜索到与关键字target相等的元素，则查找失败，返回-1
        return -1;
    }
}
