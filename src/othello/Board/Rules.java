package othello.Board;

/**
 * Interface for the game rules
 * @author xdurco00, xdomon00
 */
public interface Rules
{
    int getSize(); // return size of board
    int numberDisks(); // return num. of player stones
    Field createField(int row,int col); // create array at inserted position
}
