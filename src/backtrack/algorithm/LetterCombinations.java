package backtrack.algorithm;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

/*
    【17 电话号码的字母组合】给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
                         数字与字母的映射关系为，
                         numberToString{
                            "1": "",
                            "2": "abc",
                            "3": "def",
                            "4": "ghi",
                            "5": "jkl",
                            "6": "mno",
                            "7": "pqrs",
                            "8": "tuv",
                            "9": "wxyz"
                         }
    【用例1】
            输入：digits = "23"
            输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
            相当于[abc]和[def]组合
    【用例2】
            输入：digits = ""
            输出：[]
    【用例3】
            输入：digits = "2"
            输出：["a","b","c"]
    =======================================================================================
    【解题思路】回溯法
        ====================================================================
                                   [a b c]                                     本层结点数据集合从numberToString[digits[0]]
                  取a                 取b                取c
        ====================================================================
               [d e f]             [d e f]            [d e f]                  本层结点数据集合从numberToString[digits[1]]
           取d    取e   取f     取d    取e   取f    取d    取e   取f
        ====================================================================   ※注意：在深度递归遍历时，需要传入digits【索引】
         [a d]  [a e]  [a f]  [b d] [b e] [b f]  [c d]  [c e] [c f]
 */
public class LetterCombinations {
    List<String> result = new ArrayList<String>();
    StringBuilder path = new StringBuilder();
    // 存储数字和字符的映射关系
    String[] numberToString = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        // 从上层到下层进行深度遍历，需要不断从digits中取出元素，再到映射关系中取出真正的数据集合
        // 例如：根节点[a b c] <==> numberToString[digits[index]]，其中index = 0
        //      下层[d e f] <==> numberToString[digits[index]]，其中index = 1

        if (digits == null || digits.isEmpty()) {
            return result;
        }
        int index = 0;
        backTracking(digits, index);
        return result;
    }

    // 步骤1：确定回溯函数参数和返回值
    public void backTracking(String digits, int index) {
        // 步骤1：确定回溯终止条件
        // 注意：if(path.length() == digits.length())也可以作为终止判断条件，path的长度，就是叶子结点集合个数
        if(index == digits.length()){
            result.add(new String(path));
            return;
        }
        // 步骤2：确定本层回溯处理逻辑：取集合中元素; s 存储每一个结点的集合
        String s = numberToString[digits.charAt(index) - '0'];
        for (int i = 0; i < s.length(); i++) {
            path.append(s.charAt(i));
            backTracking(digits,index + 1);
            path.deleteCharAt(path.length()-1);
        }

        // 坑：path.deleteCharAt(path.length()-1);√
        //    path.deleteCharAt(i);错
        // 因为当遍历到叶子结点，for循环结束，i为length+1，即为2 ，如果此时path.deleteCharAt(i)则数组越界
    }
}

