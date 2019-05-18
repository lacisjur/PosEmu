package az.pashabank.posemu;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Database {

    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DEFAULT_DBF_FILE = "posemu.sqlite3";
    private final EventLog log = EventLog.getInstance();

    private static Database db;

    private Connection jdbc;

    private Database() {
    }

    static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    void connect() throws ClassNotFoundException, FileNotFoundException, SQLException {
        connect(DEFAULT_DBF_FILE);
    }

    void connect(String dbfFile) throws ClassNotFoundException, FileNotFoundException, SQLException {
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

    void disconnect() throws SQLException {
        if (this.jdbc != null) {
            this.jdbc.close();
        }
        log.info("Database disconnected");
    }

    boolean getBooleanParameter(String parameter) throws Exception {
        return Boolean.parseBoolean(getParameter(parameter));
    }

    int getIntParameter(String parameter) throws Exception {
        return Integer.parseInt(getParameter(parameter));
    }

    String getParameter(String parameter) throws Exception {
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

    //============================================
    // MESSAGE FIELD
    //============================================    
    List<IsoField> getFields(int interfaceid) throws Exception {
        final String sqlStr = "SELECT id, name, min_length, "
                + "max_length, length_qualifier, padding_char "
                + "FROM message_field WHERE interface_id = ?";
        List<IsoField> fields = new ArrayList<>();
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
                    fields.add(field);
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

    void insertFields(List<IsoField> fields, int interfaceId) throws Exception {
        for (IsoField field : fields) {
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
}
