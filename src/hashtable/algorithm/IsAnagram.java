package hashtable.algorithm;
/*
    【242 有效的字母异位词】给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
                        注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
                        提示:
                            1 <= s.length, t.length <= 5 * 104
                            s 和 t 仅包含小写字母
    【用例1】
            输入: s = "anagram", t = "nagaram"
            输出: true
    【用例2】
            输入: s = "rat", t = "car"
            输出: false
    ================================================================================
    【哈希表法】1、一般哈希表都是用来快速判断一个元素是否出现集合里，用于查询操作
              2、常见的三种哈希结构：当我们想使用哈希法来解决问题的时候，我们一般会选择如下三种数据结构。
                （1）数组：哈希表长度可以确定
                （2）set（集合）：哈希表长度不固定
                （3）map(映射)：对key有要求，需要被保存的数据是键值对
    【解题思路】：1、选取数据结构：因为需要统计字符串中26个英文【小写字母】出现的次数，表长固定为26，采用数组求取
                             当然使用map也是可以的，<字母，出现次数>
               2、用哈希表统计串 s 中每个字母出现的次数
               3、遍历串 t，对 t 包含的字母在哈希表中出现的次数进行减操作
               4、遍历哈希表，如果所有元素值都是0，那么是异位词，否则不是
 */

public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        // 步骤1：构建hash表
        int[] charToNum = new int[26];
        // 步骤2：统计 s 中字母出现的次数
        for (int i = 0; i < s.length(); i++) {
            charToNum[s.charAt(i) - 'a']++;
        }
        // 步骤3：对 t 中出现的字符次数减减
        for (int i = 0; i < t.length(); i++) {
            charToNum[t.charAt(i) - 'a']--;
        }
        // 步骤3：判断哈希表是否所有值均为0
        for (int i = 0; i < charToNum.length; i++) {
            if (charToNum[i] != 0){
                return false;
            }
        }
        return true;
    }
}
