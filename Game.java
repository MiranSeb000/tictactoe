import java.util.*;

/* =======================================
 * Author: Peter Hafner and Sebastian M-G
 * Date: 10 February 2024
 * Purpose: Handles running the game
 * =======================================
*/

public class Game {

    /* Game variables */
    public static final String border = "\n=================================================\n";
    public static Scanner scan = new Scanner(System.in);
    public static Board board = new Board();
    private Player player1, player2;
    

    /* Runs the game until it's over */
    public void run() {

        /* Initialize players */
        if (requestPlayerType() == 2) {
            player1 = new Computer();
            player2 = new Computer();
        } else {
            if (Math.random()<0.5) {
                player1 = new Computer();
                player2 = new Person();
            } else {
                player1 = new Person();
                player2 = new Computer();
            }
        }
        
        /* Game loop */
        int turn = 0;
        while(!board.getIsWon() && turn < 9) {
            if (turn % 2 == 0) player1.runTurn(board);
            else player2.runTurn(board);
            board.evaluate();
            turn++;
        }
        
        /* End game */
        System.out.println(border);
        board.printBoard();
        if (board.getIsWon()) System.out.println("Player " + ((turn-1)%2+1) + " wins!");  
        else System.out.println("It's a draw!");
        System.out.print(border);
    }

    /* Take and sanitize player type info */
    public int requestPlayerType() {
        int playerType = 0;
        boolean invalidInput = true;
        while (invalidInput){
            System.out.println(border);
            System.out.println("Select the AIs opponent:");
            System.out.print("[1] Human\n[2] AI\n--> ");
            try {
                playerType = scan.nextInt();
                if (playerType == 1 || playerType == 2) invalidInput = false;
                else System.out.print("Invalid input; enter 1 for Human or 2 for AI: ");
            }
            catch (InputMismatchException e) {
                System.out.print("Invalid input; enter 1 for Human or 2 for AI: ");
                scan.next();
            }
        }
        return playerType;
    }
}