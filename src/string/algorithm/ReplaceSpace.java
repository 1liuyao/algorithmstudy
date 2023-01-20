package string.algorithm;
/*
    【剑指 Offer 05 替换空格】请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

    【示例 1】
            输入：s = "We are happy."
            输出："We%20are%20happy."
     =======================================================================
     【解题思路】双指针法：其实很多数组填充类的问题，都可以先预先给数组扩容带填充后的大小，然后在从后向前进行操作。
               这么做有两个好处：
               （1）不用申请新数组。
               （2）从后向前填充元素，避免了从前向后填充元素时，每次添加元素都要将添加元素之后的所有元素向后移动的问题。

             1、确定空格被替换后，数组的总长度
             2、声明两个尾指针：一个指向未扩充的字符串尾部，一个指向扩充字符串尾部
             3、双指针移动过程：
                We are happy.空空空空
                           |      |
                          rear1 rear2
                We are happhappy.
                      |   |
                    rear1 rear2
                We are h%20happy.
                      ||
                  rear1 rear2
 */
public class ReplaceSpace {
    public static String replaceSpace(String s) {
        int rear1 = s.length() - 1;
        // 这里一定要写 rear1，因为随着 s 不断拼接空格，会导致 s 长度逐渐变大，如果写成 s.length() 会导致死循环
        for(int i = 0; i <= rear1; i++){
            if(s.charAt(i) == ' ')
                s = s + "  ";
        }
        int rear2 = s.length() - 1;
        if (rear1 == rear2)
            return s;
        char[] chars = s.toCharArray();

        while (rear1 >= 0 && rear2 >= 0){
            if (chars[rear1] == ' '){
                chars[rear2--] = '0';
                chars[rear2--] = '2';
                chars[rear2] = '%';
            }else {
                chars[rear2] = chars[rear1];
            }
            rear1--;
            rear2--;
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String s = "We are happy.";
        String s1 = replaceSpace(s);
        System.out.println(s1);
    }
}
