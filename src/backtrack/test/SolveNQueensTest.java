package backtrack.test;

import backtrack.algorithm.SolveNQueens;

import java.util.List;

public class SolveNQueensTest {
    public static void main(String[] args) {
        int n = 4;
        SolveNQueens solveNQueens = new SolveNQueens();
        List<List<String>> lists = solveNQueens.solveNQueens(n);
        for (List<String> list : lists) {
            System.out.println(list);
        }

    }
}
