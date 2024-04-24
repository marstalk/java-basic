package backtracing;

public class Sudoku {
    public static void main(String[] args) {
        char[][] board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        Sudoku sudoku = new Sudoku();
        sudoku.sudoku(board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private char empty = '.';
    private int m = 9;

    public void sudoku(char[][] board) {
        backtrace(board, 0, 0);
    }

    private boolean backtrace(char[][] board, int row, int col) {

        if (row == m) {
            // fillup entire board
            return true;
        }

        if (col == m) {
            return backtrace(board, row + 1, 0);
        }

        if (board[row][col] != empty) {
            return backtrace(board, row, col + 1);
        }

        for (char c = '1'; c <= '9'; c++) {
            if (!valid(board, row, col, c)) {
                continue;
            }
            board[row][col] = c;
            if (backtrace(board, row, col + 1)) {
                return true;
            }
            board[row][col] = empty;
        }

        return false;
    }

    private boolean valid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < m; i++) {
            // check row;
            if (board[row][i] == c) {
                return false;
            }
            // check col
            if (board[i][col] == c) {
                return false;
            }
            // check 3*3
            if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == c) {
                return false;
            }
        }
        return true;
    }
}
