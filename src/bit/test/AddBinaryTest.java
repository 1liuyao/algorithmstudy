package bit.test;

import bit.algorithm.AddBinary;

public class AddBinaryTest {
    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        String s = addBinary.addBinary("1010", "1011");
        System.out.println(s);
    }
}
