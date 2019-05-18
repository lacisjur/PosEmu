package az.pashabank.posemu;

import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DialogIsoInterfaces extends javax.swing.JDialog {

    private final Database db;

    private final DefaultTableModel tIfacesModel;
    private final DefaultTableModel tMessageFieldsModel;
    private final DefaultComboBoxModel cbIfacesModel;

    // dirty hack to avoid firing of  actionPerformed when rewrite JComboBox items
    private boolean cbIfacesEventActiive = true;

    public DialogIsoInterfaces(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        db = Database.getInstance();
        SwingUtils.alignColumnsLeft(this.tIfaces, 0, 3);
        SwingUtils.alignColumnsLeft(this.tMessageFields, 0, 2, 3, 4, 5);
        this.tIfacesModel = (DefaultTableModel) this.tIfaces.getModel();
        this.tMessageFieldsModel = (DefaultTableModel) this.tMessageFields.getModel();
        this.cbIfacesModel = (DefaultComboBoxModel) this.cbIfaces.getModel();
        loadInitData();
    }

    private void loadInitData() {
        try {
            List<Interface> ifaces = db.getInterfaces();
            for (Interface iface : ifaces) {
                Object[] row = ifaceToRow(iface);
                this.tIfacesModel.addRow(row);
                this.cbIfacesModel.addElement(new ComboBoxItem(Integer.toString(iface.getId()), iface.getName()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Interface getSelectedInterface(int selectedRow) {
        return new Interface((int) this.tIfaces.getValueAt(selectedRow, 0),
                (String) this.tIfaces.getValueAt(selectedRow, 1),
                (String) this.tIfaces.getValueAt(selectedRow, 2),
                (int) this.tIfaces.getValueAt(selectedRow, 3));
    }
    
    private IsoField getSelectedIsoField(int selectedRow) {
        return new IsoField((int)this.tMessageFields.getValueAt(selectedRow, 0),
                (String)this.tMessageFields.getValueAt(selectedRow, 1),
                (int)this.tMessageFields.getValueAt(selectedRow, 2),
                (int)this.tMessageFields.getValueAt(selectedRow, 3),
                (int)this.tMessageFields.getValueAt(selectedRow, 4),
                (String)this.tMessageFields.getValueAt(selectedRow, 5));
    }

    private Object[] ifaceToRow(Interface iface) {
        Object[] row = new Object[4];
        row[0] = iface.getId();
        row[1] = iface.getName();
        row[2] = iface.getDescription();
        row[3] = iface.getFieldCount();
        return row;
    }

    private Object[] isoFieldToRow(IsoField field) {
        Object[] row = new Object[6];
        row[0] = field.getId();
        row[1] = field.getName();
        row[2] = field.getMinLength();
        row[3] = field.getMaxLength();
        row[4] = field.getLengthQualifier();
        row[5] = field.getPaddingChar();
        return row;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tp = new javax.swing.JTabbedPane();
        pInterfaces = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tIfaces = new javax.swing.JTable();
        btAddIface = new javax.swing.JButton();
        btRemoveIface = new javax.swing.JButton();
        btEditIface = new javax.swing.JButton();
        btCloneIface = new javax.swing.JButton();
        btRestore = new javax.swing.JButton();
        pFields = new javax.swing.JPanel();
        cbIfaces = new javax.swing.JComboBox<>();
        lbFieldInterfaceName = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btEditField = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tMessageFields = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tIfaces.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Description", "Field count"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tIfaces);

        btAddIface.setText("Add");
        btAddIface.setToolTipText("Add new interface");
        btAddIface.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddIfaceActionPerformed(evt);
            }
        });

        btRemoveIface.setText("Remove");
        btRemoveIface.setToolTipText("Remove existing interface");
        btRemoveIface.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveIfaceActionPerformed(evt);
            }
        });

        btEditIface.setText("Edit");
        btEditIface.setToolTipText("Edit existing interface");
        btEditIface.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditIfaceActionPerformed(evt);
            }
        });

        btCloneIface.setText("Clone");
        btCloneIface.setToolTipText("Clone existing interface configuration including message fields");
        btCloneIface.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCloneIfaceActionPerformed(evt);
            }
        });

        btRestore.setText("Restore");
        btRestore.setToolTipText("Restore default interface configurations");
        btRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRestoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pInterfacesLayout = new javax.swing.GroupLayout(pInterfaces);
        pInterfaces.setLayout(pInterfacesLayout);
        pInterfacesLayout.setHorizontalGroup(
            pInterfacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInterfacesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pInterfacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pInterfacesLayout.createSequentialGroup()
                        .addComponent(btAddIface, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCloneIface, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEditIface, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRemoveIface)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
                        .addComponent(btRestore)))
                .addContainerGap())
        );
        pInterfacesLayout.setVerticalGroup(
            pInterfacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInterfacesLayout.createSequentialGroup()
                .addGroup(pInterfacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pInterfacesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pInterfacesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btAddIface)
                            .addComponent(btEditIface)
                            .addComponent(btCloneIface)
                            .addComponent(btRemoveIface)))
                    .addComponent(btRestore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        tp.addTab("Interfaces", pInterfaces);

        cbIfaces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIfacesActionPerformed(evt);
            }
        });

        lbFieldInterfaceName.setText("Interface");

        btEditField.setText("Edit");
        btEditField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditFieldActionPerformed(evt);
            }
        });

        tMessageFields.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Min length", "Max length", "Length qualifier", "Padding character"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tMessageFields);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btEditField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btEditField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Message fields", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 927, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Message types", jPanel2);

        javax.swing.GroupLayout pFieldsLayout = new javax.swing.GroupLayout(pFields);
        pFields.setLayout(pFieldsLayout);
        pFieldsLayout.setHorizontalGroup(
            pFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(pFieldsLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbFieldInterfaceName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbIfaces, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pFieldsLayout.setVerticalGroup(
            pFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pFieldsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbIfaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFieldInterfaceName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tp.addTab("Message", pFields);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tp.getAccessibleContext().setAccessibleName("Fields");
        tp.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAddIfaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddIfaceActionPerformed
        int nextSequence = 0;
        try {
            nextSequence = db.getNextSequence(Constants.OBJECT_INTERFACE);
            PanelAddEditInterface panel = new PanelAddEditInterface(nextSequence);
            int option = JOptionPane.showConfirmDialog(this, panel, "Add interface", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                Interface iface = panel.getInterface();
                Object[] row = ifaceToRow(iface);
                db.insertInterface(iface);
                this.tIfacesModel.addRow(row);
                this.cbIfacesModel.addElement(new ComboBoxItem(Integer.toString(iface.getId()), iface.getName()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btAddIfaceActionPerformed

    private void btEditIfaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditIfaceActionPerformed
        int selectedRow = this.tIfaces.getSelectedRow();
        Interface iface = getSelectedInterface(selectedRow);
        PanelAddEditInterface panel = new PanelAddEditInterface(iface);
        int option = JOptionPane.showConfirmDialog(this, panel, "Edit interface", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            iface = panel.getInterface();
            try {
                System.out.println(iface.toString());
                db.updateInterface(iface);
                this.tIfaces.setValueAt(iface.getName(), selectedRow, 1);
                this.tIfaces.setValueAt(iface.getDescription(), selectedRow, 2);
                this.tIfaces.setValueAt(iface.getFieldCount(), selectedRow, 3);
                for (int i = 0; i < this.cbIfacesModel.getSize(); i++) {
                    ComboBoxItem item = (ComboBoxItem) this.cbIfacesModel.getElementAt(i);
                    if (item.getKey().equals(iface.getId())) {
                        this.cbIfacesModel.removeElementAt(i);
                        this.cbIfacesModel.addElement(new ComboBoxItem(Integer.toString(iface.getId()), iface.getName()));
                        break;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btEditIfaceActionPerformed

    private void btRemoveIfaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveIfaceActionPerformed
        int selectedRow = this.tIfaces.getSelectedRow();
        int id = (int) this.tIfaces.getValueAt(selectedRow, 0);
        if (selectedRow == -1) {
            return;
        }
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete an interface?\nChild body fields will also be deleted!",
                "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            try {
                db.deleteinterface(id);
                this.tIfacesModel.removeRow(selectedRow);
                for (int i = 0; i < this.cbIfacesModel.getSize(); i++) {
                    ComboBoxItem item = (ComboBoxItem) this.cbIfacesModel.getElementAt(i);
                    if (item.getKey().equals(id)) {
                        this.cbIfacesModel.removeElementAt(i);
                        break;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btRemoveIfaceActionPerformed

    private void btCloneIfaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCloneIfaceActionPerformed
        int selectedRow = this.tIfaces.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }
        try {
            Interface oIface = getSelectedInterface(selectedRow);
            int id = db.getNextSequence(Constants.OBJECT_INTERFACE);
            PanelAddEditInterface panel = new PanelAddEditInterface(id, oIface);
            int option = JOptionPane.showConfirmDialog(this, panel, "Clone interface", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                Interface nIface = panel.getInterface();
                HashMap<Integer, IsoField> fields = db.getFields(oIface.getId());
                db.insertInterface(nIface);
                db.insertFields(fields, id);
                Object[] row = ifaceToRow(nIface);
                this.tIfacesModel.addRow(row);
                this.cbIfacesModel.addElement(new ComboBoxItem(Integer.toString(nIface.getId()), nIface.getName()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btCloneIfaceActionPerformed

    private void btRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRestoreActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to restore default "
                + "interface configurations?\nIt will delete all existing interface "
                + "configurations including fields!", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                db.deleteInterfaces();
                db.insertDefaultInterfces();
                this.tIfacesModel.setRowCount(0);
                this.tMessageFieldsModel.setRowCount(0);
                // dirty hack to avoid firing of  actionPerformed when rewrite JComboBox items
                this.cbIfacesEventActiive = false;
                this.cbIfacesModel.removeAllElements();
                loadInitData();
                this.cbIfacesEventActiive = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btRestoreActionPerformed

    private void cbIfacesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIfacesActionPerformed
        // dirty hack to avoid firing of  actionPerformed when rewrite JComboBox items
        if (!this.cbIfacesEventActiive) {
            return;
        }
        ComboBoxItem iface = (ComboBoxItem) this.cbIfaces.getSelectedItem();
        try {
            HashMap<Integer, IsoField> fields = db.getFields(Integer.valueOf(iface.getKey()));
            this.tMessageFieldsModel.setRowCount(0);
            for (Integer id : fields.keySet()) {
                IsoField field = fields.get(id);
                Object[] row = isoFieldToRow(field);
                this.tMessageFieldsModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cbIfacesActionPerformed

    private void btEditFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditFieldActionPerformed
        int selectedRow = this.tMessageFields.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }
        IsoField field = getSelectedIsoField(selectedRow);
        int interfaceId = Integer.valueOf(((ComboBoxItem)this.cbIfacesModel.getSelectedItem()).getKey());
        PanelEditIsoField panel = new PanelEditIsoField(field);
        int option = JOptionPane.showConfirmDialog(this, panel, "Edit ISO field", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            field = panel.getIsoField();
            try {
                db.updateField(interfaceId, field);
                this.tMessageFields.setValueAt(field.getName(), selectedRow, 1);
                this.tMessageFields.setValueAt(field.getMinLength(), selectedRow, 2);
                this.tMessageFields.setValueAt(field.getMaxLength(), selectedRow, 3);
                this.tMessageFields.setValueAt(field.getLengthQualifier(), selectedRow, 4);
                this.tMessageFields.setValueAt(field.getPaddingChar(), selectedRow, 5);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btEditFieldActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddIface;
    private javax.swing.JButton btCloneIface;
    private javax.swing.JButton btEditField;
    private javax.swing.JButton btEditIface;
    private javax.swing.JButton btRemoveIface;
    private javax.swing.JButton btRestore;
    private javax.swing.JComboBox<ComboBoxItem> cbIfaces;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbFieldInterfaceName;
    private javax.swing.JPanel pFields;
    private javax.swing.JPanel pInterfaces;
    private javax.swing.JTable tIfaces;
    private javax.swing.JTable tMessageFields;
    private javax.swing.JTabbedPane tp;
    // End of variables declaration//GEN-END:variables
}
