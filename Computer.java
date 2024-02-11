import java.util.*;

public class Computer extends Player{

    public Computer(char piece){
        super(piece);
    }

    public int search(Board board, int depth){
        
        if (depth == 0) {
            return board.evaluate();
        }

        LinkedList<Move> moves = generateMoves(piece, board);
        if (moves.size() == 0) {
            return 0; //FIGURE OUT BEST PLAY HERE DRAW?
        }
        
        int bestEval;
        if (board.pieceToMove == 'X') bestEval = Integer.MIN_VALUE;
        else bestEval = Integer.MAX_VALUE;
        
        for (Move move : moves) {
            board.makeMove(move);
            int eval = search(board, depth - 1);
            if (board.pieceToMove == 'X') bestEval = Math.max(eval, bestEval);
            else bestEval = Math.min(eval, bestEval);
            board.unmakeMove(move);
        }

        return bestEval;
    }

    private LinkedList<Move> generateMoves (char piece, Board board) {
        LinkedList<Move> moves = new LinkedList<Move> ();
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if (board.board[row][col] == ' ') {
                    Move move = new Move(col, row);
                    moves.add(move);
                }
            }
        }
        return moves;
    }
}
