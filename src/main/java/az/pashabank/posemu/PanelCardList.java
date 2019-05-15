package az.pashabank.posemu;

import java.util.List;
import javax.swing.DefaultListModel;

public class PanelCardList extends javax.swing.JPanel {

    public PanelCardList(List<Card> cards) {
        initComponents();
        DefaultListModel model = new DefaultListModel();
        for (Card card : cards) {
            model.addElement(new ComboBoxItem(card.getCard(), card.getDescription()));
        }
        this.lCards.setModel(model);
    }

    public String getPan () {
        return this.lCards.getSelectedValue().getKey();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        lCards = new javax.swing.JList<>();

        jScrollPane2.setViewportView(lCards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<ComboBoxItem> lCards;
    // End of variables declaration//GEN-END:variables
}
