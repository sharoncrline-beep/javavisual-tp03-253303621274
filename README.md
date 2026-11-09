## TP10 - Theming, Properti Komponen & Analisis MVC

### Tampilan Sebelum dan Sesudah

**Sebelum (MainWindow v0.3)**

![Character Manager v0.3](screenshots/tp08-sebelum.png)

Versi v0.3 menampilkan daftar karakter menggunakan tampilan sederhana
tanpa tema warna kartu berdasarkan role.

**Sesudah (MainWindow v0.4)**

![Character Manager v0.4](screenshots/AP13%20sesudah.png)

Versi v0.4 menggunakan tema gelap dengan warna kartu berbeda sesuai role
karakter. Karakter dengan level 5 atau lebih ditampilkan menggunakan teks
berwarna emas dan tebal. Aplikasi juga menggunakan tooltip dan perubahan
cursor sebagai properti tambahan.

### Implementasi AppTheme

Pengaturan tampilan aplikasi dipusatkan di `AppTheme.java` yang berada
di package `view`. AppTheme menyediakan palet warna, font, border, serta
method `colorFor()` untuk menentukan warna kartu berdasarkan role karakter.

`MainWindow` menggunakan pengaturan dari AppTheme untuk menampilkan judul
dan kartu karakter. Kartu karakter menggunakan `setOpaque(true)` agar warna
latar terlihat, sedangkan ukuran jendela menyesuaikan komponen menggunakan
`pack()`.

### Mini-Challenge: Level Tinggi

Karakter dengan level 5 atau lebih mendapatkan teks berwarna emas dan tebal.
Batas level tinggi disimpan dalam konstanta bernama
`HIGH_LEVEL_THRESHOLD` di AppTheme.

Karakter yang memenuhi kondisi tersebut antara lain Arjuna (level 5),
Gatotkaca (level 7), dan Ranger (level 6).

### Properti Komponen Tambahan

**1. Tooltip (`setToolTipText()`)**

Tooltip menampilkan penjelasan singkat mengenai role karakter ketika cursor
diarahkan ke kartu. Properti ini membantu pengguna memahami karakter tanpa
menambah banyak teks pada tampilan utama.

**2. Cursor (`setCursor()`)**

Cursor diubah menjadi bentuk tangan menggunakan `Cursor.HAND_CURSOR`
ketika diarahkan ke kartu karakter. Hal ini memberikan petunjuk visual
kepada pengguna bahwa komponen tersebut memiliki potensi interaksi.

### Pemisahan Warna dalam MVC

Warna disimpan di `AppTheme` karena warna merupakan bagian dari pengaturan
tampilan aplikasi yang termasuk tanggung jawab layer View. `Character`
sebagai Model hanya menyimpan data karakter seperti nama, role, level,
HP, dan ATK.

Dengan memisahkan warna dari Model, data karakter dapat digunakan kembali
pada tampilan yang berbeda tanpa bergantung pada aturan visual tertentu.
Selain itu, perubahan tema dapat dilakukan di AppTheme tanpa perlu
mengubah kode Model. Pemisahan ini menjaga prinsip MVC, yaitu Model
mengelola data dan logika bisnis, sedangkan View bertanggung jawab
terhadap tampilan.

### Analisis Properti & MVC (Bagian D)

Link Google Docs: https://docs.google.com/document/d/1BlCfHTOSXwXQEibUCTakaCCLW-2-DNsKCJT1Lels13A/edit?tab=t.0

---

# TP12 - Layout Manager, Responsive UI & Analisis Layout
## 1. Wireframe Layout

Struktur tampilan aplikasi Character Manager v0.5:

```text
+------------------------------------------------------+
| HEADER: Character Manager              8 karakter    |
+------------------------------------------------------+
|                  STATISTICS PANEL                    |
|  Total Karakter       | Rata-rata Level              |
|  Level Tertinggi      | Jenis Role                   |
+--------------------------------------+---------------+
|                                      |               |
|          DAFTAR KARAKTER             |    LEGENDA    |
|          (JScrollPane)               |     ROLE      |
|                                      |               |
+--------------------------------------+---------------+
| STATUS: Total 8 karakter | Siap                 v0.5 |
+------------------------------------------------------+
```

## 2. Screenshot Hasil Pengujian

### A. Tampilan Ukuran Normal
![Tampilan normal](screenshots/normal.png)

### B. Tampilan Jendela Diperlebar
![Tampilan lebar](screenshots/lebar.png)

### C. Tampilan Jendela Dikecilkan
![Tampilan kecil](screenshots/kecil.png)