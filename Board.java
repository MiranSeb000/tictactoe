/*
 * Author: Peter Hafner and Sebastian M-G
 * Date: 10 February 2024
 * Purpose: Board: Holds the board state
 */

public class Board {
    char[][] board = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
    int player1;
    int player2;
    boolean win = false;

    
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

        return win;
    }

    // returns integer value of current position in form 3x_2(s)+x_1(s)-(3O_2(s)+O_1(s))
    public int evaluate() {
        int X2 = 0;
        int X1 = 0;
        int O2 = 0;
        int O1 = 0;
        int Xs = 0;
        int Os = 0;

        // check rows
        for(int row = 0; row <3; row++) {
            Xs=0;
            Os=0;
            for (int col = 0; col < 3; col++) {
                if(board[row][col] == 'X'){
                    Xs++;
                } else if (board[row][col] == 'O'){
                    Os++;
                }
            }
            if (Xs == 2 && Os == 0) {
                X2++;
            } else if (Xs == 1 && Os == 0) {
                X1++;
            } else if (Os == 2 && Xs == 0) {
                O2++;
            } else if (Os == 1 && Xs == 0) {
                O1++;
            }
        }

        // check cols
        for(int col = 0; col <3; col++) {
            Xs=0;
            Os=0;
            for (int row = 0; row < 3; row++) {
                if(board[row][col] == 'X'){
                    Xs++;
                } else if (board[row][col] == 'O'){
                    Os++;
                }
            }
            if (Xs == 2 && Os == 0) {
                X2++;
            } else if (Xs == 1 && Os == 0) {
                X1++;
            } else if (Os == 2 && Xs == 0) {
                O2++;
            } else if (Os == 1 && Xs == 0) {
                O1++;
            }
        }

        // check diagonals
        Xs = 0;
        Os = 0;
        for (int diag = 0; diag < 3; diag++){
            if(board[diag][diag] == 'X') {
                Xs++;
            } else if (board[diag][diag] == 'O') {
                Os++;
            }
        }
        if (Xs == 2 && Os == 0) {
            X2++;
        } else if (Xs == 1 && Os == 0) {
            X1++;
        } else if (Os == 2 && Xs == 0) {
            O2++;
        } else if (Os == 1 && Xs == 0) {
            O1++;
        }
        Xs = 0;
        Os = 0;

        for (int diag = 0; diag < 3; diag++){
            if(board[diag][2-diag] == 'X') {
                Xs++;
            } else if (board[diag][2-diag] == 'O') {
                Os++;
            }
        }
        if (Xs == 2 && Os == 0) {
            X2++;
        } else if (Xs == 1 && Os == 0) {
            X1++;
        } else if (Os == 2 && Xs == 0) {
            O2++;
        } else if (Os == 1 && Xs == 0) {
            O1++;
        }
        
        return 3*X2+X1-(3*O2+O1);
    }

}