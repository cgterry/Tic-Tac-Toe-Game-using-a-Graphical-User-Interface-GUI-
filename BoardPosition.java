package cpsc2150.extendedTicTacToe.models;


/**
 * A class to contain information about a position on the board for
 * a game of TicTacToe.
 *
 * @invariants  row >= 0 AND row <= getNumRows() AND column >= 0 AND column <= getNumColumns
 *
 */
public class BoardPosition {
    private int row;
    private int column;
    /**
     * This constructor creates a position on the board
     *
     * @param r row of the position
     * @param col column of the position
     *
     * @pre
     *  none
     * @post
     *  row = r AND column = col
     */
    public BoardPosition(int r, int col){

        row = r;
        column = col;
    }

    /**
     * Returns the row of the position on the board
     *
     * @pre
     *  none
     * @post
     *  An integer representing the row of self AND row = #row
     * @return - the row of the position on the board
     */
    public int getRow(){
        return row;
    }

    /**
     * Returns the column of the position on the board
     *
     * @pre
     *  none
     * @post
     *  An integer representing the column of self AND column = #column
     * @return - the column of the position on the board
     */
    public int getColumn(){
        return column;
    }

    /**
     * Creates a string in the following format "<row>,<column>."
     *
     * @pre
     *  none
     * @post
     *  toString = "[row], [column]" AND row = #row AND column = #column
     * @return - a string representation of the board position
     */
    @Override
    public String toString(){

        String output;

        output = this.getRow() + ", " + this.getColumn();

        return output;
    }

    /**
     * Reports whether this BoardPosition is equal to another BoardPosition.
     *
     * @param pos position on the board
     *
     * @pre
     *  none
     * @post
     *  equals = (this.row == pos.row) AND (this.column == pos.column)
     * @return - returns true if this BoardPosition is equal to another BoardPosition
     * OR false otherwise or if position is out of range of GameBoard.
     */
    @Override
    public boolean equals(Object pos){

        if (pos == null) {
            return false;
        }

        if (pos.getClass() != this.getClass()) {
            return false;
        }

        BoardPosition temp = (BoardPosition) pos;

        // if the two positions are equal to each other
        if (this.getRow() == temp.getRow() && this.getColumn() == temp.getColumn()) {
            return true;
        }
        else {
            return false;
        }
    }
}
