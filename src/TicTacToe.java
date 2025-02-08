import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };
    private static char currentPlayer = 'X';
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;
        
        while (gameRunning) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move following this format: Row #, Column #: ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                
                if (checkWin()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameRunning = false;
                } 
                else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw. No contest.");
                    gameRunning = false;
                } 
                else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } 
            else {
                System.out.println("Invalid move. Only have a space in between the row and column numbers.");
            }
        }
        scanner.close();
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
                return true; // true means win
            }
        }
        // checks diagonals for win condition
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }
    
    // checks for draw
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') { // loop that checks if there's any empty space on the board
                    return false;
                }
            }
        }
        return true; //true here means draw occurs
    }
}
