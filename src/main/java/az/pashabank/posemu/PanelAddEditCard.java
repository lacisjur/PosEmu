package az.pashabank.posemu;

public class PanelAddEditCard extends javax.swing.JPanel {

    public PanelAddEditCard() {
        initComponents();
    }
    
    public PanelAddEditCard(Card card) {
        initComponents();
        this.tfPan.setText(card.getCard());
        this.tfKeySetId.setText(Integer.toString(card.getKeySetid()));
        this.tfTrack1.setText(card.getTrack1());
        this.tfTrack2.setText(card.getTrack2());
        this.tfDescription.setText(card.getDescription());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbPan = new javax.swing.JLabel();
        tfPan = new javax.swing.JTextField();
        lbTrack1 = new javax.swing.JLabel();
        tfTrack1 = new javax.swing.JTextField();
        lbTrack2 = new javax.swing.JLabel();
        tfTrack2 = new javax.swing.JTextField();
        lbDescription = new javax.swing.JLabel();
        tfDescription = new javax.swing.JTextField();
        tfKeySetId = new javax.swing.JTextField();
        lbKeySetId = new javax.swing.JLabel();

        lbPan.setText("PAN");

        lbTrack1.setText("Track1");

        lbTrack2.setText("Track2");

        lbDescription.setText("Description");

        lbKeySetId.setText("Key set ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPan)
                    .addComponent(lbTrack1)
                    .addComponent(lbTrack2)
                    .addComponent(lbDescription)
                    .addComponent(lbKeySetId))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfKeySetId, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTrack2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTrack1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPan)
                    .addComponent(tfPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfKeySetId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKeySetId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTrack1)
                    .addComponent(tfTrack1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTrack2)
                    .addComponent(tfTrack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescription)
                    .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public Card getCard () {
        return new Card(this.tfPan.getText(),
            Integer.parseInt(this.tfKeySetId.getText()),
            this.tfTrack1.getText(),
            this.tfTrack2.getText(),
            this.tfDescription.getText());
    }    
                 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbKeySetId;
    private javax.swing.JLabel lbPan;
    private javax.swing.JLabel lbTrack1;
    private javax.swing.JLabel lbTrack2;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfKeySetId;
    private javax.swing.JTextField tfPan;
    private javax.swing.JTextField tfTrack1;
    private javax.swing.JTextField tfTrack2;
    // End of variables declaration//GEN-END:variables
}
