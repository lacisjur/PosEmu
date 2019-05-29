package az.pashabank.posemu.emv;

import az.pashabank.posemu.Constants;
import az.pashabank.posemu.Constants.PaymentSystem;
import az.pashabank.posemu.Database;
import az.pashabank.posemu.LabelValuePair;
import java.util.List;
import javax.smartcardio.CardTerminal;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DialogEmvParameters
        extends javax.swing.JDialog {

    private Database db;
    
    private DefaultTableModel supportedAidTableModel;
    private DefaultComboBoxModel<LabelValuePair> cbAidModel;
    private DefaultListModel<LabelValuePair> lTagListModel;
    private DefaultListModel<LabelValuePair> lRequestMessageTagListModel;

    public DialogEmvParameters(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.db = Database.getInstance();
        this.supportedAidTableModel = (DefaultTableModel)this.tSupportedAids.getModel();
        this.cbAidModel = (DefaultComboBoxModel<LabelValuePair>)this.cbEmvDataAid.getModel();
        this.lTagListModel = new DefaultListModel();
        this.lRequestMessageTagListModel = new DefaultListModel();
        this.lTagList.setModel(this.lTagListModel);
        this.lRequestMessageTagList.setModel(this.lRequestMessageTagListModel);
        try {
            this.cbContactSupported.setSelected(db.getBooleanParameter(Constants.PARAM_EMV_HW_CONTACT_SUPPORTED));
            this.cbContactReader.setEnabled(this.cbContactSupported.isSelected());
            this.cbContactProtocol.setEnabled(this.cbContactSupported.isSelected());
            List<CardTerminal> readers = EmvSessionContext.getCardReaders();
            String readerName = db.getParameter(Constants.PARAM_EMV_HW_CONTACT_READER);
            for (int i = 0; i < readers.size(); i++) {
                String reader = readers.get(i).getName();
                this.cbContactReader.addItem(reader);
                if (reader.equals(readerName)) {
                    this.cbContactReader.setSelectedIndex(i);
                }
            }
            String readerProtocol = db.getParameter(Constants.PARAM_EMV_HW_CONTACT_PROTOCOL);
            for (int i = 0; i < this.cbContactReader.getItemCount(); i++) {
                if (this.cbContactReader.getItemAt(i).equals(readerProtocol)) {
                    this.cbContactProtocol.setSelectedIndex(i);
                    break;
                }
            }
            this.cbContactlessSupported.setSelected(db.getBooleanParameter(Constants.PARAM_EMV_HW_CONTACTLESS_SUPPORTED));
            this.cbContactlessReader.setEnabled(this.cbContactlessSupported.isSelected());
            this.cbContactlessProtocol.setEnabled(this.cbContactlessSupported.isSelected());
            for (int i = 0; i < readers.size(); i++) {
                String reader = readers.get(i).getName();
                this.cbContactlessReader.addItem(reader);
                if (reader.equals(readerName)) {
                    this.cbContactlessReader.setSelectedIndex(i);
                }
            }
            readerProtocol = db.getParameter(Constants.PARAM_EMV_HW_CONTACT_PROTOCOL);
            for (int i = 0; i < this.cbContactlessReader.getItemCount(); i++) {
                if (this.cbContactlessReader.getItemAt(i).equals(readerProtocol)) {
                    this.cbContactlessProtocol.setSelectedIndex(i);
                    break;
                }
            }
            List<EmvAid> aids = db.getAids();
            for (EmvAid aid : aids) {
                this.supportedAidTableModel.addRow(aidToRow(aid));
                this.cbAidModel.addElement(new LabelValuePair(aid.getAid() + " - " + aid.getName(), aid));
            }

            this.cbPerformPseSelection.setSelected(db.getBooleanParameter(Constants.PARAM_EMV_PA_PERFORM_PSE_SELECTION));
            this.cbApplicationSelectionSupported.setSelected(db.getBooleanParameter(Constants.PARAM_EMV_PA_APPLICATION_SELECTION_SUPPORTED));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tpEmvParameters = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        pContact = new javax.swing.JPanel();
        cbContactReader = new javax.swing.JComboBox<>();
        cbContactSupported = new javax.swing.JCheckBox();
        lbContactSupported = new javax.swing.JLabel();
        lbContactReader = new javax.swing.JLabel();
        cbContactProtocol = new javax.swing.JComboBox<>();
        lbContactProtocol = new javax.swing.JLabel();
        pContactless = new javax.swing.JPanel();
        cbContactlessReader = new javax.swing.JComboBox<>();
        cbContactlessSupported = new javax.swing.JCheckBox();
        lbContactlessSupported1 = new javax.swing.JLabel();
        lbContactReader1 = new javax.swing.JLabel();
        cbContactlessProtocol = new javax.swing.JComboBox<>();
        lbContactProtocol1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbPerformPseSelection = new javax.swing.JCheckBox();
        cbApplicationSelectionSupported = new javax.swing.JCheckBox();
        lbPerformPseSelection = new javax.swing.JLabel();
        lbApplicationSelectionSupported = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tSupportedAids = new javax.swing.JTable();
        lbSupportedAids = new javax.swing.JLabel();
        btAddAid = new javax.swing.JButton();
        btDeleteAid = new javax.swing.JButton();
        btEditAid = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cbEmvDataAid = new javax.swing.JComboBox<>();
        lbAid = new javax.swing.JLabel();
        lbTagList = new javax.swing.JLabel();
        lbRequestMessageTagList = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lTagList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lRequestMessageTagList = new javax.swing.JList<>();
        bAddTagToEmvData = new javax.swing.JButton();
        bRemoveTagFromEmvData = new javax.swing.JButton();
        btOk = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pContact.setBorder(javax.swing.BorderFactory.createTitledBorder("Contact "));

        cbContactReader.setEnabled(false);

        cbContactSupported.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbContactSupportedActionPerformed(evt);
            }
        });

        lbContactSupported.setText("Contact supported");
        lbContactSupported.setToolTipText("");

        lbContactReader.setText("Contact reader");

        cbContactProtocol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T=0", "T=1", "T=0/1" }));
        cbContactProtocol.setEnabled(false);

        lbContactProtocol.setText("Contact protocol");

        javax.swing.GroupLayout pContactLayout = new javax.swing.GroupLayout(pContact);
        pContact.setLayout(pContactLayout);
        pContactLayout.setHorizontalGroup(
            pContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pContactLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbContactSupported)
                    .addComponent(lbContactReader)
                    .addComponent(lbContactProtocol, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbContactReader, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbContactSupported)
                    .addComponent(cbContactProtocol, 0, 269, Short.MAX_VALUE)))
        );
        pContactLayout.setVerticalGroup(
            pContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pContactLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbContactSupported)
                    .addComponent(cbContactSupported))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbContactReader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbContactReader))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbContactProtocol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbContactProtocol))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pContactless.setBorder(javax.swing.BorderFactory.createTitledBorder("Contactless"));

        cbContactlessReader.setEnabled(false);

        cbContactlessSupported.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbContactlessSupportedActionPerformed(evt);
            }
        });

        lbContactlessSupported1.setText("Contactless supported");
        lbContactlessSupported1.setToolTipText("");

        lbContactReader1.setText("Contactless reader");

        cbContactlessProtocol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T=CL" }));
        cbContactlessProtocol.setEnabled(false);

        lbContactProtocol1.setText("Contactless protocol");

        javax.swing.GroupLayout pContactlessLayout = new javax.swing.GroupLayout(pContactless);
        pContactless.setLayout(pContactlessLayout);
        pContactlessLayout.setHorizontalGroup(
            pContactlessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pContactlessLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pContactlessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbContactlessSupported1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbContactReader1)
                    .addComponent(lbContactProtocol1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(pContactlessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbContactlessReader, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbContactlessSupported)
                    .addComponent(cbContactlessProtocol, 0, 269, Short.MAX_VALUE)))
        );
        pContactlessLayout.setVerticalGroup(
            pContactlessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pContactlessLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pContactlessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbContactlessSupported1)
                    .addComponent(cbContactlessSupported))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pContactlessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbContactlessReader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbContactReader1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pContactlessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbContactlessProtocol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbContactProtocol1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pContactless, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(275, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pContactless, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        tpEmvParameters.addTab("Hardware", jPanel3);

        lbPerformPseSelection.setText("Perform PSE selection");

        lbApplicationSelectionSupported.setText("Application selection supported");
        lbApplicationSelectionSupported.setToolTipText("");

        tSupportedAids.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RID", "PIX", "Name", "Payment system"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane1.setViewportView(tSupportedAids);

        lbSupportedAids.setText("Supportecd AIDs");

        btAddAid.setText("Add");
        btAddAid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddAidActionPerformed(evt);
            }
        });

        btDeleteAid.setText("Delete");
        btDeleteAid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteAidActionPerformed(evt);
            }
        });

        btEditAid.setText("Edit");
        btEditAid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditAidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lbApplicationSelectionSupported)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbApplicationSelectionSupported))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lbPerformPseSelection)
                                    .addGap(109, 109, 109)
                                    .addComponent(cbPerformPseSelection)))
                            .addComponent(lbSupportedAids))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btDeleteAid, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btEditAid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btAddAid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbPerformPseSelection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbApplicationSelectionSupported))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbPerformPseSelection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbApplicationSelectionSupported)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSupportedAids)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btAddAid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEditAid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDeleteAid)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        tpEmvParameters.addTab("Payment application", jPanel2);

        cbEmvDataAid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEmvDataAidActionPerformed(evt);
            }
        });

        lbAid.setText("AID");

        lbTagList.setText("Tag list");

        lbRequestMessageTagList.setText("Request message tag list");
        lbRequestMessageTagList.setToolTipText("");

        lTagList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lTagListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lTagList);

        lRequestMessageTagList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lRequestMessageTagListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lRequestMessageTagList);

        bAddTagToEmvData.setText(">");
        bAddTagToEmvData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddTagToEmvDataActionPerformed(evt);
            }
        });

        bRemoveTagFromEmvData.setText("<");
        bRemoveTagFromEmvData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemoveTagFromEmvDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbAid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEmvDataAid, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTagList)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bAddTagToEmvData, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bRemoveTagFromEmvData, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRequestMessageTagList))))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEmvDataAid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTagList)
                    .addComponent(lbRequestMessageTagList))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(bAddTagToEmvData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bRemoveTagFromEmvData)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tpEmvParameters.addTab("EMV data", jPanel4);

        btOk.setText("OK");

        btCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpEmvParameters)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btOk, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpEmvParameters, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancel)
                    .addComponent(btOk))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbContactSupportedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbContactSupportedActionPerformed
        this.cbContactReader.setEnabled(this.cbContactSupported.isSelected());
        this.cbContactProtocol.setEnabled(this.cbContactSupported.isSelected());
    }//GEN-LAST:event_cbContactSupportedActionPerformed

    private void cbContactlessSupportedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbContactlessSupportedActionPerformed
        this.cbContactlessReader.setEnabled(this.cbContactlessSupported.isSelected());
        this.cbContactlessProtocol.setEnabled(this.cbContactlessSupported.isSelected());
    }//GEN-LAST:event_cbContactlessSupportedActionPerformed

    private EmvAid getSelectedAid (int row) {
        return new EmvAid((String)this.tSupportedAids.getValueAt(row, 0),
            (String)this.tSupportedAids.getValueAt(row, 1),
            (String)this.tSupportedAids.getValueAt(row, 2),
            PaymentSystem.toPaymentSystem((String)this.tSupportedAids.getValueAt(row, 3)));
    }
    
    private Object[] aidToRow(EmvAid aid) {
        Object[] row = new Object[4];
        row[0] = aid.getRid();
        row[1] = aid.getPix();
        row[2] = aid.getName();
        row[3] = aid.getPaymentSystem().getName();
        return row;
    }

    private void btAddAidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddAidActionPerformed
        PanelAddEditAid panel = new PanelAddEditAid();
        int option = JOptionPane.showConfirmDialog(this, panel, "Add AID", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            EmvAid aid = panel.getAid();
            try {
                db.insertAid(aid);
                this.supportedAidTableModel.addRow(aidToRow(aid));
                this.cbAidModel.addElement(new LabelValuePair(aid.getAid() + " - " + aid.getName(), aid));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btAddAidActionPerformed

    private void btEditAidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditAidActionPerformed
        int selectedRow = this.tSupportedAids.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }
        EmvAid aid = getSelectedAid(selectedRow);
        PanelAddEditAid panel = new PanelAddEditAid(aid);
        int option = JOptionPane.showConfirmDialog(this, panel, "Edit AID", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            aid = panel.getAid();
            try {
                db.updateAid(aid);
                this.supportedAidTableModel.setValueAt(aid.getRid(), selectedRow, 0);
                this.supportedAidTableModel.setValueAt(aid.getPix(), selectedRow, 1);
                this.supportedAidTableModel.setValueAt(aid.getName(), selectedRow, 2);
                this.supportedAidTableModel.setValueAt(aid.getPaymentSystem().getName(), selectedRow, 3);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btEditAidActionPerformed

    private void btDeleteAidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteAidActionPerformed
        int selectedRow = this.tSupportedAids.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }
        EmvAid aid = getSelectedAid(selectedRow);
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete "
                + "AID " + aid.getAid() + "?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            db.deleteAid(aid);
            this.supportedAidTableModel.removeRow(selectedRow);
            this.cbAidModel.removeElement(aid.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btDeleteAidActionPerformed

    private void cbEmvDataAidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEmvDataAidActionPerformed
        if (this.cbEmvDataAid.getItemCount() == 0) {
            return;
        }
        try {
            LabelValuePair item = (LabelValuePair)this.cbEmvDataAid.getSelectedItem();
            EmvAid aid = (EmvAid)item.getValue();
            List<EmvTag> emvTags = db.getEmvTags();
            emvTags.addAll(db.getEmvTags(aid.getPaymentSystem()));
            this.lTagListModel.removeAllElements();
            this.lRequestMessageTagListModel.removeAllElements();
            for (EmvTag emvTag : emvTags) {
                this.lTagListModel.addElement(new LabelValuePair(emvTag.getTag() + " - " + emvTag.getName(), emvTag));
            }
            emvTags = db.getEmvData(aid);
            for (EmvTag emvTag : emvTags) {
                this.lRequestMessageTagListModel.addElement(new LabelValuePair(emvTag.getTag() + " - " + emvTag.getName(), emvTag));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cbEmvDataAidActionPerformed

    private void lTagListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lTagListValueChanged
        this.lRequestMessageTagList.clearSelection();
    }//GEN-LAST:event_lTagListValueChanged

    private void lRequestMessageTagListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lRequestMessageTagListValueChanged
        this.lTagList.clearSelection();
    }//GEN-LAST:event_lRequestMessageTagListValueChanged

    private void moveListItem (JList from, JList to) {
        int selectedRow = from.getSelectedIndex();
        if (selectedRow == -1) {
            return;
        }
        DefaultListModel<LabelValuePair> fromListModel = (DefaultListModel<LabelValuePair>)from.getModel();
        DefaultListModel<LabelValuePair> toListModel = (DefaultListModel<LabelValuePair>)to.getModel();
        LabelValuePair item = fromListModel.getElementAt(selectedRow);
        fromListModel.removeElementAt(selectedRow);
        toListModel.addElement(item);
    }
    
    private void bAddTagToEmvDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddTagToEmvDataActionPerformed
        moveListItem(this.lTagList, this.lRequestMessageTagList);
    }//GEN-LAST:event_bAddTagToEmvDataActionPerformed

    private void bRemoveTagFromEmvDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemoveTagFromEmvDataActionPerformed
        moveListItem(this.lRequestMessageTagList, this.lTagList);
    }//GEN-LAST:event_bRemoveTagFromEmvDataActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddTagToEmvData;
    private javax.swing.JButton bRemoveTagFromEmvData;
    private javax.swing.JButton btAddAid;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btDeleteAid;
    private javax.swing.JButton btEditAid;
    private javax.swing.JButton btOk;
    private javax.swing.JCheckBox cbApplicationSelectionSupported;
    private javax.swing.JComboBox<String> cbContactProtocol;
    private javax.swing.JComboBox<String> cbContactReader;
    private javax.swing.JCheckBox cbContactSupported;
    private javax.swing.JComboBox<String> cbContactlessProtocol;
    private javax.swing.JComboBox<String> cbContactlessReader;
    private javax.swing.JCheckBox cbContactlessSupported;
    private javax.swing.JComboBox<LabelValuePair> cbEmvDataAid;
    private javax.swing.JCheckBox cbPerformPseSelection;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<az.pashabank.posemu.LabelValuePair> lRequestMessageTagList;
    private javax.swing.JList<az.pashabank.posemu.LabelValuePair> lTagList;
    private javax.swing.JLabel lbAid;
    private javax.swing.JLabel lbApplicationSelectionSupported;
    private javax.swing.JLabel lbContactProtocol;
    private javax.swing.JLabel lbContactProtocol1;
    private javax.swing.JLabel lbContactReader;
    private javax.swing.JLabel lbContactReader1;
    private javax.swing.JLabel lbContactSupported;
    private javax.swing.JLabel lbContactlessSupported1;
    private javax.swing.JLabel lbPerformPseSelection;
    private javax.swing.JLabel lbRequestMessageTagList;
    private javax.swing.JLabel lbSupportedAids;
    private javax.swing.JLabel lbTagList;
    private javax.swing.JPanel pContact;
    private javax.swing.JPanel pContactless;
    private javax.swing.JTable tSupportedAids;
    private javax.swing.JTabbedPane tpEmvParameters;
    // End of variables declaration//GEN-END:variables
}
