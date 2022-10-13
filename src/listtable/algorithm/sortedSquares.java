package listtable.algorithm;
/*
    【977含负数数组平方后排序】给你一个非递减顺序排序的整数数组nums，返回每个数字的平方组成的新数组，要求也按【非递减顺序】排序。

    【用例1】
        输入：nums = [-4,-1,0,3,10]
        输出：[0,1,9,16,100]
        解释：平方后，数组变为 [16,1,0,9,100]
        排序后，数组变为 [0,1,9,16,100]

    【示例 2】
        输入：nums = [-7,-3,2,3,11]
        输出：[4,9,9,49,121]
    =============================================
    【解题思路】：【双指针法】
            【发现规律】：负数在未进行平方操作时，排在前面，但平方后，按升序排列将排在后面
                        因此越接近原点的数平方后越小，越远离原点的数平方后越大
                        那么，从数组两端向中间靠拢，值会越来越小
            【头指针】：指向数组最左边元素，逐渐向右移动，因为往数组中间走元素平方后的值越小
            【尾指针】：指向数组最右边元素，逐渐向左移动
 */
public class sortedSquares {
    public int[] sortedSquares(int[] nums) {
        // 步骤1：初始化平方升序后的新数组，首、尾指针
        int[] square = new int[nums.length];
        int front = 0;

        // 头尾指针从数组两端向数组中部移动，先遍历的元素平方值为大值
        // 为了保证平方后升序，所以从新数组的尾端向前依次插入元素
        int rear = nums.length-1;

        // 平方排序后数组指针
        int squareIndex=nums.length-1;

        // 步骤2 ：数组中可参与排序的元素区间，左闭右闭
        while (front <= rear){
            // 步骤3：比较两端元素平方值，取较大者存入新数组，并向数组中部方向移动指针
            if (nums[front] * nums[front] > nums[rear] * nums[rear]) {
                square[squareIndex--] = nums[front] * nums[front];
                front++;
            }else {
                square[squareIndex--] = nums[rear] * nums[rear];
                rear--;
            }
        }
        return square;
    }
}
