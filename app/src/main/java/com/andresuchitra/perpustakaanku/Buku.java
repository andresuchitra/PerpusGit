package com.andresuchitra.perpustakaanku;

public class Buku {
    int id;
    String judul;
    String isbn;
    String tahunTerbit;
    String penerbit;
    String kategori;
    String jumlah;
    String kodeWarna;
    String rating;
    String rangkuman;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKodeWarna() {
        return kodeWarna;
    }

    public void setKodeWarna(String kodeWarna) {
        this.kodeWarna = kodeWarna;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRangkuman() {
        return rangkuman;
    }

    public void setRangkuman(String rangkuman) {
        this.rangkuman = rangkuman;
    }
}
