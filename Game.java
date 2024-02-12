import java.util.*;

/* =======================================
 * Author: Peter Hafner and Sebastian M-G
 * Date: 10 February 2024
 * Purpose: Handles running the game
 * =======================================
*/

/* class to handle the game */
public class Game {

    /* game variables */
    public static final String border = "\n=================================================\n";
    Player player1, player2;
    Board board;
    

    /* runs the game until it's over */
    public void run() {

        /* initialize board and scanner */
        Scanner scan = new Scanner(System.in);
        board = new Board();

        /* initialize players */
        if (requestPlayerType(scan) == 2) {
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
        
        /* game loop */
        int turn = 0;
        while(!board.checkWin() && turn < 9) { // while no winner
            if (turn % 2 == 0) player1.runTurn(board, scan);
            else player2.runTurn(board, scan);
            board.evaluate();
            turn++;
        }
        
        /* end game */
        System.out.println(border);
        board.printBoard();
        if (board.checkWin()) System.out.println("Player " + ((turn-1)%2+1) + " wins!");  
        else System.out.println("It's a draw!");
        System.out.print(border);
    }

    /* take and sanitize player type info */
    public int requestPlayerType(Scanner scan) {
        int playerType = 0;
        boolean invalidInput = true;
        while (invalidInput){
            System.out.println(border);
            System.out.println("Select the AIs opponent:");
            System.out.print("[1] Human\n[2] AI\n--> ");
            try {
                playerType = scan.nextInt();
                if (playerType == 1 || playerType == 2){
                    invalidInput = false;
                }
                else{
                    System.out.print("Invalid input; enter 1 for Human or 2 for AI: ");
                }
            }
            catch (InputMismatchException e) {
                System.out.print("Invalid input; enter 1 for Human or 2 for AI: ");
                scan.next();
            }
        }
        return playerType;
    }
}