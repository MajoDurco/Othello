package othello.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import othello.Board.Board;
import othello.Game.Game;
import othello.Game.Player;
import othello.Game.ReversiRules;
import othello.othelloAI.othelloAI;

/**
 * GUI representation of game
 * @author xdurco00, xdomon00
 */
public class GameX extends javax.swing.JFrame implements Observer
{
    private ReversiRules rules; 
    private Board board; 
    private Game game;
    private Game game_serialized;
    private BoardX boardX;
    private Player p1;
    private Player p2;
    private othelloAI ai1;
    private int board_size;
    private boolean oponent_is_player;
    private boolean is_easy_diff;
    
    protected boolean freeze_stones = false;
    protected long number_freeze_stones = 0;
    protected long time_to_freeze = 0;
    protected long time_to_unfreeze = 0;
    
    protected Timer timer;

    /**
     * Create main frame of the whole game
     */
    public GameX() 
    {
        initComponents();
        visibleSidePanel(false);
        this.Undo.setEnabled(false);
        this.Save.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoardX = new javax.swing.JPanel();
        SideBar = new javax.swing.JPanel();
        Turn = new javax.swing.JLabel();
        TurnPlayer = new javax.swing.JLabel();
        WhiteStones = new javax.swing.JLabel();
        BlackStones = new javax.swing.JLabel();
        WhiteCount = new javax.swing.JLabel();
        BlackCount = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        New = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem();
        Load = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Quit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Undo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Othello");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Pictures/othello_icon.png")).getImage());
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        BoardX.setPreferredSize(new java.awt.Dimension(600, 600));
        BoardX.setLayout(new java.awt.BorderLayout());

        SideBar.setPreferredSize(new java.awt.Dimension(200, 600));

        Turn.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        Turn.setText("Turn:");

        TurnPlayer.setText("Black");

        WhiteStones.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        WhiteStones.setText("White Stones:");

        BlackStones.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        BlackStones.setText("Black Stones:");

        WhiteCount.setText("2");

        BlackCount.setText("2");

        javax.swing.GroupLayout SideBarLayout = new javax.swing.GroupLayout(SideBar);
        SideBar.setLayout(SideBarLayout);
        SideBarLayout.setHorizontalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideBarLayout.createSequentialGroup()
                        .addComponent(WhiteCount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideBarLayout.createSequentialGroup()
                        .addComponent(BlackCount, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SideBarLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(TurnPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SideBarLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BlackStones)
                            .addComponent(WhiteStones)))
                    .addGroup(SideBarLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(Turn)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        SideBarLayout.setVerticalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Turn)
                .addGap(18, 18, 18)
                .addComponent(TurnPlayer)
                .addGap(45, 45, 45)
                .addComponent(WhiteStones)
                .addGap(18, 18, 18)
                .addComponent(WhiteCount)
                .addGap(18, 18, 18)
                .addComponent(BlackStones)
                .addGap(18, 18, 18)
                .addComponent(BlackCount)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setName("Othello"); // NOI18N

        jMenu1.setText("Menu");

        New.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        New.setText("New game");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });
        jMenu1.add(New);

        Save.setText("Save game");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        jMenu1.add(Save);

        Load.setText("Load game");
        Load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadActionPerformed(evt);
            }
        });
        jMenu1.add(Load);
        jMenu1.add(jSeparator1);

        Quit.setActionCommand("Quit");
        Quit.setLabel("Quit");
        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitActionPerformed(evt);
            }
        });
        jMenu1.add(Quit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Game");

        Undo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        Undo.setText("Undo");
        Undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoActionPerformed(evt);
            }
        });
        jMenu2.add(Undo);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BoardX, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BoardX, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
            .addComponent(SideBar, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //    Quit button action
    private void QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_QuitActionPerformed
    //    New game
    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
        NewGamePanel p = new NewGamePanel();
        String[] options = {"Create","Cancel"};
        int response = JOptionPane.showOptionDialog(this,p,"Create New Game",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        if(response != JOptionPane.OK_OPTION)
        {
            return;
        }
        
        this.board_size = p.getBoardSize();
        this.oponent_is_player = p.getOponent();
        if(!this.oponent_is_player) // AI
        {
            AIDifficulty dific = new AIDifficulty();
            JOptionPane.showMessageDialog(this,dific,"Choose Difficulty",JOptionPane.PLAIN_MESSAGE);
            this.is_easy_diff=dific.getDifficulty();
        }
        this.freeze_stones = p.getFreeze();
        
        long[] constants = new long[3];
        if(freeze_stones)
        {
            FreezingStonesPanel freeze_panel = new FreezingStonesPanel();
            JOptionPane.showMessageDialog(this,freeze_panel,"Settings for freezing stones",JOptionPane.PLAIN_MESSAGE);
            constants[0] = freeze_panel.getFreezeStones();
            constants[1] = freeze_panel.getTimeToFreeze();
            constants[2] = freeze_panel.getTimeToUnfreeze();
            this.number_freeze_stones = constants[0];
            this.time_to_freeze = constants[1]*1000;
            this.time_to_unfreeze = constants[2]*1000;
            checkFreezeConstants();
        }

        if (boardX != null)
        {
            this.Undo.setEnabled(false);
            BoardX.remove(boardX);
            BoardX.repaint();
        }
        initGame();
        boardX = new BoardX(this.board_size,game,this);
        BoardX.add(boardX).setVisible(true);
        this.boardX.loadField();
        TurnPlayer.setText("Black");
        if(!this.oponent_is_player)
            ai1 = new othelloAI(getAIDiff(), boardX);
        game.setFreezeStones(freeze_stones);
        game.setFreezeConstants(constants);
        game.setOponentIsPlayer(oponent_is_player);
        game.setIsEasyDiff(is_easy_diff);

        enableSave();
        WhiteCount.setText(String.valueOf(2));
        BlackCount.setText(String.valueOf(2));
        visibleSidePanel(true); 
        pack();
        if(freeze_stones)
            startTimer();
    }//GEN-LAST:event_NewActionPerformed
    // SAVE
    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.setAcceptAllFileFilterUsed(false);  // remove all files filter      
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Othello games", "othel");
        file_chooser.setFileFilter(filter); // set new filter
        
        int returnVal = file_chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = file_chooser.getSelectedFile();
            String file_name = file.toString();
            if (!file_name.endsWith(".othel"))
                file_name += ".othel";
            try 
            {
                FileOutputStream fout = new FileOutputStream(file_name);
                try (ObjectOutputStream out_file_stream = new ObjectOutputStream(fout)) 
                {
                    out_file_stream.writeObject(this.game);
                }
            }
            catch (Exception exc)
            {
                exc.printStackTrace();
            }
        }
        else
            System.out.println("EXIT");
    }//GEN-LAST:event_SaveActionPerformed
    // LOAD
    private void LoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadActionPerformed
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.setAcceptAllFileFilterUsed(false);  // remove all files filter      
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Othello games", "othel");
        file_chooser.setFileFilter(filter); // set new filter
        
        int returnVal = file_chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = file_chooser.getSelectedFile();
            String file_name = file.toString();
            if (!file_name.endsWith(".othel"))
                file_name += ".othel";
            try 
            {
                FileInputStream f_in = new FileInputStream(file_name);
                try (ObjectInputStream in_file_stream = new ObjectInputStream(f_in)) 
                {
                    this.game_serialized = (Game) in_file_stream.readObject();
                    loadGame();
                    enableSave();
                }
            }
            catch (IOException | ClassNotFoundException exc)
            {
                exc.printStackTrace();
            }
        }
        else
            System.out.println("EXIT");
    }//GEN-LAST:event_LoadActionPerformed
    // UNDO
    private void UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoActionPerformed
        int[] num_stones = boardX.undoClicked(); // [blackstones,whitestones]
        BlackCount.setText(String.valueOf(num_stones[0]));
        this.game.getPlayer(false).setStoneNum(-(this.game.getPlayer(false).getStoneNum()-num_stones[0]),false);
        WhiteCount.setText(String.valueOf(num_stones[1]));
        this.game.getPlayer(true).setStoneNum(-(this.game.getPlayer(true).getStoneNum()-num_stones[1]),false);
        this.Undo.setEnabled(false);
    }//GEN-LAST:event_UndoActionPerformed
    
    private void initGame()
    {
        this.rules = new ReversiRules(this.board_size);
        this.board = new Board(this.rules);
        this.game = new Game(this.board);

        p1 = new Player(true); // WHITE
        p2 = new Player(false); // BLACK
        game.addPlayer(p1);
        game.addPlayer(p2);
        
        game.attach(this); // attach observers
        p1.attach(this);
        p2.attach(this);
    }
    
    private void visibleSidePanel(boolean visible)
    {
        Turn.setVisible(visible);
        TurnPlayer.setVisible(visible);
        WhiteStones.setVisible(visible);
        WhiteCount.setVisible(visible);
        BlackStones.setVisible(visible);
        BlackCount.setVisible(visible);
    }
    
    private void switchPlayer()
    {
        if(game.currentPlayer().isWhite())
            TurnPlayer.setText("White");
        else
            TurnPlayer.setText("Black");
    }
    
    private void playerStonePool(int player_pool)
    {
        if(game.currentPlayer().isWhite())
            WhiteCount.setText(String.valueOf(player_pool));
        else
            BlackCount.setText(String.valueOf(player_pool));
    }
    
        @Override
    public void update(Observable o, Object arg)    
    {
        if (arg == null)
            switchPlayer();
        else if(arg instanceof Integer)
            playerStonePool((int)arg);
        else
            return;
    }

    /**
     * @param white_player Color of player
     * @return Score of player
     */
    protected int getPlayerScore(boolean white_player)
    {
        return white_player?Integer.parseInt(WhiteCount.getText()):Integer.parseInt(BlackCount.getText());
    }
    
    /**
     * Print out the end game message
     */
    protected void endGame()
    {
        JOptionPane.showMessageDialog(this,whoWins()+" the game","End of the game",JOptionPane.PLAIN_MESSAGE);
    }
    
    private String whoWins()
    {
        int white_score = getPlayerScore(true);
        int black_score = getPlayerScore(false);
        if(white_score > black_score )
            return "White player wins";
        else if ( white_score < black_score)
            return "Black player wins";
        else
            return "It's draw, both players are winners";
    }
    
    private void loadGame()
    {
        int white_pool = this.game_serialized.getPlayer(true).getStoneNum();
        int black_pool = this.game_serialized.getPlayer(false).getStoneNum();
        this.board_size = this.game_serialized.getBoard().getSize();
        this.rules = new ReversiRules(this.board_size);
        this.board = this.game_serialized.getBoard();
        this.game = new Game(this.board);

        p1 = new Player(true); // WHITE
        if(!this.oponent_is_player)
            ai1 = new othelloAI(getAIDiff(), boardX);
        p2 = new Player(false); // BLACK
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.attach(this); // attach observers
        p1.attach(this);
        p2.attach(this);
        
        this.game.getPlayer(false).setStoneNum(black_pool-2,false);
        this.game.getPlayer(true).setStoneNum(white_pool-2,false);

        if(this.game_serialized.currentPlayer().isWhite())
            this.game.nextPlayer();
        
        freeze_stones = game_serialized.getFreezeStones();
        game.setFreezeStones(freeze_stones);
        
        long[] constants = game_serialized.getFreezeConstants();
        number_freeze_stones = constants[0];
        time_to_freeze = constants[1]*1000;
        time_to_unfreeze = constants[2]*1000;
        game.setFreezeConstants(constants);

        oponent_is_player = game_serialized.getOponentIsPlayer();
        is_easy_diff = game_serialized.getIsEasyDiff();
        game.setOponentIsPlayer(oponent_is_player);
        game.setIsEasyDiff(is_easy_diff);

        if (boardX != null)
        {
            BoardX.remove(boardX);
            BoardX.repaint();
        }
        boardX = new BoardX(this.board_size,this.game,this);
        boardX.loadField();
        BoardX.add(boardX).setVisible(true);
        this.boardX.loadField();
        if(!this.oponent_is_player){
            ai1 = new othelloAI(getAIDiff(), boardX);
        }
        WhiteCount.setText(String.valueOf(white_pool));
        BlackCount.setText(String.valueOf(black_pool));
        this.Undo.setEnabled(false);
        visibleSidePanel(true);
        pack();
        if(freeze_stones)
            startTimer();
    }
    
    /**
     * Enable Undo in menu
     */
    protected void enableUndo()
    {
        this.Undo.setEnabled(true);
    }
    
    private void enableSave()
    {
        this.Save.setEnabled(true);
    }
    
    protected static int count_timer = 0;

    /**
     * Action performed by swing timer
     */
    protected ActionListener action = new ActionListener()
    {   
        @Override
        public void actionPerformed(ActionEvent e) {
            count_timer++;
            if(count_timer == 2)
            {
                stopTimer();
            }
            else
                boardX.freezeStones(number_freeze_stones);
        }
    };
    
    /**
     * Stop the timer for freezing stones
     */
    protected void stopTimer()
    {
       timer.stop();
       boardX.unFreezeStones();
       count_timer = 0; 
    }
    
    /**
     * Start timer for freezing stones
     */
    protected void startTimer()
    {
      if(freeze_stones)
        {
            Random number = new Random();
            int unfreeze = number.nextInt((int)time_to_unfreeze);
            timer = new Timer(unfreeze,action);
            int initdelay = number.nextInt((int)time_to_freeze);
            timer.setInitialDelay(initdelay);
            System.out.println("Initial delay: " +initdelay + " Time to Unfreeze :" + unfreeze );
            timer.start();
        }  
    }
    
    private void checkFreezeConstants()
    {
        if(this.number_freeze_stones<0 || this.time_to_freeze<0 || this.time_to_unfreeze<0 ) 
            this.freeze_stones=false;
    }
    
    /**
     * @return Get difficulty of chosen AI
     */
    public boolean getAIDiff()
    {
       return this.is_easy_diff;
    }

    /**
     * @return Get the oponent player or computer
     */
    public boolean getOponentIsPlayer()
    {
       return this.oponent_is_player;         
    }
    
    public othelloAI getAI()
    {
        return this.ai1;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BlackCount;
    private javax.swing.JLabel BlackStones;
    private javax.swing.JPanel BoardX;
    private javax.swing.JMenuItem Load;
    private javax.swing.JMenuItem New;
    private javax.swing.JMenuItem Quit;
    private javax.swing.JMenuItem Save;
    private javax.swing.JPanel SideBar;
    private javax.swing.JLabel Turn;
    private javax.swing.JLabel TurnPlayer;
    private javax.swing.JMenuItem Undo;
    private javax.swing.JLabel WhiteCount;
    private javax.swing.JLabel WhiteStones;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
