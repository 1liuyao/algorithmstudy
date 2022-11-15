package sort.selectsort;
/*
    选择排序升序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] nums = {49, 38, 65, 97, 76, 13, 27, 49};
//        int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8};
//        selectSort(nums1);
        selectSort1(nums);
    }

    private static void selectSort(int[] nums) {
        // 定义已排序序列尾指针
        int rear = -1;
        int temp;
        // 定义每轮排序最小值 min 下标
        int min ;
        // 待排序序列中选取最小值
        for (rear = -1; rear < nums.length - 1 ; rear++) {
            min = rear + 1;
            for (int i = rear + 1; i < nums.length; i++) {
                if (nums[min] > nums[i]){
                    min = i;
                }
            }
            if (min != rear + 1){
                temp = nums[rear + 1];
                nums[rear + 1] = nums[min];
                nums[min] = temp;
            }
            travelNums(nums);
        }
    }

    private static void selectSort1(int[] nums) {
        // 定义已排序序列尾指针
        int temp;
        // 定义每轮排序最小值 min 下标
        int min ;
        // 待排序序列中选取最小值
        for (int i = 0; i <= nums.length - 2 ; i++) {
            min = i;
            for (int j = i; j <= nums.length - 1; j++) {
                if (nums[min] > nums[j]){
                    min = j;
                }
            }
            if (min != i){
                temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
            travelNums(nums);
        }
    }

    private static void travelNums(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
