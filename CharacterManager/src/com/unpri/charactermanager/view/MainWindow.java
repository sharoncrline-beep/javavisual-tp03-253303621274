package com.unpri.charactermanager.view;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.unpri.charactermanager.model.Character;

/**
 * Jendela utama aplikasi Character Manager.
 * Menampilkan daftar karakter yang diterima dari luar (bukan dibuat sendiri).
 */
public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private ArrayList<Character> characterList;
    private double averageLevel;   // sudah dihitung oleh Model, View hanya menampilkan

    public MainWindow(ArrayList<Character> characterList, double averageLevel) {
        super("Character Manager v0.3");
        this.characterList = characterList;
        this.averageLevel = averageLevel;

        initComponents();

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        mainPanel = new JPanel();

        JLabel titleLabel = new JLabel("=== Daftar Karakter (" + characterList.size() + ") ===");
        mainPanel.add(titleLabel);

        for (Character c : characterList) {
            String info = c.getName() + " | " + c.getType() +
                          " | Lv." + c.getLevel() +
                          " | HP:" + c.getHitPoints() +
                          " | ATK:" + c.getAttackPower();
            mainPanel.add(new JLabel(info));
        }

        // Mini-challenge: statistik di bagian bawah daftar
        JLabel statLabel = new JLabel("Rata-rata Level: " + String.format("%.1f", averageLevel));
        mainPanel.add(statLabel);

        add(mainPanel);   // harus baris TERAKHIR
    }
}