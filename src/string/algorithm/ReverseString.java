package string.algorithm;
/*
    【344 反转字符串】编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
                   不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
    【用例1】
         输入：s = ["h","e","l","l","o"]
         输出：["o","l","l","e","h"]
    【用例2】
         输入：s = ["H","a","n","n","a","h"]
         输出：["h","a","n","n","a","H"]
    =============================================================
    【解题思路】采用双指针法
        1、初始时，头指针指向字符数组起点，尾指针指向字符数组尾部
        2、反转字符串，相当于让第 0 个结点和第 n-1 个结点交换，让第 1 个结点和第 n-2 个结点交换，...
           相当于，头尾指针所指位置交换元素，然后共同向字符数组中间移动，再交换各自所指元素的过程
        3、当头指针和尾指针相遇，指向同一个结点时，不需要交换元素，因此当front < rear 时，可以不断执行交换过程
 */
public class ReverseString {
    public void reverseString(char[] s) {
        // 步骤1：初始化头尾指针
        int front = 0;
        int rear = s.length - 1;
        // 步骤2：执行交换操作，逆置数组
        while (front < rear) {
            swap(front,rear,s);
            front++;
            rear--;
        }
    }

    private void swap(int front, int rear, char[] s) {
        // 交换front和rear指向的元素
        char temp = 0;
        temp = s[front];
        s[front] = s[rear];
        s[rear] = temp;
    }
}
