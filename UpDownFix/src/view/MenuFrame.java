/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package view;

import model.DB;
import model.TableScore;
import viewmodel.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    // Deklarasi variabel
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTable scoreTable;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JButton playButton;
    private JButton exitButton;
    private DB database;
    private TableScore tscore;
    private String username;
    private Game game;

    // Konstruktor
    public MenuFrame() {
        this.setSize(640, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        initComponents();

        this.username = "";

        try {
            this.tscore = new TableScore();
            scoreTable.setModel(tscore.getTableData());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Method untuk menginisialisasi komponen UI
    private void initComponents() {
        // Inisialisasi komponen UI
        mainPanel = new JPanel();
        titleLabel = new JLabel("UP DOWN", SwingConstants.CENTER);
        scoreTable = new JTable();
        usernameField = new JTextField(20);
        usernameLabel = new JLabel("Username:");
        playButton = new JButton("Play");
        exitButton = new JButton("Exit");

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JScrollPane scrollPane = new JScrollPane(scoreTable);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(titleLabel)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(usernameLabel)
                                .addComponent(usernameField))
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(playButton)
                                .addComponent(exitButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(usernameLabel)
                                .addComponent(usernameField))
                        .addComponent(scrollPane)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(playButton)
                                .addComponent(exitButton))
        );

        // Menambahkan action listener ke tombol
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for play button
                handlePlayButtonAction();
            }
        });

        scoreTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickUser(evt);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for exit button
                System.exit(0);
            }
        });

        this.add(mainPanel);
    }

    // Method untuk menangani aksi tombol play
    public void handlePlayButtonAction() {
        String username = usernameField.getText().trim();

        if (username.isEmpty()) {
            showUsernameError();
        } else {
            // Sembunyikan MenuFrame sebelum menampilkan layar game
            JFrame menuFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            menuFrame.setVisible(false);

            JFrame gameFrame = new JFrame("Up Down");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setSize(640, 640);
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setResizable(false);

            GameFrame gameFrame1 = new GameFrame();
            gameFrame.add(gameFrame1);
            gameFrame.pack();
            gameFrame1.requestFocus();
            gameFrame.setVisible(true);

            gameFrame1.getGame().setUsername(username);
        }
    }

    // Method untuk menangani klik pada tabel skor dan menampilkan user yg diklik
    private void clickUser(java.awt.event.MouseEvent evt) {
        int row = scoreTable.getSelectedRow();
        this.username = scoreTable.getModel().getValueAt(row, 0).toString();
        usernameField.setText(this.username);
    }

    // Method menampilkan error message ketika username kosong
    public void showUsernameError() {
        JOptionPane.showMessageDialog(this, "Masukkan username terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Method untuk me-refresh score dalam tabel
    public void refreshScoreTable() {
        try {
            tscore = new TableScore();
            scoreTable.setModel(tscore.getTableData());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]) {
        new MenuFrame().setVisible(true);
    }

    // Getter dan setter
    public JPanel getMainPanel() { return mainPanel; }
    public JButton getPlayButton() { return playButton; }
    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JTable getScoreTable() {
        return scoreTable;
    }

    public void setScoreTable(JTable scoreTable) {
        this.scoreTable = scoreTable;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public void setPlayButton(JButton playButton) {
        this.playButton = playButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public DB getDatabase() {
        return database;
    }

    public void setDatabase(DB database) {
        this.database = database;
    }

    public TableScore getTscore() {
        return tscore;
    }

    public void setTscore(TableScore tscore) {
        this.tscore = tscore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
