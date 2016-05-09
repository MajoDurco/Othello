package othello.Gui;

/**
 * Panel for difficulty choose
 * @author xdurco00, xdomon00
 */
public class AIDifficulty extends javax.swing.JPanel {

    private boolean is_easy_difficult = true;
    
    /**
     * Initialize the panel for choose
     */
    public AIDifficulty() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        easy = new javax.swing.JRadioButton();
        hard = new javax.swing.JRadioButton();
        difficulty = new javax.swing.JLabel();

        buttonGroup1.add(easy);
        easy.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        easy.setSelected(true);
        easy.setText("Easy");
        easy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyActionPerformed(evt);
            }
        });

        buttonGroup1.add(hard);
        hard.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        hard.setText("Hard");
        hard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardActionPerformed(evt);
            }
        });

        difficulty.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        difficulty.setText("Choose difficulty:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(difficulty)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(easy)
                .addGap(59, 59, 59)
                .addComponent(hard)
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(difficulty)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hard)
                    .addComponent(easy))
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void easyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyActionPerformed
        is_easy_difficult = true;
    }//GEN-LAST:event_easyActionPerformed

    private void hardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardActionPerformed
        is_easy_difficult = false;
    }//GEN-LAST:event_hardActionPerformed

    /**
     * @return The chosen difficulty
     */
    protected boolean getDifficulty()
    {
        return is_easy_difficult;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel difficulty;
    private javax.swing.JRadioButton easy;
    private javax.swing.JRadioButton hard;
    // End of variables declaration//GEN-END:variables
}
