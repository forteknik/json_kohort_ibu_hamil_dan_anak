package com.forteknik.kohort.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.forteknik.kohort.Menu.Data.JSON_KHProfil;
import com.forteknik.kohort.R;

public class MenuUtama extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);

        }


    public void tgl_bersalin(View view) {
        Intent intent_tgl_bersalin = new Intent(MenuUtama.this,InformasiKehamilan.class);
        startActivity(intent_tgl_bersalin);
    }

    public void profil(View view) {
        Intent intent_profil = new Intent(MenuUtama.this,JSON_KHProfil.class);
        startActivity(intent_profil);
    }

    public void tips_makanan(View view) {
        Intent intent_tips =new Intent(MenuUtama.this, TipsMakananIbuHamil.class);
        startActivity(intent_tips);
    }

    public void hasilpemeriksaan(View view) {
        Intent intent_hasil =new Intent(MenuUtama.this, HasilPemeriksaan.class);
        startActivity(intent_hasil);

    }

    public void jadwalpemeriksaan(View view) {
        Intent intent_jadwalpemeriksaan =new Intent(MenuUtama.this, JadwalPemeriksaan.class);
        startActivity(intent_jadwalpemeriksaan);
    }

    public void keluar(View view) {

        finish();

    }

    public void tentang(View view) {
        Intent intent =new Intent(MenuUtama.this, Tentang.class);
        startActivity(intent);
    }
}
