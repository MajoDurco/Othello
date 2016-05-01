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
}
