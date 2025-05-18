package tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AITest {


    // Castig in urmatoarea mutare pentru X, daca O greseste
    @Test
    void test1()
    {
        int[][] matrix3 = {
                {  1,   1, -1 }, // x x _
                { -1,  0, -1  }, // _ 0 _
                { -1, -1, -1  } //  _ _ _
        };

        Board board = new Board(matrix3);

        Board bestMove = AI.getBestSolution(board);

        Assertions.assertEquals(Board.O, bestMove.getCell(0, 2),
                "tictactoe.AI should block X from winning by placing O at (0, 2).");
    }

    @Test
    void test2()
    {
        int[][] matrix3 = {
                {  1, -1, -1 },  //  x _ _
                { -1,  1, -1  }, //  _ x _
                {  0, -1, -1  }  //  0 _ _
        };

        Board board = new Board(matrix3);

        Board bestMove = AI.getBestSolution(board);

        Assertions.assertEquals(Board.O, bestMove.getCell(2, 2),
                "tictactoe.AI should block X from winning by placing O at (2, 2).");
    }

    @Test
    void test3()
    {
        int[][] matrix3 = {
                {  1, -1, -1 },  //  x _ _
                {  1,  0, -1  }, //  x 0 _
                {  -1, -1, -1  }  //  _ _ _
        };

        Board board = new Board(matrix3);

        Board bestMove = AI.getBestSolution(board);

        Assertions.assertEquals(Board.O, bestMove.getCell(2, 0),
                "tictactoe.AI should block X from winning by placing O at (2, 0).");
    }

    // Castig pentru O mutare directa
    @Test
    void test4()
    {
        int[][] matrix3 = {
                {   1,  1, -1 },  //  x x _
                {   0,  0, -1  }, //  0 0 _
                {   1, -1, -1  }  //  x _ _
        };

        Board board = new Board(matrix3);

        Board bestMove = AI.getBestSolution(board);

        Assertions.assertEquals(Board.O, bestMove.getCell(1, 2),
                "tictactoe.AI should block X from winning by placing O at (1, 2).");
    }


    @Test
    void test5()
    {
        int[][] matrix3 = {
                {   -1,  -1,  1 },  //  _ _ x
                {   -1,   0, -1  }, //  _ 0 _
                {    1,  -1, -1  }  //  x _ _
        };

        Board board = new Board(matrix3);

        Board bestMove = AI.getBestSolution(board);


        Assertions.assertNotEquals(Board.O, bestMove.getCell(0, 0),
                "tictactoe.AI should not place inside (0, 0) in this case.");
        Assertions.assertNotEquals(Board.O, bestMove.getCell(2, 2),
                "tictactoe.AI should not place inside (2,2) in this case.");
    }


    // Boundary value analysis, category partitioning analysis
    @Test
    void test6()
    {
        int[][] matrix3 = {
                {    -1,   -1,  1 },  //  _ _ X
                {    -1,   -1, -1  }, //  _ _ _
                {    -1,   -1, -1  }  //  _ _ _
        };

        Board board = new Board(matrix3);

        Board bestMove = AI.getBestSolution(board);

        Assertions.assertEquals(Board.O, bestMove.getCell(1, 1),
                "(1, 1).");
    }

    // Boundary value analysis - Empty board
    @Test
    void test7()
    {
        int[][] matrix3 = {
                {    -1,   -1, -1 },  //  _ _ _
                {    -1,   -1, -1  }, //  _ _ _
                {    -1,   -1, -1  }  //  _ _ _
        };

        Board board = new Board(matrix3);

        Board bestMove = AI.getBestSolution(board);


        Assertions.assertEquals(Board.O, bestMove.getCell(0, 0),
                "(O, O).");
    }

    // Boundary value analysis - Draw, one square left
    @Test
    void test8()
    {
        int[][] matrix3 = {
                {     1,   0,  1 },  //  x 0 x
                {     0,   0,  1  }, //  0 0 x
                {     0,   1, -1  }  //  0 x _
        };

        Board board = new Board(matrix3);

        Board bestMove = AI.getBestSolution(board);

        Assertions.assertEquals(Board.O, bestMove.getCell(2, 2),
                "tictactoe.AI should place in the remaining square");
    }

    // Boundary value analysis - tictactoe.Board is already full, should return null
    @Test
    void test9()
    {
        int[][] matrix3 = {
                {     1,   0,  1 },  //  x 0 x
                {     0,   0,  1  }, //  0 0 x
                {     0,   1,  0  }  //  0 x 0
        };

        Board board = new Board(matrix3);

        Board bestMove = AI.getBestSolution(board);

        Assertions.assertNull(bestMove, "Function should return null when there's no possible best move, since board is full");
    }
}
