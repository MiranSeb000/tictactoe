public abstract class Player {
    char piece;

    public Player(char piece){
        this.piece = piece;
    }
    
    public void makeTurn(int row, int col, Board board) {
        board.board[col][row] = piece;
    }
}
