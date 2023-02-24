package string.test;

import string.algorithm.Convert;

public class ConvertTest {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;

        Convert convert = new Convert();
        String convert1 = convert.convert(s, numRows);
        System.out.println(convert1);
    }
}
