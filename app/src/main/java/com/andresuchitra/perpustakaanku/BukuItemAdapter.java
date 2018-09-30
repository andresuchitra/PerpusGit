package com.andresuchitra.perpustakaanku;

import android.content.Context;
import android.graphics.Color;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class BukuItemAdapter extends ArrayAdapter<Buku> {
    public BukuItemAdapter(@NonNull Context context, @NonNull List<Buku> objects) {
        super(context, R.layout.item_buku, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            listItemView = inflater.inflate(R.layout.item_buku, parent, false);
        }

        //casting view ke internal variable
        TextView tvJudul = listItemView.findViewById(R.id.tv_item_judul);
        TextView tvPenerbit = listItemView.findViewById(R.id.tv_item_penerbit);
        TextView tvKategori = listItemView.findViewById(R.id.tv_item_kategori);
        RatingBar rbKualitas = listItemView.findViewById(R.id.rb_item_kualitas);
        ImageView imgWarnaBuku = listItemView.findViewById(R.id.img_warna_buku);

        Buku itemBuku = getItem(position);

        //Set value pada view
        tvJudul.setText(itemBuku.getJudul());
        tvKategori.setText(itemBuku.getKategori());
        tvPenerbit.setText(itemBuku.getPenerbit());
        rbKualitas.setRating(Float.valueOf(itemBuku.getRating()));

        try {
            imgWarnaBuku.setBackgroundColor(Color.parseColor("#" + itemBuku.getKodeWarna()));
        }
        catch (Exception e)
        {
            imgWarnaBuku.setBackgroundColor(Color.parseColor("#000000"));
        }
        return listItemView;
    }
}
