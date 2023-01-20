package string.algorithm;
/*
    【541 反转字符串 II】给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
                       1、如果剩余字符少于 k 个，则将剩余字符全部反转。
                       2、如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
    【用例1】
        输入：s = "abcdefg", abcdefg
        输出："bacdfeg"
    【用例2】
        输入：s = "abcd", k = 2
        输出："bacd"
     ========================================================
     【解题思路】一定要弄懂规则，确定好最小处理单元
               a b c d e f g   k = 2
               |-----| |-----|
                  2k     2k
        1、确定最小处理单元：是长度为2k的字串
        2、针对每一个最下处理单元，规则如下：
            如果存在前 k 个元素，对[i,i+k)执行reverse操作
            如果不足k个元素，即[i,i+k)中右端点超过了数组长度，那么就不需要reverse
 */
public class ReverseStr2k {
    public String reverseStr(String s, int k) {
        // 步骤1：将数组按照2k个步长进行遍历
        StringBuilder stringBuilder = new StringBuilder(s);
        for (int i = 0; i < s.length(); i = i + 2*k) {
            // 步骤2：对前k个字符进行反转操作，当然得保证有k个元素存在
            // 右开原则，i+k==s.length()时，这2k个字串单元正好只有k个元素
            if (i + k <= s.length()) {
                // 左开右闭原则，取不到i+k
                String substring = s.substring(i, i + k);
                StringBuilder stringBuilder1 = new StringBuilder(substring).reverse();
                stringBuilder.replace(i, i+k, stringBuilder1.toString());
            }else {
                // 步骤3：在2k这个处理单元中，不够k个元素
                String substring = s.substring(i, stringBuilder.length());
                StringBuilder stringBuilder1 = new StringBuilder(substring).reverse();
                stringBuilder.replace(i, s.length(), stringBuilder1.toString());
            }
        }
        return stringBuilder.toString();
    }

    public String reverseStr1(String s, int k) {
        for(int i = 0; i < s.length(); i = i + 2*k){
            if(i + k <= s.length()){
                s = reverse(s, i, i + k - 1);
            }else{
                s = reverse(s, i, s.length() - 1);
            }
        }
        return s;
    }

    public String reverse(String s, int start, int end){
        char[] ss = s.toCharArray();
        while(start < end){
            char temp = ss[start];
            ss[start] = ss[end];
            ss[end] = temp;

            start++;
            end--;
        }
        return String.valueOf(ss);
    }
}
