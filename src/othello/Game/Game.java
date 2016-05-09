package othello.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import othello.Board.*;

/**
 * Implements a othello game
 * @author xdurco00, xdomon00
 */
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

    /**
     * Initializes the game
     * @param board game board
     */
    public Game(Board board)
    {
        this.board=board;
        this.white_P = null;
        this.black_P = null;
        this.turn = null;
    }

    /**
     * Adds a player t the game
     * @param player player to add
     * @return color of added player
     */
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

    /**
     * Returns current player
     * @return current player
     */
    public Player currentPlayer()
    {
        return turn;
    }
    
    /**
     * Returns the selected player
     * @param isWhite player color
     * @return player of selected color
     */
    public Player getPlayer(boolean isWhite)
    {
        return isWhite?this.white_P:this.black_P;
    }
    
    /**
     * Returns the next player
     * @return next player
     */
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

    /**
     * Returns the othello game board
     * @return game board
     */
    public Board getBoard()
    {
        return board;
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
             observer.update(this, null);
        }
    }
    
    /**
     *
     */
    public void playerMove()
    {
        this.board.saveTurn();
    }
    
    /**
     * Undo a move
     */
    public void undo()
    {
        this.board.loadTurn();
    }

    /**
     *
     * @param constants constants
     */
    public void setFreezeConstants(long[] constants) // [number_freeze_stones,time_to_freeze,time_to_unfreeze]
    {
        System.arraycopy(constants, 0, this.constants, 0, this.constants.length);
    }
    
    /**
     *
     * @return array of freeze constants
     */
    public long[] getFreezeConstants()
    {
        long[] constants = new long[3];
        System.arraycopy(this.constants, 0, constants, 0, this.constants.length);
        return constants;
    }
    
    /**
     * Freeze the stones
     * @param freeze if true, the stones will freeze
     */
    public void setFreezeStones(boolean freeze)
    {
        freeze_stones=freeze;
    }
    
    /**
     * Return true if the stones are frozen
     * @return true if the stones are frozen
     */ 
    public boolean getFreezeStones()
    {
        return freeze_stones;
    }
    
    /**
     * Sets the opponent, player or AI
     * @param oponent oponent - player or AI
     */
    public void setOponentIsPlayer(boolean oponent)
    {
        this.oponent_is_player=oponent;
    }
    
    /**
     * Returns true if the oponent is a player,
     * or false if the oponent is the AI
     * @return oponent - player or AI
     */
    public boolean getOponentIsPlayer()
    {
        return this.oponent_is_player;
    }
    
    /**
     * Sets the AI difficulty
     * @param diff oponent difficulty
     */
    public void setIsEasyDiff(boolean diff)
    {
        this.is_easy_diff=diff;
    }
    
    /**
     * Returns the AI difficulty
     * @return oponent difficulty
     */
    public boolean getIsEasyDiff()
    {
        return this.is_easy_diff;
    }
}
