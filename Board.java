/* =====================================================
 * Author: Peter Hafner and Sebastian M-G
 * Date: 10 February 2024
 * Purpose: Handle's board state, moves, and evaluations
 * =====================================================
*/

public class Board {

    /* Board variables */
    private char[][] board = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
    private char firstPiece = 'X', secondPiece = 'O', pieceToMove = firstPiece;
    private boolean isWon = false;

    /* Getters */
    public char     getSpace(int row, int col)  {return board[row][col];}
    public char     getPieceToMove()            {return pieceToMove;}
    public boolean  getIsWon()                  {return isWon;}

    /* Makes the given move on the board */
    public void makeMove(Move move) {
        board[move.row][move.col] = pieceToMove;
        if (pieceToMove == firstPiece) pieceToMove = secondPiece;
        else pieceToMove = firstPiece;
    }

    /* Unmakes the given move on the board */
    public void unmakeMove(Move move) {
        board[move.row][move.col] = ' ';
        if (pieceToMove == firstPiece) pieceToMove = secondPiece;
        else pieceToMove = firstPiece;
        if (isWon == true) isWon = false;
    }

    /* Prints the board as it is */
    public void printBoard() {
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                System.out.print(" " + board[row][col] + " ");
                if (col < 2) System.out.print("|");
                else if (row < 2) System.out.print("\n---+---+---\n");
                else System.out.println('\n');
            }
        }
    }

    /* Returns the value of this row/col/diagonal */
    private int checkTriplet(int Xs, int Os) {
        if (Xs == 3) {
            isWon = true;
            return Integer.MAX_VALUE;
        } else if (Os == 3) {
            isWon = true;
            return Integer.MIN_VALUE;
        } 
        else if (Xs == 2 && Os == 0) return 3;
        else if (Xs == 1 && Os == 0) return 1;
        else if (Os == 2 && Xs == 0) return -3;
        else if (Os == 1 && Xs == 0) return -1;
        return 0;
    }

    /* Returns integer value of current position in form 3(X_2)+x_1-(3(O_2)+O_1) */
    public int evaluate() {

        /* Set isWon to make sure this evaluation is fresh */
        isWon = false;

        /* Variables used in the heuristic */
        int Xs = 0, Os = 0, evaluation = 0, tripletValue;

        /* Check rows */
        for(int row = 0; row <3; row++) {
            Xs=0; Os=0;
            for (int col = 0; col < 3; col++) {
                if(board[row][col] == 'X')Xs++;
                else if (board[row][col] == 'O')Os++;
            }
            tripletValue = checkTriplet(Xs, Os);
            if (isWon) return tripletValue;
            evaluation += tripletValue;
        }

        /* Check cols */
        for(int col = 0; col <3; col++) {
            Xs=0; Os=0;
            for (int row = 0; row < 3; row++) {
                if(board[row][col] == 'X') Xs++;
                else if (board[row][col] == 'O') Os++;
            }
            tripletValue = checkTriplet(Xs, Os);
            if (isWon) return tripletValue;
            evaluation += tripletValue;
        }

        /* Check diagonals */
        Xs=0; Os=0;
        for (int diag = 0; diag < 3; diag++){
            if(board[diag][diag] == 'X') Xs++;
            else if (board[diag][diag] == 'O') Os++;
        }
        tripletValue = checkTriplet(Xs, Os);
        if (isWon) return tripletValue;
        evaluation += tripletValue;
        
        Xs = 0; Os = 0;
        for (int diag = 0; diag < 3; diag++){
            if(board[diag][2-diag] == 'X') Xs++;
            else if (board[diag][2-diag] == 'O') Os++;
        }
        tripletValue = checkTriplet(Xs, Os);
        if (isWon) return tripletValue;
        evaluation += tripletValue;
        
        return evaluation;
    }
}
