package com.andresuchitra.perpustakaanku;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    TextView tvJumlahProgress;
    SeekBar sbJumlah;
    EditText etJudul, etIsbn, etRangkuman, etKodeWarna;
    Spinner spinTahunTerbit, spinPenerbit;
    RadioButton rbAgama, rbKomputer, rbNovel, rbLainLain;
    RatingBar rbKualitas;

    DbHelper dbHelper = new DbHelper(FormActivity.this,"perpustakaanku", null, 1);
    Buku buku = new Buku();


    //string to hold form data
    String judul;
    String isbn;
    String tahunTerbit;
    String penerbit;
    String kategori;
    String jumlah;
    String kodeWarna;
    String kualitas;
    String rangkuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etJudul = findViewById(R.id.et_judul_buku);
        etIsbn = findViewById(R.id.et_isbn);
        spinTahunTerbit = findViewById(R.id.spin_tahun_terbit);
        spinPenerbit = findViewById(R.id.spin_penerbit);
        tvJumlahProgress = findViewById(R.id.tv_jumlah_progress);
        sbJumlah = findViewById(R.id.sb_jumlah_buku);
        etKodeWarna = findViewById(R.id.et_kode_warna);
        //checkboxes for book category
        rbAgama = findViewById(R.id.rb_kat_agama);
        rbLainLain = findViewById(R.id.rb_kat_lain);
        rbNovel = findViewById(R.id.rb_kat_novel);
        rbKomputer = findViewById(R.id.rb_kat_komputer);
        //rating bar / stars
        rbKualitas = findViewById(R.id.ratingbar_kualitas);
        etRangkuman = findViewById(R.id.et_rangkuman);


        //set listener for seekbar
        sbJumlah.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvJumlahProgress.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_simpan:
                // save the current form data
                boolean clear = validateFormAndGeneratePOJO();
                if(clear)
                {
                    //assign form data to Buku POJO
                    buku.setJudul(judul);
                    buku.setIsbn(isbn);
                    buku.setTahunTerbit(tahunTerbit);
                    buku.setPenerbit(penerbit);
                    buku.setKategori(kategori);
                    buku.setJumlah(jumlah);
                    buku.setKodeWarna(kodeWarna);
                    buku.setRating(kualitas);
                    buku.setRangkuman(rangkuman);

                    //add another data
                    dbHelper.addBuku(buku);
                    Toast.makeText(this, "New Record created!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateFormAndGeneratePOJO()
    {

        judul = etJudul.getText().toString();
        isbn = etIsbn.getText().toString();
        kodeWarna = etKodeWarna.getText().toString();
        rangkuman = etRangkuman.getText().toString();

        if (judul.isEmpty()) {
            etJudul.setError("Judul wajib diisi!");
            etJudul.requestFocus();
            return false;
        }

        kategori ="";
        if(rbAgama.isChecked()) kategori = "Agama";
        else if(rbKomputer.isChecked()) kategori = "Komputer";
        else if(rbNovel.isChecked()) kategori = "Novel";
        else if(rbLainLain.isChecked()) kategori = "Lain-lain";

        if(kategori.isEmpty())
        {
            Toast.makeText(this, "Kategori Buku WAJIB dipilih!", Toast.LENGTH_SHORT).show();
            return false;
        }

        tahunTerbit = spinTahunTerbit.getSelectedItem().toString();
        if(spinTahunTerbit.getSelectedItemPosition() == 0)
        {
            Toast.makeText(this, "Tahun Terbit wajib dipilih", Toast.LENGTH_SHORT).show();
            return false;
        }

        penerbit = spinPenerbit.getSelectedItem().toString();
        if(spinPenerbit.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Penerbit wajib dipilih", Toast.LENGTH_SHORT).show();
            return false;
        }

        jumlah = String.valueOf(sbJumlah.getProgress());

        if(kodeWarna.isEmpty())
        {
            kodeWarna = "000000";
        }

        kualitas = rbKualitas.getRating()+"";

        return true;

    }
}
