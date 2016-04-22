package othello.Game;

import othello.Board.*;

public class Player
{
    private boolean player_color; // true - white , false - black
    private int player_pool;
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

    public boolean putDisk(Field field)
    {
        if(this.canPutDisk(field))
        {
            return (field.putDisk(new Disk(player_color)));
        }

        return false;
    }

    public void init(Board board)
    {
        int size = board.getSize();
        player_pool = size*size/2;
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
        player_pool -= 2;
    }

    @Override
    public String toString()
    {
        return this.player_color?"white":"black";
    }
}
