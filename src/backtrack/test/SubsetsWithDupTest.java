package backtrack.test;

import backtrack.algorithm.SubsetsWithDup;
import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.util.List;

public class SubsetsWithDupTest {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2};
        SubsetsWithDup subsetsWithDup = new SubsetsWithDup();
        List<List<Integer>> lists = subsetsWithDup.subsetsWithDup(nums);
        for (List<Integer> b:
             lists) {
            Object[] objects = b.toArray();
            for (Object c:
                    objects) {
                System.out.print(c);
            }
            System.out.println("================");
        }
    }
}
