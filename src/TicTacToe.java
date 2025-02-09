import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    private static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };
    private static char currentPlayer = 'X';
    private static boolean isAgainstAI = false;
    private static Random random = new Random();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose game mode: 1 for Player vs Player, 2 for Player vs Computer");
        int mode = scanner.nextInt();
        isAgainstAI = (mode == 2);
        
        boolean gameRunning = true;
        
        while (gameRunning) {
            printBoard();
            if (currentPlayer == 'O' && isAgainstAI) {
                System.out.println("Opponent is thinking...");
                try {
                    Thread.sleep(3000); // Pauses for 3 seconds before computer takes its turn
                } catch (InterruptedException e) { //This is a safeguard in case other lines of code interrupts the sleep timer
                    Thread.currentThread().interrupt();
                }
                aiTurn();
            } else {
                System.out.println("Player " + currentPlayer + ", enter your move following this format: Row #, Column #: ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;
                
                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                    board[row][col] = currentPlayer;
                } else {
                    System.out.println("Invalid move. Only have a space in between the row and column numbers.");
                    continue;
                }
            }
            
            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins.");
                gameRunning = false;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw. No contest.");
                gameRunning = false;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
        scanner.close();
    }
    
    private static void aiTurn() {
        int row, col;
        do {
            row = random.nextInt(3); //"AI" is simply placing their symbol in a random empty spot
            col = random.nextInt(3);
        } while (board[row][col] != ' ');
        board[row][col] = 'O';
        System.out.println("Opponent places 'O' at position: " + (row + 1) + " " + (col + 1));
    }
    
    private static void printBoard() {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }
    
    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) || //row
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) { //column
                return true; // true here means someone has won
            }
        }
        // checks for diagonal victories
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }
    
    // loop that checks if there's any empty space on the board
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true; // true here means that a draw occurs
    }
}
