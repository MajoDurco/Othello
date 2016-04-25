package othello.Board;

import java.io.Serializable;
import java.util.ArrayList;

public class BoardField implements Field, Serializable
{
    protected int row;
    protected int col;
    protected Disk stone = null;
    protected Field[] field_surround = new Field[8];
    private ArrayList<Field> ar_list;
    public BoardField(int row,int col)
    {
        this.row = row;
        this.col = col;
        ar_list = new ArrayList<>();
    }

    @Override
    public void addNextField(Direction dirs, Field field)
    {
        field_surround[dirs.ordinal()]=field;
    }

    @Override
    public Field nextField(Direction dirs)
    {
        return field_surround[dirs.ordinal()];
    }

    @Override
    public Disk getDisk()
    {
        return stone;
    }

    @Override
    public int putDisk(Disk disk)
    {
        if(this.stone == null)
        {
            this.stone = disk;
            for (Field F : ar_list)
            {
                F.getDisk().turn();
            }
            int stone_switched = ar_list.size();
            ar_list.clear();
            return stone_switched;
        }
        else
            return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return stone == null;
    }

    @Override
    public boolean canPutDisk(Disk disk)
    {
        boolean return_value = false;
        boolean roolback = false;
        int old_counter=0;
        int new_counter=0;
        if(ar_list.size()!=0)
            ar_list.clear();
        for(int i =0; i<8; i++)
        {
            if(field_surround[i].getDisk()!=null)
            {
                Field tmp = field_surround[i];
                if(disk.isWhite()!=tmp.getDisk().isWhite()) // farba sa nezhoduje prehladame dalej v tom smere
                {
                    ar_list.add(tmp); // pridam kamen do listu
                    new_counter+=1;
                    Direction dir = Direction.values()[i]; // ulozim si smer
                    tmp = tmp.nextField(dir); // nastavim dalsi kamen v smere
                    while(true)
                    {// najdem rovnaku farbu(true)
                        if(tmp.getDisk()==null) // najdem prazdne policko false
                        {
                            roolback=true;
                            break;
                        }
                        else if (tmp.getDisk().isWhite() == disk.isWhite()) // som v nejakom smere nasiel rovnaku farbu ako aj vkladam
                        {
                            old_counter=new_counter;
                            return_value=true;
                            roolback=false;
                            break;
                        }
                        else // stale pokracuju superove kamene
                        {
                            if(tmp.nextField(dir)!=null)
                            {
                                ar_list.add(tmp);
                                new_counter+=1;
                                tmp = tmp.nextField(dir);
                            }
                            else
                            {
                                roolback = true;
                                break;
                            }
                        }
                    }
                    if(roolback)
                    { // zmazem z listu neplatne kamene ktore som presiel a nakoniec som zistil ze cela cesta nieje platna
                        if(ar_list.size()!=0)
                        {
                            while (new_counter > old_counter)
                            {
                                ar_list.remove(new_counter-1);
                                new_counter--;
                            }
                        }
                    }
                }
            }
        }
        return return_value;
    }

    @Override
    public boolean equals(java.lang.Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BoardField that = (BoardField) obj;

        if (row != that.row) return false;
        if (col != that.col) return false;
        return stone != null ? stone.equals(that.stone) : that.stone == null;

    }

    @Override
    public int hashCode()
    {
        int result = row;
        result = 31 * result + col;
        result = 31 * result + (stone != null ? stone.hashCode() : 0);
        return result;
    }
}
