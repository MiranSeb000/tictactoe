import java.util.*;

public class Computer extends Player{
    public static final String border = "\n=================================================\n";
    private int expandedNodes;

    public int searchAndPlay(Board board, int depth, Boolean isRoot){

        if (isRoot) expandedNodes = 0;
        
        if (depth == 0) {
            expandedNodes++;
            return board.evaluate();
        }

        LinkedList<Move> moves = generateMoves(board);
        if (moves.size() == 0) {
            return 0; //FIGURE OUT BEST PLAY HERE DRAW?
        }
        
        int bestEval;
        Move bestMove = null;
        if (board.pieceToMove == 'X') bestEval = Integer.MIN_VALUE;
        else bestEval = Integer.MAX_VALUE;
        
        for (Move move : moves) {
            board.makeMove(move);
            int eval = searchAndPlay(board, depth - 1, false);

            if (board.pieceToMove == 'X'){
                bestEval = Math.min(eval, bestEval);
                if (bestEval == eval && isRoot) bestMove = move;
            } 
            else {
                bestEval = Math.max(eval, bestEval);
                if (bestEval == eval && isRoot) bestMove = move;
            }
            board.unmakeMove(move);
            //if (depth == 1) System.out.println(eval);
        }
        //if (isRoot) System.out.println(bestEval);
        if (isRoot) {
            board.makeMove(bestMove);
            System.out.println(bestMove.row + "," + bestMove.col);
            System.out.println("Nodes expanded = " + expandedNodes);
        }
        
        return bestEval;
    }

    private LinkedList<Move> generateMoves (Board board) {
        LinkedList<Move> moves = new LinkedList<Move> ();
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if (board.board[row][col] == ' ') {
                    Move move = new Move(row, col);
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    void runTurn(Board board, Scanner scan) {
        int playerNum;
        System.out.println(border);
        board.printBoard();
        if (board.pieceToMove == 'X') playerNum = 1;
        else playerNum = 2;
        System.out.println("Player " + playerNum + "'s turn...");
        searchAndPlay(board, 3, true);
    }
}
