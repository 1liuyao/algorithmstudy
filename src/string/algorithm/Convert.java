package string.algorithm;

import java.util.Arrays;

/*
    【6 N 字形变换】将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
                  比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
                  P   A   H   N
                  A P L S I I G
                  Y   I   R
                  之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
                  请你实现这个将字符串进行指定行数变换的函数：
                  string convert(string s, int numRows);
    【示例 1】
            输入：s = "PAYPALISHIRING", numRows = 3
            输出："PAHNAPLSIIGYIR"
    【示例 2】
            输入：s = "PAYPALISHIRING", numRows = 4
            输出："PINALSIGYAHRPI"
            解释：
            P     I    N
            A   L S  I G
            Y A   H R
            P     I
    【示例 3】
            输入：s = "A", numRows = 1
            输出："A"
    ==========================================================================================
    【解题思路】
            1、如果字符串长度 小于等于 numRows， 那么只需要原样输出
              例如："abc" numRows = 3 , N 字变换后
                    a
                    b
                    c

            2、每一个 N 字变换，都是以周期进行的，列 加 斜线 的形式重复，那么需要确定一共需要重复多少轮
               列填满 需要 numRows 字符，斜线填满需要 numRows - 2个字符，因此一个周期会消耗 2 * numRows - 2个字符
               那么 一共循环轮数 round = s.length() / (2 * numRows - 2) 向上取整

            3、通过二维数组存储 N 字变换后的字符串，那么二维数组应该初始化成几行几列？
              （1）行数 = numRows
              （2）列数 = 前 round - 1 轮 一定会走完成的 列和斜线 + 最后一轮可能只占一列，或者列 加 部分斜线

            4、在每一个轮次内，模拟先走一列，再走斜线，需要注意边界值的处理

 */
public class Convert {
    public String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows == 1)
            return s;

        // 计算周期数，最后一个周期可能不会走满完整的N字，向上取整当一个周期算
        int round = (int)Math.ceil(s.length() * 1.0 / (2 * numRows - 2));

        // 计算二维矩阵列数
        // 如果能整除，说明 s 的长度 恰好能走出完整的 N 字
        int column = s.length() / (2 * numRows - 2) * (1 + numRows - 2);
        // 如果不能乘除，说明最后一个轮次没走出完整的 N 字，要么需要走一列，要么需要走一列 加 斜线
        if (s.length() % (2 * numRows - 2) != 0){
            // 最后一轮一定会站一列
            column = column + 1;
            // 计算最后一轮斜线部分元素有几个
            if (s.length() % (2 * numRows - 2) / numRows > 0){
                column = column + s.length() % (2 * numRows - 2) % numRows;
            }
        }

        char[][] matrix = new char[numRows][column];

        int i = 0;
        int j = 0;
        // 遍历字符串
        int count = 0;
        while(round > 0){
            // 走列
            while(i < numRows && count < s.length()){
                matrix[i][j] = s.charAt(count);
                count++;
                i++;
            }
            // 斜线第一个元素的位置
            i = numRows - 2;
            j = j + 1;
            // 注意边界值处理：采用左闭右开的处理原则，斜线部分最后一个元素不占用第一行
            while (i > 0 && j < column && count < s.length()) {
                matrix[i][j] = s.charAt(count);
                count++;
                i--;
                j++;
            }
            // 更新轮次
            round--;
        }

        // 收集结果
        StringBuilder result = new StringBuilder();
        for (int k = 0; k < numRows; k++) {
            for (int l = 0; l < column; l++) {
                if (matrix[k][l] != '\u0000')
                    result.append(matrix[k][l]);
            }
        }

        return result.toString();
    }
}
