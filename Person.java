import java.util.*;

public class Person extends Player{

    public static final String border = "\n=================================================\n";

    public Person(char piece){
        super(piece);
    }

    public void runTurn(Board board, Scanner scan){
        System.out.print("hi");
        int row, col;
        System.out.println(border);
        board.printBoard();
        System.out.println("Player 1's turn...");

        do {
            System.out.print("Enter row[0 to 2]: ");
            row = getMoveSpace(scan);
            System.out.print("Enter col [0 to 2]: ");
            col = getMoveSpace(scan);
        } while (!checkMove(board, row, col));

        makeTurn(row, col, board);
    }

    public boolean checkMove(Board board, int row, int col) {
        boolean isFree = false;

        if (board.board[col][row] == ' '){
            isFree = true;
        } else {
            System.out.println("\nThat space is already occupied, try again");
        }

        return isFree;
    }
 
}

