package cpsc2150.extendedTicTacToe.models;
/**
 * An abstract class of GameBoard that contains an overridden
 * implementation of Object#toString().
 */
public abstract class AbsGameBoard implements IGameBoard {

    /**
     * Creates a string of the entire game board.
     *
     * @pre
     *  none
     * @post
     *  toString = [string that represents the game board and uses board array
     *  to fill in positions with the player's characters.]
     * @return - a string representation of the game board.
     */
    @Override
    public String toString(){

        // Strings to help build the board
        // and help functions
        String board = "";
        String board2 = "";
        BoardPosition temp;
        int j = 0;

        // Prints zero and first '|'
        board = "    " + j + "|";
        // Prints columns
        for (int i = 1; i < getNumColumns(); i++) {
            if (i >= 10){
                board += i + "|";
            }
            else {
                board += " " + i + "|";
            }
        }
        // New line
        board += "\n";

        // Prints rows and the values for each column
        for (int q = 0; q < getNumRows(); q++){
            if (q >= 10) {
                board2 += q;
            }
            else {
                board2 += " " + q;
            }
            for (int i = 0; i < getNumColumns(); i++) {
                temp = new BoardPosition(q, j + i);
                board2 += "|" + this.whatsAtPos(temp) + " ";
            }
            // New line
            board2 += "|\n";
        }

        return board + board2;
    }
}
