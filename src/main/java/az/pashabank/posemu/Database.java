package az.pashabank.posemu;

import az.pashabank.posemu.emv.EmvAid;
import az.pashabank.posemu.emv.EmvTag;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {

    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DEFAULT_DBF_FILE = "posemu.sqlite3";
    private final EventLog log = EventLog.getInstance();

    private static Database db;

    private Connection jdbc;

    private Database() {
    }

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    public void connect() throws ClassNotFoundException, FileNotFoundException, SQLException {
        connect(DEFAULT_DBF_FILE);
    }

    public void connect(String dbfFile) throws ClassNotFoundException, FileNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        if (!dbfFile.equals(DEFAULT_DBF_FILE)) {
            if (!new File(dbfFile).exists()) {
                log.error("Custom DBF file " + dbfFile + " not found");
                throw new FileNotFoundException("Custom DBF file " + dbfFile + " not found");
            }
        }
        this.jdbc = DriverManager.getConnection("jdbc:sqlite:" + dbfFile);
        log.info("Database " + dbfFile + " connected");
    }

    public void disconnect() throws SQLException {
        if (this.jdbc != null) {
            this.jdbc.close();
        }
        log.info("Database disconnected");
    }

    public boolean getBooleanParameter(String parameter) throws Exception {
        return Boolean.parseBoolean(getParameter(parameter));
    }

    public int getIntParameter(String parameter) throws Exception {
        return Integer.parseInt(getParameter(parameter));
    }

    public String getParameter(String parameter) throws Exception {
        String sqlStr = "SELECT value FROM parameter WHERE name = ?";
        String value = null;
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, parameter);
            try (ResultSet rs = sql.executeQuery()) {
                rs.next();
                value = rs.getString(1);
            }
        } catch (SQLException e) {
            log.error("Failed to select parameter: " + e.getMessage(), e);
            throw new Exception("Failed to select parameter: " + e.getMessage(), e);
        }
        log.info("Get parameter: " + parameter + " = " + value);
        return value;
    }

    String getTransaction() throws Exception {
        String sqlStr = "SELECT max(transaction_id)+1 FROM transactions";
        String value = null;
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            try (ResultSet rs = sql.executeQuery()) {
                rs.next();
                value = rs.getString(1);
            }
        } catch (SQLException e) {
            log.error("Failed to select parameter: " + e.getMessage(), e);
            throw new Exception("Failed to select parameter: " + e.getMessage(), e);
        }
        log.info("Get Transaction ID: " + value);
        return value;
    }

    void setTransaction(String trn) throws Exception {
        String sqlStr = "insert into transactions(transaction_id) values(?)";
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, trn);
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to add currency: " + e.getMessage(), e);
            throw new Exception("Failed to add currency: " + e.getMessage(), e);
        }
    }

    void updateTransaction(String trn, String param, String var) throws Exception {
        String sqlStr = null;
        switch (param) {
            case "card":
                sqlStr = "update transactions set card=? where transaction_id=?";
                break;
            case "amount":
                sqlStr = "update transactions set amount=? where transaction_id=?";
                break;
            case "status":
                sqlStr = "update transactions set status=? where transaction_id=?";
                break;
            case "rrn":
                sqlStr = "update transactions set rrn=? where transaction_id=?";
                break;
            case "fld_039":
                sqlStr = "update transactions set fld_039=? where transaction_id=?";
                break;
            case "addinfo":
                sqlStr = "update transactions set add_info=? where transaction_id=?";
                break;
            case "date":
                sqlStr = "update transactions set date=? where transaction_id=?";
                break;
        }

        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, var);
            sql.setString(2, trn);
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to add currency: " + e.getMessage(), e);
            throw new Exception("Failed to add currency: " + e.getMessage(), e);
        }
    }

    List<Currency> getCurrencies() throws Exception {
        String sqlStr = "SELECT ccy_num_code, ccy_alpha_code, ccy_name FROM currency";
        List<Currency> currencies = new ArrayList<>();
        try (Statement sql = jdbc.createStatement()) {
            try (ResultSet rs = sql.executeQuery(sqlStr)) {
                while (rs.next()) {
                    Currency currency = new Currency(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3));
                    currencies.add(currency);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to select currencies: " + e.getMessage(), e);
            throw new Exception("Failed to select currencies: " + e.getMessage(), e);
        }
        return currencies;
    }

    void addCurrency(Currency currency) throws Exception {
        String sqlStr = "INSERT INTO currency (ccy_num_code, ccy_alpha_code, ccy_name) VALUES (?, ?, ?)";
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, currency.getCurrencyNum());
            sql.setString(2, currency.getCurrencyAlpha());
            sql.setString(3, currency.getCurrencyName());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to add currency: " + e.getMessage(), e);
            throw new Exception("Failed to add currency: " + e.getMessage(), e);
        }
    }

    void deleteCurrency(Currency currency) throws Exception {
        String sqlStr = "DELETE FROM currency WHERE ccy_num_code = ?";
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, currency.getCurrencyNum());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to delete currency: " + e.getMessage(), e);
            throw new Exception("Failed to delete currency: " + e.getMessage(), e);
        }
    }
    
    void insertParameter(String parameter, boolean value) throws Exception {
        insertParameter(parameter, Boolean.toString(value));
    }
    
    void insertParameter(String parameter, int value) throws Exception {
        insertParameter(parameter, Integer.toString(value));
    }
    
    void insertParameter(String parameter, String value) throws Exception {
        final String sqlStr = "INSERT INTO parameter (name, value) VALUES (?, ?)";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, parameter);
            sql.setString(2, value);
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to insert parameter: " + e.getMessage(), e);
            throw new Exception("Failed to insert parameter: " + e.getMessage(), e);
        }
    }

    void updateParameter(String parameter, boolean value) throws Exception {
        updateParameter(parameter, (value) ? 1 : 0);
    }

    void updateParameter(String parameter, int value) throws Exception {
        updateParameter(parameter, Integer.toString(value));
    }

    void updateParameter(String parameter, String value) throws Exception {
        final String sqlStr = "UPDATE parameter SET value = ? WHERE name = ?";
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, value);
            sql.setString(2, parameter);
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to update parameter value: " + e.getMessage(), e);
            throw new Exception("Failed to update parameter value: " + e.getMessage(), e);
        }
    }
    
    void deleteParameters() throws Exception {
        final String sqlStr = "DELETE FROM parameter";
        try (Statement sql = jdbc.createStatement()) {
            sql.executeUpdate(sqlStr);
        } catch (SQLException e) {
            log.error("Failed to delete parameters: " + e.getMessage(), e);
            throw new Exception("Failed to delete parameters: " + e.getMessage(), e);   
        }
    }
    
    void insertDefaultParameters () throws Exception {
        deleteParameters();
        insertParameter(Constants.PARAM_ACQ_HOST_IP_ADDRESS, "127.0.0.1");
        insertParameter(Constants.PARAM_ACQ_HOST_PORT, 1500);
        insertParameter(Constants.PARAM_ACQ_HOST_TIMEOUT, 30000);
        insertParameter(Constants.PARAM_TERMINAL_ID, "POS000");
        insertParameter(Constants.PARAM_TERMINAL_MERCHANT_ID, "1010107");
        insertParameter(Constants.PARAM_TERMINAL_INTERFACE_ID, 1);
        insertParameter(Constants.PARAM_PIN_KEY, "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        insertParameter(Constants.PARAM_PIN_KEY_IS_USED, true);
        insertParameter(Constants.PARAM_ENCRYPTION_KEY, "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        insertParameter(Constants.PARAM_ENCRYPTION_KEY_IS_USED, false);
        insertParameter(Constants.PARAM_MAC_KEY, "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        insertParameter(Constants.PARAM_MAC_KEY_IS_USED, false);
        insertParameter(Constants.PARAM_MASTER_KEY, "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        insertParameter(Constants.PARAM_MASTER_KEY_IS_USED, false);
    }

    //============================================
    // MESSAGE FIELD
    //============================================    
    HashMap<Integer, IsoField> getFields(int interfaceid) throws Exception {
        final String sqlStr = "SELECT id, name, min_length, "
                + "max_length, length_qualifier, padding_char "
                + "FROM message_field WHERE interface_id = ?";
        HashMap<Integer, IsoField> fields = new HashMap<>();
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setInt(1, interfaceid);
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    IsoField field = new IsoField(rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6));
                    fields.put(field.getId(), field);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to select message fields: " + e.getMessage(), e);
            throw new Exception("Failed to select message fields: " + e.getMessage(), e);
        }
        return fields;
    }

    void updateField(int interfaceId, IsoField field) throws Exception {
        final String sqlStr = "UPDATE message_field SET name = ?, min_length = ?, "
                + "max_length = ?, length_qualifier = ?, padding_char = ? "
                + "WHERE id = ? AND interface_id = ?";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, field.getName());
            sql.setInt(2, field.getMinLength());
            sql.setInt(3, field.getMaxLength());
            sql.setInt(4, field.getLengthQualifier());
            sql.setString(5, String.valueOf(field.getPaddingChar()));
            sql.setInt(6, field.getId());
            sql.setInt(7, interfaceId);
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to update message field: " + e.getMessage(), e);
            throw new Exception("Failed to update message field: " + e.getMessage(), e);
        }
    }

    @Deprecated
    void insertTietoPosIsoFields(int interfaceId) throws Exception {
        insertField(1, interfaceId, "Bitmap Extended", 16);
        insertField(2, interfaceId, "Primary Account Number (PAN)", 1, 19, 2);
        insertField(3, interfaceId, "Processing code", 6);
        insertField(4, interfaceId, "Amount, transaction", 12);
        insertField(5, interfaceId, "Amount, reconciliation", 12);
        insertField(6, interfaceId, "Amount, cardholder billing", 12);
        insertField(7, interfaceId, "Date and time, transission", 10);
        insertField(8, interfaceId, "Amount, cardholder billing fee", 8);
        insertField(9, interfaceId, "Conversion rate, reconciliation", 8);
        insertField(10, interfaceId, "Conversion rate, cardholder billing", 8);
        insertField(11, interfaceId, "System trace audit number", 6);
        insertField(12, interfaceId, "Date and time, local transaction", 12);
        insertField(13, interfaceId, "Date, effective", 4);
        insertField(14, interfaceId, "Date, expiration", 4);
        insertField(15, interfaceId, "Date, settlement", 14);
        insertField(16, interfaceId, "Date, conversion", 4);
        insertField(17, interfaceId, "Date, capture", 4);
        insertField(18, interfaceId, "Merchant type", 4);
        insertField(19, interfaceId, "Country code, acquiring institution", 3);
        insertField(20, interfaceId, "Country code, primary account number", 3);
        insertField(21, interfaceId, "Country code, forwarding institution", 3);
        insertField(22, interfaceId, "Point of service data code", 12);
        insertField(23, interfaceId, "Card sequence number", 3);
        insertField(24, interfaceId, "Function code", 3);
        insertField(25, interfaceId, "Message reason code", 4);
        insertField(26, interfaceId, "Merchant Category Code (MCC);", 4);
        insertField(27, interfaceId, "Approval code length", 1);
        insertField(28, interfaceId, "Date reconciliation BDD", 6);
        insertField(29, interfaceId, "Reconciliation indicator BDDn", 3);
        insertField(30, interfaceId, "Original amount", 24);
        insertField(31, interfaceId, "Acquirer reference data", 1, 99, 2);
        insertField(32, interfaceId, "Acquiring institution identification code", 1, 11, 2);
        insertField(33, interfaceId, "Forwarding institution identification code", 1, 11, 2);
        insertField(34, interfaceId, "Primary account number, extended", 1, 28, 2);
        insertField(35, interfaceId, "Track 2", 1, 37, 2);
        insertField(36, interfaceId, "Track 3", 1, 104, 3);
        insertField(37, interfaceId, "Retrieval reference number", 12);
        insertField(38, interfaceId, "Approval code", 6);
        insertField(39, interfaceId, "Action code", 3);
        insertField(40, interfaceId, "Service code", 3);
        insertField(41, interfaceId, "Card acceptor terminal identifier", 8, " ");
        insertField(42, interfaceId, "Card acceptor identification code", 15);
        insertField(43, interfaceId, "Card acceptor name/location", 1, 99, 2);
        insertField(44, interfaceId, "Additional response data", 1, 99, 2);
        insertField(45, interfaceId, "Track 1", 1, 76, 2);
        insertField(46, interfaceId, "Amounts, fees", 1, 204, 3);
        insertField(47, interfaceId, "Additional data, national", 1, 999, 3);
        insertField(48, interfaceId, "Additional data, Private", 1, 999, 3);
        insertField(49, interfaceId, "Currency code, transaction", 3);
        insertField(50, interfaceId, "Currency code, reconciliation", 3);
        insertField(51, interfaceId, "Currency code, cardholder billing", 3);
        insertField(52, interfaceId, "Encrypted PIN", 16);
        insertField(53, interfaceId, "Security related control information", 1, 96, 2);
        insertField(54, interfaceId, "Amounts, additional", 1, 360, 3);
        insertField(55, interfaceId, "ICC Related Data", 1, 255, 3);
        insertField(56, interfaceId, "Original data", 1, 35, 2);
        insertField(57, interfaceId, "Authorization life cycle code", 3);
        insertField(58, interfaceId, "Authorizing agent institution identificationcode", 1, 11, 2);
        insertField(59, interfaceId, "Transport data", 1, 999, 3);
        insertField(60, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(61, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(62, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(63, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(64, interfaceId, "Message authentication code (MAC);", 16);
        insertField(65, interfaceId, "Reserved, ISO use", 16);
        insertField(66, interfaceId, "Amounts, original fees", 1, 204, 3);
        insertField(67, interfaceId, "Extended payment data", 2);
        insertField(68, interfaceId, "Country code, receiving institution", 3);
        insertField(69, interfaceId, "Country code, settlement institution", 3);
        insertField(70, interfaceId, "Country code, authorizing agent institution", 3);
        insertField(71, interfaceId, "Message number", 8);
        insertField(72, interfaceId, "Data record", 1, 999, 3);
        insertField(73, interfaceId, "Date, action", 6);
        insertField(74, interfaceId, "Credits, number", 10);
        insertField(75, interfaceId, "Credits reversal, number", 10);
        insertField(76, interfaceId, "Debits, number", 10);
        insertField(77, interfaceId, "Debits reversal, number", 10);
        insertField(78, interfaceId, "Transfer, number", 10);
        insertField(79, interfaceId, "Transfer, reversal number", 10);
        insertField(80, interfaceId, "Inquiries number", 10);
        insertField(81, interfaceId, "Authorization, number", 10);
        insertField(82, interfaceId, "Inquiries, reversal number", 10);
        insertField(83, interfaceId, "Payments, number", 10);
        insertField(84, interfaceId, "Payments, reversal number", 10);
        insertField(85, interfaceId, "Fee collections, number", 10);
        insertField(86, interfaceId, "Credits, amount", 16);
        insertField(87, interfaceId, "Credits reversal, amount", 16);
        insertField(88, interfaceId, "Debits, amount", 16);
        insertField(89, interfaceId, "Debits reversal, amounts", 16);
        insertField(90, interfaceId, "Authorizations, reversal number", 10);
        insertField(91, interfaceId, "Country code, transaction destinationinstitution", 3);
        insertField(92, interfaceId, "Country code, transaction originatorinstitution", 3);
        insertField(93, interfaceId, "Transaction destination institution identification code", 1, 11, 2);
        insertField(94, interfaceId, "Transaction originator institution identification code", 1, 11, 2);
        insertField(95, interfaceId, "Card issuer reference data", 1, 99, 2);
        insertField(96, interfaceId, "Key management data", 16, 998, 3);
        insertField(97, interfaceId, "Amount, net reconciliation", 17);
        insertField(98, interfaceId, "Payee", 25);
        insertField(99, interfaceId, "Settlement institution identification code", 1, 11, 2);
        insertField(100, interfaceId, "Receiving institution identification code", 1, 11, 2);
        insertField(101, interfaceId, "File name", 1, 17, 2);
        insertField(102, interfaceId, "Account Identification 1", 1, 28, 2);
        insertField(103, interfaceId, "Account Identification 2", 1, 28, 2);
        insertField(104, interfaceId, "Transaction description", 1, 100, 3);
        insertField(105, interfaceId, "Credits, chargeback amount", 16);
        insertField(106, interfaceId, "Debits, chargeback amount", 16);
        insertField(107, interfaceId, "Credits, chargeback number", 10);
        insertField(108, interfaceId, "Debits, chargeback number", 10);
        insertField(109, interfaceId, "Credits, fee amounts", 10);
        insertField(110, interfaceId, "Debits, fee amounts", 10);
        insertField(111, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(112, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(113, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(114, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(115, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(116, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(117, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(118, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(119, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(120, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(121, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(122, interfaceId, "4CSC value", 1, 999, 3);
        insertField(123, interfaceId, "CVC2 check ", 1, 999, 3);
        insertField(124, interfaceId, "Statement Data", 1, 999, 3);
        insertField(125, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(126, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(127, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(128, interfaceId, "Message authentication code (MAC);", 16);
    }

    @Deprecated
    void insertIso8583_1993Fields(int interfaceId) throws Exception {
        insertField(1, interfaceId, "Bitmap Extended", 8);
        insertField(2, interfaceId, "Primary Account Number (PAN)", 1, 19, 2);
        insertField(3, interfaceId, "Processing code", 6);
        insertField(4, interfaceId, "Amount, transaction", 12);
        insertField(5, interfaceId, "Amount, reconciliation", 12);
        insertField(6, interfaceId, "Amount, cardholder billing", 12);
        insertField(7, interfaceId, "Date and time, transission", 10);
        insertField(8, interfaceId, "Amount, cardholder billing fee", 8);
        insertField(9, interfaceId, "Conversion rate, reconciliation", 8);
        insertField(10, interfaceId, "Conversion rate, cardholder billing", 8);
        insertField(11, interfaceId, "System trace audit number", 6);
        insertField(12, interfaceId, "Date and time, local transaction", 12);
        insertField(13, interfaceId, "Date, effective", 4);
        insertField(14, interfaceId, "Date, expiration", 4);
        insertField(15, interfaceId, "Date, settlement", 6);
        insertField(16, interfaceId, "Date, conversion", 4);
        insertField(17, interfaceId, "Date, capture", 4);
        insertField(18, interfaceId, "Merchant type", 4);
        insertField(19, interfaceId, "Country code, acquiring institution", 3);
        insertField(20, interfaceId, "Country code, primary account number", 3);
        insertField(21, interfaceId, "Country code, forwarding institution", 3);
        insertField(22, interfaceId, "Point of service data code", 12);
        insertField(23, interfaceId, "Card sequence number", 3);
        insertField(24, interfaceId, "Function code", 3);
        insertField(25, interfaceId, "Message reason code", 4);
        insertField(26, interfaceId, "Merchant Category Code (MCC);", 4);
        insertField(27, interfaceId, "Approval code length", 1);
        insertField(28, interfaceId, "Date reconciliation BDD", 6);
        insertField(29, interfaceId, "Reconciliation indicator BDDn", 3);
        insertField(30, interfaceId, "Original amount", 24);
        insertField(31, interfaceId, "Acquirer reference data", 1, 99, 2);
        insertField(32, interfaceId, "Acquiring institution identification code", 1, 11, 2);
        insertField(33, interfaceId, "Forwarding institution identification code", 1, 11, 2);
        insertField(34, interfaceId, "Primary account number, extended", 1, 28, 2);
        insertField(35, interfaceId, "Track 2", 1, 37, 2);
        insertField(36, interfaceId, "Track 3", 1, 104, 3);
        insertField(37, interfaceId, "Retrieval reference number", 12);
        insertField(38, interfaceId, "Approval code", 6);
        insertField(39, interfaceId, "Action code", 3);
        insertField(40, interfaceId, "Service code", 3);
        insertField(41, interfaceId, "Card acceptor terminal identifier", 8);
        insertField(42, interfaceId, "Card acceptor identification code", 15);
        insertField(43, interfaceId, "Card acceptor name/location", 1, 99, 2);
        insertField(44, interfaceId, "Additional response data", 1, 99, 2);
        insertField(45, interfaceId, "Track 1", 1, 76, 2);
        insertField(46, interfaceId, "Amounts, fees", 1, 204, 3);
        insertField(47, interfaceId, "Additional data, national", 1, 999, 3);
        insertField(48, interfaceId, "Additional data, Private", 1, 999, 3);
        insertField(49, interfaceId, "Currency code, transaction", 3);
        insertField(50, interfaceId, "Currency code, reconciliation", 3);
        insertField(51, interfaceId, "Currency code, cardholder billing", 3);
        insertField(52, interfaceId, "Encrypted PIN", 16);
        insertField(53, interfaceId, "Security related control information", 1, 48, 2);
        insertField(54, interfaceId, "Amounts, additional", 1, 120, 3);
        insertField(55, interfaceId, "ICC Related Data", 1, 255, 3);
        insertField(56, interfaceId, "Original data", 1, 35, 2);
        insertField(57, interfaceId, "Authorization life cycle code", 3);
        insertField(58, interfaceId, "Authorizing agent institution identificationcode", 1, 11, 2);
        insertField(59, interfaceId, "Transport data", 1, 999, 3);
        insertField(60, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(61, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(62, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(63, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(64, interfaceId, "Message authentication code (MAC);", 8);
        insertField(65, interfaceId, "Reserved, ISO use", 8);
        insertField(66, interfaceId, "Amounts, original fees", 1, 204, 3);
        insertField(67, interfaceId, "Extended payment data", 2);
        insertField(68, interfaceId, "Country code, receiving institution", 3);
        insertField(69, interfaceId, "Country code, settlement institution", 3);
        insertField(70, interfaceId, "Country code, authorizing agent institution", 3);
        insertField(71, interfaceId, "Message number", 8);
        insertField(72, interfaceId, "Data record", 1, 999, 3);
        insertField(73, interfaceId, "Date, action", 6);
        insertField(74, interfaceId, "Credits, number", 10);
        insertField(75, interfaceId, "Credits reversal, number", 10);
        insertField(76, interfaceId, "Debits, number", 10);
        insertField(77, interfaceId, "Debits reversal, number", 10);
        insertField(78, interfaceId, "Transfer, number", 10);
        insertField(79, interfaceId, "Transfer, reversal number", 10);
        insertField(80, interfaceId, "Inquiries number", 10);
        insertField(81, interfaceId, "Authorization, number", 10);
        insertField(82, interfaceId, "Inquiries, reversal number", 10);
        insertField(83, interfaceId, "Payments, number", 10);
        insertField(84, interfaceId, "Payments, reversal number", 10);
        insertField(85, interfaceId, "Fee collections, number", 10);
        insertField(86, interfaceId, "Credits, amount", 16);
        insertField(87, interfaceId, "Credits reversal, amount", 16);
        insertField(88, interfaceId, "Debits, amount", 16);
        insertField(89, interfaceId, "Debits reversal, amounts", 16);
        insertField(90, interfaceId, "Authorizations, reversal number", 10);
        insertField(91, interfaceId, "Country code, transaction destinationinstitution", 3);
        insertField(92, interfaceId, "Country code, transaction originatorinstitution", 3);
        insertField(93, interfaceId, "Transaction destination institutionidentification code", 1, 11, 2);
        insertField(94, interfaceId, "Transaction originator institutionidentification code", 1, 11, 2);
        insertField(95, interfaceId, "Card issuer reference data", 1, 99, 2);
        insertField(96, interfaceId, "Key management data", 1, 999, 3);
        insertField(97, interfaceId, "Amount, net reconciliation", 17);
        insertField(98, interfaceId, "Payee", 25);
        insertField(99, interfaceId, "Settlement institution identification code", 1, 11, 2);
        insertField(100, interfaceId, "Receiving institution identification code", 1, 11, 2);
        insertField(101, interfaceId, "File name", 1, 17, 2);
        insertField(102, interfaceId, "Account Identification 1", 1, 28, 2);
        insertField(103, interfaceId, "Account Identification 2", 1, 28, 2);
        insertField(104, interfaceId, "Transaction description", 1, 100, 3);
        insertField(105, interfaceId, "Credits, chargeback amount", 16);
        insertField(106, interfaceId, "Debits, chargeback amount", 16);
        insertField(107, interfaceId, "Credits, chargeback number", 10);
        insertField(108, interfaceId, "Debits, chargeback number", 10);
        insertField(109, interfaceId, "Credits, fee amounts", 1, 84, 2);
        insertField(110, interfaceId, "Debits, fee amounts", 1, 84, 2);
        insertField(111, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(112, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(113, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(114, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(115, interfaceId, "Reserved, ISO use", 1, 999, 3);
        insertField(116, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(117, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(118, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(119, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(120, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(121, interfaceId, "Reserved, national use", 1, 999, 3);
        insertField(122, interfaceId, "4CSC value", 1, 999, 3);
        insertField(123, interfaceId, "CVC2 check ", 1, 999, 3);
        insertField(124, interfaceId, "Statement Data", 1, 999, 3);
        insertField(125, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(126, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(127, interfaceId, "Reserved, private use", 1, 999, 3);
        insertField(128, interfaceId, "Message authentication code (MAC);", 8);
    }

    @Deprecated
    void insertField(int id, int interfaceId, String name, int length) throws Exception {
        insertField(id, interfaceId, name, length, null);
    }

    @Deprecated
    void insertField(int id, int interfaceId, String name, int length, String paddingChar) throws Exception {
        insertField(id, interfaceId, name, length, length, 0, paddingChar);
    }

    @Deprecated
    void insertField(int id, int interfaceId, String name, int minLength, int maxLength,
            int lengthQualifier) throws Exception {
        insertField(id, interfaceId, name, minLength, maxLength, lengthQualifier, null);
    }

    @Deprecated
    void insertField(int id, int interfaceId, String name,
            int minLength, int maxLength, int lengthQualifier, String paddingChar) throws Exception {
        final String sqlStr = "INSERT INTO message_field (id, interface_id, name, min_length, "
                + "max_length, length_qualifier, padding_char) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setInt(1, id);
            sql.setInt(2, interfaceId);
            sql.setString(3, name);
            sql.setInt(4, minLength);
            sql.setInt(5, maxLength);
            sql.setInt(6, lengthQualifier);
            sql.setString(7, String.valueOf(paddingChar));
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to insert message field: " + e.getMessage(), e);
            throw new Exception("Failed to insert message field: " + e.getMessage(), e);
        }
    }

    void insertFields(HashMap<Integer, IsoField> fields, int interfaceId) throws Exception {
        for (Integer id : fields.keySet()) {
            IsoField field = fields.get(id);
            insertField(field.getId(), interfaceId, field.getName(), field.getMinLength(),
                    field.getMaxLength(), field.getLengthQualifier(), field.getPaddingChar());
        }
    }

    void deleteFields(int interfaceId) throws Exception {
        final String sqlStr = "DELETE FROM message_field WHERE interface_id = ?";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setInt(1, interfaceId);
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to delete message fields for interface " + interfaceId + ": " + e.getMessage(), e);
            throw new Exception("Failed to delete message fields for interface " + interfaceId + ": " + e.getMessage(), e);
        }
    }

    @Deprecated
    void deleteFields() throws Exception {
        final String sqlStr = "DELETE FROM message_field";
        try (Statement sql = this.jdbc.createStatement()) {
            sql.executeUpdate(sqlStr);
        } catch (SQLException e) {
            log.error("Failed to delete message fields: " + e.getMessage(), e);
            throw new Exception("Failed to delete message fields: " + e.getMessage(), e);
        }
    }

    //============================================
    // INTERFACE
    //============================================    
    List<Interface> getInterfaces() throws Exception {
        final String sqlStr = "SELECT id, name, description, field_count FROM interface";
        List<Interface> ifaces = new ArrayList<>();
        try (Statement sql = jdbc.createStatement();
                ResultSet rs = sql.executeQuery(sqlStr)) {
            while (rs.next()) {
                Interface iface = new Interface(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                ifaces.add(iface);
            }
        } catch (SQLException e) {
            log.error("Failed to select interfaces: " + e.getMessage(), e);
            throw new Exception("Failed to select interfaces: " + e.getMessage(), e);
        }
        return ifaces;
    }

    @Deprecated
    void insertDefaultInterfces() throws Exception {
        insertInterface(new Interface(1, "ISO8583-1993", "Standard ISO8583-1993 interface", 128));
        insertIso8583_1993Fields(1);
        insertInterface(new Interface(2, "POS_ISO", "Tieto POS_ISO interface", 128));
        insertTietoPosIsoFields(2);
    }

    void insertInterface(Interface iface) throws Exception {
        final String sqlStr = "INSERT INTO interface (id, name, description, field_count) VALUES (?, ?, ?, ?)";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setInt(1, iface.getId());
            sql.setString(2, iface.getName());
            sql.setString(3, iface.getDescription());
            sql.setInt(4, iface.getFieldCount());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to insert interface: " + e.getMessage(), e);
            throw new Exception("Failed to insert interface: " + e.getMessage(), e);
        }
    }

    void updateInterface(Interface iface) throws Exception {
        final String sqlStr = "UPDATE interface SET name = ?, description = ?, field_count = ? WHERE id = ?";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, iface.getName());
            sql.setString(2, iface.getDescription());
            sql.setInt(3, iface.getFieldCount());
            sql.setInt(4, iface.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to update interface: " + e.getMessage(), e);
            throw new Exception("Failed to update interface: " + e.getMessage(), e);
        }
    }

    void deleteinterface(int id) throws Exception {
        deleteFields(id);
        final String sqlStr = "DELETE FROM interface WHERE id = ?";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to delete interface: " + e.getMessage(), e);
            throw new Exception("Failed to delete interface: " + e.getMessage(), e);
        }
    }

    @Deprecated
    void deleteInterfaces() throws Exception {
        deleteFields();
        final String sqlStr = "DELETE FROM interface";
        try (Statement sql = this.jdbc.createStatement()) {
            sql.executeUpdate(sqlStr);
        } catch (SQLException e) {
            log.error("Failed to delete interfaces: " + e.getMessage(), e);
            throw new Exception("Failed to delete interfaces: " + e.getMessage(), e);
        }
    }

    //============================================
    // CARD KEYS
    //============================================
    List<CardKeySet> getCardKeySets() throws Exception {
        return null;
    }

    CardKeySet getCardKeySet(int id) throws Exception {
        return null;
    }

    void insertCardKeySet(CardKeySet cardKeySet) throws Exception {
        final String sqlStr = "INSERT INTO card_key_set (id, name, cvk, pvk, track1_mapping, track2_mapping) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setInt(1, cardKeySet.getId());
            sql.setString(2, cardKeySet.getName());
            sql.setString(3, cardKeySet.getCvk());
            sql.setString(4, cardKeySet.getPvk());
            sql.setString(5, cardKeySet.getTrack1Mapping());
            sql.setString(6, cardKeySet.getTrack2Mapping());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to insert card key set: " + e.getMessage(), e);
            throw new Exception("Failed to insert card key set: " + e.getMessage(), e);
        }
    }

    void updateCardKeySet(CardKeySet cardKeySet) throws Exception {
        final String sqlStr = "UPDATE card_key_set SET name = ?, cvk = ?, pvk = ?, "
                + "track1_mapping = ?, track2_mapping = ? WHERE id = ?";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, cardKeySet.getName());
            sql.setString(2, cardKeySet.getCvk());
            sql.setString(3, cardKeySet.getPvk());
            sql.setInt(4, cardKeySet.getId());
            sql.setString(5, cardKeySet.getTrack1Mapping());
            sql.setString(6, cardKeySet.getTrack2Mapping());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to update card key set: " + e.getMessage(), e);
            throw new Exception("Failed to update card key set: " + e.getMessage(), e);
        }
    }

    //============================================
    // SYSTEM
    //============================================
    List<Card> getCards() throws Exception {
        final String sqlStr = "SELECT card, key_set_id, track1, track2, description FROM card ORDER BY card";
        List<Card> cards = new ArrayList<>();
        try (Statement sql = jdbc.createStatement();
                ResultSet rs = sql.executeQuery(sqlStr)) {
            while (rs.next()) {
                Card card = new Card(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                cards.add(card);
            }
        } catch (SQLException e) {
            log.error("Failed to select cards: " + e.getMessage(), e);
            throw new Exception("Failed to select cards: " + e.getMessage(), e);
        }
        return cards;
    }

    Card getCard(String pan) throws Exception {
        final String sqlStr = "SELECT key_set_id, track1, track2, description FROM card WHERE card = ?";
        Card card = null;
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, pan);
            try (ResultSet rs = sql.executeQuery()) {
                rs.next();
                card = new Card(pan,
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            log.error("Failed to select card: " + e.getMessage(), e);
            throw new Exception("Failed to select card: " + e.getMessage(), e);
        }
        return card;
    }

    Receipt getReceipt(String trn) throws Exception {
        final String sqlStr = "SELECT card, amount, rrn, fld_039, date FROM transactions WHERE transaction_id = ?";
        Receipt rcp = null;
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, trn);
            try (ResultSet rs = sql.executeQuery()) {
                rs.next();
                rcp = new Receipt(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (SQLException e) {
            log.error("Failed to select receipt: " + e.getMessage(), e);
            throw new Exception("Failed to select card: " + e.getMessage(), e);
        }
        return rcp;
    }

    
    void deleteCard(String pan) throws Exception {
        final String sqlStr = "DELETE FROM card WHERE card = ?";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, pan);
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to delete card: " + e.getMessage(), e);
            throw new Exception("Failed to delete card: " + e.getMessage(), e);
        }
    }

    void updateCard(Card card) throws Exception {
        final String sqlStr = "update card set card=?, key_set_id=?, track1=?, track2=?, description=?  WHERE card = ?";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, card.getCard());
            sql.setInt(2, card.getKeySetid());
            sql.setString(3, card.getTrack1());
            sql.setString(4, card.getTrack2());
            sql.setString(5, card.getDescription());
            sql.setString(6, card.getCard());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to update card: " + e.getMessage(), e);
            throw new Exception("Failed to update card: " + e.getMessage(), e);
        }
    }

    void insertCard(Card card) throws Exception {
        final String sqlStr = "INSERT INTO card (card, key_set_id, track1, track2, description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, card.getCard());
            sql.setInt(2, card.getKeySetid());
            sql.setString(3, card.getTrack1());
            sql.setString(4, card.getTrack2());
            sql.setString(5, card.getDescription());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to insert card: " + e.getMessage(), e);
            throw new Exception("Failed to insert card: " + e.getMessage(), e);
        }
    }
    
    //============================================
    // EMV 
    //============================================
    public List<EmvAid> getAids () 
            throws Exception {
        final String sqlStr = "SELECT rid, pix, name, payment_system FROM emv_aid ORDER BY rid || pix";
        List<EmvAid> aids = new ArrayList<>();
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    EmvAid aid = new EmvAid(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        Constants.PaymentSystem.toPaymentSystem(rs.getInt(4)));
                    aids.add(aid);
                }
            } 
        } catch (SQLException e) {
            log.error("Failed to select AIDs: " + e.getMessage(), e);
            throw new Exception("Failed to select AIDs: " + e.getMessage(), e);
        }
        return aids;
    }
    
    public EmvAid getAid (String rid, String pix) 
            throws Exception {
        return getAid(rid + pix);
    }
    
    public EmvAid getAid (String aidId) 
            throws Exception {
        final String sqlStr = "SELECT rid, pix, name, payment_system FROM emv_aid WHERE rid || pix = ?";
        EmvAid aid = null;
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, aidId);
            try (ResultSet rs = sql.executeQuery()) {
                rs.next();
                aid = new EmvAid(rs.getString(1), 
                    rs.getString(2),
                    rs.getString(3),
                    Constants.PaymentSystem.toPaymentSystem(rs.getInt(4)));
            }
        } catch (SQLException e) {
            log.error("Failed to select AID " + aidId + ": " + e.getMessage(), e);
            throw new Exception("Failed to select AID " + aidId + ": " + e.getMessage(), e);
        }
        return aid;
    }
    
    public void insertAid (EmvAid aid) 
            throws Exception {
        final String sqlStr = "INSERT INTO emv_aid (rid, pix, name, payment_system) VALUES (?, ?, ?, ?)";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
             sql.setString(1, aid.getRid());
             sql.setString(2, aid.getPix());
             sql.setString(3, aid.getName());
             sql.setInt(4, aid.getPaymentSystem().getId());
             sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to insert AID: " + e.getMessage(), e);
            throw new Exception("Failed to insert AID: " + e.getMessage(), e);
        }
    }
    
    public void updateAid (EmvAid aid) 
            throws Exception {
        final String sqlStr = "UPDATE emv_aid SET name = ?, payment_system = ? WHERE rid = ? AND pix = ?";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, aid.getName());
            sql.setInt(2, aid.getPaymentSystem().getId());
            sql.setString(3, aid.getRid());
            sql.setString(4, aid.getPix());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to update AID: " + e.getMessage(), e);
            throw new Exception("Failed to update AID: " + e.getMessage(), e);
        }
    }
    
    public void deleteAid (EmvAid aid) 
            throws Exception {
        final String sqlStr = "DELETE FROM emv_aid WHERE rid = ? AND pix = ?";
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, aid.getRid());
            sql.setString(2, aid.getPix());
            sql.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to delete AID: " + e.getMessage(), e);
            throw new Exception ("Failed to delete AID: " + e.getMessage(), e);
        }
    }
    
    //============================================
    // EMV TAGS
    //============================================
    public List<EmvTag> getEmvTags () 
            throws Exception {
        return getEmvTags(null);
    }
    
    public List<EmvTag> getEmvTags (Constants.PaymentSystem paymentSystem) 
            throws Exception {
        final StringBuilder sqlStr = new StringBuilder("SELECT tag, name FROM ");
        if (paymentSystem == null) {
            sqlStr.append("emv_tag ");
        } else {
            switch (paymentSystem) {
                case VISA_INTERNATIONAL: sqlStr.append("emv_tag_visa "); break;
                case MASTERCARD_WORLDWIDE: sqlStr.append("emv_tag_mastercard "); break;
                case AMERICAN_EXPRESS: sqlStr.append("emv_tag_amex "); break;
            }
        }
        sqlStr.append("ORDER BY tag");
        List<EmvTag> tags = new ArrayList<>();
        try (Statement sql = this.jdbc.createStatement()) {
            try (ResultSet rs = sql.executeQuery(sqlStr.toString())) {
                while (rs.next()) {
                    tags.add(new EmvTag(rs.getString(1), rs.getString(2)));
                }
            }
        } catch (SQLException e) {
            log.error("Failed to select EMV tags: " + e.getMessage(), e);
            throw new Exception("Failed to select EMV tags: " + e.getMessage(), e);
        }
        return tags;
    }
    
    //============================================
    // EMV DATA
    //============================================
    public List<EmvTag> getEmvData(EmvAid aid) throws Exception {
        String sqlStr = "SELECT a.tag, b.name FROM emv_data a JOIN emv_tag b "
                + "ON a.tag = b.tag WHERE aid = ?";
        List<EmvTag> tags = new ArrayList<>();
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, aid.getAid());
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    tags.add(new EmvTag(rs.getString(1), rs.getString(2)));
                }
            }
        } catch (SQLException e) {
            log.error("Failed to select EMV data for " + aid.getAid() + ": " + e.getMessage(), e);
            throw new Exception("Failed to select EMV data for " + aid.getAid() + ": " + e.getMessage(), e);
        }
        return tags;
    }
    
    //============================================
    // SYSTEM
    //============================================
    int getNextSequence(String objectName) throws Exception {
        final String sqlStr = "SELECT seq, count(*) AS cnt FROM sqlite_sequence WHERE UPPER(name) = UPPER(?)";
        int nextSequence = 0;
        try (PreparedStatement sql = this.jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, objectName);
            try (ResultSet rs = sql.executeQuery()) {
                rs.next();
                if (rs.getInt(2) == 0) {
                    throw new Exception("No sequence with name " + objectName + " found");
                }
                nextSequence = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            log.error("Failed to select sequence: " + e.getMessage(), e);
            throw new Exception("Failed to select sequence: " + e.getMessage(), e);
        }
        return nextSequence;
    }
    
    //============================================
    // SQL
    //============================================
    
    List<DbSchema> getDbSchemas () throws Exception {
        List<DbSchema> schemas = new ArrayList<>();
        try (Statement sql = this.jdbc.createStatement();
                ResultSet rs = sql.executeQuery("PRAGMA database_list")) {
            while (rs.next()) {
                schemas.add(new DbSchema(rs.getString("name"), rs.getString("file")));
            }
        } catch (SQLException e) {
            log.error("Failed to select database schemas: " + e.getMessage(), e);
            throw new Exception("Failed to select database schemas: " + e.getMessage(), e);
        }
        return schemas;
    }
    
    List<DbObject> getDbTables () throws Exception {
        return getDbObjects(DbObjectType.TABLE);
    }
    
    List<DbObject> getDbIndexes () throws Exception {
        return getDbObjects(DbObjectType.INDEX);
    }
    
    List<DbObject> getDbTriggers () throws Exception {
        return getDbObjects(DbObjectType.TRIGGER);
    }
    
    List<DbObject> getDbViews () throws Exception {
        return getDbObjects(DbObjectType.VIEW);
    }
    
    List<DbObject> getDbObjects () throws Exception {
        return getDbObjects(null);
    }
    
    List<DbObject> getDbObjects (DbObjectType type) throws Exception {
        final String sqlStrAllObjects = "SELECT name, type, tbl_name, rootPage, sql FROM sqlite_master ORDER BY name";
        final String sqlStrTypedObjects = "SELECT name, type, tbl_name, rootPage, sql FROM sqlite_master WHERE type = ? ORDER BY name";
        List<DbObject> objects = new ArrayList<>();
        try (PreparedStatement sql = this.jdbc.prepareStatement((type == null) ? sqlStrAllObjects : sqlStrTypedObjects)) {
            if (type != null) {
                sql.setString(1, type.getType());
            }
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    DbObject object = new DbObject(
                            rs.getString(1),
                            DbObjectType.toType(rs.getString(2)),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5)
                    );
                    objects.add(object);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to select database tables: " + e.getMessage(), e);
            throw new Exception("Failed to select database objects of type " + type + ": " + e.getMessage(), e);            
        }
        return objects;
    }
    
    class DbSchema {
        
        private final String name;
        private final String fullfilePath;
        private String file;
        
        DbSchema (String name, String fullFilePath) {
            this.name = name;
            this.fullfilePath = fullFilePath;
            if (this.fullfilePath != null && !this.fullfilePath.isEmpty()) {
                this.file = new File(this.fullfilePath).getName();
            }
        }
        
        String getName () {
            return this.name;
        }
        
        String getFullFilePath () {
            return this.fullfilePath;
        }
        
        String getFile () {
            return this.file;
        }
    }
    
    enum DbObjectType {
        TABLE(0, "table"),
        INDEX(1, "index"),
        TRIGGER(2, "trigger"),
        VIEW(3, "view"),
        UNKNOWN(99, "unknown");
        
        private final int value;
        private String type;
        
        private DbObjectType (int value, String type) {
            this.value = value;
            this.type = type;
        }
        
        int getValue () {
            return this.value;
        }
        
        String getType () {
            return this.type;
        }
        
        static DbObjectType toType (String type) {
            switch (type) {
                case "table": return TABLE;
                case "index": return INDEX;
                case "trigger": return TRIGGER;
                case "view": return VIEW;
                default: return UNKNOWN;
            }
        }
    }
    
    class DbObject {
        
        private final String name;
        private final DbObjectType type;
        private final String tblName;
        private final int rootPage;
        private final String sql;

        public DbObject(String name, DbObjectType type, String tblName, int rootPage, String sql) {
            this.name = name;
            this.type = type;
            this.tblName = tblName;
            this.rootPage = rootPage;
            this.sql = sql;
        }

        public String getName() {
            return name;
        }

        public DbObjectType getType() {
            return type;
        }

        public String getTblName() {
            return tblName;
        }

        public int getRootPage() {
            return rootPage;
        }

        public String getSql() {
            return sql;
        }
        
    }
}
