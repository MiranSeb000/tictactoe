public class Board {
    char[][] board = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
    int player1;
    int player2;

    

    public void printBoard() {
        System.out.println();
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                System.out.print(" " + board[col][row] + " ");
                if (col < 2) System.out.print("|");
                else if (row < 2) System.out.print("\n---+---+---\n");
                else System.out.println();
                
            }
        }
    }
}