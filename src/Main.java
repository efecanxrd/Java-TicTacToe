import java.util.Scanner;

public class Main {
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';
    private static final int BOARD_SIZE = 3;

    private char[][] board;
    private char currentPlayer;

    public Main() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = X;

        // Initialize the board with empty cells
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameFinished = false;

        // Game loop
        while (!gameFinished) {
            printBoard();

            // Get the current player's move
            int row, col;
            do {
                System.out.printf("Player %s, enter your move (row[1-3] column[1-3]): ", currentPlayer);
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
            } while (!isValidMove(row, col));

            // Update the board
            board[row][col] = currentPlayer;

            // Check if the game is over
            if (isGameOver()) {
                printBoard();
                System.out.printf("Player %s has won the game!\n", currentPlayer);
                gameFinished = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a tie!");
                gameFinished = true;
            } else {
                // Switch to the other player
                currentPlayer = (currentPlayer == X) ? O : X;
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            // Out of bounds
            return false;
        }

        if (board[row][col] != EMPTY) {
            // Cell is already occupied
            return false;
        }

        return true;
    }

    private boolean isGameOver() {
        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != EMPTY) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != EMPTY) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY) {
            return true;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != EMPTY) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++)
                if (board[row][col] == EMPTY) {
                    return false;
                }
        }
        return true;
}

    private void printBoard() {
        System.out.println("+---+---+---+");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print("| ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.printf("%c | ", board[row][col]);
            }
            System.out.println("\n+---+---+---+");
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.play();
    }
}
