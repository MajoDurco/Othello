package othello.Gui;

import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Random;
import othello.Board.Board;
import othello.Board.Disk;
import othello.Board.Field;
import othello.Game.Game;
import othello.othelloAI.othelloAI;

/**
 * Panel for the main board of the game
 * @author xdurco00, xdomon00
 */
public class BoardX extends javax.swing.JPanel {
    private final int fields;
    private final Game game;
    private FieldX[][] ar_fields;
    private final GameX gameX;
    private othelloAI art_int;
    protected ArrayList<FieldX> freeze_fieldsX;
    protected ArrayList<Field> freeze_fields;
    
    /**
     * Create board, initialize fields,stones
     * @param fields Number of fields in board
     * @param game The game
     * @param gameX The GUI game
     */
    public BoardX(int fields,Game game,GameX gameX) 
    {
        this.fields = fields;
        this.game = game;
        this.gameX = gameX;
        freeze_fieldsX = new ArrayList<>();
        freeze_fields = new ArrayList<>();

        initComponents();
        initFields();
        initStones();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDoubleBuffered(false);
        setPreferredSize(new java.awt.Dimension(600, 600));
        setLayout(new java.awt.GridBagLayout());
    }// </editor-fold>//GEN-END:initComponents

    private void initFields()
    {
       GridBagConstraints c = new GridBagConstraints();
       int x = 0;
       int y = 0;
       this.ar_fields = new FieldX[fields][fields];

       c.anchor = GridBagConstraints.NORTHWEST;
       for (int i = 0; i<fields*fields; i++)
        {
          c.gridx = x;
          c.gridy = y;
          FieldX f = new FieldX(y+1,x+1,fields,y%2==0?x%2:(x+1)%2,this);
          ar_fields[y][x]=f;
          this.add(f,c);
          if( x == (fields-1) )
          {
              x = 0;
              y++;
          }
          else
              x++;
        }
        c.weighty = 1;
        c.weightx = 1;
    }
    
    private void initStones()
    {
        switch (fields)
        {
            case 6:
                ar_fields[2][2].setStone(true);
                ar_fields[3][3].setStone(true);
                ar_fields[2][3].setStone(false);
                ar_fields[3][2].setStone(false);
                break;
            case 8:
                ar_fields[3][3].setStone(true);
                ar_fields[4][4].setStone(true);
                ar_fields[3][4].setStone(false);
                ar_fields[4][3].setStone(false);
                break;
            case 10:
                ar_fields[4][4].setStone(true);
                ar_fields[5][5].setStone(true);
                ar_fields[4][5].setStone(false);
                ar_fields[5][4].setStone(false);
                break;
            case 12:
                ar_fields[5][5].setStone(true);
                ar_fields[6][6].setStone(true);
                ar_fields[5][6].setStone(false);
                ar_fields[6][5].setStone(false);
                break;
        }

    }
    
    // this col and row starts from [1,1]

    /**
     * Result of click listener on field
     * @param row Row of clicked field
     * @param col Col of clicked field
     */
    protected void clickPerformed(int row,int col)
    {
        if (canPlaceStone(row,col)) // true
        {
            if(!gameX.getOponentIsPlayer() && game.currentPlayer().isWhite())
                return;
            Field f1 = game.getBoard().getField(row, col);
            if(!game.currentPlayer().isWhite() && !gameX.getOponentIsPlayer()) // is black so its player
            {
                game.playerMove(); // save board for undo
                gameX.enableUndo();
            }
            int swaped = this.game.currentPlayer().putDisk(f1);
            ar_fields[row-1][col-1].setStone(game.currentPlayer().isWhite());
            refactor();
            if(gameX.freeze_stones)
                gameX.stopTimer();
            game.nextPlayer(); // on successful place the stone switch player
            this.game.currentPlayer().setStoneNum(-swaped,true); // have to balance the number of stones on the other side
            if(checkEndGame())
                gameX.endGame();
            if(gameX.freeze_stones && gameX.getOponentIsPlayer())
                gameX.startTimer();
            if(!gameX.getOponentIsPlayer() && game.currentPlayer().isWhite())  // player is AI
            {
                this.art_int = gameX.getAI();
                art_int.getFieldAI(game);
            } 
         }
    }
    
    /**
     * Places stones for AI
     * @param row Row where place stone
     * @param col Col where place stone
     */
    public void moveAI(int row,int col)
    {
        Field f1 = game.getBoard().getField(row, col);
        if(!this.game.currentPlayer().canPutDisk(f1))
            return;
        int swaped = this.game.currentPlayer().putDisk(f1);
        ar_fields[row-1][col-1].setStone(game.currentPlayer().isWhite());
        refactor();
        if(gameX.freeze_stones)
            gameX.stopTimer();
        game.nextPlayer(); // on successful place the stone switch player
        this.game.currentPlayer().setStoneNum(-swaped,true); // have to balance the number of stones on the other side
        if(checkEndGame())
            gameX.endGame();
        if(gameX.freeze_stones)
            gameX.startTimer();
    }
    
