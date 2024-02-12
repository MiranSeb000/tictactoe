import java.util.*;

/* ==========================================================
 * Author: Peter Hafner and Sebastian M-G
 * Date: 10 February 2024
 * Purpose: Implementation of Player interface for AI players
 * ==========================================================
*/

public class Computer implements Player {

    /* Computer variables */
    private int expandedNodes;

    /* Implementation of minimax which plays the best move */
    public int searchAndPlay(Board board, int depth, Boolean isInitial){

        /* Set node count to 0 if this is the initial call */
        if (isInitial) expandedNodes = 0;

        /* Recursive base case - met is depth is 0 or a winning move has been made in the search */
        int eval = board.evaluate();
        if (depth == 0 || board.getIsWon()) {
            expandedNodes++;
            board.setIsWon(false);
            return board.evaluate();
        }

        /* Generate list of moves */
        LinkedList<Move> moves = generateMoves(board);

        /* Account for draws in the node count */
        if (moves.size() == 0) {
            expandedNodes++;
            return 0;
        }
        
        /* Initialize variables to track the best moves */
        int bestEval;
        Move bestMove = null;
        char currentPiece = board.getPieceToMove();
        if (currentPiece == 'X') bestEval = Integer.MIN_VALUE;
        else bestEval = Integer.MAX_VALUE;
        
        /* For each move generated, make that move, continue the search, and unmake it */
        for (Move move : moves) {
            board.makeMove(move);
            eval = searchAndPlay(board, depth - 1, false);
            if (currentPiece == 'X'){
                bestEval = Math.max(eval, bestEval);
                if (bestEval == eval && isInitial) bestMove = move;
            } 
            else {
                bestEval = Math.min(eval, bestEval);
                if (bestEval == eval && isInitial) bestMove = move;
            }
            board.unmakeMove(move);
        }

        /* Once the best move is found, make it */
        if (isInitial) {
            board.makeMove(bestMove);
            System.out.println(bestMove.row + "," + bestMove.col);
            System.out.println("Nodes expanded = " + expandedNodes);
        }
        return bestEval;
    }

    /* Generate a linked list of legal moves for the given board */
    private LinkedList<Move> generateMoves (Board board) {
        LinkedList<Move> moves = new LinkedList<Move> ();
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if (board.getSpace(row, col) == ' ') {
                    Move move = new Move(row, col);
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    /* Run this player's turn */
    public void runTurn(Board board) {
        int playerNum;
        System.out.println(Game.border);
        board.printBoard();
        if (board.getPieceToMove() == 'X') playerNum = 1;
        else playerNum = 2;
        System.out.println("Player " + playerNum + "'s turn...");
        searchAndPlay(board, 3, true);
        
    }
}
