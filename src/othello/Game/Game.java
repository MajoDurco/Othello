package othello.Game;

import othello.Board.*;

public class Game
{
    protected Board board;
    private Player white_P;
    private Player black_P;
    private Player turn;

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
            this.turn=player;
            player.init(board);
            return true;
        }
        else if(!color && this.black_P==null)
        {
            this.black_P=player;
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
            return (turn = black_P);
        else
            return (turn = white_P);
    }

    public Board getBoard()
    {
        return board;
    }
}
