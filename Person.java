import java.util.*;

public class Person extends Player{

    public static final String border = "\n=================================================\n";

    public void runTurn(Board board, Scanner scan){
        int row, col, playerNum;
        System.out.println(border);
        board.printBoard();
        if (board.pieceToMove == 'X') playerNum = 1;
        else playerNum = 2;
        System.out.println("Player " + playerNum + "'s turn...");

        do {
            System.out.print("Enter row[0 to 2]: ");
            row = getMoveSpace(scan);
            System.out.print("Enter col [0 to 2]: ");
            col = getMoveSpace(scan);
        } while (!checkMove(board, row, col));

        Move move = new Move(row, col);
        board.makeMove(move);
    }

    // checks to see if the given move is valid
    public boolean checkMove(Board board, int row, int col) {
        boolean isFree = false;

        if (board.board[row][col] == ' '){
            isFree = true;
        } else {
            System.out.println("\nThat space is already occupied, try again");
        }

        return isFree;
    }

    // sanitize input for move space
    public int getMoveSpace(Scanner scan) {
        int move = -1;
        while (move < 0 || move > 2) {
            if (scan.hasNextInt()) {
                move = scan.nextInt();
                if (move < 0 || move > 2) {
                    System.out.print("Invalid input; enter an int from 0 to 2: ");
                }
            } else {
                System.out.print("Invalid input; enter an int from 0 to 2: ");
                scan.next(); // Consume the invalid input
            }
        }
        return move;
    }
 
}
