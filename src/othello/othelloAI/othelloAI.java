package othello.othelloAI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.Timer;
import othello.Board.*;
import othello.Game.*;
import othello.Gui.*;

public class othelloAI
{
    private final boolean is_easy_diff;
    private ArrayList<Field> fieldList;
    private int board_size;
    private Random randomField;
    private BoardX board;
    
    public othelloAI(boolean is_easy_diff, BoardX board)
    {
        this.is_easy_diff = is_easy_diff;
        fieldList = new ArrayList<>();
        randomField = new Random();
        this.board = board;
    }
    
    public boolean isWhite()
    {
        return true;
    }
    
    public void getFieldAI(Game game)
    {    
        this.board_size = game.getBoard().getSize();
        Field field_ai;
        Field f1, f_max = null;
        int max = 0;
        int pom;
        
        for (int i = 1; i <= this.board_size; i++){
            for (int j = 1; j <= this.board_size; j++)
            {
                f1 = game.getBoard().getField(i, j);
                if (game.currentPlayer().canPutDisk(f1))
                {
                    if (this.is_easy_diff)
                    {
                        fieldList.add(f1);
                    }
                    else 
                    {
                        pom = f1.array_size();
                        if (pom > max)
                        {
                            f_max = f1;
                            max = pom;
                        }
                    }
                }
            }
        }
        
        if (this.is_easy_diff){
            int index = randomField.nextInt(fieldList.size());
            field_ai = fieldList.get(index);
            fieldList.clear();
        }
        else {
            field_ai = f_max;
        }
        
        int row,col;
        
        row = field_ai.get_row();
        col = field_ai.get_col();
        
        int delay=1500;

        Timer timer = new Timer(delay, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                board.moveAI(row, col);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