    private boolean canPlaceStone(int row,int col)
    {
            Field f1 = game.getBoard().getField(row, col);
            return this.game.currentPlayer().canPutDisk(f1);
    }
    
    private void refactor()
    {
        Board b = game.getBoard();
        for(int y=1; y<=fields; y++)
            for(int x=1; x<=fields; x++)
            {
                Disk f = b.getField(y, x).getDisk();
                FieldX fX = ar_fields[y-1][x-1];
                if (f != null)
                { 
                   if(fX.isWhite != f.isWhite()) // control the colors on the same position
                   { // repaint 
                      fX.setStone(f.isWhite());
                   }
                }
            }
    }
    
    private boolean checkEndGame()
    {
        Board b = game.getBoard();
        boolean next_player_move=false;
        for(int y=1; y<=fields; y++)
            for(int x=1; x<=fields; x++)
            {
                Disk f = b.getField(y, x).getDisk();        
                if (f == null && canPlaceStone(y,x))
                    return false;
                if (f == null && (game.getPlayer(!game.currentPlayer().isWhite()).canPutDisk(b.getField(y,x))))
                    next_player_move=true;

            }
        if(next_player_move)
        {
            game.nextPlayer(); // switch player
            if(!gameX.getOponentIsPlayer() && game.currentPlayer().isWhite())  // player is AI
            {
                this.art_int = gameX.getAI();
                art_int.getFieldAI(game);
            } 
            return false;
        }
        // if all stones on the board is equal to board_size*board_size
        int sum = gameX.getPlayerScore(true) + gameX.getPlayerScore(false);
        if(sum == this.fields*this.fields)
            return true;
        return true;
    }
    
    /**
     *  Load the state of fields
     */
    protected void loadField()
    {
        Board b = game.getBoard();
        for(int y=1; y<=fields; y++)
            for(int x=1; x<=fields; x++)
            {
                Disk f = b.getField(y, x).getDisk();
                FieldX fX = ar_fields[y-1][x-1];
                if (f != null)
                { 
                    if(f.isWhite())
                       fX.setStone(true);
                   else
                       fX.setStone(false);
                }
            }
    }
    
    /**
     * Calculate the right score after undo
     * @return Sum each stones on the board
     */
    protected int[] undoClicked()
    {
        int black=0;
        int white=0;
        int[] ret = {black,white};
        
        this.game.undo();
        
        Board b = game.getBoard();
        for(int y=1; y<=fields; y++)
            for(int x=1; x<=fields; x++)
            {
                Disk f = b.getField(y, x).getDisk();
                FieldX fX = ar_fields[y-1][x-1];
                if (!fX.isEmpty() && f==null)
                { 
                    fX.removeStone();
                }
                else
                {
                    if (f != null)
                    { 
                        if(f.isWhite())
                        {
                            fX.setStone(true);
                            white++;
                        }
                        else
                        {
                            fX.setStone(false);
                            black++;
                        }
                    }
                }
            }
        ret[0] = black;
        ret[1] = white;
        return ret;
    }
    
    /**
     * Get occupied fields by stones
     * @return Fields on which is stone
     */
    protected ArrayList<FieldX> getOccupiedFields()
    {
        ArrayList<FieldX> occupied_fields = new ArrayList<>();
        for(int y=1; y<=fields; y++)
            for(int x=1; x<=fields; x++)
            {
                FieldX fX = ar_fields[y-1][x-1];
                if (!fX.isEmpty())
                { 
                    occupied_fields.add(fX);
                }
            }
        return occupied_fields;
    } 

    /**
     * Freeze stones
     * @param count Number of stones to freeze
     */
    protected void freezeStones(long count)
    {
       ArrayList<FieldX> occupied_fields = getOccupiedFields();
       Random randomGenerator = new Random();
       if(count > occupied_fields.size())
           count = occupied_fields.size();
       for(int i=0; i<count; i++)
        {
            int index = randomGenerator.nextInt(occupied_fields.size());
            FieldX occupied_field = occupied_fields.get(index);
            occupied_field.freezeField();
            freeze_fieldsX.add(occupied_field);
            Field f = this.game.getBoard().getField(occupied_field.row, occupied_field.col);
            f.freezeField();
            freeze_fields.add(f);
            occupied_fields.remove(index);
        }
    }
    
    /**
     * Unfreeze stones
     */
    protected void unFreezeStones()
    {
       for(FieldX f:freeze_fieldsX)
       {
        f.unFreezeField();
       }
       freeze_fieldsX.clear();
       
       for(Field f:freeze_fields)
       {
        f.unFreezeField();
       }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}