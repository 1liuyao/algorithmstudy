package string.algorithm;
/*
    【459. 重复的子字符串】给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。

    【用例1】
        输入: s = "abab"
        输出: true
        解释: 可由子串 "ab" 重复两次构成。
    【用例2】
        输入: s = "aba"
        输出: false
    【用例3】
        输入: s = "abcabcabcabc"
        输出: true
        解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
     ===================================================================
     【解题思路】
     【暴力遍历法】如果一个串 s 是通过重复的子串组成，那么 s + s 去掉头尾元素一定包含子串
                1、字符串拼接：[a b a b][a b a b]
                2、去掉字符串头尾元素：b a b a b a
                3、判断新串是否包含原串：b a b a b a 包含a b a b，则串 s 是由重复的子串组成，返回true
                  【举例】：a b a --> a b a a b a -->b a a b，不包含a b a，则串不是由重复的子串组成，返回false
                  【原理】其实将串本身拼接是将串的前缀和后缀拼接在了一起，如果串是由重复的子串组成，那么一定可以重新拼成原串
                         |--------||---------|
                             s          s
                             |----------|
                                  s
     【KMP】               串s         a  b  c  a  b  c  a  b  c
                         next[]       0  0  0  1  2  3  4  5  6
                    串s最大相等前缀：    a  b  c  a  b  c
                    串s最大相等后缀：             a  b  c  a  b  c
                      最小重复字串      a  b  c
            判断串s是否由重复子串组成：    s.length() % (s.length() - next[6]) == 0

            1、如果串是重复的，那么最大相等前后缀一定出现在：next[s.length() - 1]
            2、最小重复子串 = 串 s 中除去最大相等前缀 或 后缀 后剩下的那一部分子串
                                    s0 s1 s2 s3 s4 s5 s6 s7 s8
              【证明】   串s          a  b  c  a  b  c  a  b  c
                                    t0 t1 t2 t3 t4 t5
                    串s最大相等前缀t： a  b  c  a  b  c
                    串s最大相等后缀f：          a  b  c  a  b  c
                                             f0 f1 f2 f3 f4 f5
                    由于，t0 = f0 = s0 , t1 = f1 = s1 , t2 = f2 = s2
                         f0 = s3 , f1 = s4 , f2 = s5
                    所以，s0 = s3 , s1 = s4 , s2 = s5 , 即s0s1s2 = s3s4s5
                    由于，t3 = f3 = s6 , t4 = f4 = s7 , t5 = f5 = s8
                         t3 = s3 , t4 = s4 , t5 = s5
                    所以，s3s4s5 = s6s7s8
                    因此，s0s1s2 = s3s4s5 = s6s7s8
                    可见，s0s1s2 或 s6s7s8就是最小的重复字串，也就是s 中除去最大相等前缀 或 后缀 后剩下的那一部分子串

 */
public class RepeatedSubstringPattern {
    // 方式1：如果一个串 s 是通过重复的子串组成，那么 s + s 去掉头尾元素一定包含子串
    public boolean repeatedSubstringPattern(String s) {
        // 步骤1：字符串和自身做拼接
        String ss = s.concat(s);
        // 步骤2：去掉首尾字符
        String substring = ss.substring(1, 2 * s.length() - 1);
        // 步骤3：判断新是否包含原串
        return substring.contains(s);
    }

    // 方式2：KMP算法上修改
    public boolean repeatedSubstringPattern1(String s) {
        // 步骤1：找到最大相等前后缀的长度
        int next[] = getNext(s);
        // 步骤2：找到最小重复字串长度
        int minSubLen = s.length() - next[s.length() - 1];
        // 步骤3：判断串 s 是否包含字串
        // 注意：如果是abac,next[] = 0 0 1 0，此时也可以被整除，但是应该返回false
        // 当然如果题目中明确，一个字符也为true，那么也需要单独判断
        if (next[s.length() - 1] == 0)
            return false;
        return s.length() % (s.length() - next[s.length() - 1]) == 0;
    }

    private int[] getNext(String s) {
        int[] next = new int[s.length()];
        // 步骤1：初始化最大相等前缀、后缀尾指针
        int j = 0;
        int i = 1;
        next[0] = 0;
        // 步骤2：如果前缀尾不等于后缀尾，j连续跳转到next[j-1]，需注意数组越界判断
        for (; i < s.length(); i++){
            while (s.charAt(i) != s.charAt(j) && j >0)
                j = next[j-1];
            // 步骤3：如果前缀尾等于后缀尾，那么j就是该位置的最大相等前后缀长度
            if(s.charAt(i) == s.charAt(j))
                j++;
            // 步骤4：如果不相等j回退到0，则next[i]=0，如果相等next[i] = j;
            // 注意此处已经j++，所以原来字符相等的位置是j-1
            // 这里这个if可以省略，我瞎整哈哈
            if ( j == 0 || s.charAt(i) == s.charAt(j-1) )
                next[i] = j;
        }
        return next;
    }
}
