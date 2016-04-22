package othello.Board;

public class Board
{
    protected Field[] board_array; // array of objects which implements interface Field
    protected int size;
    protected Rules rules;

    public Board(Rules rules)
    {
        this.rules = rules;
        this.size = rules.getSize();
        this.board_array = new Field[(this.size + 2) * (this.size + 2)];

        for (int i = 0; i < size + 2; i++)
        {
            for (int j = 0; j < size + 2; j++)
            {
                this.board_array[i * (this.size + 2) + j]=rules.createField(i,j);
            }
        }
        // set environment of field
        int index = 0;
        for (int i = 0; i < this.size + 2; i++)
            for (int j = 0; j < this.size + 2; j++)
            {
                board_array[index].addNextField(Field.Direction.D, getField(i + 1, j));
                board_array[index].addNextField(Field.Direction.L, getField(i, j - 1));
                board_array[index].addNextField(Field.Direction.U, getField(i - 1, j));
                board_array[index].addNextField(Field.Direction.R, getField(i, j + 1));
                board_array[index].addNextField(Field.Direction.LD, getField(i + 1, j - 1));
                board_array[index].addNextField(Field.Direction.LU, getField(i - 1, j - 1));
                board_array[index].addNextField(Field.Direction.RD, getField(i + 1, j + 1));
                board_array[index].addNextField(Field.Direction.RU, getField(i - 1, j + 1));
                index++;
            }
    }


    // return object on inserted [row,col] otherwise null
    public final Field getField(int row, int col)
    {
        if (row >= 0 && row < (size + 2) && col >= 0 && col < (size + 2))
            return board_array[row*(this.size+2)+col];
        else
            return null;
    }

    // return the size of the board
    public int getSize()
    {
        return this.size;
    }

    // return object of rules
    public Rules getRules()
    {
        return this.rules;
    }
}
