package listtable.test;

import listtable.algorithm.GenerateMatrix;

public class GenerateMatrixTest {
    public static void main(String[] args) {
        GenerateMatrix generateMatrix = new GenerateMatrix();
        int n = 5;
        int[][] ints = generateMatrix.generateMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ints[i][j]+" ");
            }
            System.out.println();
        }
    }
}
