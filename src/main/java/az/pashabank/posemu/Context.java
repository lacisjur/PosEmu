package az.pashabank.posemu;

import java.util.List;

public class Context {

    private final String acqHostIpAddress;
    private final int acqHostPort;
    private final int acqHostTimeout;
    private final int terminalInterfaceId;
    private final String terminalId;
    private final String terminalMerchantId;
    private final List<Currency> currencies;
    private String pinKey;
    private final boolean pinKeyUsed;
    private String encryptionKey;
    private final boolean encryptionKeyUsed;
    private String macKey;
    private final boolean macKeyUsed;
    private String masterKey;
    private final boolean masterKeyUsed;
    
    private final Database db;
    
    public Context () throws Exception {
        this.db = Database.getInstance();
        this.acqHostIpAddress = db.getParameter(Constants.PARAM_ACQ_HOST_IP_ADDRESS);
        this.acqHostPort = db.getIntParameter(Constants.PARAM_ACQ_HOST_PORT);
        this.acqHostTimeout = db.getIntParameter(Constants.PARAM_ACQ_HOST_TIMEOUT);
        this.terminalId = db.getParameter(Constants.PARAM_TERMINAL_ID);
        this.terminalMerchantId = db.getParameter(Constants.PARAM_TERMINAL_MERCHANT_ID);
        this.terminalInterfaceId = db.getIntParameter(Constants.PARAM_TERMINAL_INTERFACE_ID);
        this.currencies = db.getCurrencies();
        this.pinKey = db.getParameter(Constants.PARAM_PIN_KEY);
        this.pinKeyUsed = db.getBooleanParameter(Constants.PARAM_PIN_KEY_IS_USED);
        this.encryptionKey = db.getParameter(Constants.PARAM_ENCRYPTION_KEY);
        this.encryptionKeyUsed = db.getBooleanParameter(Constants.PARAM_ENCRYPTION_KEY_IS_USED);
        this.macKey = db.getParameter(Constants.PARAM_MAC_KEY);
        this.macKeyUsed = db.getBooleanParameter(Constants.PARAM_MAC_KEY_IS_USED);
        this.masterKey = db.getParameter(Constants.PARAM_MASTER_KEY);
        this.masterKeyUsed = db.getBooleanParameter(Constants.PARAM_MASTER_KEY_IS_USED);
        IsoMessage.isoFields = db.getFields(this.terminalInterfaceId);
    }

    public String getAcqHostIpAddress() {
        return acqHostIpAddress;
    }

    public int getAcqHostPort() {
        return acqHostPort;
    }

    public int getAcqHostTimeout() {
        return acqHostTimeout;
    }

    public int getTerminalInterfaceId() {
        return terminalInterfaceId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public String getTerminalMerchantId() {
        return terminalMerchantId;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public String getPinKey() {
        return pinKey;
    }

    public boolean isPinKeyUsed() {
        return pinKeyUsed;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public boolean isEncryptionKeyUsed() {
        return encryptionKeyUsed;
    }

    public String getMacKey() {
        return macKey;
    }

    public boolean isMacKeyUsed() {
        return macKeyUsed;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public boolean isMasterKeyUsed() {
        return masterKeyUsed;
    }

    public Database getDb() {
        return db;
    }

    public void setPinKey(String pinKey) throws Exception {
        this.db.updateParameter(Constants.PARAM_PIN_KEY, pinKey);
        this.pinKey = pinKey;
    }

    public void setEncryptionKey(String encryptionKey) throws Exception {
        this.db.updateParameter(Constants.PARAM_ENCRYPTION_KEY, encryptionKey);
        this.encryptionKey = encryptionKey;
    }

    public void setMacKey(String macKey) throws Exception {
        this.db.updateParameter(Constants.PARAM_MAC_KEY, macKey);
        this.macKey = macKey;
    }

    public void setMasterKey(String masterKey) throws Exception {
        this.db.updateParameter(Constants.PARAM_MASTER_KEY, masterKey);
        this.masterKey = masterKey;
    }
    
    
}
