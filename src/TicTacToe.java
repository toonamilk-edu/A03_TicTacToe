import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain;

        do {
            // Clear the board and set the player to X (X always moves first)
            String player = "X";
            int moveCount = 0;
            boolean gameOver = false;
            int row;
            int col;

            clearBoard();
            do {
                display();
                // Get the move (1-3), convert it to the array index (0-2), and loop until it's valid
                do {
                    row = SafeInput.getRangedInt(in, "Player " + player + ", enter your row", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in, "Player " + player + ", enter your column", 1, 3) - 1;
                    if (!isValidMove(row, col)) {
                        System.out.println("That spot is taken. Try again.");
                    }
                } while (!isValidMove(row, col));

                // Record the move
                board[row][col] = player;
                moveCount++;

                // Check for a win or a tie once one is possible
                if (moveCount >= 5 && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (moveCount >= 7 && isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    gameOver = true;
                }

                // Toggle the player for the next move
                if (player.equals("X")) {
                    player = "O";
                } else {
                    player = "X";
                }
            } while (!gameOver);

            // Ask to play again
            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again");
        } while (playAgain);
    }

    // Sets every square on the board to a space
    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    // Prints the board
    private static void display() {
        for (int row = 0; row < ROWS; row++) {
            System.out.print("| ");
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }

    // Returns true if the square is empty
    private static boolean isValidMove(int row, int col) {
        boolean retVal = false;
        if (board[row][col].equals(" ")) {
            retVal = true;
        }
        return retVal;
    }

    // Returns true if the player has any kind of win
    private static boolean isWin(String player) {
        boolean retVal = false;
        if (isRowWin(player) || isColWin(player) || isDiagWin(player)) {
            retVal = true;
        }
        return retVal;
    }

    // Checks each row for 3 in a row
    private static boolean isRowWin(String player) {
        boolean retVal = false;
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                retVal = true;
            }
        }
        return retVal;
    }

    // Checks each column for 3 in a row
    private static boolean isColWin(String player) {
        boolean retVal = false;
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                retVal = true;
            }
        }
        return retVal;
    }

    // Checks both diagonals
    private static boolean isDiagWin(String player) {
        boolean retVal = false;
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            retVal = true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            retVal = true;
        }
        return retVal;
    }

    // Returns true if all 8 ways to win have both an X and an O
    private static boolean isTie() {
        boolean retVal = true;
        boolean xFlag;
        boolean oFlag;

        // Rows
        for (int row = 0; row < ROWS; row++) {
            xFlag = false;
            oFlag = false;
            if (board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X")) {
                xFlag = true;
            }
            if (board[row][0].equals("O") || board[row][1].equals("O") || board[row][2].equals("O")) {
                oFlag = true;
            }
            if (!xFlag || !oFlag) {
                retVal = false;
            }
        }

        // Columns
        for (int col = 0; col < COLS; col++) {
            xFlag = false;
            oFlag = false;
            if (board[0][col].equals("X") || board[1][col].equals("X") || board[2][col].equals("X")) {
                xFlag = true;
            }
            if (board[0][col].equals("O") || board[1][col].equals("O") || board[2][col].equals("O")) {
                oFlag = true;
            }
            if (!xFlag || !oFlag) {
                retVal = false;
            }
        }

        // Diagonal 1
        xFlag = false;
        oFlag = false;
        if (board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) {
            xFlag = true;
        }
        if (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")) {
            oFlag = true;
        }
        if (!xFlag || !oFlag) {
            retVal = false;
        }

        // Diagonal 2
        xFlag = false;
        oFlag = false;
        if (board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X")) {
            xFlag = true;
        }
        if (board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O")) {
            oFlag = true;
        }
        if (!xFlag || !oFlag) {
            retVal = false;
        }

        return retVal;
    }
}