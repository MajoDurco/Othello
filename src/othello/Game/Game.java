package othello.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import othello.Board.*;

public class Game extends Observable implements Serializable
{
    protected Board board;
    private Player white_P;
    private Player black_P;
    private Player turn;
    
    private boolean freeze_stones=false;
    long [] constants = new long[3];
    
    private boolean oponent_is_player;
    private boolean is_easy_diff;

        private final transient List<Observer> observers = new ArrayList<>();

    public Game(Board board)
    {
        this.board=board;
        this.white_P = null;
        this.black_P = null;
        this.turn = null;
    }

    public boolean addPlayer(Player player)
    {
        boolean color = player.isWhite();
        if(color && this.white_P==null)
        {
            this.white_P=player;
            player.init(board);
            return true;
        }
        else if(!color && this.black_P==null)
        {
            this.black_P=player;
            this.turn=player;
            player.init(board);
            return true;
        }
        else
            return false;
    }

    public Player currentPlayer()
    {
        return turn;
    }
    
    public Player getPlayer(boolean isWhite)
    {
        return isWhite?this.white_P:this.black_P;
    }
    
    public Player nextPlayer()
    {
        if(turn.isWhite())
        {
            this.turn = black_P;
            notifyAllObservers();
            return (this.black_P);
        }
        else
        {
            this.turn = white_P;
            notifyAllObservers();
            return this.white_P;
        }
    }

    public Board getBoard()
    {
        return board;
    }
    
    public void attach(Observer observer)
    {
        observers.add(observer);
    }
    
    public void removeAllObservers()
    {
        observers.clear();
    }
    
    public void notifyAllObservers()
    {
        for (Observer observer : observers)
        {
             observer.update(this, null);
        }
    }
    
    public void playerMove()
    {
        this.board.saveTurn();
    }
    
    public void undo()
    {
        this.board.loadTurn();
    }

    public void setFreezeConstants(long[] constants) // [number_freeze_stones,time_to_freeze,time_to_unfreeze]
    {
        System.arraycopy(constants, 0, this.constants, 0, this.constants.length);
    }
    
    public long[] getFreezeConstants()
    {
        long[] constants = new long[3];
        System.arraycopy(this.constants, 0, constants, 0, this.constants.length);
        return constants;
    }
    
    public void setFreezeStones(boolean freeze)
    {
        freeze_stones=freeze;
    }
    
    public boolean getFreezeStones()
    {
        return freeze_stones;
    }
    
    public void setOponentIsPlayer(boolean oponent)
    {
        this.oponent_is_player=oponent;
    }
    
    public boolean getOponentIsPlayer()
    {
        return this.oponent_is_player;
    }
    
    public void setIsEasyDiff(boolean diff)
    {
        this.is_easy_diff=diff;
    }
    
    public boolean getIsEasyDiff()
    {
        return this.is_easy_diff;
    }
}
