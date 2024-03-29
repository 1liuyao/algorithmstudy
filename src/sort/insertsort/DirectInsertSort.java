package sort.insertsort;
/*
    nums = [49 38 65 97 76 13 27 49]
    直接插入升序排列
 */
public class DirectInsertSort {
    public static void main(String[] args) {
        int[] nums = {49, 38, 65, 97, 76, 13, 27, 49};
        //int[] result = directInsertSort(nums);
        directInsertSort1(nums);
    }

    private static int[] directInsertSort(int[] nums) {
        // 声明有序队列
        int[] result = new int[nums.length];
        // 声明待排序序列尾指针，初始状态没有任何元素
        int j = -1;

        // 顺序遍历 nums 获得待排序元素
        for (int i = 0; i < nums.length; i++) {
            // 待排序元素依次和有序队列元素比较，遇到第一个比他小的元素插入在该元素的后面
            while (j >= 0 && nums[i] < result[j]){
                result[j + 1] = result[j];
                j--;
            }
            // 找到插入位置
            result[j + 1] = nums[i];
            travelNums(result);
            // 更新有序序列尾指针
            j = i;
        }
        return result;
    }
    // 使用一个数组实现
    private static void directInsertSort1(int[] nums) {
        // 记录待排元素
        int temp;
        // 已排序序列尾指针，初始状态没有任何元素
        int j = -1 ;
        // 顺序遍历 nums 获得待排序元素
        for (int i = 0; i < nums.length; i++) {
            // 待排元素前是有序队列
            temp = nums[i];
            // 待排序元素依次和有序队列元素比较，遇到第一个比他小的元素插入在该元素的后面
            while (j >= 0 && temp < nums[j]){
                nums[j + 1] = nums[j];
                j--;
            }
            // 找到插入位置
            nums[j + 1] = temp;
            travelNums(nums);
            j = i;
        }
    }
    private static void travelNums(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
