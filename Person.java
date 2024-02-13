/* =============================================================
 * Author: Peter Hafner and Sebastian M-G
 * Date: 10 February 2024
 * Purpose: Implementation of Player interface for human players
 * =============================================================
*/

public class Person implements Player{

    /* Conducts the human player's turn */
    public void runTurn(){
        int row, col, playerNum;
        System.out.println(Game.border);
        Game.board.printBoard();
        if (Game.board.getPieceToMove() == 'X') playerNum = 1;
        else playerNum = 2;
        System.out.println("Player " + playerNum + "'s turn...");
        do {
            System.out.print("Enter row[0 to 2]: ");
            row = getMoveSpace();
            System.out.print("Enter col [0 to 2]: ");
            col = getMoveSpace();
        } while (!checkMove(Game.board, row, col));
        Move move = new Move(row, col);
        Game.board.makeMove(move);
    }

    // Checks to see if the given move is valid
    private boolean checkMove(Board board, int row, int col) {
        boolean isFree = false;
        if (board.getSpace(row, col) == ' '){
            isFree = true;
        } else {
            System.out.println("\nThat space is already occupied, try again");
        }
        return isFree;
    }

    // Sanitize input for move space
    private int getMoveSpace() {
        int move = -1;
        while (move < 0 || move > 2) {
            if (Game.scan.hasNextInt()) {
                move = Game.scan.nextInt();
                if (move < 0 || move > 2) {
                    System.out.print("Invalid input; enter an int from 0 to 2: ");
                }
            } else {
                System.out.print("Invalid input; enter an int from 0 to 2: ");
                Game.scan.next(); // Consume the invalid input
            }
        }
        return move;
    }
 
}
