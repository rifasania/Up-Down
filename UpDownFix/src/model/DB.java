/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package model;

import java.sql.*;
public class DB {
    // String koneksi ke database
    private String connAdress = "jdbc:mysql://localhost/tmd_dpbo?user=root&password=";
    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stmt = null;

    // Konstruktor
    public DB() throws Exception, SQLException {
        try {
            // Memuat driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            // Membuat koneksi ke database
            conn = DriverManager.getConnection(connAdress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        } catch (SQLException es) {
            throw es;
        }
    }

    // Method untuk membuat query
    public void createQuery(String Query) throws Exception, SQLException {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            if (stmt.execute(Query)) {
                rs = stmt.getResultSet();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    // Method untuk membuat update
    public void createUpdate(String Query) throws Exception, SQLException {
        try {
            stmt = conn.createStatement();
            int res = stmt.executeUpdate(Query);
        } catch (SQLException e) {
            throw e;
        }
    }

    // Method untuk mendapatkan hasil query
    public ResultSet getResult() throws Exception {
        ResultSet Temp = null;
        try {
            return rs;
        } catch (Exception e) {
            return Temp;
        }
    }

    // Getter dan setter

    public String getConnAdress() {
        return connAdress;
    }

    public void setConnAdress(String connAdress) {
        this.connAdress = connAdress;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
}
