package othello.Gui;

import java.awt.Color;

public class FieldX extends javax.swing.JPanel {
    private final int[] size;
    private final int even_position;
    protected int row;
    protected int col;
    private BoardX board;
    protected boolean isWhite;

    
    public FieldX(int row,int col,int dimension,int even,BoardX board) {
        this.size = calcSizeOfField(dimension);
        this.even_position = even;
        setFieldColor();
        this.row = row;
        this.col = col;
        this.board = board;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new java.awt.Dimension(size[0],size[1]));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        board.clickPerformed(row, col);
    }//GEN-LAST:event_formMouseReleased
    private int[] calcSizeOfField(int dimension)
    {
        int[] a = new int[2];
        switch (dimension)
        {
            case 6:
                a[0]=94;
                a[1]=92;
                return a;
            case 8:
                a[0]=70;
                a[1]=68;
                return a;
            case 10:
                a[0]=55;
                a[1]=53;
                return a;
            case 12:
                a[0]=45;
                a[1]=44;
                return a;
        }
        return a;
    }
    
    private void setFieldColor()
    {
        if (even_position==1)
            setBackground(new Color(46,195,16));
        else
            setBackground(new Color(0,255,34));   
    }
    
    protected void setStone(boolean white)
    {
        if (white)
        {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/white_s.png")));
            this.isWhite = true;
        }
        else
        {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/black_s.png")));
            this.isWhite = false;
        }
    }
    
    protected boolean isEmpty()
    {
        return jLabel1.getIcon() == null;
    }
    
    protected void removeStone()
    {
        jLabel1.setIcon(null);
        jLabel1.revalidate();
    }
    
    protected boolean isWhite()
    {
        return this.isWhite;
    }
    
    protected void freezeField()
    {
        setBackground(new Color(95,95,95));
    }
    
    protected void unFreezeField()
    {
        setFieldColor();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
