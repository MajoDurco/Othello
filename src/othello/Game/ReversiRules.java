package othello.Game;

import othello.Board.*;
public class ReversiRules implements Rules
{
    private int size;

    public ReversiRules(int size)
    {
        this.size = size;
    }

    public int getSize()
    {
        return this.size;
    }

    public int numberDisks()
    {
        return (this.size*this.size)/2;
    }

    public Field createField(int row, int col)
    {
        if ((row == 0 || row == size + 1) || (col == 0 || col == size + 1))
            return (new BorderField());
        else
            return (new BoardField(row, col));
    }
}
