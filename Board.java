public class Board {
    char[][] board = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
    int player1;
    int player2;

    
    // prints board in readable format
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

    // returns true if there is a winner in current state
    public boolean checkWin() {

        return false;
    }

    // returns integer value of current position in form 3x_2(s)+x_1(s)-(3O_2(s)+O_1(s))
    public int evaluate() {
        int eval = 0;
        
        // check columns

        // check rows

        // check diagonals

        return eval;
    }


}