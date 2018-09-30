package com.andresuchitra.perpustakaanku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper
{
    private SQLiteDatabase sqLiteDatabase;


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE buku (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "judul TEXT," +
                        "isbn TEXT," +
                        "tahun_terbit TEXT," +
                        "penerbit TEXT," +
                        "kategori TEXT," +
                        "jumlah TEXT," +
                        "kode_warna TEXT," +
                        "rating TEXT," +
                        "rangkuman TEXT"+
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addBuku(Buku buku)
    {
        ContentValues cv = new ContentValues();

        cv.put("judul", buku.getJudul());
        cv.put("isbn", buku.getIsbn());
        cv.put("tahun_terbit", buku.getTahunTerbit());
        cv.put("penerbit", buku.getPenerbit());
        cv.put("kategori",buku.getKategori());
        cv.put("jumlah",buku.getJumlah());
        cv.put("kode_warna", buku.getKodeWarna());
        cv.put("rating", buku.getRating());
        cv.put("rangkuman",buku.getRangkuman());

        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("buku",null,cv);
        sqLiteDatabase.close();
    }

    public List<Buku> getAllBooks(){
        sqLiteDatabase = getWritableDatabase();
        // Cursor wajib untuk read process
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM buku", new String[]{});
        cursor.moveToFirst();

        List<Buku> bookList = new ArrayList<>();
        while(!cursor.isAfterLast())
        {
            Buku buku = new Buku();
            buku.setId(cursor.getInt(cursor.getColumnIndex("id")));

            buku.setJudul(cursor.getString(cursor.getColumnIndex("judul")));
            buku.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
            buku.setTahunTerbit(cursor.getString(cursor.getColumnIndex("tahun_terbit")));
            buku.setPenerbit(cursor.getString(cursor.getColumnIndex("penerbit")));
            buku.setKodeWarna(cursor.getString(cursor.getColumnIndex("kode_warna")));
            buku.setKategori(cursor.getString(cursor.getColumnIndex("kategori")));
            buku.setJumlah(cursor.getString(cursor.getColumnIndex("jumlah")));
            buku.setRating(cursor.getString(cursor.getColumnIndex("rating")));
            buku.setRangkuman(cursor.getString(cursor.getColumnIndex("rangkuman")));

            bookList.add(buku);
            cursor.moveToNext();
        }
        cursor.close();
        sqLiteDatabase.close();
        return bookList;
    }

    public List<Buku> searchBuku(String query)
    {
        sqLiteDatabase = getWritableDatabase();
        // Pakai SQL query based on search query
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM buku WHERE " +
                        "judul LIKE ? OR " +
                        "isbn LIKE ? OR " +
                        "penerbit LIKE ? OR " +
                        "kategori LIKE ?",
                new String[]{
                        "%"+ query +"%",
                        "%"+ query +"%",
                        "%"+ query +"%",
                        "%"+ query +"%"
                });
        cursor.moveToFirst();

        List<Buku> bookList = new ArrayList<>();
        while(!cursor.isAfterLast())
        {
            Buku buku = new Buku();
            buku.setId(cursor.getInt(cursor.getColumnIndex("id")));
            buku.setJudul(cursor.getString(cursor.getColumnIndex("judul")));
            buku.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
            buku.setTahunTerbit(cursor.getString(cursor.getColumnIndex("tahun_terbit")));
            buku.setPenerbit(cursor.getString(cursor.getColumnIndex("penerbit")));
            buku.setKodeWarna(cursor.getString(cursor.getColumnIndex("kode_warna")));
            buku.setKategori(cursor.getString(cursor.getColumnIndex("kategori")));
            buku.setJumlah(cursor.getString(cursor.getColumnIndex("jumlah")));
            buku.setRating(cursor.getString(cursor.getColumnIndex("rating")));
            buku.setRangkuman(cursor.getString(cursor.getColumnIndex("rangkuman")));

            bookList.add(buku);
            cursor.moveToNext();
        }
        cursor.close();
        sqLiteDatabase.close();
        return bookList;
    }

    public Buku getBook(int id)
    {
        sqLiteDatabase = getWritableDatabase();
        // Cursor wajib untuk read process
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM buku WHERE id = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();

        //declare Buku object to be returned to DetailActivity
        Buku buku = new Buku();
        buku.setJudul(cursor.getString(cursor.getColumnIndex("judul")));
        buku.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
        buku.setTahunTerbit(cursor.getString(cursor.getColumnIndex("tahun_terbit")));
        buku.setPenerbit(cursor.getString(cursor.getColumnIndex("penerbit")));
        buku.setKodeWarna(cursor.getString(cursor.getColumnIndex("kode_warna")));
        buku.setKategori(cursor.getString(cursor.getColumnIndex("kategori")));
        buku.setJumlah(cursor.getString(cursor.getColumnIndex("jumlah")));
        buku.setRating(cursor.getString(cursor.getColumnIndex("rating")));
        buku.setRangkuman(cursor.getString(cursor.getColumnIndex("rangkuman")));

        sqLiteDatabase.close();
        cursor.close();
        return buku;
    }
}
