// imports
import java.util.*;

public class Game {
    public static final String border = "\n=================================================\n";

    public void run() {
        Scanner scan = new Scanner(System.in);
        Board board = new Board();
        System.out.print(border);

        // get AI's opponent
        int AIOpp = getAIOpp(scan);
        
        // randomly decide who goes first
        if (Math.random()<0.5){
            System.out.println("AI goes first.");
        } else {
            System.out.println("You go first.");
        }

        int row, col;
        // game loop
        while(!board.checkWin()){
            // player 1
            System.out.println(border);
            board.printBoard();
            System.out.println("Player 1's turn...");
            System.out.print("Enter row[0 to 2]: ");
            row = scan.nextInt();
            System.out.print("Enter col [0 to 2]: ");
            col = scan.nextInt();

            // player 2
            System.out.println(border);
            board.printBoard();
            System.out.println("Player 2's turn...");
            System.out.print("Enter row[0 to 2]: ");
            row = scan.nextInt();
            System.out.print("Enter col [0 to 2]: ");
            col = scan.nextInt();
        }
        System.out.println(border);


        board.printBoard();
        System.out.print(border);
    }

    public int getAIOpp(Scanner scan){

        int AIOpp = 0;
        boolean invalidInput = true;
        while (invalidInput){
            System.out.println("Select the AIs opponent:");
            System.out.print("[1] Human\n[2] AI\n--> ");
            try {
                AIOpp = scan.nextInt();
                if (AIOpp == 1 || AIOpp == 2){
                    invalidInput = false;
                }
                else{
                    System.out.println("Invalid input; enter 1 for Human or 2 for AI.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input; enter 1 for Human or 2 for AI.");
                scan.next();
            }
        }
        return AIOpp;
    }
}