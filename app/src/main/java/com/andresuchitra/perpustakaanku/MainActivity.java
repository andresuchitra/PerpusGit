package com.andresuchitra.perpustakaanku;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper = new DbHelper(MainActivity.this,"perpustakaanku",null,1);
    List<Buku> listBuku = new ArrayList<Buku>();
    Buku buku = new Buku();
    SearchView svSearchBuku;
    ListView lvListBuku;
    BukuItemAdapter bukuItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting UI objects
        svSearchBuku = findViewById(R.id.sv_cari_buku);
        lvListBuku = findViewById(R.id.lv_list_buku);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menampilkan menu main
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Membaca pilihan user
        switch (item.getItemId()) {
            case R.id.action_tambah:
                //action disini untuk pindah ke FormActivity
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // get object of Students first, before creating Adapter for ListView
        listBuku = dbHelper.getAllBooks();
        bukuItemAdapter = new BukuItemAdapter(MainActivity.this,listBuku);
        lvListBuku.setAdapter(bukuItemAdapter);

        // set listView item such that click will move to Detail Activity
        lvListBuku.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                buku = bukuItemAdapter.getItem(i);

                int id = buku.getId();

                Log.d("MainActivity - onClickListener"," ---------  bukuID: "+buku.getId()+" ---------");

                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                // additional information passed from list view for Read DB access when opening Detail
                // activity
                detailIntent.putExtra("bukuId", buku.getId());
                // memulai Detail intent
                startActivity(detailIntent);
            }
        });

        svSearchBuku.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listBuku.clear();
                listBuku.addAll(dbHelper.searchBuku(newText));
                bukuItemAdapter.notifyDataSetChanged();

                return false;
            }
        });

    }
}
