package othello.Board;

import java.io.Serializable;

/**
 * Disk or stone in the game
 * @author xdurco00, xdomon00
 */
public class Disk implements Serializable
{
    protected boolean isWhite; // color of the disk, true == white, false == blacks

    /**
     * Create disk
     * @param isWhite color of disk
     */
    public Disk(boolean isWhite)
    {
        this.isWhite=isWhite;
    }

    // true if disk is white

    /**
     * Check disk's color
     * @return color of disk
     */
    public boolean isWhite()
    {
        if(this.isWhite)
        {
            return true;
        }
        return false;
    }
    /**
     * Switch disk color
     */
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
