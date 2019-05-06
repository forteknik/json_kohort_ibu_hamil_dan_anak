package com.forteknik.kohort;

public class User {

    private String id,
            noktp,
            nama,
            alamat,
            tempatlahir,
            tgllahir,
            agama,
            telp,
            pekerjaan,
            pendidikan,
            goldarah,
            tglhpht,
            password;

    public User(String id,
                String noktp,
                String nama,
                String alamat,
                String tempatlahir,
                String tgllahir,
                String agama,
                String telp,
                String pekerjaan,
                String pendidikan,
                String goldarah,
                String tglhpht,
                String password
                ) {
        this.id=id;
        this.noktp=noktp;
        this.nama=nama;
        this.alamat=alamat;
        this.tempatlahir=tempatlahir;
        this.tgllahir=tgllahir;
        this.agama=agama;
        this.telp=telp;
        this.pekerjaan=pekerjaan;
        this.pendidikan=pendidikan;
        this.goldarah=goldarah;
        this.tglhpht=tglhpht;
        this.password=password;


    }

    public String getId() {
        return id;
    }
    public String getNoktp() { return noktp;}
    public String getNama() { return nama;}
    public String getAlamat() { return alamat;}
    public String getTempatlahir() { return tempatlahir;}
    public String getTgllahir() { return tgllahir;}
    public String getAgama() { return agama;}
    public String getTelp() { return telp;}
    public String getPekerjaan() { return pekerjaan ;}
    public String getPendidikan() { return pendidikan; }
    public String getGoldarah() { return goldarah;}
    public String getTglhpht(){return tglhpht;}
    public String getPassword() { return password;}


}
