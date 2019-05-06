package com.forteknik.kohort.Menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.forteknik.kohort.R;

public class Tentang extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height  = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));
    }

    public void ok(View view) {
        finish();
    }
}
