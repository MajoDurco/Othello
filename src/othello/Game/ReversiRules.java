package othello.Game;

import java.io.Serializable;
import othello.Board.*;
public class ReversiRules implements Rules,Serializable
{
    private final int size;

    public ReversiRules(int size)
    {
        this.size = size;
    }

    @Override
    public int getSize()
    {
        return this.size;
    }

    @Override
    public int numberDisks()
    {
        return (this.size*this.size)/2;
    }

    @Override
    public Field createField(int row, int col)
    {
        if ((row == 0 || row == size + 1) || (col == 0 || col == size + 1))
            return (new BorderField());
        else
            return (new BoardField(row, col));
    }
}
