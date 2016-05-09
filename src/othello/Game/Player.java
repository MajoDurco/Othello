package othello.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import othello.Board.*;

/**
 * Implements the othello Player
 * @author xdurco00, xdomon00
 */
public class Player extends Observable implements Serializable
{
    private boolean player_color; // true - white , false - black
    private int player_pool;
    private final transient List<Observer> observers = new ArrayList<>();
    
    /**
     * Initializes player color and pool
     * @param isWhite player color
     */
    public Player(boolean isWhite)
    {
        this.player_color = isWhite;
        this.player_pool = 0;
    }

    /**
     * Returns player color
     * @return player color
     */
    public boolean isWhite()
    {
        return player_color;
    }

    /**
     * Decides, if the player can put a disk on the selected Field
     * @param field selected field
     * @return success of operation
     */
    public boolean canPutDisk(Field field)
    {
        if(field.isEmpty())
        {
            Disk try_disk = new Disk(player_color);
            return field.canPutDisk(try_disk);
        }
        return false;
    }

    /**
     * Returns true if the player pool is empty and
     * false if it's not
     * @return true if the player pool is empty
     */
    public boolean emptyPool()
    {
        return player_pool==0?true:false;
    }
    
    /**
     * Sets the player pool
     * @param number number of stones
     * @param notify notify
     */
    public void setStoneNum(int number,boolean notify)
    {
        this.player_pool += number;
        if (notify)
            notifyAllObservers();
    }
    
    /**
     * Returns the player pool
     * @return number of stones
     */
    public int getStoneNum()
    {
        return this.player_pool;
    }

    /**
     * Player puts the disk on the selected field
     * @param field selected field
     * @return number of switched stones
     */
    public int putDisk(Field field)
    {
        if(this.canPutDisk(field))
        {
            int switched_stones = field.putDisk(new Disk(player_color));
            setStoneNum(switched_stones+1,true); // have to add also the new
            return (switched_stones); // return num of swaped without new one
        }
        return 0;
    }

    /**
     * Initializes the player
     * Puts the starting stones on the board
     * @param board game board
     */
    public void init(Board board)
    {
        int size = board.getSize();
        Field init_stone;
        if(player_color)// white
        {
            init_stone = board.getField(size/2,size/2);
            init_stone.putDisk(new Disk(player_color));
            init_stone = board.getField(size/2+1,size/2+1);
            init_stone.putDisk(new Disk(player_color));
        }
        else // black
        {
            init_stone = board.getField(size/2+1,size/2);
            init_stone.putDisk(new Disk(player_color));
            init_stone = board.getField(size/2,size/2+1);
            init_stone.putDisk(new Disk(player_color));
        }
        player_pool += 2;
    }

    @Override
    public String toString()
    {
        return this.player_color?"white":"black";
    }
    
    /**
     *
     * @param observer observer
     */
    public void attach(Observer observer)
    {
        observers.add(observer);
    }
    
    /**
     *
     */
    public void removeAllObservers()
    {
        observers.clear();
    }
    
    /**
     *
     */
    public void notifyAllObservers()
    {
        for (Observer observer : observers)
        {
             observer.update(this, this.player_pool);
        }
    }
}
