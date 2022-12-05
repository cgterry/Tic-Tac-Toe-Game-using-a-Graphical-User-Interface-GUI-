package cpsc2150.extendedTicTacToe.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that creates and contains the 2-dimensional user specified game board
 * that the players will play TicTacToe on.
 *
 *
 * @correspondances self = [board.containsKey() for each player] AND self = empty
 *
 * @invariant
 *  board = HashMap<Character, List<BoardPosition>>() AND numRows = rows
 *  AND numCols = cols AND tokensToWin = numToWin
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {

   private Map<Character, List<BoardPosition>> board;

    private int numCols;
    private int numRows;
    private int tokensToWin;

    /**
     * TThis constructor that creates the 2-dimensional user-specified game board.
     *
     *
     * @pre
     *  none
     * @post
     *  Creates a user specified 2D Map that will hold each player as keys and each player's list is empty.
     */
    public GameBoardMem(int rows, int cols, int numToWin){
        numRows = rows;
        numCols = cols;
        tokensToWin = numToWin;
        board = new HashMap<Character, List<BoardPosition>>();
    }

    public void placeMarker(BoardPosition marker, char player)
    {
        if (!board.containsKey(player)){
            board.put(player, new ArrayList<>());
        }
        board.get(player).add(marker);
    }

    public char whatsAtPos(BoardPosition pos)
    {

        Character value = ' ';

        for(Map.Entry<Character, List<BoardPosition>> test: board.entrySet()){
            List<BoardPosition> list = test.getValue();
            int size = list.size();
            for (int i = 0; i < size; i++){
                BoardPosition test2 = list.get(i);
               boolean equal = false;
               equal = test2.equals(pos);
               if (equal == true){
                   value = test.getKey();
                   break;
               }
            }

        }

        if (value == null){
            value = ' ';
            return value;
        }
        else {
            return value;
        }
    }

    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {

        char test =  ' ';
        boolean equal = false;

        List<BoardPosition> temp = board.get(player);
        int size = temp.size();
        for (int i = 0; i < size; i++) {
            BoardPosition test2 = temp.get(i);
            equal = test2.equals(pos);
            if (equal == true) {
                test = player;
            }
        }

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

