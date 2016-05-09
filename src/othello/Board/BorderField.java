package othello.Board;

import java.io.Serializable;

/**
 * Implements a single field on the outside border of the board
 * @author xdurco00, xdomon00
 */
public class BorderField implements Field, Serializable
{
    protected Field[] field_surround = new Field[8];

    /**
     * Adds a border field
     * @param dirs direction of next field
     * @param field selected field
     */
    @Override
    public void addNextField(Direction dirs, Field field)
    {
        field_surround[dirs.ordinal()]=field;
    }

    /**
     * Returns null
     * @param dirs direction
     * @return next field
     */
    @Override
    public Field nextField(Direction dirs)
    {
        return null;
    }

    /**
     * Does nothing
     * @return null
     */
    @Override
    public Disk getDisk()
    {
        return null;
    }

    /**
     * Does nothing
     * @param disk
     * @return 0
     */
    @Override
    public int putDisk(Disk disk)
    {
        return 0;
    }

    /**
     * Returns true, this field is always empty
     * @return true
     */
    public boolean isEmpty()
    {
        return true;
    }

    /**
     * Return false
     * @param disk
     * @return false
     */
    public boolean canPutDisk(Disk disk)
    {
        return false;
    }
    
    /**
     * Does nothing
     * @return 0
     */
    @Override
    public int array_size()
    {
        return 0;
    }
    
    /**
     * Does nothing
     * @return 0
     */
    @Override
    public int get_row()
    {
        return 0;
    }
    
    /**
     * Does nothing
     * @return 0
     */
    @Override
    public int get_col()
    {
        return 0;
    }

    /**
     * DOes nothing
     */
    @Override
    public void freezeField() {}

    /**
     * Does nothing
     */
    @Override
    public void unFreezeField() {}

    /**
     * DOes nothing
     * @return false
     */
    @Override
    public boolean isFrozen() 
    {
        return false;
    }
}
