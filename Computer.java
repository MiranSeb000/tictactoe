import java.util.*;

public class Computer extends Player{

    public Computer(char piece){
        super(piece);
    }

    public int searchAndPlay(Board board, int depth, Boolean isRoot){
        
        if (depth == 0) {
            return board.evaluate();
        }

        LinkedList<Move> moves = generateMoves(board);
        if (moves.size() == 0) {
            return 0; //FIGURE OUT BEST PLAY HERE DRAW?
        }
        
        int bestEval;
        Move bestMove;
        if (board.pieceToMove == 'X') bestEval = Integer.MIN_VALUE;
        else bestEval = Integer.MAX_VALUE;
        
        for (Move move : moves) {
            board.makeMove(move);
            int eval = searchAndPlay(board, depth - 1, false);

            if (board.pieceToMove == 'X'){
                bestEval = Math.max(eval, bestEval);
                if (bestEval == eval && isRoot) bestMove = move;
            } 
            else {
                bestEval = Math.min(eval, bestEval);
                if (bestEval == eval && isRoot) bestMove = move;
            }
            board.unmakeMove(move);
        }
        if (isRoot) board.makeMove(bestMove);
        return bestEval;
    }

    private LinkedList<Move> generateMoves (Board board) {
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
