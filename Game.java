/*
 * Author: Peter Hafner and Sebastian M-G
 * Date: 10 February 2024
 * Purpose: Game: Handles running the game
 */

// imports
import java.util.*;

public class Game {
    public static final String border = "\n=================================================\n";
    Player player1, player2;
    Board board;
    

    public void run() {
        //Init board and scanner
        Scanner scan = new Scanner(System.in);
        board = new Board();

        // create players
        if (getAIOpp(scan) == 1) {
            player1 = new Person();
            player2 = new Person();
        } else {
            if (Math.random()<0.5) {
                player1 = new Computer();
                player2 = new Person();
            } else {
                player1 = new Person();
                player2 = new Computer();
            }
        }
        
        // game loop
        int turn = 0;
        while(!board.checkWin()) { // while no winner
            if (turn % 2 == 0) player1.runTurn(board, scan);
            else player2.runTurn(board, scan);
            board.evaluate();
            turn++;
        }
        
        // the game has ended
        System.out.println(border);
        board.printBoard();
        System.out.println("Player " + ((turn-1)%2+1) + " wins!");     
        System.out.print(border);
    }

    // sanitize input for getting system's opponent
    public int getAIOpp(Scanner scan) {
        int AIOpp = 0;
        boolean invalidInput = true;
        while (invalidInput){
            System.out.println(border);
            System.out.println("Select the AIs opponent:");
            System.out.print("[1] Human\n[2] AI\n--> ");
            try {
                AIOpp = scan.nextInt();
                if (AIOpp == 1 || AIOpp == 2){
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
        return AIOpp;
    }
}