package sort.swapsort;

/*
    nums = [49 38 65 97 76 13 27 49]
    冒泡升序排列
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {49, 38, 65, 97, 76, 13, 27, 49};
        //bubbleSort(nums);// 未优化，执行7轮
        //bubbleSort1(nums);  // 优化后，执行5轮
        bubbleSort2(nums);// 未优化，执行7轮
    }

    private static void bubbleSort(int[] nums) {
        // 交换变量
        int temp;
        // 确认遍历趟数 nums.length - 1
        for (int round = 0; round < nums.length - 1; round++) {
            // 每一轮参与交换操作的元素范围
            for (int i = 0; i < nums.length - 1 - round; i++) {
                if (nums[i] > nums[i + 1]) {
                    temp = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = temp;
                }
            }
            travelNums(nums);
        }
    }

    // 优化：如果遍历一趟的过程中，没有交换操作，则其他趟排序就不用做了
    private static void bubbleSort1(int[] nums) {
        // 交换变量
        int temp;
        // 标记交换一轮中是否发生变化
        boolean flag;
        // 确认遍历趟数 nums.length - 1
        for (int round = 0; round < nums.length - 1; round++) {
            flag = false;
            // 每一轮参与交换操作的元素范围
            for (int i = 0; i < nums.length - 1 - round; i++) {
                if (nums[i] > nums[i + 1]) {
                    temp = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = temp;

                    flag = true;
                }
            }
            travelNums(nums);
            if (flag == false) {
                System.out.println("轮次为 ：" + round);
                return;
            }
        }
    }

    private static void bubbleSort2(int[] nums) {
        // 交换变量
        int temp;
        // 标记交换一轮中是否发生变化
        boolean flag;

        // 已排序序列头指针范围 从 nums.length 到 1，因为 1 排好序了 0自然就排好了
        for (int head = nums.length; head >= 1; head--) {
            flag = false;
            // 从待排序序列中执行交换操作，逐步选出最大值
            for (int i = 0; i < head; i++) {
                if ((i + 1) < nums.length && nums[i] > nums[i + 1]) {
                    temp = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = temp;
                    flag = true;
                }
            }
            travelNums(nums);
            if (flag == false)
                return;
        }
    }

    private static void travelNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
