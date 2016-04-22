package othello.Board;

public class Disk
{
    protected boolean isWhite; // color of the disk, true == white, false == blacks
    public Disk(boolean isWhite)
    {
        this.isWhite=isWhite;
    }

    // true if disk is white
    public boolean isWhite()
    {
        if(this.isWhite)
        {
            return true;
        }
        return false;
    }

    // switch the disk color
    public void turn()
    {
        this.isWhite = !this.isWhite;
    }

    @Override
    public boolean equals(java.lang.Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Disk disk = (Disk) obj;

        return isWhite == disk.isWhite;

    }

    @Override
    public int hashCode()
    {
        return (isWhite ? 1 : 0);
    }
}
