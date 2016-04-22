package othello.Gui;

public class FieldX extends javax.swing.JPanel {
    private final int[] size;
    private final int[] color;
    
    public FieldX(int dimension,int even) {
        this.size = calcSizeOfField(dimension);
        this.color = setFieldColor(even);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(color[0],color[1],color[2]));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new java.awt.Dimension(size[0],size[1]));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());
        add(jLabel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/white_s.png")));
    }//GEN-LAST:event_formMouseReleased
    private int[] calcSizeOfField(int dimension)
    {
        int[] a = new int[2];
        switch (dimension)
        {
            case 6:
                a[0]=98;
                a[1]=96;
                return a;
            case 8:
                a[0]=74;
                a[1]=72;
                return a;
            case 12:
                a[0]=49;
                a[1]=48;
                return a;
        }
        return a;
    }
    
    private int[] setFieldColor(int even)
    {
        int[] dark = {46,195,16};
        int[] light = {0,255,34};
        return even==0?light:dark;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
