package exercise;
/*

1.	每个句子由多个单词组成，句子中的每个单词的长度都可能不一样，我们假设每个单词的长度Ni为该单词的重输入字符串为中文拼音号码串或者英文号码串，
如果输入是中文拼音号码串则转成英文号码串，如果输入是英文号码串则转成中文号码串。
2.	特殊情况是英文号码串会出现Double + 英文数字或者拼音数值。如果是英文则可以正常转换，如果是拼音则返回“ERROR”。
输入
输入为中文拼音数字串（用例保证中文数字串均为数字拼音），或者英文数字串（可能带Double）
输出
输出为输入的 中->英 或 英->中 的转换，如果遇到double+中拼音时输出“ERROR”

样例
输入样例 1
YiWuSanJiuSi

输出样例 1
OneFiveThreeNineFour

 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String input = "YiWuSanJiuSi";
        StringBuilder output = new StringBuilder();

        List<String> list = splitPlus(input);

        HashMap<String, String> hash = new HashMap<>();
        hash.put("Yi", "One");
        hash.put("Wu", "Five");
        hash.put("San", "Three");
        hash.put("Jiu", "Nine");
        hash.put("Si", "Four");

        for (int i = 0; i < list.size(); i++) {
            String s = hash.get(list.get(i));
            output.append(s);
        }

        System.out.println(output);
    }

    public static List<String> splitPlus(String s){
        List<String> sb = new ArrayList<>();
        int start = 0;
        int end = 0;
        // 按照大写截取字符串
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                end = i;
                // 截取
                String substring = s.substring(start, end);
                sb.add(substring);
                // 更新start
                start = end;
            }

        }
        // 尾部最后一个串
        sb.add(s.substring(end, s.length()));
        return sb;
    }
}
