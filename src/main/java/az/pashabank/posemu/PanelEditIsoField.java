package az.pashabank.posemu;

public class PanelEditIsoField extends javax.swing.JPanel {
    
    public PanelEditIsoField(IsoField field) {
        initComponents();
        this.tfFieldId.setText(Integer.toString(field.getId()));
        this.tfFieldName.setText(field.getName());
        this.tfFieldMinLength.setText(Integer.toString(field.getMinLength()));
        this.tfFieldMaxLength.setText(Integer.toString(field.getMaxLength()));
        this.tfLengthQualifier.setText(Integer.toString(field.getLengthQualifier()));
        this.tfFieldPaddingChar.setText(field.getPaddingChar());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbFieldMinLength = new javax.swing.JLabel();
        tfLengthQualifier = new javax.swing.JTextField();
        lbLengthQualifier = new javax.swing.JLabel();
        tfFieldName = new javax.swing.JTextField();
        lbFieldName = new javax.swing.JLabel();
        tfFieldId = new javax.swing.JTextField();
        lbFieldId = new javax.swing.JLabel();
        tfFieldMinLength = new javax.swing.JTextField();
        lbFieldMaxLength = new javax.swing.JLabel();
        tfFieldMaxLength = new javax.swing.JTextField();
        lbFieldPaddingChar = new javax.swing.JLabel();
        tfFieldPaddingChar = new javax.swing.JTextField();

        lbFieldMinLength.setText("Min length");

        lbLengthQualifier.setText("Length qualifier");

        lbFieldName.setText("Name");

        tfFieldId.setEditable(false);
        tfFieldId.setEnabled(false);

        lbFieldId.setText("ID");

        lbFieldMaxLength.setText("Max length");

        lbFieldPaddingChar.setText("Padding char");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbLengthQualifier)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfLengthQualifier, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFieldName)
                                    .addComponent(lbFieldMinLength)
                                    .addComponent(lbFieldId)
                                    .addComponent(lbFieldMaxLength))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfFieldMaxLength, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfFieldMinLength, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfFieldName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfFieldId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbFieldPaddingChar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfFieldPaddingChar, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFieldId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFieldName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFieldMinLength)
                    .addComponent(tfFieldMinLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfFieldMaxLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFieldMaxLength))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLengthQualifier)
                    .addComponent(tfLengthQualifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFieldPaddingChar)
                    .addComponent(tfFieldPaddingChar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    IsoField getIsoField () {
        return new IsoField (Integer.parseInt(this.tfFieldId.getText()), 
            this.tfFieldName.getText(), 
            Integer.parseInt(this.tfFieldMinLength.getText()),
            Integer.parseInt(this.tfFieldMaxLength.getText()),
            Integer.parseInt(this.tfLengthQualifier.getText()),
            this.tfFieldPaddingChar.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbFieldId;
    private javax.swing.JLabel lbFieldMaxLength;
    private javax.swing.JLabel lbFieldMinLength;
    private javax.swing.JLabel lbFieldName;
    private javax.swing.JLabel lbFieldPaddingChar;
    private javax.swing.JLabel lbLengthQualifier;
    private javax.swing.JTextField tfFieldId;
    private javax.swing.JTextField tfFieldMaxLength;
    private javax.swing.JTextField tfFieldMinLength;
    javax.swing.JTextField tfFieldName;
    javax.swing.JTextField tfFieldPaddingChar;
    javax.swing.JTextField tfLengthQualifier;
    // End of variables declaration//GEN-END:variables
}
