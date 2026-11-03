package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        // 1. Siapkan data (layer Model)
        CharacterManager manager = new CharacterManager();
        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4));
        manager.addCharacter(new Character("Gatotkaca", CharacterType.WARRIOR, 7)); // level tinggi
        manager.addCharacter(new Character("Morgana", CharacterType.MAGE, 1));      // level rendah
        manager.addCharacter(new Character("Ranger", CharacterType.ARCHER, 6));
        manager.addCharacter(new Character("Lancelot", CharacterType.WARRIOR, 8));
        manager.addCharacter(new Character("Digo", CharacterType.MAGE, 7));
        manager.addCharacter(new Character("Viera", CharacterType.WARRIOR, 2));


        // 2. Jalankan GUI di Event Dispatch Thread
           SwingUtilities.invokeLater(() -> {
       MainWindow window = new MainWindow(manager.getAllCharacters());
       window.setVisible(true);
    });
    }
}