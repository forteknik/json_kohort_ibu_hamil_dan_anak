package com.forteknik.kohort.Menu.Data;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.forteknik.kohort.Menu.Login;
import com.forteknik.kohort.Menu.Logout;
import com.forteknik.kohort.R;
import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.User;

public class JSON_KHProfil extends AppCompatActivity {

   TextView Id,
    Noktp,
    Nama,
    Alamat,
    Tempatlahir,
    Tgllahir,
    Tglhpht,
    Agama,
    Telp,
    Pekerjaan,
    Goldarah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);

        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Login.class));
        }

        Id = (TextView) findViewById(R.id.tv_id);
        Noktp = (TextView) findViewById(R.id.tv_noktp);
        Nama = (TextView) findViewById(R.id.tv_nama_pasien);
        Alamat = (TextView) findViewById(R.id.tv_alamat);
        Tempatlahir = (TextView) findViewById(R.id.tv_tmplahir);
        Tgllahir = (TextView) findViewById(R.id.tv_tglLahirPasien);
        Tglhpht = (TextView) findViewById(R.id.tv_tglhpht);
        Agama = (TextView) findViewById(R.id.tv_agama);
        Telp = (TextView) findViewById(R.id.tv_telp);
        Pekerjaan = (TextView) findViewById(R.id.tv_pekerjaan);
        Goldarah = (TextView) findViewById(R.id.tv_goldarah);



        //Mengambil data Shared
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        Id.setText("ID: ("+user.getId()+")");
        Noktp.setText("No KTP: ("+user.getNoktp()+")");
        Nama.setText("Ibu "+user.getNama());
        Alamat.setText(user.getAlamat());
        Tempatlahir.setText(user.getTempatlahir());
        Tgllahir.setText(user.getTgllahir());
        Tglhpht.setText(user.getTglhpht());
        Agama.setText(user.getAgama());
        Telp.setText(user.getTelp());
        Pekerjaan.setText(user.getPekerjaan());
        Goldarah.setText(user.getGoldarah());

        findViewById(R.id.image_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(JSON_KHProfil.this, Logout.class);
                startActivity(intent);
            }
        });

    }


}