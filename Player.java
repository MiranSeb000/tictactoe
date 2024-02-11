import java.util.Scanner;

public abstract class Player {
    //char piece;

    //public Player(char piece){
    //    this.piece = piece;
    //}

    //abstract void runTurn();
    abstract void runTurn(Board board, Scanner scan);

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
