package othello.Board;

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
}