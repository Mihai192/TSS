package tictactoe;

public class AI {
    private static final int INF = 3000;

    public static int minimax(Board board, boolean isMinPlayer) {
        int state = board.gameOver();

        if (state == Board.X) {
            return 10;
        } else if (state == Board.O) {
            return -10;
        } else if (state == 3) {
            return 0;
        } else {
            if (isMinPlayer) {
                int minValue = INF;
                for (int i = 0; i < board.getBoardDim(); i++) {
                    for (int j = 0; j < board.getBoardDim(); j++) {
                        if (board.getCell(i, j) == Board.EMPTY) {
                            board.setCell(i, j, Board.O);
                            int value = minimax(board,false);
                            minValue = Math.min(minValue, value);
                            board.setCell(i, j, Board.EMPTY);
                        }
                    }
                }
                return minValue;
            } else {
                int maxValue = -INF;
                for (int i = 0; i < board.getBoardDim(); i++) {
                    for (int j = 0; j < board.getBoardDim(); j++) {
                        if (board.getCell(i, j) == Board.EMPTY) {
                            board.setCell(i, j, Board.X);
                            int value = minimax(board, true);
                            maxValue = Math.max(maxValue, value);
                            board.setCell(i, j, Board.EMPTY);
                        }
                    }
                }
                return maxValue;
            }
        }
    }

    public static Board getBestSolution(Board board) {
        Board bestBoard = null;
        int bestValue = INF;

        for (int i = 0; i < board.getBoardDim(); i++) {
            for (int j = 0; j < board.getBoardDim(); j++) {
                if (board.getCell(i, j) == Board.EMPTY) {
                    board.setCell(i, j, Board.O);
                    int solutionValue = minimax(board, false);

                    if (solutionValue < bestValue) {
                        bestValue = solutionValue;
                        bestBoard = board.copy();
                    }

                    board.setCell(i, j, Board.EMPTY);
                }
            }
        }
        return bestBoard;
    }
}
