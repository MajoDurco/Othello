package othello.Gui;

import javax.swing.JOptionPane;
import othello.Board.Board;
import othello.Game.Game;
import othello.Game.Player;
import othello.Game.ReversiRules;

public class GameX extends javax.swing.JFrame {
    private ReversiRules rules; 
    private Board board; 
    private Game game;
    private BoardX boardX;
    private Player p1;
    private Player p2;
    private int board_size;
    private boolean oponent_is_player;
    
    public GameX() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoardX = new javax.swing.JPanel();
        SideBar = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Othello");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Pictures/othello_icon.png")).getImage());
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        BoardX.setPreferredSize(new java.awt.Dimension(600, 600));
        BoardX.setLayout(new java.awt.BorderLayout());

        SideBar.setPreferredSize(new java.awt.Dimension(200, 600));

        javax.swing.GroupLayout SideBarLayout = new javax.swing.GroupLayout(SideBar);
        SideBar.setLayout(SideBarLayout);
        SideBarLayout.setHorizontalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        SideBarLayout.setVerticalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setName("Othello"); // NOI18N

        jMenu1.setText("Menu");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("New game");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem1.setActionCommand("Quit");
        jMenuItem1.setLabel("Quit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

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
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    //    New game
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        NewGamePanel p = new NewGamePanel();
        String[] options = {"Create","Cancel"};
        int response = JOptionPane.showOptionDialog(this,p,"Create New Game",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        if(response != JOptionPane.OK_OPTION)
        {
            System.out.println("EXITED OR CANCELED");
            return;
        }
        this.board_size = p.getBoardSize();
        this.oponent_is_player = p.getOponent();
        System.out.println("Selected size : "+ this.board_size);
        System.out.println("Selected oponent : "+ this.oponent_is_player);

        if (boardX == null)
        {
            initGame();
            boardX = new BoardX(this.board_size,game);
            BoardX.add(boardX).setVisible(true);
            pack();
        }
        else // recreate new game on existing one
        {
            BoardX.remove(boardX);
            BoardX.repaint();
            initGame();  // reinitialize the game
            boardX = new BoardX(this.board_size,game);
            BoardX.add(boardX).setVisible(true);
            pack();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    private void initGame()
    {
        this.rules = new ReversiRules(this.board_size);
        this.board = new Board(this.rules);
        this.game = new Game(this.board);
        // TODO AI PLAYER 2 (computer)
        p1 = new Player(true); // WHITE
        p2 = new Player(false); // BLACK
        game.addPlayer(p1);
        game.addPlayer(p2);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoardX;
    private javax.swing.JPanel SideBar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}