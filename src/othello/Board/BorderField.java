package othello.Board;

import java.io.Serializable;

public class BorderField implements Field, Serializable
{
    protected Field[] field_surround = new Field[8];
    @Override
    public void addNextField(Direction dirs, Field field)
    {
        field_surround[dirs.ordinal()]=field;
    }

    @Override
    public Field nextField(Direction dirs)
    {
        return null;
    }

    @Override
    public Disk getDisk()
    {
        return null;
    }

    @Override
    public int putDisk(Disk disk)
    {
        return 0;
    }

    public boolean isEmpty()
    {
        return true;
    }

    public boolean canPutDisk(Disk disk)
    {
        return false;
    }
    
    @Override
    public int array_size()
    {
        return 0;
    }
    
    @Override
    public int get_row()
    {
        return 0;
    }
    
    @Override
    public int get_col()
    {
        return 0;
    }

    @Override
    public void freezeField() {}

    @Override
    public void unFreezeField() {}

    @Override
    public boolean isFrozen() 
    {
        return false;
    }
}
