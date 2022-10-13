package listtable.algorithm;
/*
    【59 螺旋矩阵】给你一个正整数 n ，生成一个包含 1 到 n的平方 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
    【用例1】
        输入：n = 3
        输出：[[1,2,3],[8,9,4],[7,6,5]]
    【用例2】
        输入：n = 1
        输出：[[1]]
    ==================================================================
    【解题思路】：按照顺时针旋转方向采用【左开右闭】原则依次遍历每一条边
              ---------->
            ^ 00 01 02 03 |
            | 10 11 12 13 |
            | 20 21 22 23 |
            | 30 31 32 33 V
             <------------
            1、明确需要需要转几圈
            2、明确每一圈的起始点
            3、根据【左开右闭】原则按顺时针旋转遍历每一条边，例如针对第一行行索引为0，不变，
               列索引的取值范围为[0,3),最后一个元素留给下一条边遍历。
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        //步骤1：初始化起始点坐标，以及遍历指针
        int startX = 0;
        int startY = 0;
        int[][] matrix = new int[n][n];
        int count = 1;
        int i, j;
        // 每转一圈，列指针可遍历的范围就减1
        int offset = 0;

//        //============================================
//        // 考虑只转最外层一圈的情况
//        // 根据【左闭右开】原则按照顺时针旋转方式遍历第一条边，注意：最后一个元素取不到
//        for (j = startY; j < n-1; j++) {
//            matrix[startX][j]=count++;
//        }
//        // 遍历第二条边
//        for (i = startX; i < n-1; i++) {
//            matrix[i][j] = count++;
//        }
//        // 遍历第三条边
//        for (j = n-1; j > startY; j-- )
//            matrix[i][j] = count--;
//        // 遍历第四条边
//        for (i = n-1; i > startX; i--)
//            matrix[i][j] = count--;
//        //============================================

        // 步骤二：确定会转几圈
        int loop = n/2;
        while(loop > 0){
            // 考虑只转最外层一圈的情况
            // 根据【左闭右开】原则按照顺时针旋转方式遍历第一条边，注意：最后一个元素取不到
            for (j = startY; j < n-1-offset; j++) {
                matrix[startX][j]=count++;
            }
            // 遍历第二条边
            for (i = startX; i < n-1-offset; i++) {
                matrix[i][j] = count++;
            }
            // 遍历第三条边
//            for (j = n-1-offset; j > startY; j-- )
            // 可简化为以下形式，因为上一个循环结束时，i就等于n-1-offset
            for ( ; j > startY; j-- )
                matrix[i][j] = count++;
            // 遍历第四条边
//            for (i = n-1-offset; i > startX; i--)
            //可化简为
            for ( ; i > startX; i--)
                matrix[i][j] = count++;

            // 步骤3：更新下一圈起点坐标
            startX++;
            startY++;
            offset++;
            loop--;
        }

        // 注意当n为奇数时，n/2会少转最内全，也就是缺少了最中心的点a[n/2][n/2],例如a[2][2]
        if ( n % 2 == 1)
            matrix[startX][startY] = count;
        return matrix;
    }
}
