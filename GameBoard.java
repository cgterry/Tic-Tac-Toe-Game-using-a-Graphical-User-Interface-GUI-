package cpsc2150.extendedTicTacToe.models;


/**
 * A class that creates and contains the 2-dimensional user specified game board
 * that the players will play TicTacToe on.
 *
 * @correspondances self = board[0 ...NUM_ROW][0 ...NUM_COLUMNS] AND self = empty
 *
 * @invariant
 *  board = char[NUM_ROWS][NUM_COLUMNS] AND numRows = rows
 *  AND numCols = cols AND tokensToWin = numToWin
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {


    private char[][] board;
    private int numCols;
    private int numRows;
    private int tokensToWin;

    /**
     * This constructor that creates the 2-dimensional user-specified game board.
     *
     *
     * @pre
     *  none
     * @post
     *  Creates a user specified 2D array that is empty.
     */
    public GameBoard(int rows, int cols, int numToWin){
        numRows = rows;
        numCols = cols;
        tokensToWin = numToWin;
        board = new char[numRows][numCols];
    }

    public void placeMarker(BoardPosition marker, char player)
    {
        board[marker.getRow()][marker.getColumn()] = player;
    }

    public char whatsAtPos(BoardPosition pos)
    {
        char value = ' ';
        char value2 = '\0';
        value = board[pos.getRow()][pos.getColumn()];

        if (value == value2){
            value = ' ';
            return value;
        }
        else {
            return value;
        }
    }

    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {

        char test;
        test = board[pos.getRow()][pos.getColumn()];

        if (test == player) {
            return true;
        }
        else{
            return false;
        }
    }

    public int getNumColumns() {
        return numCols;
    }

    public int getNumRows(){
        return numRows;
    }
    public int getNumToWin(){
        return tokensToWin;
    }
}
