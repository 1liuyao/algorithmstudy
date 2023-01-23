package string.algorithm;

/*
    【151 反转字符串中的单词】给你一个字符串 s ，请你反转字符串中 单词 的顺序。
        单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
        返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
        注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，
        单词间应当仅用单个空格分隔，且不包含任何额外的空格。
        【用例1】
                输入：s = "the sky is blue"
                输出："blue is sky the"
        【示例2】
                输入：s = "  hello world  "
                输出："world hello"
                解释：反转后的字符串中不能存在前导空格和尾随空格。
        【用例3】
                输入：s = "a good   example"
                输出："example good a"
                解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
        ======================================================================
        【解题思路】先对字符串整体进行反转，再对每个字符串进行反转
                1、（预处理）删除串中多余空格：删除首个单词前的所有空格         ___hello__world__
                                         删除单词间多余空格，只保留一个空格    hello_world
                                         删除最后一个单词尾部的所有空格
                2、对字符串整体进行反转                                      dlrow_olleh
                3、对每个单词进行反转                                        world_hello

 */
public class ReverseWords {
    public String reverseWords(String s) {
        // 步骤1：去除字符串不合法空格
        String s1 = trimSpace(s);
        // 步骤2：对字符串整体进行反转
        StringBuilder stringBuilder = new StringBuilder(s1);
        StringBuilder reverse = stringBuilder.reverse();
        // 步骤3：对每个单词进行反转
        int start = 0;
        int end;
        String word;
        for (end = 0; end < s1.length(); end++) {
            // 如果走到数组尾部，那么截取最后一个单词
            if (stringBuilder.charAt(end) == ' ') {
                String substring = stringBuilder.substring(start, end);
                StringBuilder reverse1 = new StringBuilder(substring).reverse();
                stringBuilder.replace(start, end, reverse1.toString());
                start = end + 1;
            }
            if (end == s1.length() - 1) {
                String substring = stringBuilder.substring(start, s1.length());
                StringBuilder reverse1 = new StringBuilder(substring).reverse();
                stringBuilder.replace(start, s1.length(), reverse1.toString());
            }
        }
        return stringBuilder.toString();
    }

    // 使用快慢指针的思想删除字符串中的元素
    private String trimSpace(String s) {
        // 步骤1：初始化快慢指针
        int fast = 0;
        int slow = 0;
        char[] chars = s.toCharArray();
        for (fast = 0; fast < s.length(); fast++) {
            // 步骤2：当快指针所指元素不为''，应该将其放在slow位置
//            if (chars[fast] != ' ') {
//                // 如果是第一个单词，前面不应该保留任何空格
//                if (slow == 0) {
//                    // fast和slow同时移动走完一个单词
//                    while (chars[fast] != ' ' && fast < s.length()){
//                        chars[slow++] = chars[fast++];
//                    }
//                    // 走完一个单词后，就可以继续fast，寻找下一个单词
//                    continue;
//                }
//                if (slow != 0){
//                    // 如果非第一个单词，应该保留单词前的一个空格
//                    chars[slow++] = ' ';
//                    // fast和slow同时移动走完一个单词
//                    while (chars[fast] != ' ' && fast < s.length()){
//                        chars[slow++] = chars[fast++];
//                    }
//                }

            // 化简代码
            if (chars[fast] != ' ') {
                // 如果非第一个单词，应该保留单词前的一个空格
                if (slow != 0) {
                    chars[slow] = ' ';
                    // 因为加了空格的原因，导致fast比slow快一不，所以真正存储字符的位置是存储空格后的下一个位置
                    slow++;
                }
                // 如果是第一个单词那fast和slow同时移动走完一个单词
                // 注意一定把长度判断写在前面，否则能fast已经走到length，先判断chars[fast]就会抛出数组越界异常
                while (fast < s.length() && chars[fast] != ' ') {
                    chars[slow++] = chars[fast++];
                }
            }
        }

        return new String(chars).substring(0, slow);
    }

    // 二刷
    public String reverseWords1(String s) {
        s = trimSpace1(s);
        s = reverse(s, 0, s.length() - 1);
        int start = 0;
        int end = 0;

        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == ' ') {
                end = i - 1;
                s = reverse(s, start, end);
                start = i + 1;
            }
            if (i == s.length() - 1) {
                s = reverse(s, start, s.length() - 1);
            }
        }
        return s;
    }

    // 使用快慢指针的思想删除字符串中的元素
    private String trimSpace1(String s) {
        char[] chars = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            // 考虑什么样的元素应该放在 slow 中
            if (chars[fast] != ' ') {
                // 如果不是第一个单词
                if (slow != 0) {
                    chars[slow++] = ' ';
                }
                // 遍历一整个单词
                while (fast < s.length() && chars[fast] != ' ') {
                    chars[slow++] = chars[fast++];
                }
            }
        }
        return String.valueOf(chars, 0,slow);
    }

    private String reverse(String s, int start, int end) {
        char[] chars = s.toCharArray();
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(chars);
    }
}
