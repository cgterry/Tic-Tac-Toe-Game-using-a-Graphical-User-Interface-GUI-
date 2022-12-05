package cpsc2150.extendedTicTacToe.models;


/**
 * IGameBoard is 2-dimensional user specified GameBoard.
 * A GameBoard containing empty characters.
 *
 * Initialization ensures:
 *      The GameBoard has [user specified rows]x[user specified columns] and empty, contains empty characters.
 *
 * Constraints:
 *      self.Rows = getNumRows AND self.Columns = getNumColumns
 */
public interface IGameBoard {

    /**
     * This method tests if a board position is available.
     *
     * @param pos a position on the game board
     *
     * @return - returns true if the position specified in pos is available; false otherwise
     *
     * @pre
     *  pos.getRow() >= 0 AND pos.getRow() <= getNumRows() AND pos.getColumn() >= 0 AND pos.getColumn() <= getNumColumns
     * @post
     *  returns true if the position specified in pos is available; false otherwise
     *
     */
    default boolean checkSpace(BoardPosition pos)
    {
        int tempCol = pos.getColumn();
        int tempRow = pos.getRow();

        if (tempCol >= 0 && tempCol < getNumColumns() && tempRow >= 0 && tempRow < getNumRows()){

            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method places the character in marker's position
     * if the space is available.
     *
     * @param marker a position on the game board
     * @param player a player's character/key
     *
     * @pre
     *  marker.getRow >= 0 AND marker.getRow <= getNumRows AND
     *  marker.getColumn >= 0 AND marker.getColumn <= getNumColumns
     *  AND marker is free and available
     * @post
     *  marker's position on the board = player AND self = #self
     */
    void placeMarker(BoardPosition marker, char player);

    /**
     * This method tests if the last position placed resulted in a winner.
     *
     * @param lastPos the last/most recent position on the game board
     *
     * @return - returns true if the lastPos is a winner, otherwise false
     *
     * @pre
     *  lastPos = [the latest move/play on the board]
     * @post
     *  winner if lastPos = [connects getNumToWin in a row horizontal,
     *  vertical, or diagonally], otherwise false AND self = #self
     *
     */
    default boolean checkForWinner(BoardPosition lastPos)
    {
        boolean check = false;
        char player = whatsAtPos(lastPos);

        //
        check = checkHorizontalWin(lastPos, player);
        if (check == false) {
            check = checkVerticalWin(lastPos, player);
        }
        if (check == false){
            check = checkDiagonalWin(lastPos, player);
        }

        return check;
    }

    /**
     * This method tests if the game has resulted in a tie
     * if the game board has no free positions remaining.
     *
     * @return - returns true if the game is tied, false otherwise
     *
     * @pre
     *  board = [the game board doesn't have a winner after latest move]
     * @post
     *  board = [ every position on the board is a char without a connection
     *  of getNumToWin]
     */
    default boolean checkForDraw()
    {
        int count = 0;

        for (int i = 0; i < getNumRows(); i++){
            for (int j = 0; i < getNumColumns(); i++){

                BoardPosition temp;
                temp = new BoardPosition(i,j);
                char player = this.whatsAtPos(temp);
                if (player == '\0') {
                    return false;
                }
                else {
                    count += 1;
                }
            }
        }
        if (count == getNumColumns() * getNumRows()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method tests if the last marker placed resulted in required tokens needed in a row
     * to win horizontally.
     *
     * @param lastPos the last/most recent position on the game board
     * @param player a player's character/key
     *
     * @return - returns true if the lastPos results in a horizontal win,
     * otherwise false
     *
     * @pre
     *  lastPos = [the latest move/play on the board] AND lastPos = player
     * @post
     *  winner if on the board[lastPos.getRow and lastPos.getColumn + 1..] = player
     *  AND on the board[lastPos.getRow and lastPos.getColumn - 1..] = player until
     *  required positions filled is found, else false
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        int col = lastPos.getColumn();
        int row = lastPos.getRow();
        int total = 1;
        boolean check = true;
        boolean isWinner = false;

        for (int i = 1; check != false; i++){
            BoardPosition temp;
            temp = new BoardPosition(row, col - i);
            check = this.checkSpace(temp);
            if (check == false){
                break;
            }
            else {
                if (this.whatsAtPos(temp) == player){
                    total += 1;
                }
                else {
                    break;
                }
            }
        }
        check = true;
        for (int i = 1; check != false; i++){
            BoardPosition temp;
            temp = new BoardPosition(row, col + i);
            check = this.checkSpace(temp);
            if (check == false){
                break;
            }
            else {
                if (this.whatsAtPos(temp) == player){
                    total += 1;
                }
                else {
                    break;
                }
            }
        }

        if (total == getNumToWin()){
            isWinner = true;
        }
        return isWinner;
    }

    /**
     * This method tests if the last marker placed resulted in required tokens in a row
     * to win vertically.
     *
     * @param lastPos the last/most recent position on the game board
     * @param player a player's character/key
     *
     * @return - returns true if the lastPos results in a vertical win,
     * otherwise false
     *
     * @pre
     *  lastPos = [the latest move/play on the board] AND lastPos = player
     * @post
     *  winner if on the board[lastPos.getRow + 1.. and lastPos.getColumn] = player
     *  AND on the board[lastPos.getRow - 1.. and lastPos.getColumn] = player until
     *  required positions filled is found, else false
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        int col = lastPos.getColumn();
        int row = lastPos.getRow();
        int total = 1;
        boolean check = true;
        boolean isWinner = false;

        for (int i = 1; check != false; i++){
            BoardPosition temp;
            temp = new BoardPosition(row - i, col);
            check = this.checkSpace(temp);
            if (check == false){
                break;
            }
            else {
                if (this.whatsAtPos(temp) == player){
                    total += 1;
                }
                else {
                    break;
                }
            }
        }
        check = true;
        for (int i = 1; check != false; i++){
            BoardPosition temp;
            temp = new BoardPosition(row + i, col);
            check = this.checkSpace(temp);
            if (check == false){
                break;
            }
            else {
                if (this.whatsAtPos(temp) == player){
                    total += 1;
                }
                else {
                    break;
                }
            }
        }

        if (total == getNumToWin()){
            isWinner = true;
        }
        return isWinner;
    }

    /**
     * This method tests if the last marker placed resulted in required tokens in a row
     * to win diagonally.
     *
     * @param lastPos the last/most recent position on the game board
     * @param player a player's character/key
     *
     * @return - returns true if the lastPos results in a diagonal win,
     * otherwise false
     *
     * @pre
     *  lastPos = [the latest move/play on the board] AND lastPos = player
     * @post
     *  winner if on the board[lastPos.getRow + 1.. and lastPos.getColumn - 1..] = player
     *  AND on the board[lastPos.getRow - 1.. and lastPos.getColumn + 1..] = player until
     *  required positions filled is found, else false
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        int col = lastPos.getColumn();
        int row = lastPos.getRow();
        int total = 1;
        boolean check = true;
        boolean isWinner = false;

        for (int i = 1; check != false; i++){
            BoardPosition temp;
            temp = new BoardPosition(row - i, col - i);
            check = this.checkSpace(temp);
            if (check == false){
                break;
            }
            else {
                if (this.whatsAtPos(temp) == player){
                    total += 1;
                }
                else {
                    break;
                }
            }
        }
        check = true;
        for (int i = 1; check != false; i++){
            BoardPosition temp;
            temp = new BoardPosition(row + i, col + i);
            check = this.checkSpace(temp);
            if (check == false){

            }
            else {
                if (this.whatsAtPos(temp) == player){
                    total += 1;
                }
                else {
                    break;
                }
            }
        }

        if (total == getNumToWin()){
            isWinner = true;
        }
        return isWinner;
    }

    /**
     * This method tests if the player is at a certain position
     *
     * @param  pos a position on the game board
     *
     * @return - returns character at pos; otherwise, it returns blank space char
     *
     * @pre
     *  pos = [within the user specified bounds of board]
     * @post
     *  pos on the GameBoard = '[token]' OR pos on the GameBoard = '[empty]'
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * This method tests if the player is at a certain position
     *
     * @param pos a position on the game board
     * @param player a player's character/key
     *
     * @return - true if the player is at pos; otherwise, it returns false
     *
     * @pre
     *  pos = [within the user specified bounds of GameBoard]
     * @post
     *  returns true if pos on the board = player, otherwise false
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player);

    /**
     * This method returns the total number of rows in the GameBoard.
     *
     * @return - returns the number of rows in the GameBoard
     *
     * @pre
     *  none
     * @post
     *  An integer that represents the number of rows on the board.
     *
     */
    public int getNumRows();

    /**
     * This method returns the total number of columns in the GameBoard.
     *
     * @return - returns the number of columns in the GameBoard
     *
     * @pre
     *  none
     * @post
     *  An integer that presents the number of columns on the board.
     *
     */
    public int getNumColumns();

    /**
     * This method returns the total number of tokens needed to win on the
     * GameBoard.
     *
     * @return - returns the number of tokens in a row needed to win the game
     *
     * @pre
     *  none
     * @post
     *  An integer that represents the amount of positions needed
     *  for the player to connect in a row to win the game.
     *
     */
    public int getNumToWin();

}
