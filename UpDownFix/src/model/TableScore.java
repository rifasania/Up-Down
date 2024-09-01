/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package model;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class TableScore extends DB {
    private String tableName;

    // Konstruktor
    public TableScore() throws Exception, SQLException {
        super();
        this.tableName = "tscore";
    }

    // Method untuk mengambil semua data dari tabel
    public void select() {
        try {
            String query = "SELECT * FROM " + this.tableName;
            createQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // Method untuk mengambil data berdasarkan username
    public void select(String username) {
        try {
            String query = "SELECT * FROM " + this.tableName + " WHERE username='" + username + "'";
            createQuery(query);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    // Method untuk menyimpan atau memperbarui skor
    public void insertUpdate(String username, int score, int up, int down) {
        boolean update = false;
        int previousScore = 0;
        try {
            // Cek apakah username sudah ada
            TableScore temp = new TableScore();
            temp.select(username);
            if (temp.getResult().next()) {
                update = true;
                previousScore = temp.getResult().getInt("score"); // Mendapatkan skor sebelumnya
            } else {
                update = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Jika username belum ada, lakukan insert
        if (!update) {
            try {
                String query = "INSERT INTO " + this.tableName + " VALUES('" + username + "', " + Integer.toString(score) + ", " + Integer.toString(up) + ", " + Integer.toString(down) + ")";
                createUpdate(query);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        // Jika username sudah ada dan skor baru lebih tinggi, lakukan update
        else if (update && score > previousScore) {
            try {
                String query = "UPDATE " + this.tableName + " SET score=" + score + ", up=" + up + ", down=" + down + " WHERE username='" + username + "'";
                createUpdate(query);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Method untuk mendapatkan data tabel dalam bentuk DefaultTableModel
    public DefaultTableModel getTableData() {
        DefaultTableModel tableData = null;

        try {
            Object[] column = {"USERNAME", "SCORE", "UP", "DOWN"};
            tableData = new DefaultTableModel(null, column);

            String query = "SELECT * FROM " + this.tableName + " ORDER BY score DESC";
            this.createQuery(query);

            while(this.getResult().next()) {
                Object[] row = new Object[4];

                row[0] = this.getResult().getString(1);
                row[1] = this.getResult().getString(2);
                row[2] = this.getResult().getString(3);
                row[3] = this.getResult().getString(4);
                tableData.addRow(row);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return tableData;
    }

    // Method untuk mendapatkan skor tertinggi dari seorang pemain (player)
    public int getHighScore(String username) {
        int highScore = 0;
        try {
            String query = "SELECT score FROM " + this.tableName + " WHERE username='" + username + "' ORDER BY score DESC LIMIT 1";
            createQuery(query);
            if (getResult().next()) {
                highScore = getResult().getInt("score");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return highScore;
    }

    // getter dan setter

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
