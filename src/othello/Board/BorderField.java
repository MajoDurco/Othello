package othello.Board;

public class BorderField implements Field
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
}
