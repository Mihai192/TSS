package tictactoe;

import java.util.Arrays;

public class Board {
    public static final int EMPTY = -1;
    public static final int O = 0;
    public static final int X = 1;

    private  final int[][] matrix;
    private  final int n = 3;

    public Board() {
        matrix = new int[n][n];
        for (int[] row : matrix) {
            Arrays.fill(row, EMPTY);
        }
    }

    public int getBoardDim() {
        return n;
    }

    public Board(int[][] matrix) {
        this.matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, n);
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setCell(int row, int col, int player) {
        matrix[row][col] = player;
    }

    public int getCell(int row, int col) {
        return matrix[row][col];
    }

    /**
     *
     * 1 = X wins
     * 0 = O wins
     * 3 = draw
     * 2 = game ongoing
     */
    public int gameOver() {

        if (matrix[0][0] != EMPTY && matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2]) {
            return matrix[0][0];
        }

        if (matrix[0][n - 1] != EMPTY && matrix[0][n - 1] == matrix[1][n - 2] && matrix[0][n - 1] == matrix[2][0]) {
            return matrix[0][n - 1];
        }

        for (int i = 0; i < n; i++) {
            int val = matrix[i][0];
            if (val != EMPTY && val == matrix[i][1] && val == matrix[i][2]) {
                return val;
            }
        }

        for (int j = 0; j < n; j++) {
            int val = matrix[0][j];
            if (val != EMPTY && val == matrix[1][j] && val == matrix[2][j]) {
                return val;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == EMPTY) {
                    return 2;
                }
            }
        }
        return 3; // Draw
    }


    public Board copy() {
        return new Board(matrix);
    }
}
