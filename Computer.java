import java.util.*;

public class Computer extends Player{

    public Computer(char piece){
        super(piece);
    }

    private List<Move> generateMoves (char piece, Board board) {
        LinkedList<Move> moves = new LinkedList<Move> ();
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if (board.board[row][col] != ' ') {
                    Move move = new Move(col, row, piece);
                    moves.add(move);
                }
            }
        }
        return null;
    }
 
}
