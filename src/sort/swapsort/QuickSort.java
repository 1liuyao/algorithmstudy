package sort.swapsort;
/*
    nums = [49 38 65 97 76 13 27 49]
    快速升序排列
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {49, 38, 65, 97, 76, 13, 27, 49};
        // 左闭右闭区间
        int left = 0;
        int right = nums.length - 1;
        quickSort(nums, left, right);
    }

    private static void quickSort(int[] nums, int left, int right) {
        // 递归终止条件
        if(left >= right){
            return;
        }
        int i = left;
        int j = right;
        int temp = nums[i];
        while(i < j) {
            // 右指针遍历
            while (i < j && nums[j] >= temp)
                j--;
            // 保证退出循环条件是 nums[j] < temp
            if (i < j) {
                nums[i] = nums[j];
                // 移动左指针
                i++;
            }
            // 左指针遍历
            while (i < j && nums[i] <= temp)
                i++;
            // 保证退出循环条件是 nums[j] > temp
            if (i < j) {
                nums[j] = nums[i];
                // 移动左指针
                j--;
            }
        }
        // 此处 i 和 j 相遇
        if (i == j)
            nums[i] = temp;
        travelNums(nums);
        // 递归排序左区间，递归排序右区间
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    private static void travelNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
