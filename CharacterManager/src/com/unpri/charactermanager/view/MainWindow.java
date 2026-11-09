package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private ArrayList<Character> characterList;     // data dari Main.java (Model)

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager " + AppTheme.APP_VERSION);
        this.characterList = characterList;

        initComponents();                           // 1. susun semua area

        pack();                                     // 2. ukuran awal = preferred size isi
        setMinimumSize(new Dimension(560, 380));    // 3. batas terkecil yang masih rapi
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                // 4. pusatkan SETELAH pack()
    }

    private void initComponents() {
        getContentPane().setBackground(AppTheme.BG_MAIN);
        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH);
        add(createListArea(),    BorderLayout.CENTER);
        add(createLegendPanel(), BorderLayout.EAST);
        add(createStatusPanel(), BorderLayout.SOUTH);
    }

    /** NORTH: header dan panel statistik. */
    private JPanel createTopPanel() {
    JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBackground(AppTheme.BG_MAIN);

        top.add(createHeaderPanel());
        top.add(createStatisticsPanel());

    return top;
    }

    /** Panel statistik dengan susunan 2 baris dan 2 kolom. */
    private JPanel createStatisticsPanel() {
    JPanel stats = new JPanel(new GridLayout(2, 2, 8, 8));
    stats.setBackground(AppTheme.BG_MAIN);
    stats.setBorder(BorderFactory.createEmptyBorder(4, 16, 8, 16));
    // Statistik 1: Total karakter
    JLabel total = new JLabel("Total Karakter: " + characterList.size());

    // Statistik 2: Rata-rata level
    int totalLevel = 0;
    for (Character c : characterList) {
    totalLevel += c.getLevel();
    }
    double average = characterList.isEmpty() ? 0 :
        (double) totalLevel / characterList.size();

    JLabel avg = new JLabel(String.format("Rata-rata Level: %.1f", average));

    // Statistik 3: Level tertinggi
    int highest = 0;
    for (Character c : characterList) {
    highest = Math.max(highest, c.getLevel());
    }
    JLabel max = new JLabel("Level Tertinggi: " + highest);

    // Statistik 4: Jumlah jenis role
    JLabel roles = new JLabel("Jenis Role: " + CharacterType.values().length);

    // Atur tampilan setiap label
    JLabel[] items = {total, avg, max, roles};

    for (JLabel item : items) {
    item.setFont(AppTheme.FONT_BODY);
    item.setForeground(AppTheme.TEXT_MAIN);
    item.setBackground(AppTheme.BG_STATUS);
    item.setOpaque(true);
    item.setBorder(AppTheme.CARD_BORDER);
    stats.add(item);
    }

    return stats;
    }

    /** NORTH: judul di kiri, jumlah karakter di kanan. */
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AppTheme.BG_MAIN);
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));

        JLabel title = new JLabel("Character Manager");
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.TEXT_MAIN);

        JLabel count = new JLabel(characterList.size() + " karakter");
        count.setFont(AppTheme.FONT_BODY);
        count.setForeground(AppTheme.GOLD);

        header.add(title, BorderLayout.WEST);
        header.add(count, BorderLayout.EAST);
        return header;
    }

    /** CENTER: kartu karakter di dalam JScrollPane. */
    private JScrollPane createListArea() {
        JPanel cards = new JPanel(new GridLayout(0, 1, 0, 8));
        cards.setBackground(AppTheme.BG_MAIN);
        for (Character c : characterList) {
            cards.add(createCharacterLabel(c));
        }

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(AppTheme.BG_MAIN);
        holder.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 8));
        holder.add(cards, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(AppTheme.BG_MAIN);
        scroll.setPreferredSize(new Dimension(460, 280));
        return scroll;
    }

    /** Satu kartu karakter (v0.4 + teks emas untuk level tinggi). */
    private JLabel createCharacterLabel(Character c) {
        String info = c.getName() + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();

        JLabel lbl = new JLabel(info);
        lbl.setFont(AppTheme.FONT_BODY);
        lbl.setForeground(AppTheme.TEXT_LIGHT);
        lbl.setBackground(AppTheme.colorFor(c.getType()));
        lbl.setOpaque(true);
        lbl.setBorder(AppTheme.CARD_BORDER);
        lbl.setToolTipText(AppTheme.describe(c.getType()));
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Mini-challenge TP10: level tinggi tampil emas dan tebal
        if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) {
            lbl.setForeground(AppTheme.GOLD);
            lbl.setFont(AppTheme.FONT_BODY.deriveFont(Font.BOLD));
        }
        return lbl;
    }

    /** EAST: legenda warna per role, ditumpuk vertikal dengan BoxLayout. */
    private JPanel createLegendPanel() {
        JPanel legend = new JPanel();
        legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));
        legend.setBackground(AppTheme.BG_MAIN);
        legend.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 16));

        JLabel head = new JLabel("Legenda Role");
        head.setFont(AppTheme.FONT_BODY.deriveFont(Font.BOLD));
        head.setForeground(AppTheme.TEXT_MAIN);
        legend.add(head);
        legend.add(Box.createVerticalStrut(8));

        for (CharacterType type : CharacterType.values()) {
            JLabel chip = new JLabel(type.name());
            chip.setOpaque(true);
            chip.setBackground(AppTheme.colorFor(type));
            chip.setForeground(AppTheme.TEXT_LIGHT);
            chip.setFont(AppTheme.FONT_MONO);
            chip.setBorder(AppTheme.CARD_BORDER);
            chip.setToolTipText(AppTheme.describe(type));
            legend.add(chip);
            legend.add(Box.createVerticalStrut(6));
        }
        legend.add(Box.createVerticalGlue());
        return legend;
    }

    /** SOUTH: bar status, teks di kiri dan versi di kanan. */
    private JPanel createStatusPanel() {
        JPanel status = new JPanel(new BorderLayout());
        status.setBackground(AppTheme.BG_STATUS);
        status.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));

        JLabel left = new JLabel("Total: " + characterList.size() + " karakter  |  Siap");
        left.setFont(AppTheme.FONT_MONO);
        left.setForeground(AppTheme.TEXT_MUTED);

        JLabel right = new JLabel(AppTheme.APP_VERSION);
        right.setFont(AppTheme.FONT_MONO);
        right.setForeground(AppTheme.TEXT_MUTED);

        status.add(left, BorderLayout.WEST);
        status.add(right, BorderLayout.EAST);
        return status;
    }
}