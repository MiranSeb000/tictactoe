import java.util.Scanner;

public abstract class Player {
    char piece;

    public Player(char piece){
        this.piece = piece;
    }

    //abstract void runTurn();
    abstract void runTurn(Board board, Scanner scan);
    
    public void makeTurn(int row, int col, Board board) {
        Move move = new Move(row, col, this.piece);
        board.makeMove(move);
    }
}
