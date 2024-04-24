package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class NQueen {
    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
        List<List<String>> rtn = nQueen.solveNQueens(10);
        for (int i = 0; i < rtn.size(); i++) {
            List<String> an = rtn.get(i);
            for (int j = 0; j < an.size(); j++) {
                System.out.println(an.get(j));
            }
            System.out.println();

        }
    }

    /**
     * [
     * [".Q..","...Q","Q...","..Q."]
     * ,["..Q.","Q...","...Q",".Q.."]
     * ]
     */
    List<List<String>> rtn = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            // 额，第一次用这个方法。
            Arrays.fill(board[i], '.');
        }

        backtrace(board, 0);
        
        return rtn;
    }

    /**
     * 
     * @param board
     * @param row
     */
    private void backtrace(char[][] board, int row) {
        if (row == board.length) {
            // 得到一个结果。
            rtn.add(toList(board));
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (!valid(board, row, col)) {
                continue;
            }

            board[row][col] = 'Q';
            backtrace(board, row + 1);
            board[row][col] = '.';
        }

    }

    /**
     * 
     * @param board
     * @param row
     * @param col
     * @return
     */
    private boolean valid(char[][] board, int row, int col) {
        // 这一列
        for (int i = 0; i < row; i++) {
            // 这一列已经放置过Q了，不能再放了。
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 左上角
        for (int i = row - 1, j = col - 1; i > -1 && j > -1; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 右上角
        for (int i = row - 1, j = col + 1; i > -1 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private List<String> toList(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            // 额，第一次用这个方法
            list.add(String.copyValueOf(row));
        }
        return list;
    }
}