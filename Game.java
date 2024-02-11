/*
 * Author: Peter Hafner and Sebastian M-G
 * Date: 10 February 2024
 * Purpose: Game: Handles running the game
 */

// imports
import java.util.*;



public class Game {
    public static final String border = "\n=================================================\n";
    private Board board;
    char player1Piece = 'X';
    char player2Piece = 'O';

    public void run() {
        Scanner scan = new Scanner(System.in);
        board = new Board();
        System.out.print(border);
        
        // randomly decide who goes first
        // Assume AI goes first
        char AIChar = 'X';
        char AIOppChar = 'O';
        if (Math.random()<0.5){
            // AI's Opponent goes first
            AIChar = 'O';
            AIOppChar = 'X';
        }

        // create players
        Player player1 = new Person(player1Piece);
        Player player2 = new Person(player2Piece);
        //players = new ArrayList<>();
        //Computer system = new Computer(AIChar);
        //players.add(system);
        // get AI's opponent
        //int AIOpp = getAIOpp(scan);
        //if (AIOpp == 1) {
        //    // make Human player
        //    Person person = new Person(AIOppChar);
        //    if (person.piece == 'X'){
        //        players.add(0, person);
        //    } else {
        //        players.add(person);
        //    }
        //    players.add(person);
        //} if (AIOpp == 2) {
        //    // make AI player
        //    Computer computer = new Computer(AIOppChar);
        //    if (computer.piece == 'X'){
        //        players.add(0, computer);
        //    } else {
        //        players.add(computer);
        //    }
        //}
        
        // game loop
        int turn = 0;
        while(!board.checkWin()) { // while no winner
            if (turn % 2 == 0) player1.runTurn(board, scan);
            else player2.runTurn(board, scan);
            turn++;
        }
        
        System.out.println(border);


        board.printBoard();
        System.out.print(border);
    }

    // sanitize input for getting system's opponent
    public int getAIOpp(Scanner scan) {
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