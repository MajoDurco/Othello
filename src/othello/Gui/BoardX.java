package othello.Gui;

import java.awt.GridBagConstraints;
import javax.swing.JPanel;

public class BoardX extends javax.swing.JPanel {
    private final int fields;
    
    public BoardX(int fields) {
        this.fields = fields;
        initComponents();
        initFields();
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
       c.anchor = GridBagConstraints.NORTHWEST;
       for (int i = 0; i<fields*fields; i++)
        {
          c.gridx = x;
          c.gridy = y;
          c.fill = GridBagConstraints.BOTH;
          FieldX f = new FieldX(fields,y%2==0?x%2:(x+1)%2);
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
//        JPanel filler = new JPanel();
//        filler.setOpaque(false);
//        this.add(filler,c);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
