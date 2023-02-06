package exercise;

import java.util.HashMap;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String a = in.nextLine();
            String b = in.nextLine();

            int[] nums1 = new int[Integer.parseInt(a)];
            int[] nums2 = new int[Integer.parseInt(b)];

            String s1 = in.nextLine();
            String[] s = s1.split(" ");

            String s2 = in.nextLine();
            String[] ss = s2.split(" ");

            for (int i = 0; i < nums1.length; i++) {
                nums1[i] = Integer.parseInt(s[i]);
            }
            for (int i = 0; i < nums2.length; i++) {
                nums2[i] = Integer.parseInt(ss[i]);
            }

            int sum = getSum(nums1, nums2);
            System.out.println(sum);
        }

    }

    public static int getSum(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashtable = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (hashtable.containsKey(nums1[i])) {
                hashtable.put(nums1[i], hashtable.get(nums1[i]) + 1);
            } else {
                hashtable.put(nums1[i], 1);
            }
        }

        for (int i = 0; i < nums2.length; i++) {
            if (hashtable.containsKey(nums2[i]))
                sum += hashtable.get(nums2[i]);
        }
        return sum;
    }


}
