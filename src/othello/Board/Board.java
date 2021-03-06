package othello.Board;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Implements the othello board
 * @author xdurco00, xdomon00
 */
public class Board implements Serializable
{
    public Field[] board_array; // array of objects which implements interface Field
    protected int size;
    protected Rules rules;
    private Field[] undo_board;

    /**
     * Initializes the board, board size, creates fields
     * @param rules Rules of the game
     */
    public Board(Rules rules)
    {
        this.rules = rules;
        this.size = rules.getSize();
        this.board_array = new Field[(this.size + 2) * (this.size + 2)];
        this.undo_board = new Field[(this.size + 2) * (this.size + 2)];

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

    /**
     * Returns the selected field
     * @param row number of row on the board
     * @param col number of column on the board
     * @return field or null
     */
    public final Field getField(int row, int col)
    {
        if (row >= 0 && row < (size + 2) && col >= 0 && col < (size + 2))
            return board_array[row*(this.size+2)+col];
        else
            return null;
    }

    // return the size of the board

    /**
     * Returns the size of the board
     * @return size of the board
     */
    public int getSize()
    {
        return this.size;
    }

    // return object of rules

    /**
     * Returns the rules
     * @return rules of the game
     */
    public Rules getRules()
    {
        return this.rules;
    }
    
    /**
     * Saves the current turn
     */
    public void saveTurn()
    {
        this.undo_board  = (Field[])deepClone(this.board_array);
    }
    
    /**
     * Loads the current turn
     */
    public void loadTurn()
    {
        if ( this.undo_board != null)
            this.board_array = (Field[])deepClone(this.undo_board);
    }
    
    /**
     *
     * @param object Clonning object
     * @return object Clonned object
     */
    public static Object deepClone(Object object) 
  {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
      ObjectInputStream ois = new ObjectInputStream(bais);
      return ois.readObject();
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
