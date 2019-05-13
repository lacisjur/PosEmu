package az.pashabank.posemu;

public class PanelAddEditInterface extends javax.swing.JPanel {

    public PanelAddEditInterface(int id) {
        initComponents();
        this.tfInterfaceId.setText(Integer.toString(id));
    }
    
    public PanelAddEditInterface(Interface iface) {
        initComponents();
        this.tfInterfaceId.setText(Integer.toString(iface.getId()));
        this.tfInterfaceName.setText(iface.getName());
        this.taInterfaceDescription.setText(iface.getDescription());
        this.tfInterfaceFieldCount.setText(Integer.toString(iface.getFieldCount()));
    }
    
    public PanelAddEditInterface(int id, Interface iface) {
        initComponents();
        this.tfInterfaceId.setText(Integer.toString(id));
        this.tfInterfaceName.setText(iface.getName());
        this.taInterfaceDescription.setText(iface.getDescription());
        this.tfInterfaceFieldCount.setText(Integer.toString(iface.getFieldCount()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbInterfaceDescription = new javax.swing.JLabel();
        tfInterfaceFieldCount = new javax.swing.JTextField();
        lbInterfaceFieldCount = new javax.swing.JLabel();
        tfInterfaceName = new javax.swing.JTextField();
        lbInterfaceName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taInterfaceDescription = new javax.swing.JTextArea();
        tfInterfaceId = new javax.swing.JTextField();
        lbInterfaceId = new javax.swing.JLabel();

        lbInterfaceDescription.setText("Description");

        lbInterfaceFieldCount.setText("Field count");

        lbInterfaceName.setText("Name");

        taInterfaceDescription.setColumns(20);
        taInterfaceDescription.setRows(5);
        jScrollPane1.setViewportView(taInterfaceDescription);

        tfInterfaceId.setEditable(false);
        tfInterfaceId.setEnabled(false);

        lbInterfaceId.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbInterfaceName)
                    .addComponent(lbInterfaceFieldCount)
                    .addComponent(lbInterfaceDescription)
                    .addComponent(lbInterfaceId))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(tfInterfaceName)
                    .addComponent(tfInterfaceFieldCount, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(tfInterfaceId))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfInterfaceId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInterfaceId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfInterfaceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInterfaceName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInterfaceDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfInterfaceFieldCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInterfaceFieldCount))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    Interface getInterface () {
        return new Interface (Integer.parseInt(this.tfInterfaceId.getText()), 
            this.tfInterfaceName.getText(), 
            this.taInterfaceDescription.getText(),
            Integer.parseInt(this.tfInterfaceFieldCount.getText()));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbInterfaceDescription;
    private javax.swing.JLabel lbInterfaceFieldCount;
    private javax.swing.JLabel lbInterfaceId;
    private javax.swing.JLabel lbInterfaceName;
    javax.swing.JTextArea taInterfaceDescription;
    javax.swing.JTextField tfInterfaceFieldCount;
    private javax.swing.JTextField tfInterfaceId;
    javax.swing.JTextField tfInterfaceName;
    // End of variables declaration//GEN-END:variables
}
