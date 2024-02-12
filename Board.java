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

    /* Returns integer value of current position in form 3x_2(s)+x_1(s)-(3O_2(s)+O_1(s)) */
    public int evaluate() {

        /* Variables used in the heuristic */
        int Xs = 0, X2 = 0, X1 = 0, Os = 0, O1 = 0, O2 = 0;

        /* Check rows */
        for(int row = 0; row <3; row++) {
            Xs=0; Os=0;
            for (int col = 0; col < 3; col++) {
                if(board[row][col] == 'X'){
                    Xs++;
                } else if (board[row][col] == 'O'){
                    Os++;
                }
            }
            if (Xs == 3) {
                isWon = true;
                return Integer.MAX_VALUE;
            } else if (Os == 3) {
                isWon = true;
                return Integer.MIN_VALUE;
            } 
            else if (Xs == 2 && Os == 0) X2++;
            else if (Xs == 1 && Os == 0) X1++;
            else if (Os == 2 && Xs == 0) O2++;
            else if (Os == 1 && Xs == 0) O1++;
        }

        /* Check cols */
        for(int col = 0; col <3; col++) {
            Xs=0; Os=0;
            for (int row = 0; row < 3; row++) {
                if(board[row][col] == 'X'){
                    Xs++;
                } else if (board[row][col] == 'O'){
                    Os++;
                }
            }
            if (Xs == 3) {
                isWon = true;
                return Integer.MAX_VALUE;
            } else if (Os == 3) {
                isWon = true;
                return Integer.MIN_VALUE;
            }
            else if (Xs == 2 && Os == 0) X2++;
            else if (Xs == 1 && Os == 0) X1++;
            else if (Os == 2 && Xs == 0) O2++;
            else if (Os == 1 && Xs == 0) O1++;
        }

        /* Check diagonals */
        Xs=0; Os=0;
        for (int diag = 0; diag < 3; diag++){
            if(board[diag][diag] == 'X') {
                Xs++;
            } else if (board[diag][diag] == 'O') {
                Os++;
            }
        }
        if (Xs == 3) {
            isWon = true;
            return Integer.MAX_VALUE;
        } else if (Os == 3) {
            isWon = true;
            return Integer.MIN_VALUE;
        }
        else if (Xs == 2 && Os == 0) X2++;
        else if (Xs == 1 && Os == 0) X1++;
        else if (Os == 2 && Xs == 0) O2++;
        else if (Os == 1 && Xs == 0) O1++;
        
        Xs = 0; Os = 0;
        for (int diag = 0; diag < 3; diag++){
            if(board[diag][2-diag] == 'X') Xs++;
            else if (board[diag][2-diag] == 'O') Os++;
        }
        if (Xs == 3) {
            isWon = true;
            return Integer.MAX_VALUE;
        } else if (Os == 3) {
            isWon = true;
            return Integer.MIN_VALUE;
        }
        else if (Xs == 2 && Os == 0) X2++;
        else if (Xs == 1 && Os == 0) X1++;
        else if (Os == 2 && Xs == 0) O2++;
        else if (Os == 1 && Xs == 0) O1++;
        
        isWon = false;
        return 3*X2+X1-(3*O2+O1);
    }
}
