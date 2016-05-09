package othello.Game;

import java.io.Serializable;
import othello.Board.*;

/**
 * Implements the rules of the othello game
 * @author xdurco00, xdomon00
 */
public class ReversiRules implements Rules,Serializable
{
    private final int size;

    /**
     * Initielizes the game board size
     * @param size size of board
     */
    public ReversiRules(int size)
    {
        this.size = size;
    }

    /**
     * Returns the size of the board
     * @return size of board
     */
    @Override
    public int getSize()
    {
        return this.size;
    }

    /**
     * Returns the number of the disks
     * @return number of disks
     */
    @Override
    public int numberDisks()
    {
        return (this.size*this.size)/2;
    }

    /**
     * Creates a field on the board
     * @param row number of row
     * @param col number of column
     * @return selected field
     */
    @Override
    public Field createField(int row, int col)
    {
        if ((row == 0 || row == size + 1) || (col == 0 || col == size + 1))
            return (new BorderField());
        else
            return (new BoardField(row, col));
    }
}
