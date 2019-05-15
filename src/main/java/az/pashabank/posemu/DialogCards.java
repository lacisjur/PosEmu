package az.pashabank.posemu;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DialogCards extends javax.swing.JDialog {

    private Database db;
    private final DefaultTableModel tCardsModel;
    
    public DialogCards(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.db = Database.getInstance();
        this.tCardsModel = (DefaultTableModel)this.tCards.getModel();
        loadInitData();
    }
    
    private void loadInitData () {
        try {
            List<Card> cards = db.getCards();
            for (Card card : cards) {
                Object[] row = cardToRow(card);
                this.tCardsModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Object[] cardToRow (Card card) {
        Object[] row = new Object[5];
        row[0] = card.getCard();
        row[1] = card.getKeySetid();
        row[2] = card.getTrack1();
        row[3] = card.getTrack2();
        row[4] = card.getDescription();
        return row;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tCards = new javax.swing.JTable();
        btAdd = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tCards.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PAN", "Key set ID", "Track1", "Track2", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tCards);

        btAdd.setText("Add");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btEdit.setText("Edit");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        btDelete.setText("Delete");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdd)
                    .addComponent(btEdit)
                    .addComponent(btDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        PanelAddEditCard panel = new PanelAddEditCard();
        int option = JOptionPane.showConfirmDialog(this, panel, "Add card", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Card card = panel.getCard();
            try {
                this.db.insertCard(card);
                this.tCardsModel.addRow(cardToRow(card));
            } catch  (Exception e) {
                JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_btAddActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        int selectedRow = this.tCards.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }
        String pan = (String)this.tCardsModel.getValueAt(selectedRow, 0);
        try {
            db.deleteCard(pan);
            this.tCardsModel.removeRow(selectedRow);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
         
        int selectedRow = this.tCards.getSelectedRow();         
           if (selectedRow == -1) {
            return;
            }        
        
        
        Card editcard = new Card(
        tCards.getModel().getValueAt(tCards.getSelectedRow(), 0).toString(),
        Integer.valueOf(tCards.getModel().getValueAt(tCards.getSelectedRow(), 1).toString()),
        tCards.getModel().getValueAt(tCards.getSelectedRow(), 2).toString(),
        tCards.getModel().getValueAt(tCards.getSelectedRow(), 3).toString(),       
        tCards.getModel().getValueAt(tCards.getSelectedRow(), 4).toString()
        );
                        
        PanelAddEditCard panel = new PanelAddEditCard(editcard);               
        int option = JOptionPane.showConfirmDialog(this, panel, "Update card", JOptionPane.OK_CANCEL_OPTION);        
        if (option == JOptionPane.OK_OPTION) {
            Card card = panel.getCard();
            try {
                this.tCardsModel.removeRow(selectedRow);
                this.db.updateCard(card);
                this.tCardsModel.addRow(cardToRow(card));
            } catch  (Exception e) {
                JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_btEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tCards;
    // End of variables declaration//GEN-END:variables
}
