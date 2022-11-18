package backtrack.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
    【51 N 皇后】按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
                n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
                给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
                每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    【示例 1】
            输入：n = 4
            输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
            解释：如上图所示，4 皇后问题存在两个不同的解法。
    【示例 2】
            输入：n = 1
            输出：[["Q"]]
    =========================================================================================
    【解题思路】1、确定回溯函数参数：row：标记行变量，向下一层递归时，需要确定棋盘上下一行皇后摆放的位置
              2、确定回溯单层递归逻辑：如果不符合棋盘规则：同一行同一列同一斜线上只能有一个皇后，不符合规则需要剪枝
              3、确定回溯终止条件：row 到达棋盘最大行
 */
public class SolveNQueens {
    List<List<String>> result = new ArrayList<>();
    // 初始化棋盘
    char[][] checkerBoard;
    public List<List<String>> solveNQueens(int n) {
        // 初始化棋盘
        checkerBoard = new char[n][n];
        for (char[] a: checkerBoard) {
            Arrays.fill(a, '.');
        }
        // 每次递归都是从一行中选择一个位置放皇后，所以递归中 row 需要作为参数传递
        int row = 0;
        backTracking(checkerBoard, row, n);
        return result;
    }

    private void backTracking(char[][] checkerBoard, int row, int n) {
        // 终止条件：皇后放完了最后一行
        if (row == n){
            ArrayList<String> checkerBoardRow = new ArrayList<>();
            // 将棋盘放到 result 中
            for (char[] a: checkerBoard) {
                checkerBoardRow.add(String.valueOf(a));
            }
            result.add(checkerBoardRow);
            return;
        }
        // 单层递归逻辑
        for (int column = 0; column < n; column++) {
            // 放皇后，需要满足规则，如果不满足，就树层剪枝
            if(!isValid(checkerBoard, row, column, n)){
                continue;
            }
            checkerBoard[row][column] = 'Q';
            backTracking(checkerBoard, row + 1, n);
            checkerBoard[row][column] = '.';
        }
    }

    private boolean isValid(char[][] checkerBoard, int row, int column, int n) {
        // 行约束已经在单层回溯中限制了
        // 列约束
        for (int i = 0; i < row; i++){
            if (checkerBoard[i][column] == 'Q')
                return false;
        }
        // 右斜线约束
        for (int i = row - 1, j = column + 1; (i >= 0) && (j < n);i--, j++){
            if (checkerBoard[i][j] == 'Q')
                return false;
        }
        // 左斜线约束
        for (int i = row - 1, j = column - 1; (i >= 0) && (j >= 0);i--, j--){
            if (checkerBoard[i][j] == 'Q')
                return false;
        }
        return true;
    }
}
