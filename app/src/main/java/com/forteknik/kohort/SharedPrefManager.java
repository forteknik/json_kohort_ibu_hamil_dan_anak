package com.forteknik.kohort;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.forteknik.kohort.Menu.Login;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_ID = "keyid";
    private static final String KEY_NOKTP="keynoktp";
    private static final String KEY_NAMA="keynama";
    private static final String KEY_ALAMAT="keyalamat";
    private static final String KEY_TEMPATLAHIR="keytempatlahir";
    private static final String KEY_TGLLAHIR="keytgllahir";
    private static final String KEY_TGLHPHT="keytglhpht";
    private static final String KEY_AGAMA="keyagama";
    private static final String KEY_TELP="keytelp";
    private static final String KEY_PEKERJAAN="keypekerjaan";
    private static final String KEY_PENDIDIKAN="keypendidikan";
    private static final String KEY_GOLDARAH="keygoldarah";
    private static final String KEY_PASSWORD="keypassword";



    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //Stor data to Shared
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, user.getId());
        editor.putString (KEY_NOKTP, user.getNoktp());
        editor.putString (KEY_NAMA, user.getNama());
        editor.putString (KEY_ALAMAT, user.getAlamat());
        editor.putString (KEY_TEMPATLAHIR, user.getTempatlahir());
        editor.putString (KEY_TGLLAHIR, user.getTgllahir());
        editor.putString (KEY_AGAMA, user.getAgama());
        editor.putString (KEY_TELP, user.getTelp());
        editor.putString (KEY_PEKERJAAN, user.getPekerjaan());
        editor.putString (KEY_PENDIDIKAN, user.getPendidikan());
        editor.putString (KEY_GOLDARAH, user.getGoldarah());
        editor.putString (KEY_TGLHPHT, user.getTglhpht());
        editor.putString (KEY_PASSWORD, user.getPassword());


        editor.apply();
    }
    //Cek Status Login
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID, null) != null;
    }


    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_NOKTP, null),
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_ALAMAT, null),
                sharedPreferences.getString(KEY_TEMPATLAHIR, null),
                sharedPreferences.getString(KEY_TGLLAHIR, null),
                sharedPreferences.getString(KEY_AGAMA, null),
                sharedPreferences.getString(KEY_TELP, null),
                sharedPreferences.getString(KEY_PEKERJAAN, null),
                sharedPreferences.getString(KEY_PENDIDIKAN, null),
                sharedPreferences.getString(KEY_GOLDARAH, null),
                sharedPreferences.getString(KEY_TGLHPHT, null),
                sharedPreferences.getString(KEY_PASSWORD, null)

                );
    }

    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login.class));
    }
}