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
    
    private static Database db;
    
    private Connection jdbc;
    
    private Database () { }
    
    static Database getInstance () {
        if (db == null) {
            db = new Database();
        }
        return db;
    }
    
    void connect () throws ClassNotFoundException, FileNotFoundException, SQLException {
        connect(DEFAULT_DBF_FILE);
    }
    
    void connect (String dbfFile) throws ClassNotFoundException, FileNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        if (!dbfFile.equals(DEFAULT_DBF_FILE)) {
            if (!new File(dbfFile).exists()) {
                throw new FileNotFoundException("Custom DBF file " + dbfFile + " not found");
            }
        }
        this.jdbc = DriverManager.getConnection("jdbc:sqlite:" + dbfFile);
    }
    
    void disconnect () throws SQLException {
        if (this.jdbc != null) {
            this.jdbc.close();
        }
    }
    
    boolean getBooleanParameter (String parameter) throws Exception {
        return Boolean.parseBoolean(getParameter(parameter));
    }
    
    String getParameter (String parameter) throws Exception {
        String sqlStr = "SELECT value FROM parameter WHERE name = ?";
        String value = null;
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, parameter);
            try (ResultSet rs = sql.executeQuery()) {
                rs.next();
                value = rs.getString(1);
            } 
        } catch (SQLException e) {
            throw new Exception("Failed to select parameter: " + e.getMessage(), e);
         }
        return value;
    }
    
    List<Currency> getCurrencies () throws Exception {
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
            throw new Exception("Failed to select currencies: " + e.getMessage(), e);
        } 
        return currencies;
    }
    
    void addCurrency (Currency currency) throws Exception {
        String sqlStr = "INSERT INTO currency (ccy_num_code, ccy_alpha_code, ccy_name) VALUES (?, ?, ?)";
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, currency.getCurrencyNum());
            sql.setString(2, currency.getCurrencyAlpha());
            sql.setString(3, currency.getCurrencyName());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Failed to add currency: " + e.getMessage(), e);
        }
    }
    
    void deleteCurrency (Currency currency) throws Exception {
        String sqlStr = "DELETE FROM currency WHERE ccy_num_code = ?";
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, currency.getCurrencyNum());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Failed to delete currency: " + e.getMessage(), e);
        }
    }
    
    void updateParameter (String parameter, boolean value) throws Exception {
        updateParameter(parameter, (value) ? 1 : 0);
    }
    
    void updateParameter (String parameter, int value) throws Exception {
        updateParameter(parameter, Integer.toString(value));
    }
    
    void updateParameter (String parameter, String value) throws Exception {
        final String sqlStr = "UPDATE parameter SET value = ? WHERE name = ?";
        try (PreparedStatement sql = jdbc.prepareStatement(sqlStr)) {
            sql.setString(1, value);
            sql.setString(2, parameter);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Failed to update parameter value: " + e.getMessage(), e);
        }
    }
    
}
