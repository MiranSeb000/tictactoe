/*
 * Author: Peter Hafner and Sebastian M-G
 * Date: 10 February 2024
 * Purpose: Game: Handles running the game
 */

// imports
import java.util.*;

public class Game {
    public static final String border = "\n=================================================\n";
    private List<Player> players;
    private Player currentPlayer;
    public static Board board;

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
        players = new ArrayList<>();
        Computer system = new Computer(AIChar);
        players.add(system);
        // get AI's opponent
        int AIOpp = getAIOpp(scan);
        if (AIOpp == 1) {
            // make Human player
            Person person = new Person(AIOppChar);
            if (person.piece == 'X'){
                players.add(0, person);
            } else {
                players.add(person);
            }
            players.add(person);
        } if (AIOpp == 2) {
            // make AI player
            Computer computer = new Computer(AIOppChar);
            if (computer.piece == 'X'){
                players.add(0, computer);
            } else {
                players.add(computer);
            }
        }

        // game loop
        while(!board.checkWin()) { // while no winner
            int row, col;
            // player 1
            System.out.println(border);
            board.printBoard();
            System.out.println("Player 1's turn...");
            System.out.print("Enter row[0 to 2]: ");
            row = scan.nextInt();
            System.out.print("Enter col [0 to 2]: ");
            col = scan.nextInt();
            players.get(0).makeTurn(row, col, board);

            // player 2
            System.out.println(border);
            board.printBoard();
            System.out.println("Player 2's turn...");
            System.out.print("Enter row[0 to 2]: ");
            row = scan.nextInt();
            System.out.print("Enter col [0 to 2]: ");
            col = scan.nextInt();
            players.get(1).makeTurn(row, col, board);
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