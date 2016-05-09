package othello.Board;

/**
 * Interace for a field on the board
 * @author xdurco00, xdomon00
 */
public interface Field
{
    enum Direction
    {
        LU, U, RU, L, R, LD ,D, RD;
    }
    void addNextField(Field.Direction dirs, Field field);
    Disk getDisk();
    Field nextField(Field.Direction dirs);
    int putDisk(Disk disk);
    boolean isEmpty();
    boolean canPutDisk(Disk disk);
    void freezeField();
    void unFreezeField();
    boolean isFrozen();
    int array_size();
    int get_row();
    int get_col();
}