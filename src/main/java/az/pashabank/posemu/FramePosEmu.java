package az.pashabank.posemu;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class FramePosEmu extends javax.swing.JFrame {

    private Database db;
    //private final DialogEventLog dialogEventLog = new DialogEventLog(this, false);
    private EventLog log;

    public FramePosEmu() {
        initComponents();
        screenClear();
        //this.dialogEventLog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        log = EventLog.getInstance();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taDisplay = new javax.swing.JTextArea();
        bt1 = new javax.swing.JButton();
        bt2 = new javax.swing.JButton();
        bt3 = new javax.swing.JButton();
        bt4 = new javax.swing.JButton();
        bt5 = new javax.swing.JButton();
        bt6 = new javax.swing.JButton();
        bt7 = new javax.swing.JButton();
        bt8 = new javax.swing.JButton();
        bt9 = new javax.swing.JButton();
        bt0 = new javax.swing.JButton();
        btSharp = new javax.swing.JButton();
        btAsterix = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        btClear = new javax.swing.JButton();
        btClear1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miEventLog = new javax.swing.JMenuItem();
        mPosEmu = new javax.swing.JMenu();
        miParameters = new javax.swing.JMenuItem();
        miIsoMessgaeFIelds = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taDisplay.setEditable(false);
        taDisplay.setBackground(new java.awt.Color(204, 255, 255));
        taDisplay.setColumns(20);
        taDisplay.setFont(new java.awt.Font("Courier New", 1, 13)); // NOI18N
        taDisplay.setRows(5);
        jScrollPane1.setViewportView(taDisplay);

        bt1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt1.setText("1");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        bt2.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt2.setText("2");
        bt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        bt3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt3.setText("3");
        bt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        bt4.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt4.setText("4");
        bt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        bt5.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt5.setText("5");
        bt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        bt6.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt6.setText("6");
        bt6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        bt7.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt7.setText("7");
        bt7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        bt8.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt8.setText("8");
        bt8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        bt9.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt9.setText("9");
        bt9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        bt0.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt0.setText("0");
        bt0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btSharp.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        btSharp.setText("#");
        btSharp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSharpActionPerformed(evt);
            }
        });

        btAsterix.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        btAsterix.setText("*");

        btCancel.setBackground(new java.awt.Color(255, 0, 0));
        btCancel.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        btCancel.setText("X");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        btClear.setBackground(new java.awt.Color(255, 255, 0));
        btClear.setFont(new java.awt.Font("Courier New", Font.BOLD, 24));
        btClear.setText("\u2190");
        btClear.setToolTipText("");
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });

        btClear1.setBackground(new java.awt.Color(51, 255, 0));
        btClear1.setFont(new java.awt.Font("Courier New", 1, 18));
        btClear1.setText("\u21B5");
        btClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClear1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setToolTipText("Swipe magnetic stripe");

        jMenu1.setText("File");

        miEventLog.setText("Event log");
        miEventLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEventLogActionPerformed(evt);
            }
        });
        jMenu1.add(miEventLog);

        jMenuBar1.add(jMenu1);

        mPosEmu.setText("Edit");

        miParameters.setText("Parameters");
        miParameters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miParametersActionPerformed(evt);
            }
        });
        mPosEmu.add(miParameters);

        miIsoMessgaeFIelds.setText("ISO8583 message fields");
        miIsoMessgaeFIelds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miIsoMessgaeFIeldsActionPerformed(evt);
            }
        });
        mPosEmu.add(miIsoMessgaeFIelds);

        jMenuBar1.add(mPosEmu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bt1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bt2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bt3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btAsterix, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt0, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSharp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btClear, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt0, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSharp, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btAsterix, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btClear, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void run(String dbfFile, String logLevel) {
        log.info("Starting simulator...");
        db = Database.getInstance();
        try {
            db.connect();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "JDBC driver for SQLite not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error occured while connecting database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            try {
                db.disconnect();
            } catch (SQLException e2) {
                JOptionPane.showMessageDialog(this, "Error occured while closing databaseL " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private PosState state = PosState.IDLE;
    private String amount = "";
    private String pin = "............";
    private String screenData = "";  
    private double amt = 0.0;
    private boolean digit=true;

    private String screenClear() {

        // Variables setting to default value  
        this.state = PosState.IDLE;
        String amount = "0.00";
        String pin = "............";
       
        
        //amt=0.00;

        String screenData = "\n\n\n\n\n\n\n\n\n      Welcome to POS Emulator 1.0\n\n Please fell free to use this device\n         as real POS terminal";
        this.taDisplay.setText(screenData);
        return null;
    }

    private String screenEnterPin() {
        String screenData = "\n\n\n\n\n\n\n\n\n\n\n\n      Please enter PIN code:\n\n        " + pin;
        this.taDisplay.setText(screenData);
        return null;
    }

    private String screenChooseTrn() {
        String screenData = "\n\n\n\n\n\n\n\n\n\n\n\n    Please choose Transaction type:\n\n        1. Sales\n        2. Refund\n        3. Close POS day";
        this.taDisplay.setText(screenData);
        return null;
    }

    private String enterAmount() {
               
        String screenData = "\n\n\n\n\n\n\n\n\n\n\n\n    Please enter Amount:\n\n             " + amount;
        this.taDisplay.setText(screenData);      
        return null;
    }

    private void nextState() {
        this.state = this.state.nextState();
        switch (this.state) {
            case ENTER_PIN:
                screenEnterPin();
                break;
        }
    }


    private void numberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberButtonActionPerformed

        System.out.println("numberButtonActionPerformed ");
        
        //this.taDisplay.append(evt.getActionCommand());
        switch (this.state) {
            
        
            case CHOOSE_TRN:
                switch (evt.getActionCommand()) {
                    case "1":
                        this.state = this.state.nextState();
                        enterAmount();                    
                        break;
                    case "2":
                        System.out.println("2 option is choosed");
                        break;
                    case "3":
                        System.out.println("3 option is choosed");
                        break;
                }
                this.state = PosState.ENTER_AMT;
                break;
            case ENTER_AMT:
                String amountStr = input.add(evt.getActionCommand());
                screenData = "\n\n\n\n\n\n\n\n\n\n\n\n    Please enter Amount:\n\n             " + amountStr;
                this.taDisplay.setText(screenData);
                break;
                /*
                if (digit) {

                 screenData = "\n\n\n\n\n\n\n\n\n\n\n\n    Please enter Amount:\n\n             0.00";
                 this.taDisplay.setText(screenData);  

                 digit=false;  
                }
                else 
                {

                if (amount.length()<13)
                {                                                    
                //if amount.    
                if (amount.length() < 4) {                  
                    amount = amount + evt.getActionCommand();
                    amt = Double.parseDouble(amount) / 100;             
                    screenData = "\n\n\n\n\n\n\n\n\n\n\n\n    Please enter Amount:\n\n             " + amt;
                    this.taDisplay.setText(screenData);                
                }
                else
                {
                    amount = amount + evt.getActionCommand();                                
                    amount=amount.replaceAll("[^0-9]","");              
                    amount = amount.substring(0, amount.length()-2) + "." + amount.substring((amount.length()-2), amount.length());                                                
                    screenData = "\n\n\n\n\n\n\n\n\n\n\n\n    Please enter Amount:\n\n             " + amount;
                    this.taDisplay.setText(screenData);
                }

                }            
                }
                */

        }
    }//GEN-LAST:event_numberButtonActionPerformed

    private void miParametersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miParametersActionPerformed
        DialogParameters dlg = new DialogParameters(this, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_miParametersActionPerformed

    private void btClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClear1ActionPerformed
        // TODO add your handling code here:

        if (this.state == PosState.IDLE) {

            this.state = this.state.nextState();
            this.state = this.state.nextState();
            screenChooseTrn();
        }
        //screenEnterPin();
    }//GEN-LAST:event_btClear1ActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        // TODO add your handling code here:
        screenClear();
    }//GEN-LAST:event_btCancelActionPerformed

    private void btSharpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSharpActionPerformed
        // TODO add your handling code here:
        if (this.state == PosState.IDLE) {
            System.out.println("Other menu");
        }
    }//GEN-LAST:event_btSharpActionPerformed

    AmountInput input = new AmountInput();
    
    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
        
       if (this.state == PosState.ENTER_AMT) {
           String amountStr = input.backspace();
           screenData = "\n\n\n\n\n\n\n\n\n\n\n\n    Please enter Amount:\n\n             " + amountStr;
           this.taDisplay.setText(screenData);  
           
           
           
           
           
           /*
           if (!digit)
           {
               
                if (amount.length() < 7) {     
                 
             //amount =  amount.replaceAll("[^0-9]","");         
            // amount = amount.substring(0, amount.length()-1); 

            System.out.println("amount="+amount);
            
            double d=Double.valueOf(amount);        
            amount=String.valueOf(d/1000);
            amount = amount.substring(0, amount.length()-1);
                                                 
            screenData = "\n\n\n\n\n\n\n\n\n\n\n\n    Please enter Amount:\n\n             " + amount;
            this.taDisplay.setText(screenData); 
            
            amount=String.valueOf(Double.valueOf(amount)*100);
            
            }
            else
            {
                amount = amount + evt.getActionCommand();                                
                amount=amount.replaceAll("[^0-9]","");              
                amount = amount.substring(0, amount.length()-2) + "." + amount.substring((amount.length()-2), amount.length());                                                
                screenData = "\n\n\n\n\n\n\n\n\n\n\n\n    Please enter Amount:\n\n             " + amount;
                this.taDisplay.setText(screenData);
            }
               
               
           }  
*/
       } 
    }//GEN-LAST:event_btClearActionPerformed

    private void miIsoMessgaeFIeldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miIsoMessgaeFIeldsActionPerformed
        DialogIsoMessageFields dlg = new DialogIsoMessageFields(this, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_miIsoMessgaeFIeldsActionPerformed

    private void miEventLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEventLogActionPerformed
        if (this.log.isVisible()) {
            return;
        }
        this.log.setVisible(true);
    }//GEN-LAST:event_miEventLogActionPerformed

    private static void cryForHelp () {
        System.out.println("Arguments:");
        System.out.println("--help - displays help");
        System.out.println("--dbf-file - database file to be used, if not provided, programm uses default");
        System.out.println("--log-level - logging level, if not provided, logging level INFO is used. "
                + "Allowed values: NONE, ERROR, WARNING, INFO, DEBUG");
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePosEmu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePosEmu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePosEmu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePosEmu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        FramePosEmu frame = new FramePosEmu();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });

        String dbfFile = null;
        String logLevel = null;
        
        for (int i = 0; i < args.length; i++){
            if (args[i].equals("--help")) {
                cryForHelp();
            } else if (args[i].equals("--dbf-file")) {
                dbfFile = args[i + 1];
                i++;
            } else if (args[i].equals("--log-level")) {
                logLevel = args[i + 1];
                i++;
            }
        }
        frame.run(dbfFile, logLevel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt0;
    private javax.swing.JButton bt1;
    private javax.swing.JButton bt2;
    private javax.swing.JButton bt3;
    private javax.swing.JButton bt4;
    private javax.swing.JButton bt5;
    private javax.swing.JButton bt6;
    private javax.swing.JButton bt7;
    private javax.swing.JButton bt8;
    private javax.swing.JButton bt9;
    private javax.swing.JButton btAsterix;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btClear;
    private javax.swing.JButton btClear1;
    private javax.swing.JButton btSharp;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mPosEmu;
    private javax.swing.JMenuItem miEventLog;
    private javax.swing.JMenuItem miIsoMessgaeFIelds;
    private javax.swing.JMenuItem miParameters;
    private javax.swing.JTextArea taDisplay;
    // End of variables declaration//GEN-END:variables
}
