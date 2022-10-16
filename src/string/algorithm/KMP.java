package string.algorithm;
/*
    【28 找出字符串中第一个匹配项的下标】给你两个字符串 haystack 和 needle ，
                                   请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
                                   如果 needle 不是 haystack 的一部分，则返回  -1 。
    【用例1】
        输入：haystack = "sadbutsad", needle = "sad"
        输出：0
        解释："sad" 在下标 0 和 6 处匹配。
        第一个匹配项的下标是 0 ，所以返回 0 。
    【用例2】
        输入：haystack = "leetcode", needle = "leeto"
        输出：-1
        解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
     ===================================================================================================
     【解题思路】

     【暴力解法】遍历主串，从主串 index = 0 位置开始依次和模式串匹配，一旦出现不匹配的情况，则从主串 index = 位置开始，继续匹配...
     【KMP】这个解法只是优化了一旦主串和模式串不匹配，主串索引和模式串索引都需要回到初始位置重新遍历的问题
           一旦出现字符不匹配的情况，就让模式串索引 j 跳转到 next[j-1]，主串索引 i 继续向前移动
           主串：a a b a a f
           子串：a a b a a c
           当f和c不匹配 a a b a a c
           当f和c不匹配     a a b a a c
           一旦出现不匹配，模式串j就会跳转到next[j-1]，这是个连续跳转的过程
           1、如何确定 next 数组？
              模式串： a a b a a c
              next[0]代表 a  的最大相等前后缀，由于只有一个元素，因此，不存在前后缀，因此 next[0] = 0;
              next[1]代表 aa 的最大相等前后缀，前缀 a == 后缀a ，因此，next[1] = 1;
              next[2]代表 aab的最大相等前后缀，其中前缀集合为{a，aa}，后缀集合为{ab，b}，不存在相等的前后缀，因此next[2] = 0;
              以此类推：next = [0, 1, 0, 1, 2, 0]
           2、为什么跳转位置是next[j-1]?
              如上例，当主串索引走到f，模式串索引走到c，出现了不匹配，这时模式串索引跳转到b位置，继续和主串匹配，
              b的索引位置与next[j-1]的值对应，其中j为 5
 */
public class KMP {
    // 方案一：暴力解法 O(m * n)
    public int strStr(String haystack, String needle) {
        // 步骤1：初始化主串和模式串的遍历指针
        int i = 0;
        int j = 0;
        // 存储每一次主串开始和模式串匹配时，记录i的位置，如果匹配成功，k的位置就是匹配成功的起始位置
        int k = i;
        while (i < haystack.length() && j < needle.length()){
            if(haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            }else {
                i = ++k;
                j = 0;
            }
        }
        // 只要模式串能成功遍历完，那就说明匹配上了
        if(j == needle.length())
            return k;
        else
            return -1;
    }

    // 方案2：采用kmp算法，O(m + n)
    public int strStr1(String haystack, String needle) {
        int i = 0;
        int j = 0;
        // int k = i;
        // 步骤1：初始化next数组
        int[] next = createNextArray(needle);
        while (i < haystack.length() && j < needle.length()){
            if(haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            }else if(haystack.charAt(i) != needle.charAt(j) && j > 0){
                // 相对于暴力解法，模式串指针，只需要跳转到next[j]，不需要从j = 0开始，既然主串到i没有匹配成功，那么直接继续向前
                // j >0 此条件是为了防止数组越界,同时如果退到0点就到头了
                j = next[j-1];

                // 如果j已经无路可退，那主串需要移动，不然就死循环了
            } else if (haystack.charAt(i) != needle.charAt(j) && j == 0) {
                i++;
            }
            // 只要模式串能成功遍历完，那就说明匹配上了
        }
        if(j == needle.length())
            return i - needle.length();
        else
            return -1;
    }

    //生成next数组
    public int[] createNextArray(String needle) {
        // 步骤1：初始化前缀尾指针和后缀尾指针
        // 例如：模式串是a b a，前缀集合是{a，ab}，后缀集合为{b，ba}
        // 前缀索引的尾部是从0 开始到length-2，取不到最后一个元素
        int j = 0;
        // 后缀的尾指针一定是取不到第一个元素的，所以从1开始
        int i = 1;
        int[] next = new int[needle.length()];
        next[0] = 0;

        // 外层循环是后缀尾指针只要不走到字符串末尾，就可以一致参与匹配过程
        for (; i < needle.length(); i++) {
            // 步骤2：判断不匹配的情况
            while (needle.charAt(j) != needle.charAt(i) && j > 0)
                // 前后和后缀的匹配过程也是模式匹配的过程
                // 回退是个连续的操作
                j = next[j - 1];
            // 说明已经回退到0，则无路可退 next[i] = 0
//            if(j == 0)
//                next[i] = j;
//            // 步骤3：
//            if (needle.charAt(j) == needle.charAt(i)) {
//                // 如果匹配上了
//                j++;
//                next[i] = j;
//            }

            //简化为
            if (needle.charAt(j) == needle.charAt(i))
                // 如果匹配上了，说明最大相等前后缀需要+1
                j++;

            // 如果没有匹配上，同时 j 回退到0位置，那么就next[i] = j，此时j为 0
            // 如果匹配上了，j就表示和i匹配时，的最大前后缀长度
            next[i] = j;
        }
        return next;
    }

}
