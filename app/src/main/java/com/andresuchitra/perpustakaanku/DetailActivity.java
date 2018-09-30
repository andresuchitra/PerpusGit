package com.andresuchitra.perpustakaanku;

import android.graphics.Color;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvJudul, tvIsbn, tvRangkuman, tvTahunTerbit, tvPenerbit, tvJumlah, tvKategori;
    RatingBar rbKualitas;
    ImageView imgKodeWarna;

    DbHelper dbHelper = new DbHelper(DetailActivity.this,"perpustakaanku", null,1);
    Buku buku = new Buku();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra("bukuId", 0);

        Log.d("DetailActivity","=============== id buku =========: "+id);
        if(id > 0)
        {
            //casting views
            tvJudul = findViewById(R.id.tv_detail_judul);
            tvIsbn = findViewById(R.id.tv_detail_isbn);
            tvTahunTerbit = findViewById(R.id.tv_detail_tahun_terbit);
            tvPenerbit = findViewById(R.id.tv_detail_penerbit);
            tvKategori = findViewById(R.id.tv_detail_kategori);
            tvJumlah = findViewById(R.id.tv_detail_jumlah);
            tvRangkuman = findViewById(R.id.tv_detail_rangkuman);
            rbKualitas = findViewById(R.id.rb_detail_kualitas);
            imgKodeWarna = findViewById(R.id.img_detail_kode_warna);

            //get buku object from DbHelper
            buku = dbHelper.getBook(id);

            //set values
            tvJudul.setText(buku.getJudul());
            tvIsbn.setText(buku.getIsbn());
            tvTahunTerbit.setText(buku.getTahunTerbit());
            tvPenerbit.setText(buku.getPenerbit());
            tvKategori.setText(buku.getKategori());
            tvJumlah.setText(buku.getJumlah());
            tvRangkuman.setText(buku.getRangkuman());

            //set badge color based on kode warna
            try {
                imgKodeWarna.setBackgroundColor(Color.parseColor("#" + buku.getKodeWarna()));
            }
            catch (Exception e)
            {
                imgKodeWarna.setBackgroundColor(Color.parseColor("#000000"));
            }

            //set rating bar value
            rbKualitas.setRating(Float.valueOf(buku.getRating()));
        }


    }
}
