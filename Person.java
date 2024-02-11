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
        System.out.print("Enter row[0 to 2]: ");
        row = getMoveSpace(scan);
        System.out.print("Enter col [0 to 2]: ");
        col = getMoveSpace(scan);
        makeTurn(row, col, board);
    }
 
}

