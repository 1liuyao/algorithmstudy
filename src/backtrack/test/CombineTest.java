package backtrack.test;

import backtrack.algorithm.Combine;

import java.util.LinkedList;
import java.util.List;

public class CombineTest {
    public static void main(String[] args) {
//        int n = 4;
//        int k = 2;

        int n = 1;
        int k = 1;
        Combine combine = new Combine();
        List<List<Integer>> combine1 = combine.combine(n, k);
        System.out.println(combine1);

//        LinkedList<Integer> path = new LinkedList<>();
//        LinkedList<Integer> path1 = new LinkedList<>();
//        List<List<Integer>> result = new LinkedList<>();
//        path.add(1);
//        path.add(2);
//        path1.add(3);
//        path1.add(4);
//
//        result.add(path);
//        result.add(path1);
//
//        result.add(new LinkedList<>(path));
//        result.add(new LinkedList<>(path1));
    }
}
