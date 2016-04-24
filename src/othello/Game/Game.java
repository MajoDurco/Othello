package othello.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import othello.Board.*;

public class Game extends Observable
{
    protected Board board;
    private Player white_P;
    private Player black_P;
    private Player turn;
    
    private final List<Observer> observers = new ArrayList<>();

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

    public Player nextPlayer()
    {
        if(turn.isWhite())
        {
            this.turn = black_P;
            System.out.println("Next player will be black");
            notifyAllObservers();
            return (this.black_P);
        }
        else
        {
            this.turn = white_P;
            System.out.println("Next player will be black");
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
    
    public void notifyAllObservers()
    {
        for (Observer observer : observers)
        {
            System.out.println("Notify sent");  
            observer.update(this, null);
        }
    }
}
