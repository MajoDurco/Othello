package othello.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import othello.Board.*;

public class Player extends Observable
{
    private boolean player_color; // true - white , false - black
    private int player_pool;
    private final List<Observer> observers = new ArrayList<>();
    
    public Player(boolean isWhite)
    {
        this.player_color = isWhite;
        this.player_pool = 0;
    }

    public boolean isWhite()
    {
        return player_color;
    }

    public boolean canPutDisk(Field field)
    {
        if(field.isEmpty())
        {
            Disk try_disk = new Disk(player_color);
            return field.canPutDisk(try_disk);
        }
        return false;
    }

    public boolean emptyPool()
    {
        return player_pool==0?true:false;
    }
    
    public void setStoneNum(int number)
    {
        this.player_pool += number;
        notifyAllObservers();
    }
    
    public int getStoneNum()
    {
        return this.player_pool;
    }

    public int putDisk(Field field)
    {
        if(this.canPutDisk(field))
        {
            int switched_stones = field.putDisk(new Disk(player_color));
            setStoneNum(switched_stones+1); // have to add also the new
            return (switched_stones); // return num of swaped without new one
        }
        return 0;
    }

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
    
    public void attach(Observer observer)
    {
        observers.add(observer);
    }
    
    public void notifyAllObservers()
    {
        for (Observer observer : observers)
        {
             observer.update(this, this.player_pool);
        }
    }
}
