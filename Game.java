
public class Game {
    public static final String border = "\n=================================================\n";

    public void run() {
        Board board = new Board();
        System.out.print(border);
        board.printBoard();
        System.out.print(border);
    }
}