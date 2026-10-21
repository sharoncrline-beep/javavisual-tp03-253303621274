package com.unpri.charactermanager.model;

import java.util.ArrayList;

/**
 * Mengelola koleksi Character: tambah, cari, hapus, dan statistik dasar.
 * Menerapkan Composition: CharacterManager "has-a" ArrayList<Character>.
 */
public class CharacterManager {

    private ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();   // list kosong saat manager pertama dibuat
    }

    /**
     * Menambahkan karakter baru ke dalam list.
     * Menolak jika sudah ada karakter dengan nama yang sama.
     * @return true jika berhasil ditambahkan, false jika ditolak karena duplikat
     */
    public boolean addCharacter(Character character) {
        if (findByName(character.getName()) != null) {   // cek dulu apakah nama sudah ada
            System.out.println("[GAGAL] Karakter dengan nama '" + character.getName() + "' sudah ada!");
            return false;
        }
        characterList.add(character);
        System.out.println("[OK] Karakter '" + character.getName() + "' berhasil ditambahkan.");
        return true;
    }

    /**
     * Mencari karakter berdasarkan nama.
     * @return object Character jika ditemukan, null jika tidak ada
     */
    public Character findByName(String name) {
        for (Character c : characterList) {         // iterasi setiap elemen di list
            if (c.getName().equals(name)) {          // bandingkan ISI nama, bukan ==
                return c;                             // kembalikan referensi yang ditemukan
            }
        }
        return null;                                  // tidak ditemukan setelah seluruh list diperiksa
    }
        /**
     * Menghapus karakter dari list berdasarkan nama.
     * @return true jika berhasil dihapus, false jika tidak ditemukan
     */
    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target == null) {
            System.out.println("[GAGAL] Hapus: karakter '" + name + "' tidak ditemukan.");
            return false;
        }
        characterList.remove(target);                 // ArrayList.remove(Object) memakai equals() di balik layar
        System.out.println("[HAPUS] Karakter '" + name + "' berhasil dihapus.");
        return true;
    }

    public int getTotalCharacters() {
        return characterList.size();
    }

    /**
     * Mengembalikan SALINAN list, bukan referensi asli,
     * agar pemanggil tidak bisa mengubah data internal manager secara langsung.
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);        // defensive copy
    }
        /**
     * Menghitung rata-rata level semua karakter.
     * @return rata-rata level, atau 0 jika list kosong
     */
    public double getAverageLevel() {
        if (characterList.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (Character c : characterList) {
            total = total + c.getLevel();
        }
        return (double) total / characterList.size();
    }

    /**
     * Mencari semua karakter dengan tipe tertentu.
     * @return list baru berisi karakter bertipe tersebut (kosong jika tidak ada)
     */
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> hasil = new ArrayList<>();
        for (Character c : characterList) {
            if (c.getType() == type) {
                hasil.add(c);
            }
        }
        return hasil;
    
    }
        /**
     * Menghitung jumlah karakter dengan tipe tertentu.
     * Hanya mengembalikan angka, sehingga ArrayList asli tidak terekspos.
     * @return jumlah karakter bertipe tersebut (0 jika tidak ada)
     */
    public int countByType(CharacterType type) {
        int jumlah = 0;
        for (Character c : characterList) {
            if (c.getType() == type) {
                jumlah++;
            }
        }
        return jumlah;
    }

}