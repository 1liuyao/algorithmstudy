package listtable.test;

import listtable.algorithm.BinarySearch;

public class BinarySearchTest {
    public static void main(String[] args) {
//         【用例1】
//         输入: nums = [-1,0,3,5,9,12], target = 9
//         输出: 4
//         解释: 9 出现在 nums 中并且下标为 4
        int[] nums=new int[]{-1,0,3,5,9,12};
        BinarySearch binarySearch = new BinarySearch();
        int result=binarySearch.search(nums,9);
        System.out.println(result);

//        【用例2】:
//        输入: nums = [-1,0,3,5,9,12], target = 2
//        输出: -1
//        解释: 2 不存在 nums 中因此返回 -1
        int result2=binarySearch.search2(nums,2);
        System.out.println(result2);

    }
}
