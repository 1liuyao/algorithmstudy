package backtrack.algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
    【93 复原 IP 地址】有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
                     例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
                     但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
                     给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，
                     这些地址可以通过在 s 中插入 '.' 来形成。
                     你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。

                提示：1 <= s.length <= 20
                     s 仅由数字组成
    【示例 1】
            输入：s = "25525511135"
            输出：["255.255.11.135","255.255.111.35"]
    【示例 2】
            输入：s = "0000"
            输出：["0.0.0.0"]
    【示例 3】
            输入：s = "101023"
            输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
    =========================================================================================================
    【解题思路】1、确定回溯函数参数：（1）startIndex 去重
                               （2）"." 的数量，这也表示了递归的深度
              2、确定回溯终止条件：加入的 "." 数量不超过3个
              3、单层递归：（1）如何表示 "." 的插入位置： startIndex
                         （2）如何表示 截取的 合法子串 ： [startIndex, i]  左闭右闭区间
 */
public class RestoreIpAddresses {
    List<String> result = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        int startIndex = 0;
        int pointSum = 0;
        StringBuilder stringBuilder = new StringBuilder(s);
        backTracking(stringBuilder, startIndex, pointSum);
        return result;
    }

    private void backTracking(StringBuilder s, int startIndex, int pointSum) {
        // 确定回溯终止条件
        if (pointSum == 3){
            // 此时已经截取了 4 个子串，前三个已经在递归过程中判断了合法性，最后一个串未判断
            if (isValid(s.substring(startIndex, s.length())))
                result.add(s.toString());
            return;
        }
        // 单层递归逻辑
        for (int i = startIndex; i < s.length(); i++){
            // 截取的子串区间 [startIndex, i] ，若非法需剪枝，注意：库函数的左闭右开的
            String substring = s.substring(startIndex, i + 1);
            if (!isValid(substring))
                continue;
            // 添加 "."
            // s的修改，就是在收集path
            s.insert(i + 1, '.');
            pointSum ++;
            // s多加了 "." ，从 i + 2 开始
            backTracking(s, i + 2, pointSum);

            pointSum --;
            s.deleteCharAt(i + 1);
        }
    }

    private boolean isValid(String substring) {
        // 如果串为空，非法
        if(substring.length() == 0)
            return false;
        // 位数大于1位的子串以 0 开头，非法
        if(substring.length() > 1 && substring.charAt(0) == '0')
            return false;
        // 包含 非数字  非"." 违法
        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) < '0' || substring.charAt(i) > '9' && substring.charAt(i) != '.'){
                return false;
            }
        }
        // 判断子串位数，不超过3
        if (substring.length() > 3)
            return false;
        // 判断子串数值不超过255
        if (Integer.parseInt(substring) > 255)
            return false;
        return true;
    }
}
