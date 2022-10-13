package listtable.test;

import listtable.algorithm.DeleteElement;

import java.util.Arrays;
import java.util.List;

public class DeleteElementTest {
    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int val = 3;
        DeleteElement deleteElement = new DeleteElement();
        int size=deleteElement.deleteElement(nums,val);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
        System.out.println("数组长度为："+size);

        System.out.println("===============");

        int[] numss = {3,2,2,3};
        int size2 = deleteElement.deleteElement2(numss,val);

        for (int i = 0; i < numss.length; i++) {
            System.out.print(numss[i]);
        }
        System.out.println("数组长度为："+size2);
    }
}
