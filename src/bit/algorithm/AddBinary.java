package bit.algorithm;

import java.math.BigInteger;

/*
    【67 二进制求和】给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。

    【示例 1】
            输入:a = "11", b = "1"
            输出："100"
    【示例 2】
            输入：a = "1010", b = "1011"
            输出："10101"
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        BigInteger i = new BigInteger(a, 2);
        BigInteger i1 = new BigInteger(b, 2);

        // 坑：不能使用 i.intValue()精度损失
        // 针对大数，可能高位存在1，但是低32位 仍为 0，此时应该继续计算，但是却会停止
        while(i1.doubleValue() != 0){
            BigInteger temp = i.xor(i1);
            i1 = (i.and(i1)).shiftLeft(1);
            i = temp;
        }

        return i.toString(2);
    }

    public String addBinary1(String a, String b) {
        BigInteger aBigInt = new BigInteger(a, 2);
        BigInteger bBigInt = new BigInteger(b, 2);

        // Step 2: Calculate the sum
        BigInteger sumBigInt = aBigInt.add(bBigInt);

        // Step 3: Convert the sum back to a binary string
        String sumBinary = sumBigInt.toString(2);

        // Return the result
        return sumBinary;
    }
    // 竖式对齐
    public String addBinary2(String a, String b) {
        int sum = 0;
        int jinWei = 0;
        StringBuilder stringBuilder = new StringBuilder();
        // 从后向前逐位相加
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0 ; i--, j--) {
            // 如果被加数存在，取 a 中对应位上数字，如果被加数不存在，补 0
            int beiJiaShu = i >= 0 ? a.charAt(i) - '0' : 0;
            // 如果加数存在，取 b 中对应位上数字，如果被加数不存在，补 0
            int jiaShu = j >= 0 ? b.charAt(j) - '0' : 0;
            // 计算被加数和加数在第 i 位求和后的进位，和本位上的数字
            sum = beiJiaShu + jiaShu + jinWei;
            // 计算进位
            // 例如：7 + 5 = 12，十进制 12 / 10 = 1，进 1; 本位 12 % 10 = 2 ，本位剩 2
            jinWei = sum / 2;
            // 保存本位
            stringBuilder.append(sum % 2);
        }

        // 计算到最后一位，如果仍然有进位，那仍然需要添加到最终结果中
        if (jinWei == 1)
            stringBuilder.append(jinWei);
        return stringBuilder.reverse().toString();
    }
}
