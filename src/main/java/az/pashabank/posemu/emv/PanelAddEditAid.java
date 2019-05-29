package az.pashabank.posemu.emv;

import az.pashabank.posemu.Constants;

public class PanelAddEditAid extends javax.swing.JPanel {

    public PanelAddEditAid() {
        initComponents();
    }
    
    public PanelAddEditAid(EmvAid aid) {
        initComponents();
        this.tfRid.setEnabled(false);
        this.tfPix.setEnabled(false);
        this.cbPaymentSystem.setEnabled(false);
        this.tfRid.setText(aid.getRid());
        this.tfPix.setText(aid.getPix());
        this.tfName.setText(aid.getName());
        for (int i = 0; i < this.cbPaymentSystem.getItemCount(); i++) {
            if (this.cbPaymentSystem.getItemAt(i).equals(aid.getPaymentSystem().toString())) {
                this.cbPaymentSystem.setSelectedIndex(i);
                break;
            }
        }
    }
    
    public EmvAid getAid () {
        return new EmvAid(this.tfRid.getText(),
            this.tfPix.getText(),
            this.tfName.getText(),
            Constants.PaymentSystem.toPaymentSystem((String)this.cbPaymentSystem.getSelectedItem()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbRid = new javax.swing.JLabel();
        lbPix = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbPaymentSystem = new javax.swing.JLabel();
        tfRid = new javax.swing.JTextField();
        tfPix = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        cbPaymentSystem = new javax.swing.JComboBox<>();

        lbRid.setText("RID");

        lbPix.setText("PIX");

        lbName.setText("Name");

        lbPaymentSystem.setText("Payment system");

        cbPaymentSystem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Visa International", "MasterCard Worldwide", "American Express" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbRid)
                        .addGap(98, 98, 98)
                        .addComponent(tfRid))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPaymentSystem)
                        .addGap(18, 18, 18)
                        .addComponent(cbPaymentSystem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPix)
                        .addGap(101, 101, 101)
                        .addComponent(tfPix, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbName)
                        .addGap(84, 84, 84)
                        .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRid)
                    .addComponent(tfRid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPix)
                    .addComponent(tfPix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPaymentSystem)
                    .addComponent(cbPaymentSystem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbPaymentSystem;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPaymentSystem;
    private javax.swing.JLabel lbPix;
    private javax.swing.JLabel lbRid;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPix;
    private javax.swing.JTextField tfRid;
    // End of variables declaration//GEN-END:variables
}
