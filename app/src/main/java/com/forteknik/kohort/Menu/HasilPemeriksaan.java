package com.forteknik.kohort.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.forteknik.kohort.Menu.Data.JSON_KHNifas;
import com.forteknik.kohort.Menu.Data.ListKohort.JSON_NPIbu;
import com.forteknik.kohort.Menu.Data.ListKohort.DaftarKHBalita;
import com.forteknik.kohort.Menu.Data.ListKohort.DaftarKHBayi;
import com.forteknik.kohort.R;

public class HasilPemeriksaan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_pemeriksaan);

    }

    public void onClickKH_Nifas(View view) {
        Intent intent =new Intent(HasilPemeriksaan.this, JSON_KHNifas.class);
        startActivity(intent);
    }

    public void onClickKH_Bayi(View view) {
        Intent intent =new Intent(HasilPemeriksaan.this, DaftarKHBayi.class);
        startActivity(intent);
    }

    public void onClickKH_Ibu(View view) {
        Intent intent =new Intent(HasilPemeriksaan.this, JSON_NPIbu.class);
        startActivity(intent);
    }

    public void onClickKH_Balita(View view) {
        Intent intent =new Intent(HasilPemeriksaan.this, DaftarKHBalita.class);
        startActivity(intent);
    }
}
