package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements MouseListener {
    private Board board;
    private boolean gameOver;
    private int turn; // -1 = player X, 1 = tictactoe.AI O
    private int xScore;
    private int oScore;

    private JLabel scoreLabel;

    public Game() {
        board = new Board();
        gameOver = false;
        turn = -1;
        xScore = 0;
        oScore = 0;

        this.setPreferredSize(new Dimension(600, 640));
        this.addMouseListener(this);

        scoreLabel = new JLabel();
        updateScoreboard();

        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(scoreLabel, BorderLayout.NORTH);
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateScoreboard() {
        scoreLabel.setText(String.format("Player - %d    Bot - %d", xScore, oScore));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 600, 640);


        g.setColor(Color.BLACK);
        for (int i = 1; i < 3; i++) {
            g.drawLine(i * 200, 40, i * 200, 640);
            g.drawLine(0, 40 + i * 200, 600, 40 + i * 200);
        }


        for (int i = 0; i < board.getBoardDim(); i++) {
            for (int j = 0; j < board.getBoardDim(); j++) {
                int cell = board.getCell(i, j);
                if (cell == Board.X) {
                    g.setColor(Color.RED);
                    g.drawLine(j * 200 + 10, i * 200 + 40 + 10, j * 200 + 190, i * 200 + 40 + 190);
                    g.drawLine(j * 200 + 10, i * 200 + 40 + 190, j * 200 + 190, i * 200 + 40 + 10);
                } else if (cell == Board.O) {
                    g.setColor(Color.BLUE);
                    g.drawOval(j * 200 + 30, i * 200 + 40 + 30, 140, 140);
                }
            }
        }
    }

    private boolean isGameOver() {
        int result = board.gameOver();
        if (result == 2) return false;

        if (result == Board.X) {
            xScore++;
            JOptionPane.showMessageDialog(this, "Player (X) wins!");
        } else if (result == Board.O) {
            oScore++;
            JOptionPane.showMessageDialog(this, "Bot (O) wins!");
        } else if (result == 3) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
        }

        Timer timer = new Timer(100, e -> resetGame());
        timer.setRepeats(false);
        timer.start();

        return true;
    }

    private void resetGame() {
        board = new Board();
        gameOver = false;
        turn = -1;
        updateScoreboard();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameOver) {
            return;
        }

        if (turn == -1) {  // Player X turn
            int x = e.getX() / 200;
            int y = (e.getY() - 40) / 200;
            if (x >= 0 && x < board.getBoardDim() && y >= 0 && y < board.getBoardDim() && board.getCell(y, x) == Board.EMPTY) {
                board.setCell(y, x, Board.X);
                repaint();

                if (isGameOver()) {
                    gameOver = true;
                    return;
                }
                turn = 1;
                aiMove();
            }
        }
    }

    private void aiMove() {
        Board newBoard = AI.getBestSolution(board);
        if (newBoard != null) {
            board = newBoard;
        }
        repaint();

        if (isGameOver()) {
            gameOver = true;
            return;
        }

        turn = -1;
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
