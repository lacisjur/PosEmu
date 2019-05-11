package az.pashabank.posemu;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DialogParameters extends javax.swing.JDialog {

    private Database db;
    
    public DialogParameters(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.db = Database.getInstance();
        try {
            this.tfAcqHostIp.setText(db.getParameter(Constants.PARAM_ACQ_HOST_IP_ADDRESS));
            this.tfAcqHostPort.setText(db.getParameter(Constants.PARAM_ACQ_HOST_PORT));
            this.tfAcqHostTimeout.setText(db.getParameter(Constants.PARAM_ACQ_HOST_TIMEOUT));
            this.tfTerminalId.setText(db.getParameter(Constants.PARAM_TERMINAL_ID));
            this.tfMerchantId.setText(db.getParameter(Constants.PARAM_TERMINAL_MERCHANT_ID));
            this.tfPinKey.setText(new TripleDesKey(db.getParameter(Constants.PARAM_PIN_KEY)).toString());
            this.cbPinKeyIsUsed.setSelected(db.getBooleanParameter(Constants.PARAM_PIN_KEY_IS_USED));
            this.tfPinKey.setEnabled(this.cbPinKeyIsUsed.isSelected());
            this.btGeneratePinKey.setEnabled(this.cbPinKeyIsUsed.isSelected());
            this.tfEncryptionKey.setText(new TripleDesKey(db.getParameter(Constants.PARAM_ENCRYPTION_KEY)).toString());
            this.cbEncryptionKeyIsUsed.setSelected(db.getBooleanParameter(Constants.PARAM_ENCRYPTION_KEY_IS_USED));
            this.tfEncryptionKey.setEnabled(this.cbEncryptionKeyIsUsed.isSelected());
            this.btGenerateEncryptionKey.setEnabled(this.cbEncryptionKeyIsUsed.isSelected());
            this.tfMacKey.setText(new TripleDesKey(db.getParameter(Constants.PARAM_MAC_KEY)).toString());
            this.cbMacKeyIsUsed.setSelected(db.getBooleanParameter(Constants.PARAM_MAC_KEY_IS_USED));
            this.tfMacKey.setEnabled(this.cbMacKeyIsUsed.isSelected());
            this.btGenerateMacKey.setEnabled(this.cbMacKeyIsUsed.isSelected());
            this.tfMasterKey.setText(new TripleDesKey(db.getParameter(Constants.PARAM_MASTER_KEY)).toString());
            this.cbMasterKeyIsUsed.setSelected(db.getBooleanParameter(Constants.PARAM_MASTER_KEY_IS_USED));
            this.tfMasterKey.setEnabled(this.cbMasterKeyIsUsed.isSelected());
            this.btGenerateMasterKey.setEnabled(this.cbMasterKeyIsUsed.isSelected());
            List<Currency> currencies = db.getCurrencies();
            List<String> columns = new ArrayList<>();
            columns.add("Numeric code");
            columns.add("Alphabetic code");
            columns.add("Name");
            List<String[]> rows = new ArrayList<>();
            for (Currency currency : currencies) {
                String[] row = new String[3];
                row[0] = currency.getCurrencyNum();
                row[1] = currency.getCurrencyAlpha();
                row[2] = currency.getCurrencyName();
                rows.add(row);
            }
            this.tCurrencies.setModel(new DefaultTableModel(rows.toArray(new Object [][] {}), columns.toArray()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to populate paramters: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btOk = new javax.swing.JButton();
        tpParameters = new javax.swing.JTabbedPane();
        pGeneral = new javax.swing.JPanel();
        pAcquirerHost = new javax.swing.JPanel();
        tfAcqHostIp = new javax.swing.JTextField();
        lbAcqHostIp = new javax.swing.JLabel();
        lbAcqHostPort = new javax.swing.JLabel();
        tfAcqHostPort = new javax.swing.JTextField();
        lbAcqHostTimeout = new javax.swing.JLabel();
        tfAcqHostTimeout = new javax.swing.JTextField();
        pTerminalParameters = new javax.swing.JPanel();
        tfTerminalId = new javax.swing.JTextField();
        lbMerchantId = new javax.swing.JLabel();
        tfMerchantId = new javax.swing.JTextField();
        lbAcqHostTimeout1 = new javax.swing.JLabel();
        pCurrencies = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCurrencies = new javax.swing.JTable();
        btAddCurrency = new javax.swing.JButton();
        btRemoveCurrency = new javax.swing.JButton();
        pCryptography = new javax.swing.JPanel();
        lbPinKey = new javax.swing.JLabel();
        tfPinKey = new javax.swing.JTextField();
        cbPinKeyIsUsed = new javax.swing.JCheckBox();
        lbEncryptionKey = new javax.swing.JLabel();
        tfEncryptionKey = new javax.swing.JTextField();
        cbEncryptionKeyIsUsed = new javax.swing.JCheckBox();
        lbMacKey = new javax.swing.JLabel();
        tfMacKey = new javax.swing.JTextField();
        cbMacKeyIsUsed = new javax.swing.JCheckBox();
        tfPinKeyCheckValue = new javax.swing.JTextField();
        tfEncryptionKeyCheckValue = new javax.swing.JTextField();
        tfMacKeyCheckValue = new javax.swing.JTextField();
        lbMasterKey = new javax.swing.JLabel();
        tfMasterKey = new javax.swing.JTextField();
        cbMasterKeyIsUsed = new javax.swing.JCheckBox();
        tfMasterKeyCheckValue = new javax.swing.JTextField();
        btGeneratePinKey = new javax.swing.JButton();
        btGenerateEncryptionKey = new javax.swing.JButton();
        btGenerateMacKey = new javax.swing.JButton();
        btGenerateMasterKey = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Configuration"); // NOI18N
        setResizable(false);

        btOk.setText("OK");

        pAcquirerHost.setBorder(javax.swing.BorderFactory.createTitledBorder("Acquirer host"));

        lbAcqHostIp.setText("IP address");
        lbAcqHostIp.setToolTipText("");

        lbAcqHostPort.setText("Port");
        lbAcqHostPort.setToolTipText("");

        lbAcqHostTimeout.setText("Timeout");
        lbAcqHostTimeout.setToolTipText("");

        javax.swing.GroupLayout pAcquirerHostLayout = new javax.swing.GroupLayout(pAcquirerHost);
        pAcquirerHost.setLayout(pAcquirerHostLayout);
        pAcquirerHostLayout.setHorizontalGroup(
            pAcquirerHostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAcquirerHostLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAcquirerHostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbAcqHostPort, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbAcqHostIp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(lbAcqHostTimeout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(pAcquirerHostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfAcqHostPort, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(tfAcqHostTimeout)
                    .addComponent(tfAcqHostIp))
                .addContainerGap())
        );
        pAcquirerHostLayout.setVerticalGroup(
            pAcquirerHostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAcquirerHostLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAcquirerHostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAcqHostIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAcqHostIp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAcquirerHostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAcqHostPort)
                    .addComponent(tfAcqHostPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAcquirerHostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAcqHostTimeout)
                    .addComponent(tfAcqHostTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pTerminalParameters.setBorder(javax.swing.BorderFactory.createTitledBorder("Terminal general parameters"));

        lbMerchantId.setText("Merchant ID");
        lbMerchantId.setToolTipText("");

        lbAcqHostTimeout1.setText("Termnal ID");
        lbAcqHostTimeout1.setToolTipText("");

        javax.swing.GroupLayout pTerminalParametersLayout = new javax.swing.GroupLayout(pTerminalParameters);
        pTerminalParameters.setLayout(pTerminalParametersLayout);
        pTerminalParametersLayout.setHorizontalGroup(
            pTerminalParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTerminalParametersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTerminalParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTerminalParametersLayout.createSequentialGroup()
                        .addComponent(lbMerchantId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pTerminalParametersLayout.createSequentialGroup()
                        .addComponent(lbAcqHostTimeout1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGroup(pTerminalParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfTerminalId, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(tfMerchantId))
                .addContainerGap())
        );
        pTerminalParametersLayout.setVerticalGroup(
            pTerminalParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTerminalParametersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTerminalParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTerminalId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAcqHostTimeout1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pTerminalParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMerchantId)
                    .addComponent(tfMerchantId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pCurrencies.setBorder(javax.swing.BorderFactory.createTitledBorder("Currencies"));

        tCurrencies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numeric code", "Alphabetic code", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tCurrencies.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tCurrencies);

        btAddCurrency.setText("Add");

        btRemoveCurrency.setText("Remove");

        javax.swing.GroupLayout pCurrenciesLayout = new javax.swing.GroupLayout(pCurrencies);
        pCurrencies.setLayout(pCurrenciesLayout);
        pCurrenciesLayout.setHorizontalGroup(
            pCurrenciesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCurrenciesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCurrenciesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAddCurrency, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRemoveCurrency, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        pCurrenciesLayout.setVerticalGroup(
            pCurrenciesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCurrenciesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCurrenciesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pCurrenciesLayout.createSequentialGroup()
                        .addComponent(btAddCurrency)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRemoveCurrency)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pGeneralLayout = new javax.swing.GroupLayout(pGeneral);
        pGeneral.setLayout(pGeneralLayout);
        pGeneralLayout.setHorizontalGroup(
            pGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pAcquirerHost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pTerminalParameters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pCurrencies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        pGeneralLayout.setVerticalGroup(
            pGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pGeneralLayout.createSequentialGroup()
                .addComponent(pAcquirerHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pTerminalParameters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pCurrencies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpParameters.addTab("General", pGeneral);

        lbPinKey.setText("PIN key");

        tfPinKey.setEditable(false);
        tfPinKey.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        tfPinKey.setText("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

        cbPinKeyIsUsed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPinKeyIsUsedActionPerformed(evt);
            }
        });

        lbEncryptionKey.setText("Encryption key");

        tfEncryptionKey.setEditable(false);
        tfEncryptionKey.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        tfEncryptionKey.setText("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        tfEncryptionKey.setToolTipText("");

        cbEncryptionKeyIsUsed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEncryptionKeyIsUsedActionPerformed(evt);
            }
        });

        lbMacKey.setText("MAC key");

        tfMacKey.setEditable(false);
        tfMacKey.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        tfMacKey.setText("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

        cbMacKeyIsUsed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMacKeyIsUsedActionPerformed(evt);
            }
        });

        tfPinKeyCheckValue.setEditable(false);
        tfPinKeyCheckValue.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        tfPinKeyCheckValue.setText("FFFFFF");

        tfEncryptionKeyCheckValue.setEditable(false);
        tfEncryptionKeyCheckValue.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        tfEncryptionKeyCheckValue.setText("FFFFFF");

        tfMacKeyCheckValue.setEditable(false);
        tfMacKeyCheckValue.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        tfMacKeyCheckValue.setText("FFFFFF");

        lbMasterKey.setText("Master key");

        tfMasterKey.setEditable(false);
        tfMasterKey.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        tfMasterKey.setText("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

        cbMasterKeyIsUsed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMasterKeyIsUsedActionPerformed(evt);
            }
        });

        tfMasterKeyCheckValue.setEditable(false);
        tfMasterKeyCheckValue.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        tfMasterKeyCheckValue.setText("FFFFFF");

        btGeneratePinKey.setText("Generate");
        btGeneratePinKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGeneratePinKeyActionPerformed(evt);
            }
        });

        btGenerateEncryptionKey.setText("Generate");
        btGenerateEncryptionKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGenerateEncryptionKeyActionPerformed(evt);
            }
        });

        btGenerateMacKey.setText("Generate");
        btGenerateMacKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGenerateMacKeyActionPerformed(evt);
            }
        });

        btGenerateMasterKey.setText("Generate");
        btGenerateMasterKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGenerateMasterKeyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pCryptographyLayout = new javax.swing.GroupLayout(pCryptography);
        pCryptography.setLayout(pCryptographyLayout);
        pCryptographyLayout.setHorizontalGroup(
            pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCryptographyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbMasterKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEncryptionKey, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPinKey, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMacKey, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfMasterKey, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addComponent(tfMacKey, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfPinKey, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfEncryptionKey, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pCryptographyLayout.createSequentialGroup()
                        .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfMacKey, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(tfMasterKey))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pCryptographyLayout.createSequentialGroup()
                                .addComponent(cbMasterKeyIsUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfMasterKeyCheckValue, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGenerateMasterKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pCryptographyLayout.createSequentialGroup()
                                .addComponent(cbMacKeyIsUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfMacKeyCheckValue, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGenerateMacKey, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(109, Short.MAX_VALUE))
                    .addGroup(pCryptographyLayout.createSequentialGroup()
                        .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfEncryptionKey, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(tfPinKey))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMacKeyCheckValue))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCryptographyLayout.createSequentialGroup()
                        .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbPinKeyIsUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEncryptionKeyIsUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfPinKeyCheckValue, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(tfEncryptionKeyCheckValue))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btGeneratePinKey, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(btGenerateEncryptionKey, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(btGenerateMacKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btGenerateMasterKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        pCryptographyLayout.setVerticalGroup(
            pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCryptographyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPinKey)
                        .addComponent(tfPinKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfPinKeyCheckValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btGeneratePinKey))
                    .addComponent(cbPinKeyIsUsed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbEncryptionKey)
                        .addComponent(tfEncryptionKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbEncryptionKeyIsUsed)
                    .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfEncryptionKeyCheckValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btGenerateEncryptionKey))
                    .addComponent(cbEncryptionKeyIsUsed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbMacKey)
                            .addComponent(tfMacKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cbMacKeyIsUsed))
                    .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfMacKeyCheckValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btGenerateMacKey)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCryptographyLayout.createSequentialGroup()
                        .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMasterKeyIsUsed)
                            .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfMasterKeyCheckValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btGenerateMasterKey)))
                        .addGap(52, 165, Short.MAX_VALUE))
                    .addGroup(pCryptographyLayout.createSequentialGroup()
                        .addGroup(pCryptographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMasterKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbMasterKey, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tpParameters.addTab("Cryptography", pCryptography);

        btCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpParameters)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btOk, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpParameters)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancel)
                    .addComponent(btOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setKey (String keyName, JTextField tfKey, JCheckBox cbIsUsed, JButton bGenerateKey, JTextField tfCheckValue) {
        tfKey.setEnabled(cbIsUsed.isSelected());
        bGenerateKey.setEnabled(cbIsUsed.isSelected());
        TripleDesKey key = new TripleDesKey(tfKey.getText());
        if (!key.isUnset()) {
            try {
                tfCheckValue.setText(Crypto.generateKeyCheckValue(key));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to generate KCV for " + keyName + " key: " + e.getMessage());
            }
        }
    }
    
    private void generateKey (String name, JTextField tfKey, JTextField tfKeyCheckValue) {
        try {
            TripleDesKey key = Crypto.generate3DesKey(Crypto.DesKeyLength.DOUBLE);
            tfKey.setText(key.toString());
            String kcv = Crypto.generateKeyCheckValue(key);
            tfKeyCheckValue.setText(kcv);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cbPinKeyIsUsedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPinKeyIsUsedActionPerformed
        setKey("PIN", this.tfPinKey, this.cbPinKeyIsUsed, this.btGeneratePinKey, this.tfPinKeyCheckValue);
    }//GEN-LAST:event_cbPinKeyIsUsedActionPerformed

    private void cbEncryptionKeyIsUsedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEncryptionKeyIsUsedActionPerformed
        setKey("Encryption", this.tfEncryptionKey, this.cbEncryptionKeyIsUsed, this.btGenerateEncryptionKey, this.tfEncryptionKeyCheckValue);
    }//GEN-LAST:event_cbEncryptionKeyIsUsedActionPerformed

    private void cbMacKeyIsUsedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMacKeyIsUsedActionPerformed
        setKey("MAC", this.tfMacKey, this.cbMacKeyIsUsed, this.btGenerateMacKey, this.tfMacKeyCheckValue);
    }//GEN-LAST:event_cbMacKeyIsUsedActionPerformed

    private void cbMasterKeyIsUsedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMasterKeyIsUsedActionPerformed
        setKey("Master", this.tfMasterKey, this.cbMasterKeyIsUsed, this.btGenerateMasterKey, this.tfMasterKeyCheckValue);
    }//GEN-LAST:event_cbMasterKeyIsUsedActionPerformed

    private void btGeneratePinKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGeneratePinKeyActionPerformed
        generateKey("PIN", this.tfPinKey, this.tfPinKeyCheckValue);
    }//GEN-LAST:event_btGeneratePinKeyActionPerformed

    private void btGenerateEncryptionKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGenerateEncryptionKeyActionPerformed
        generateKey("Encryption", this.tfEncryptionKey, this.tfEncryptionKeyCheckValue);
    }//GEN-LAST:event_btGenerateEncryptionKeyActionPerformed

    private void btGenerateMacKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGenerateMacKeyActionPerformed
        generateKey("MAC", this.tfMacKey, this.tfMacKeyCheckValue);
    }//GEN-LAST:event_btGenerateMacKeyActionPerformed

    private void btGenerateMasterKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGenerateMasterKeyActionPerformed
        generateKey("Master", this.tfMasterKey, this.tfMasterKeyCheckValue);
    }//GEN-LAST:event_btGenerateMasterKeyActionPerformed
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogParameters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogParameters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogParameters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogParameters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogParameters dialog = new DialogParameters(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddCurrency;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btGenerateEncryptionKey;
    private javax.swing.JButton btGenerateMacKey;
    private javax.swing.JButton btGenerateMasterKey;
    private javax.swing.JButton btGeneratePinKey;
    private javax.swing.JButton btOk;
    private javax.swing.JButton btRemoveCurrency;
    private javax.swing.JCheckBox cbEncryptionKeyIsUsed;
    private javax.swing.JCheckBox cbMacKeyIsUsed;
    private javax.swing.JCheckBox cbMasterKeyIsUsed;
    private javax.swing.JCheckBox cbPinKeyIsUsed;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAcqHostIp;
    private javax.swing.JLabel lbAcqHostPort;
    private javax.swing.JLabel lbAcqHostTimeout;
    private javax.swing.JLabel lbAcqHostTimeout1;
    private javax.swing.JLabel lbEncryptionKey;
    private javax.swing.JLabel lbMacKey;
    private javax.swing.JLabel lbMasterKey;
    private javax.swing.JLabel lbMerchantId;
    private javax.swing.JLabel lbPinKey;
    private javax.swing.JPanel pAcquirerHost;
    private javax.swing.JPanel pCryptography;
    private javax.swing.JPanel pCurrencies;
    private javax.swing.JPanel pGeneral;
    private javax.swing.JPanel pTerminalParameters;
    private javax.swing.JTable tCurrencies;
    private javax.swing.JTextField tfAcqHostIp;
    private javax.swing.JTextField tfAcqHostPort;
    private javax.swing.JTextField tfAcqHostTimeout;
    private javax.swing.JTextField tfEncryptionKey;
    private javax.swing.JTextField tfEncryptionKeyCheckValue;
    private javax.swing.JTextField tfMacKey;
    private javax.swing.JTextField tfMacKeyCheckValue;
    private javax.swing.JTextField tfMasterKey;
    private javax.swing.JTextField tfMasterKeyCheckValue;
    private javax.swing.JTextField tfMerchantId;
    private javax.swing.JTextField tfPinKey;
    private javax.swing.JTextField tfPinKeyCheckValue;
    private javax.swing.JTextField tfTerminalId;
    private javax.swing.JTabbedPane tpParameters;
    // End of variables declaration//GEN-END:variables
}
