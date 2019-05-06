package com.forteknik.kohort.Menu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.forteknik.kohort.R;
import com.forteknik.kohort.SharedPrefManager;
import com.forteknik.kohort.User;

public class InformasiKehamilan extends Activity {
    int  mYear, mMonth, mDay;

    private TextView txt_HPHT;
    private ImageView icon_calendar, iconInfoUsiaKehamilan;
    TextView txt_usiabayi, txt_tglbersalin,txt_lahirDM, txt_sisahari;
    TextView tv_info;
    private String[] arrMonth = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informasi_kehamilan);
        //icon_calendar = (ImageView) findViewById(R.id.icon_calendar);


        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_info.setMovementMethod(new ScrollingMovementMethod());


        txt_HPHT = (TextView) findViewById(R.id.txt_HPHT);
        txt_usiabayi = (TextView) findViewById(R.id.txt_usiabayi);
        txt_tglbersalin = (TextView) findViewById(R.id.txt_tglbersalin);
        txt_lahirDM = (TextView) findViewById(R.id.txt_lahirDM);
        txt_sisahari = (TextView) findViewById(R.id.txt_sisahari);

        txt_HPHT.setVisibility(View.INVISIBLE);
        txt_lahirDM.setVisibility(View.INVISIBLE);

        //btnHitungUmur = (Button) findViewById(R.id.btnHitungUmur);
        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        User user = SharedPrefManager.getInstance(this).getUser();
        String tglHPHT = user.getTglhpht();
        Calendar now = Calendar.getInstance();
        Calendar haidpertama = Calendar.getInstance();
        SimpleDateFormat df_haidpertama = new SimpleDateFormat("yyyy-MM-dd");
        try {
            haidpertama.setTime(df_haidpertama.parse(tglHPHT));
            Integer KalenderbulanHPHT = haidpertama.get(Calendar.MONTH);


            if (KalenderbulanHPHT > 3) {
                int tahunHPHT = haidpertama.get(Calendar.YEAR) + 1;
                int bulanHPHT = KalenderbulanHPHT - 3;
                int hariHPHT = haidpertama.get(Calendar.DAY_OF_MONTH) + 7;

                if (hariHPHT > now.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    bulanHPHT++;
                    hariHPHT -= now.getActualMaximum(Calendar.DAY_OF_MONTH);
                }
                if (bulanHPHT > 12) {
                    tahunHPHT++;
                    bulanHPHT -= 12;
                }

                String lahir = hariHPHT + " " + arrMonth[bulanHPHT] + " " + tahunHPHT;
                String lahirDM = hariHPHT + " - " +bulanHPHT+ " - " + tahunHPHT;
                txt_tglbersalin.setText(lahir);
                txt_lahirDM.setText(lahirDM);

            }
            if (KalenderbulanHPHT < 3) {
                int tahunHPHT = haidpertama.get(Calendar.YEAR);
                int bulanHPHT = KalenderbulanHPHT + 9;
                int hariHPHT = haidpertama.get(Calendar.DAY_OF_MONTH) + 7;
                if (hariHPHT > now.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    bulanHPHT++;
                    hariHPHT -= now.getActualMaximum(Calendar.DAY_OF_MONTH);
                }
                if (bulanHPHT > 12) {
                    tahunHPHT++;
                    bulanHPHT -= 12;
                }

                String lahir = hariHPHT + " " + arrMonth[bulanHPHT] + " " + tahunHPHT;
                String lahirDM = hariHPHT + " - " +bulanHPHT+ " - " + tahunHPHT;
                txt_tglbersalin.setText(lahir);
                txt_lahirDM.setText(lahirDM);
            }

            //Sisa Waktu Bersalin
            SimpleDateFormat df_harilahir = new SimpleDateFormat("dd - MM - yyy");
            String lahir = txt_lahirDM.getText().toString();
            Calendar calTglBersalin = Calendar.getInstance();
            calTglBersalin.setTime(df_harilahir.parse(lahir));

            String sisahari = String.valueOf(daysBetween(now, calTglBersalin));
            txt_sisahari.setText(sisahari + " Hari");


            //usiabayi
            int years = now.get(Calendar.YEAR) - haidpertama.get(Calendar.YEAR);
            int months = now.get(Calendar.MONTH) - haidpertama.get(Calendar.MONTH);
            int days = now.get(Calendar.DAY_OF_MONTH) - haidpertama.get(Calendar.DAY_OF_MONTH);

            String hasil = String.valueOf(daysBetween(haidpertama, now));
                int inthasil = Integer.parseInt(hasil);
                txt_HPHT.setText(hasil);
                int week = inthasil / 7;


                if (days < 0) {
                    months--;
                    days += now.getActualMaximum(Calendar.DAY_OF_MONTH);
                }
                if (months < 0) {
                    years --;
                    months += 12;
                }
                String umur = months + " Bulan " + days + " Hari \n(" + week + " Minggu)";
                txt_usiabayi.setText(umur);

                if (week == 1) {
                    String minggu1 = "Belum Ada Tanda Yang Signifikan";
                    tv_info.setText(Html.fromHtml(minggu1));
                } else if (week == 2) {
                    String minggu2 = "Belum Ada Ciri-Ciri Yang Signifikan";
                    tv_info.setText(Html.fromHtml(minggu2));
                } else if (week == 3) {
                    String minggu3 = "Belum Ada Ciri-Ciri Yang Signifikan";
                    Html.fromHtml(minggu3);
                } else if (week == 4) {
                    String minggu4 = "<h2>Perkembangan Janin Minggu Ke-4 (1 Bulan)</h2>\n" +
                            "<p>Kehamilan baru bisa di deteksi jika usia kehamilan sudah mencapai usia 4 minggu. Meski ada ibu hamil yang bisa mendeteksi kehamilan sebelum usianya 4 minggu, namun dokter kandungan baru bisa menyatakan bahwa ibu hamil tersebut benar-benar hamil jika usia kandungannya sudah 4 minggu. Dokter kandungan akan menyuruh ibu hamil tersebut kembali lagi jika selama 4 minggu atau lebih jika belum mendapatkan menstruasi.</p>\n" +
                            "<p><strong>1. Perkembangan&nbsp;di Dalam Rahim</strong></p>\n" +
                            "<p>Saat hamil usia 4 minggu, akan ada proses pembentukan embrio dan bakal janin di dalam rahim. Berikut ini proses pembentukan embrio yang ada di dalam rahim :</p>\n" +
                            "<p>- Embrio atau bakal janin telah tumbuh di dalam rahim ibu.</p>\n" +
                            "<p>- Ukurannya baru sebiji kacang hijau. Bahkan jika dilakukan USG maka hanya akan terlihat seperti setitik gumpalan darah.</p>\n" +
                            "<p>- Otak, tulangg punggung, tulang belakang dan juga syaraf mendapatkan perkembangan dan berada di lapisan atas.</p>\n" +
                            "<p>- Sel plasenta mulai ada.</p>\n" +
                            "<p>- Sel plasenta membentuk jaringan mikrokopis.</p>\n" +
                            "<p>- Plasenta mampu memberikan nutrisi kepada embrio di dalam rahim.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ciri ciri orang hamil&nbsp;saat usia 4 minggu, ibu hamil akan merasakan bad mood. Mudah marah dan tersinggung, sangat sensitif terhadap perkataan dan bau-bau yang ada di sekitarnya. Ibu hamil juga akan merasakan hal-hal di bawah ini :</p>\n" +
                            "<p>- Mual</p>\n" +
                            "<p>- Malas</p>\n" +
                            "<p>- Pusing</p>\n" +
                            "<p>- Kepala berat</p>\n" +
                            "<p>- Pegal-pegal seperti sebelum menstruasi</p>\n" +
                            "<p>- Kram juga dirasakan ibu hamil</p>\n" +
                            "<p>- Jumlah lendir lebih banyak</p>\n" +
                            "<p>- Payudara terasa nyeri</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Pada saat minggu keempat atau bulan pertama kehamilan. Janin baru memiliki ukuran setitik darah atau seukuran kacang hijau.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Perkembangan janin&nbsp;yang ada di dalam rahim saar&nbsp;berusia empat minggu sudah membentuk organ-organ di bawah ini :</p>\n" +
                            "<p>- Plasenta</p>\n" +
                            "<p>- Otak</p>\n" +
                            "<p>- Tulang punggung</p>\n" +
                            "<p>- Tulang belakang</p>";
                    tv_info.setText(Html.fromHtml(minggu4));
                } else if (week == 5) {
                    String minggu5 = "<h2>Perkembangan Janin&nbsp;Minggu Ke-5 (1 Bulan)</h2>\n" +
                            "<p>Saat ibu hamil menggunakan tes pendeteksi kehamilan, hormon HCG pun sudah terdeteksi. Jumlah garis yang dihasilkan berjumlah dua atau tes menunjukkan simbol positif. Segera kunjungi dokter untuk&nbsp;cara menjaga kehamlan agar tetap sehat, untuk&nbsp;mendapatkan vitamin asam folat dan prenatal yang sangat dibutuhkan oleh embrio anda.</p>\n" +
                            "<p><strong>1. Perkembangan di Dalam Rahim</strong></p>\n" +
                            "<p>Proses yang ada di rahim mulai banyak. Embrio yang dulunya hanya sekecil biji kacang hijau kini mulai berkembang dengan pesat. Berikut ini berbagai macam proses yang ada di dalam rahim :</p>\n" +
                            "<p>- Embrio sudah terhubung dengan aliran darah di dalam tubuh ibu hamil.</p>\n" +
                            "<p>- Embrio jika diamati menggunakan USG, &nbsp;sudah berbentuk seperti kecebong.</p>\n" +
                            "<p>- Organ utamanya mulai tumbuh yaitu hati dan ginjalnya.</p>\n" +
                            "<p>- Embrio juga sedang terbentuk usus buntu.</p>\n" +
                            "<p>- Tabung syaraf embrio sudah terbuka, tabung tersebut akan terhubung ke otak dan juga ke bagian sumsum tulang belakang embrio.</p>\n" +
                            "<p>- Tangan dan kaki juga sudah mulai muncul meski bentuknya masih seperti tunas tumbuhan yang masih kecil.</p>\n" +
                            "<p>- Mulut juga sudah ada, meski hanya membentuk lipatan kecil saja.</p>\n" +
                            "<p>- Hati sudah bisa berdebar.</p>\n" +
                            "<p>- Kerangka tubuh mulai terbentuk.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Saat minggu ke-5 kehamilan, ibu hamil masih mengalami&nbsp;gangguan kehamilan&nbsp;morning sickness. Terutama ibu hamil dengan asam lambung tinggi. Mual, muntah dan pusing masih mendominasi ibu hamil.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Embrio</strong></p>\n" +
                            "<p>Ukuran janin masih seperti kecebong, sehingga belum bisa dipastikan berapa berat dari embrio tersebut. Yang terlihat adalah panjang embrio.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Embrio</strong></p>\n" +
                            "<p>Saat embrio berusia lima minggu, panjang embrio mencapai panjang 0,118 inchi.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Saat usia kandungan lima minggu, embrio sudah membnetuk organ seperti di bawah ini :</p>\n" +
                            "<p>- Kerangka tubuh</p>\n" +
                            "<p>- Hati dan ginjal</p>\n" +
                            "<p>- Jantung</p>\n" +
                            "<p>- Tabung syaraf</p>\n" +
                            "<p>- Tangan dan kaki meski masih kecil</p>";
                    tv_info.setText(Html.fromHtml(minggu5));
                } else if (week == 6) {
                    String minggu6 = "<h2>Perkembangan Janin&nbsp;Minggu Ke-6 (1 Bulan)</h2>\n" +
                            "<p>Pada saat minggu ke-6, perkembangan janin&nbsp;menjadi sangat&nbsp;pesat. Berikut ini adalah perkembangannya.</p>\n" +
                            "<p><strong>1. Perkembangan di Dalam Rahim</strong></p>\n" +
                            "<p>Di dalam rahim bakal janin telah mengalami perkembangan dengan pesat. Berikut ini proses perkembangan yang terjadi di dalam rahim :</p>\n" +
                            "<p>- Bakal janin telah memiliki denyut jantung, denyut jantungnya 150 denyutan per menit. Denyut jantung itu dua kali lipat dari denyut jantung manusia dewasa.</p>\n" +
                            "<p>- Embrio juga telah membentuk kepala dan otak yang berkembang di dalamnya. Otak terlihat lebih besar dibandingkan dengan bagian yang lainnya.</p>\n" +
                            "<p>- Lubang hidung mulai terbentuk.</p>\n" +
                            "<p>- Lubang telinga terbentuk.</p>\n" +
                            "<p>- Tangan dan kaki lebih panjang dibandingkan sebelumnya.</p>\n" +
                            "<p>- Otot dan jaringan otot telah terbentuk di dalam rahim.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan mengalami perubahan hormon. Sering merasa murung kemudian bisa mudah berganti dengan perasaan senang. Hal itu adalah normal dan merupakan akibat dari hormon yang telah mengalami fluktuasi. Ibu hamil juga merasakan lebih emosional, namun ibu hamil harus bisa menahan emosi agar tidak terkena hipertensi. Ibu hamil mengalami penurunan berat badan dan area puting lebih gelap.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Embrio</strong></p>\n" +
                            "<p>Embrio belum bisa terdeteksi beratnya. Berat embrio baru akan terlihat saat usia kehamilan usia tujuh minggu.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Embrio</strong></p>\n" +
                            "<p>Saat usia kehamilan memasuki usia 6 minggu, embrio akan memiliki panjang rata-rata 2-4 mm, diukur dari atas kepala embrio sampai dengan bokong.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Saat embrio berusia enam minggu, organ yang terbentuk adalah :</p>\n" +
                            "<p>- Jantung yang mulai berdetak.</p>\n" +
                            "<p>- Sistem pencernaan.</p>\n" +
                            "<p>- Saluran pernafasan.</p>\n" +
                            "<p>- Kaki dan tangan yang lebih panjang dibandingkan sebelumnya.</p>";
                    tv_info.setText(Html.fromHtml(minggu6));
                } else if (week == 7) {
                    String minggu7 = "<h2>Perkembangan Janin Minggu Ke-7 (1 Bulan)</h2>\n" +
                            "<p>Saat usia embrio berusia tujuh minggu, embrio sudah memiliki berat dan memiliki ukuran yang lebih panjang dibandingkan sebelumnya.</p>\n" +
                            "<p><strong>1. Perkembangan&nbsp;di Dalam Rahim</strong></p>\n" +
                            "<p>Berikut ini berbagai macam proses yang akan terjadi di dalam rahim. Proses tersebut adalah perkembangan embrio yang ada di dalam rahim ibu hamil :</p>\n" +
                            "<p>- Embrio telah berukuran sebesar kacang polong.</p>\n" +
                            "<p>- Lengan mulai membelah jadi dua dan lebih panjang. Lengan telah membelah menjadi bahu dan tangan yang mungil.</p>\n" +
                            "<p>- Jantung juga telah terbagi bagiannya. Yaitu bilik kanan jantung maupun bilik kiris.</p>\n" +
                            "<p>- Paru-paru juga sudah terbagi menjadi bilik kiri dan bilik kanan.</p>\n" +
                            "<p>- Selaput jari kaki dan jari tangan mulai terbentuk.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan mood yang sama saat usia kandungan memasuki usia ke-6 minggu.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Embrio</strong></p>\n" +
                            "<p>Saat usia tujuh minggu ukuran embrio seberat 0,8 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Embrio</strong></p>\n" +
                            "<p>Saat usia kehamilan tujuh minggu, panjang embrio sepanjang 5-13 mm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Embrio dengan usia tujuh minggu mulai membentuk berbagai macam organ. Diantaranya adalah :</p>\n" +
                            "<p>- Lengan dan bahu</p>\n" +
                            "<p>- Jantung dengan bilik kanan dan bilik kiri</p>\n" +
                            "<p>- Saluran pernafasan</p>\n" +
                            "<p>- Paru-paru dengan bilik kanan dan bilik kiri</p>\n" +
                            "<p>- Selaput jari tangan dan jari kaki</p>";
                    tv_info.setText(Html.fromHtml(minggu7));
                } else if (week == 8) {
                    String minggu8 = "<h2>Perkembangan Janin&nbsp;Usia 8 Minggu (2 Bulan)</h2>\n" +
                            "<p>Embrio sudah banyak berkembang di dalam rahim. Melalui berbagai proses yang ada di dalam rahim, embrio mulai mengalami perkembangan pesat.</p>\n" +
                            "<p><strong>1.&nbsp;Perkembangan&nbsp;di Dalam Rahim</strong></p>\n" +
                            "<p>Di dalam rahim mengalami berbagai macam proses berikut ini :</p>\n" +
                            "<p>- Gigi dan langit-langit mulut mulai terbentuk</p>\n" +
                            "<p>- Telinga terus mengalami perkembangan</p>\n" +
                            "<p>- Terbentuk jaringan kulit</p>\n" +
                            "<p>- Pembuluh vena dapat terlihat dengan jelas</p>\n" +
                            "<p>- Ujung hidung sudah mulai terlihat</p>\n" +
                            "<p>- Kelopak mata mulai berkembang</p>\n" +
                            "<p>- Terbentuklah siku pada lengan embrio</p>\n" +
                            "<p>- Lidah mulai terbentuk disertai dengan perkembangan bibir</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Tanda tanda kehamilan&nbsp;pada usia ini dapat dirasakan&nbsp;hal-hal seperti&nbsp;:</p>\n" +
                            "<p>- Nyeri pada payudara</p>\n" +
                            "<p>- Keseimbangan cairan tubuh terganggu</p>\n" +
                            "<p>- Morning sickness</p>\n" +
                            "<p>- Rahim juga akan membesar mengkuti perkembangan embrio</p>\n" +
                            "<p>- Tertekannya kandung kemih sehingga buang air kecil lebih sering</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Embrio</strong></p>\n" +
                            "<p>Saat embrio berusia 8 minggu, embrio memiliki berat 2 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Embrio</strong></p>\n" +
                            "<p>Rahim membesar mengikuti panjang dan beratnya embrio. Perkembangan janin di usia 8 minggu, memiliki panjang sekitar 14-20 mm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Organ embrio memiliki perkembangan yang pesat. Diantaranya adalah :</p>\n" +
                            "<p>- Kelopak mata</p>\n" +
                            "<p>- Saluran pernafasan lebih berkembang</p>\n" +
                            "<p>- Siku</p>\n" +
                            "<p>- Lubang hidung</p>\n" +
                            "<p>- Pembuluh darah vena</p>\n" +
                            "<p>- Gigi, mulut dan lidah</p>\n" +
                            "<p>- Langit-langit mulut</p>";
                    tv_info.setText(Html.fromHtml(minggu8));
                } else if (week == 9) {
                    String minggu9 = "<h2>Perkembangan Janin Usia 9 Minggu (2 Bulan)</h2>\n" +
                            "<p>Saat usia kehamilan memasuki usia 9 minggu, embrio berkembang semakin pesat. Perkembangan itu diikuti dengan perubahan fisik dan psikis pada ibu hamil.</p>\n" +
                            "<p><strong>1. Perkembangan&nbsp;di Dalam Rahim</strong></p>\n" +
                            "<p>Di dalam rahim akan terjadi berbagai macam proses berikut ini :</p>\n" +
                            "<p>- Telinga luar mulai terbentuk</p>\n" +
                            "<p>- Kaki dan tangan juga terus berkembang</p>\n" +
                            "<p>- Embrio mulai ditumbuhi dengan jari-jari kaki dan tangan</p>\n" +
                            "<p>- Embrio bergerak secara halus</p>\n" +
                            "<p>- Detak jantung bisa didengar oleh ibu melalui bantuan alat doppler</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Saat usia kehamilan memasuki usia 9 minggu, ibu hamil akan merasakan mudah lelah dan lesu. Olahraga ringan diperlukan untuk membantu stamina ibu hamil. Selain itu,&nbsp;makanan sehat untuk ibu hamil&nbsp;perlu dikonsumsi setiap harinya.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Embrio</strong></p>\n" +
                            "<p>Janin berukuran 4 gram ketika usianya 9 minggu.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Embrio</strong></p>\n" +
                            "<p>Embrio memiliki panjang sekitar 2,3 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Embrio akan membentuk organ di bawah ini :</p>\n" +
                            "<p>- Jari tangan dan kaki</p>\n" +
                            "<p>- Telinga luar</p>";
                    tv_info.setText(Html.fromHtml(minggu9));
                } else if (week == 10) {
                    String minggu10 = "<h2>Perkembangan Janin Usia 10 Minggu (2 Bulan)</h2>\n" +
                            "<p>Saat usia kehamilan memasuki usia 10 minggu, embrio atau bakal janin telah berubah menjadi janin. Jika sudah menjadi janin, embrio tidak akan berbentuk seperti kecebong lagi. Namun sudah menyerupai manusia meski bentuknya masih kecil.</p>\n" +
                            "<p><strong>1. Perkembangan Embrio</strong></p>\n" +
                            "<p>Kini bakal janin tidak bisa disebut dengan embrio lagi, namun sudah disebut dengan janin atau bayi. Janin mengalami perkembangan seperti di bawah ini :</p>\n" +
                            "<p>- Janin sudah aktif, lebih aktif daripada saat menjadi embrio.</p>\n" +
                            "<p>- Janin mampu menelan cairan.</p>\n" +
                            "<p>- Menendang kakinya.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil masih merasakan mood yang berubah-ubah. Janin juga bisa dirasakan di bagian tengah atas kemaluan ibu hamil.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Saat usia janin memasuki usia 10 minggu, dia memiliki berat 7 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Saat usia kehamilan 10 minggu, janin memiliki panjang 32 &ndash; 43 mm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ yang Terbentuk</strong></p>\n" +
                            "<p>Janin mulai membentuk organ-organ di bawah ini :</p>\n" +
                            "<p>- Fungsi ginjal, hati, otak dan usus sudah bekerja</p>\n" +
                            "<p>- Kepala terbentuk dan membesar hampir setengah dari panjang tubuhnya</p>\n" +
                            "<p>- Kuku</p>";
                    tv_info.setText(Html.fromHtml(minggu10));
                } else if (week == 11) {
                    String minggu11 = "<h2>Perkembangan Janin Usia 11 Minggu (2 Bulan)</h2>\n" +
                            "<p>Saat usia dua bulan, janin akan mengalami banyak perkembangan dan perubahan pesat. Panjang janin pun sudah semakin panjang dan juga semakin berat.</p>\n" +
                            "<p><strong>1. Perkembangan Janin</strong></p>\n" +
                            "<p>Berikut ini berbagai proses perkembangan janin yang ada di dalam tubuh:</p>\n" +
                            "<p>- Tunas dari gigi muncul</p>\n" +
                            "<p>- Kaki terbelah menjadi dua yaitu bagian lutut telah terbentuk</p>\n" +
                            "<p>- Janin mulai menendang dan melakukan peregangan otot</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Tidak hanya psikis saja yang terlihat, namun juga perubahan fisik. Perubahan fisik itu adalah munculnya garis gelap secara vertikal di perut ibu hamil. Ibu hamil juga susah tidur.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin dalam usia ini memiliki ukuran sebesar 8 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin saat usia 11 minggu adalah 6,5 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Saat usia 11 minggu, janin akan membentuk berbagai organ di bawah ini :</p>\n" +
                            "<p>- Kuku</p>\n" +
                            "<p>- Lutut</p>\n" +
                            "<p>- Tunas gigi</p>\n" +
                            "<p>- Tulang ekor telah terbentuk</p>";
                    tv_info.setText(Html.fromHtml(minggu11));
                } else if (week == 12) {
                    String minggu12 = "<h2>Perkembangan Janin Usia 12 Minggu (3 Bulan)</h2>\n" +
                            "<p>Janin dalam usia ini sudah bertambah panjang dan berat. Bahkan pergerakan janin di dalam rahim pun semakin meningkat dan terasa. Berikut ini perkembangan janin di usia 12 minggu.</p>\n" +
                            "<p><strong>1. Proses Perkembangan Embrio</strong></p>\n" +
                            "<p>Janin akan mengalami berbagai proses perkembangan di dalam rahim seperti berikut ini :</p>\n" +
                            "<p>- Sel motorik janin semakin terasah dan terasa</p>\n" +
                            "<p>- Janin akan menggeliat</p>\n" +
                            "<p>- Sel-sel syaraf yang ada di janin terhubung dengan sel syaraf di otaknya</p>\n" +
                            "<p>- Janin bisa menutup jari-jari tangannya</p>\n" +
                            "<p>- Mengepalkan otot matanya</p>\n" +
                            "<p>- Meringkuk</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan sensasi yang luar biasa. Sebab ibu hamil dengan perut yang tipis bisa merasakan pergerakan janin. Morning sickness mulai hilang dan ibu hamil mulai mendapatkan kembali nafsu makannya.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Ukuran janin seberat 14 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin sekitar 63 mm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Janin mulai terbentuk bagian-bagian organnya dan juga mendekati sempurna. Organ yang terbentuk adalah :</p>\n" +
                            "<p>- Telinga mulai sempurna</p>\n" +
                            "<p>- Otot mata telah ada</p>\n" +
                            "<p>- Kelopak mata telah berfungsi</p>";
                    tv_info.setText(Html.fromHtml(minggu12));
                } else if (week == 13) {
                    String minggu13 = "<h2>Perkembangan Janin Usia 13 Minggu (3 Bulan)</h2>\n" +
                            "<p>Berikut ini berbagai macam perkembangan janin saat usianya 13 minggu.</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Janin saat usia 13 minggu sudah bisa melakukan berbagai macam proses di dalam rahim ibunya. Berikut ini berbagai macam proses yang dilalui dan dilakukan oleh janin :</p>\n" +
                            "<p>- Janin bisa menghisap otot-otot yang ada di dalam pipinya</p>\n" +
                            "<p>- Janin bisa bergerak memutar di rahim, apalagi ketika ibu hamil mengusap-usap perutnya</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan gejala-gejala di bawah ini :</p>\n" +
                            "<p>- Kelelahan</p>\n" +
                            "<p>- Pandangan mata berkabut</p>\n" +
                            "<p>- Sering buang air kecil</p>\n" +
                            "<p>- Perut ibu hamil mulai membesar dan membulat</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Dalam usia ini janin memiliki ukuran seberat 23 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin jika diukur mulai dari ujung kepala sampai dengan pantatnya panjang janin skitar 6,7 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>- Kepala janin membesar</p>\n" +
                            "<p>- Kelopak mata bayi bisa menutup dan membuka sendiri</p>\n" +
                            "<p>- Badan bayi mulai membesar mengkuti pembesaran kepala</p>";
                    tv_info.setText(Html.fromHtml(minggu13));
                } else if (week == 14) {
                    String minggu14 = "<h2>Perkembangan Janin Usia 14 Minggu (3 Bulan)</h2>\n" +
                            "<p>Saat usia 14 minggu, janin akan mengalami berbagai macam proses perkembangan.</p>\n" +
                            "<p><strong>1. Proses&nbsp;di dalam rahim</strong></p>\n" +
                            "<p>- Detak jantung bayi mulai menguat</p>\n" +
                            "<p>- Kelenjar prostat bayi mulai berkembang</p>\n" +
                            "<p>- Kelenjar kulit mulai menutupi tubuhnya</p>\n" +
                            "<p>- Bulu halus mulai muncul</p>\n" +
                            "<p>- Alis mulai tumbuh</p>\n" +
                            "<p>- Janin bisa mengemut ibu jarinya</p>\n" +
                            "<p>- Ginjal janin memproduksi urin</p>\n" +
                            "<p>- Urin membentuk ketuban</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan frekuensi buang air kecil lebih sering karena janin bisa memproduksi urin sendiri.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran 40 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang sekitar 8 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Dalam usia ini, janin akan memiliki berbagai macam organ. Diantaranya adalah :</p>\n" +
                            "<p>- Bulu halus termasuk alis dan rambut</p>\n" +
                            "<p>- Kulit menyelimuti seluruh tubuh</p>\n" +
                            "<p>- Wajah bayi terbentuk</p>\n" +
                            "<p>- Fungsi ginjal mulai mengeluarkan urin</p>";
                    tv_info.setText(Html.fromHtml(minggu14));
                } else if (week == 15) {
                    String minggu15 = "<h2>Perkembangan Janin Usia 15 Minggu (3 Bulan)</h2>\n" +
                            "<p>Bayi terus berkembang selama kebutuhan nutrisi&nbsp;gizi ibu hamil&nbsp;tercukupi di dalam tubuhnya.</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>- Janin akan memiliki sendi sehingga anggota tubuhnya bisa bergerak</p>\n" +
                            "<p>- Kemaluannya terus berkembang</p>\n" +
                            "<p>- Bayi juga mengalami cegukan</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil yang normal akan kehilangan morning sicknessnya. Sehingga usia kehamilan 15 minggu, sangat tepat bagi ibu hamil untuk melakukan berbagai macam olahraga ringan. Olahraga ini akan&nbsp;meningkatkan kebugaran ibu hamil.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran seberat 70 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin di dalam rahim memiliki panjang sekitar 9,1 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang terbentuk</strong></p>\n" +
                            "<p>Berikut ini berbagai macam organ yang terbentuk pada bayi :</p>\n" +
                            "<p>- Kemaluan</p>\n" +
                            "<p>- Sendi di seluruh organ tubuh</p>";
                    tv_info.setText(Html.fromHtml(minggu15));
                } else if (week == 16) {
                    String minggu16 = "<h2>Perkembangan Janin Usia 16 Minggu (4 Bulan)</h2>\n" +
                            "<p>Usia 16 minggu akan ada moment spesial bagi ibu hamil. Janin yang ada di dalam rahimnya mulai memiliki nyawa dan telah diberikan jenis kelamin oleh tuhan meskipun ibu hamil baru bisa melihatnya saat usia kehamilan 18 minggu.</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Janin di dalam rahim akan mengalami proses-proses berikut ini :</p>\n" +
                            "<p>- Janin akan aktif menarik-narik tali pusarnya. Tali pusar seperti mainan untuk janin di dalam rahim</p>\n" +
                            "<p>- Janin bisa menegakkan kepalanya saat dia merasa lelah</p>\n" +
                            "<p>- Fungsi peredaran darah dan juga saluran kemih telah bekerja penuh</p>\n" +
                            "<p>- Janin bisa menghirup dan menghembuskan cairan ketuban melalui paru-parunya</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Saat usia tepat 4 bulan, ibu hamil akan merasakan sakit pada sebelah sisi perutnya. Hal itu dikarenakan ligamen ada di setiap sisi rahim dijadikan peregangan untuk janin.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Usia 4 bulan janin memiliki ukuran sebesar 100 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang 10 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Pada usia 4 bulan, organ yang terbentuk pada janin adalah sistem peredaran darah dan juga sistem saluran kemih.</p>";
                    tv_info.setText(Html.fromHtml(minggu16));
                } else if (week == 17) {
                    String minggu17 = "<h2>Perkembangan Janin Usia 17 Minggu (4 Bulan)</h2>\n" +
                            "<p>Janin yang sudah memiliki nyawa akan terus berkembang. Berikut ini perkembangan yang ada pada janin dengan usia 17 minggu.</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Janin di dalam rahim ibu hamil akan mengalami proses berikut ini :</p>\n" +
                            "<p>- Tulang rawan janin mulai terbentuk, tulang rawan juga mulai mengeras</p>\n" +
                            "<p>- Myelin mulai membungkus bagian tulang belakang janin</p>\n" +
                            "<p>- Kelenjar keringat mulai muncul dan ada</p>\n" +
                            "<p>- Tali pusar dan plasenta semakin tumbuh dan besar</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan rahim membesar dan akan mengalami&nbsp;kesulitan untuk tidur. Pusar sudah tidak berada di tengah namun bergeser ke kanan karena rahim yang membesar, sehingga perlu diketahui&nbsp;posisi tidur yang baik saat kehamilan.</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Saat usia kehamilan memasuki 17 minggu, beratnya sebesar 140 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin di dalam rahim sekitar 11 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Berikut ini berbagai organ yang terbentuk saat usia janin 17 minggu:</p>\n" +
                            "<p>- Myelin di bagian tulang belakang</p>\n" +
                            "<p>- Tulang rawan</p>\n" +
                            "<p>- Kelenjar keringat</p>\n" +
                            "<p>- Penebalan dan penguatan tali pusar dan plasenta</p>";
                    tv_info.setText(Html.fromHtml(minggu17));
                } else if (week == 18) {
                    String minggu18 = "<h2>Perkembangan Janin Usia 18 Minggu (4 Bulan)</h2>\n" +
                            "<p>Berikut ini berbagai macam perkembangan janin saat usia kehamilan 18 minggu.</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Janin akan mengalami berbagai macam perkembangan. Diantaranya adalah sebagai berikut ini :</p>\n" +
                            "<p>- Saat bernapas dada janin akan naik dan turun</p>\n" +
                            "<p>- Pembuluh darah terlihat dari kulit tipisnya</p>\n" +
                            "<p>- Telinga sudah sedikit sempurna</p>\n" +
                            "<p>- Janin bisa menghisap ibu jarinya</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan hal-hal seperti tendangan kaki bayi meski dalam gerakan yang lembut. Ibu hamil tidak bisa tidur dengan posisi telentang, hal itu dikarenakan ibu hamil akan merasakan sesak pada pernafasannya. Ketika telentang uterus akan menekan vena akibatnya ibu hamil akan pusing diakibatkan kurang darah.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Ukuran janin sebesar 190 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin sekitar 12 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Janin juga akan mengalami perkembangan dan pembentukan organ. Saat usia 18 minggu organ akan mengalami penyempurnaan pembuluh darah dan juga sistem pernapasan.</p>";
                    tv_info.setText(Html.fromHtml(minggu18));
                } else if (week == 19) {
                    String minggu19 = "<h2>Perkembangan Janin Usia 19 Minggu (4 Bulan)</h2>\n" +
                            "<p>Janin saat usia 19 minggu akan mengalami berbagai macam proses di dalam rahim. Berikut ini adalah proses-prosesnya .</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Berikut ini proses yang dilakukan janin selama berada di dalam rahim :</p>\n" +
                            "<p>- Janin bisa menelan cairan ketuban</p>\n" +
                            "<p>- Janin terus memproduksi urin</p>\n" +
                            "<p>- Rambut kepala semakin tumbuh</p>\n" +
                            "<p>- Janin mulai bisa mendengar, membau, melihat dan menyentuh</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan gerakan bayi berupa peregangan dan gerakan janin. Ibu juga mulai merasakan gesekan yang ada di dinding perutnya. Kehamilan pertama akan merasakan gerakan bayi nyata di usia 19 minggu ini. Namun kehamilan kedua dan seterusnya bisa lebih awal dirasakan oleh ibu hamil.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran seberat 240 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin sekitar 14,2 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Berikut ini berbagai macam organ yang terbentuk di usia kehamilan 19 minggu.</p>\n" +
                            "<p>- Rambut semakin tumbuh</p>\n" +
                            "<p>- Motor sensorik janin semakin berkembang</p>\n" +
                            "<p>- Sel syaraf berkembang pesat sehingga janin bisa merasakan, mendengar, melihat dan membaui</p>\n" +
                            "<p>- Sel syaraf semain berkembang membentuk sistem syaraf yang semakin komplek</p>";
                    tv_info.setText(Html.fromHtml(minggu19));
                } else if (week == 20) {
                    String minggu20 = "<h2>Perkembangan Janin Usia 20 Minggu (5 Bulan)</h2>\\n\" +\n" +
                            "                            \"<p>Janin di dalam rahim ibu hamil akan mengalami berbagai macam proses perkembangan baik organ maupun aktivitas di dalam rahim ibunya.</p>\\n\" +\n" +
                            "                            \"<p><strong>1. Proses di Dalam Rahim</strong></p>\\n\" +\n" +
                            "                            \"<p>Janin di dalam rahim akan mengalami berbagai macam proses berikut ini :</p>\\n\" +\n" +
                            "                            \"<p>- Janin akan menelan lebih banyak air ketuban di minggu ini</p>\\n\" +\n" +
                            "                            \"<p>- Janin menyerap air lebih banyak</p>\\n\" +\n" +
                            "                            \"<p>&nbsp;</p>\\n\" +\n" +
                            "                            \"<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\\n\" +\n" +
                            "                            \"<p>Ibu hamil akan merasakan mudah lapar dan perutnya mudah keroncongan. Ibu hamil juga akan merasakan gejala anemia. Oleh sebab itu mengkonsumsi makanan kaya akan zat besi sangat diperlukan oleh tubuh, seperti&nbsp;buah buahan untuk ibu hamil.</p>\\n\" +\n" +
                            "                            \"<p>&nbsp;</p>\\n\" +\n" +
                            "                            \"<p><strong>3. Ukuran Janin</strong></p>\\n\" +\n" +
                            "                            \"<p>Janin memiliki ukuran seberat 290 gram.</p>\\n\" +\n" +
                            "                            \"<p>&nbsp;</p>\\n\" +\n" +
                            "                            \"<p><strong>4. Panjang Janin</strong></p>\\n\" +\n" +
                            "                            \"<p>Janin memiliki panjang sekitar 26 cm.</p>\\n\" +\n" +
                            "                            \"<p>&nbsp;</p>\\n\" +\n" +
                            "                            \"<p><strong>5. Organ Yang Terbentuk</strong></p>\\n\" +\n" +
                            "                            \"<p>Janin akan memiliki perkembangan organ-organ di bawah ini :</p>\\n\" +\n" +
                            "                            \"<p>- Kelenjar lemak telah terbentuk. Kelenjar lemak juga akan melindungi kulit bayi.</p>\\n\" +\n" +
                            "                            \"<p>- Sistem pencernaan mulai terbentuk dan lebih baik dibandingkan minggu-minggu sebelumnya.</p>";
                    tv_info.setText(Html.fromHtml(minggu20));
                } else if (week == 21) {
                    String minggu21 = "<h2>Perkembangan Janin Usia 21 Minggu (5 Bulan)</h2>\n" +
                            "<p>Usia 5 bulan atau 21 minggu bayi mengalami berbagai macam proses di dalam rahim ibunya.</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Janin akan mengalami atau melakukan hal-hal di bawah ini selama berada di dalam rahim sang ibu :</p>\n" +
                            "<p>- Alis mata janin bisa berkembang sepenuhnya</p>\n" +
                            "<p>- Janin bisa berkedip perlahan karena kelopak mata telah sepenuhnya terkembang</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Di dalam perut, ibu hamil juga meraskaan janin semakin aktif. Akan ada dorongan dan tendangan di dalam perutnya. Ibu hamil merasakan senang dengan gerakan aktif dari janin tersebut. Ibu hamil juga merasakan nyaman. Nyeri, kelelahan dan mual sudah hilang sepenuhnya.</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran 160 gram.</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang 27 cm.</p>\n" +
                            "<p><strong>4. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Janin akan membentuk alis dan kelopak mata yang sempurna sehingga bisa membuatnya berkedip.</p>";
                    tv_info.setText(Html.fromHtml(minggu21));
                } else if (week == 22) {
                    String minggu22 = "<h2>Perkembangan Janin Usia 22 Minggu (5 Bulan)</h2>\n" +
                            "<p>Janin di usia 22 minggu bisa melakukan hal-hal yang tidak disangka oleh ibu hamil. Janin juga berkembang lebih sempurna dibandingkan dengan minggu sebelumnya.</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Janin akan mengalami proses seperti di bawah ini :</p>\n" +
                            "<p>- Janin bisa meringkuk dengan menekuk kakinya</p>\n" +
                            "<p>- Lemak belum berkembang sempurna</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Nafsu makan ibu hamil akan terus meningkat. Setiap minggu ibu hamil akan mendapatkan kenaikan berat badan sekitar 225 gram. Menginginkan makan makanan tertentu juga akan terjadi, termasuk makanan&nbsp;larangan&nbsp;ibu hamil.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin seberat 430 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang 28 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Saat janin di minggu ini, pankreas semakin berkembang pesat. Sedangkan kelenjar lemak belum berkembang secara sempurna. Hormon di dalam pankeras pun juga akan berkembang terus.</p>";
                    tv_info.setText(Html.fromHtml(minggu22));
                } else if (week == 23) {
                    String minggu23 = "<h2>Perkembangan Janin Usia 23 Minggu (5 Bulan)</h2>\n" +
                            "<p>Janin akan semakin menggemaskan di dalam rahim ibunya. Berikut ini berbagai macam proses perkembangan janin yang dilakukan&nbsp;menjelang bulan ke 6 ini.</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Pada saat minggu ini bayi akan mulai merespon berbagai macam suara yang ada di sekitarnya begitu pula dengan suara ibunya. Janin juga bisa mendengar detak jantung ibunya dan juga suara perut ibunya.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan berbagai macam gangguan misalnya saja gusi berdarah, pusar semakin menjorok ke dalam.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran 500 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin juga berkembang pesat yaitu sekitar 29 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Organ yang terbentuk dalam minggu ini adalah pendengaran bayi lebih sempurna, paru-paru mempersiapkan diri untuk bernafas.</p>";
                    tv_info.setText(Html.fromHtml(minggu23));
                } else if (week == 24) {
                    String minggu24 = "<h2>Perkembangan Janin Usia 24 Minggu (6 Bulan )</h2>\n" +
                            "<p>Janin dalam usia 24 minggu mengalami perkembangan pesat terutama di bagian otaknya.</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Janin akan sering meregangkan tangan dan sering menendang menggunakan kakinya.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan perbuahan hormon di dalam tubuhnya. Dia akan merasakan gatal saat berkeringat, payudara gatal dan kencang. Jika di garuk akan menimbulkan stretch mark di perut dan kulit. Menggunakan bra yang tepat bisa mengurangi rasa gatal pada payudara ibu hamil. Mata mudah menjadi kering dan mengalami iritasi.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin minggu ini memiliki ukuran 600 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang 30 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Terbentuk</strong></p>\n" +
                            "<p>Banyak organ yang terbentuk, diantaranya adalah sebagai berikut ini :</p>\n" +
                            "<p>- Otak berkembang pesat</p>\n" +
                            "<p>- Paru-paru berkembang</p>\n" +
                            "<p>- Sidik kaki dan sidik jari terbentuk</p>";
                    tv_info.setText(Html.fromHtml(minggu24));
                } else if (week == 25) {
                    String minggu25 = "<h2>Perkembangan Janin Usia 25 Minggu (6 Bulan)</h2>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Saat janin berusia 25 minggu, janin akan mengalami proses berupa indera yang lebih canggih. Otak janin juga peka terhadap sentuhan.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>1. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan gatal karena perubahan hormon, stretch mark semakin melebar.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran 660 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang 35 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Berikut ini organ yang terbentuk pada minggu ini :</p>\n" +
                            "<p>- Indera lebih sempurna dibandingkan sebelumnya.</p>\n" +
                            "<p>- Bayi akan mengeluarkan keringat melalui kulit keriputnya.</p>\n" +
                            "<p>- Syaraf optik otak bekerja dan berfungsi.</p>";
                    tv_info.setText(Html.fromHtml(minggu25));
                } else if (week == 26) {
                    String minggu26 = "<h2>Perkembangan Janin Usia Minggu Ke-26&nbsp;(6 Bulan)</h2>\n" +
                            "<p><strong>1.&nbsp;Perkembangan di Dalam Rahim</strong></p>\n" +
                            "<p>Bayi bisa mendengarkan suara ibunya atau ayahnya, akan merespon menggunakan gerakan. Janin juga akan lebih tanggap dengan suasana hati ibunya. Jika sedih bayi akan terus bergerak di dalam perut. Janin juga akan sering mengambil nafas lebih sering di dalam rahim.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan gerakan bayi berupa tendangan atau peregangan kaki atau bayi yang menarik tali pusar. Hal itu bisa terjadi ketika anda mengajaknya berbicara.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki berat 760 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang 36 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>.5. Organ Terbentuk</strong></p>\n" +
                            "<p>Perkembangan organ terjadi di minggu ini terutama perkembangan organ otak. Sensorik dan motoriknya juga berkembang pesat.</p>";
                    tv_info.setText(Html.fromHtml(minggu26));
                } else if (week == 27) {
                    String minggu27 = "<h2>Perkembangan Janin Usia 27 Minggu (6 Bulan)</h2>\n" +
                            "<p><strong>1.&nbsp;Proses Perkembangan di Dalam Rahim</strong></p>\n" +
                            "<p>Bayi saat di dalam rahim ibunya mulai aktif membuka dan menutup matanya. Kelopak matanya mulai bekerja secara sempurna. Janin aktif menghisap ibu jari jempol. Bayi juga akan cegukan.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan peregangan dan tendangan saat janin terbangun dari tidurnya sebab saat minggu ini janin mulai tidur dan bangun. Ibu hamil juga merasakan sesak nafas. Kram, sembelit dan juga kaki bengkak akan dirasakan oleh ibu hamil.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memilikin berat 875 gram.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin sekitar 36 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Terbentuk</strong></p>\n" +
                            "<p>Janin akan memiliki otak dan mata yang aktif.</p>";
                    tv_info.setText(Html.fromHtml(minggu27));
                } else if (week == 28) {
                    String minggu28 = "<h2>Perkembangan Janin Usia 28 Minggu (7 Bulan)</h2>\n" +
                            "<p>7 bulan adalah saat janin sudah benar-benar membentuk manusia kecil, ibu hamil juga mulai bisa mendeteksi jenis kelamin bayinya.</p>\n" +
                            "<p><strong>1. Proses Perkembangan Di Dalam Rahim</strong></p>\n" +
                            "<p>Saat janin berusia 28 minggu janin sudah bisa melakukan berbagai proses. Proses tersebut adalah berikut ini :</p>\n" +
                            "<p>- Janin mulai bisa membuka mata dan bisa mengubah posisi kepalanya di dalam rahim ibunya.</p>\n" +
                            "<p>- Janin sudah bisa berkedip secara sempurna terutama jika dia melihat cahaya terang dan terus menerus.</p>\n" +
                            "<p>- Tulang janin belum keras dan juga kokoh.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan peregangan dari janin yang dikandungnya. Tidak hanya itu saja, tendangan demi tendangan pun akan dirasakan ibu hamil. Kini janin bisa menampakkan kaki atau sikunya, sehingga perut ibu hamil menonjol-nonjol.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Saat janin berusia 28 minggu, maka janin akan memiliki berat 1 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran panjang 38 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ yang Terbentuk</strong></p>\n" +
                            "<p>Saat usia kandungan 28 minggu janin telah memiliki bulu mata dan lapisan lemak terbentuk lebih sempurna.</p>";
                    tv_info.setText(Html.fromHtml(minggu28));
                } else if (week == 29) {
                    String minggu29 = "<h2>Perkembangan Janin Minggu Ke-29 (7 Bulan)</h2>\n" +
                            "<p>Dalam minggu ini, janin akan lebih aktif dibandingkan dengan sebelumnya. Di dalam rahim janin akan terus berkembang terutama tulang dan organ-organ yang lainnya.</p>\n" +
                            "<p><strong>1.&nbsp;Perkembangan di Dalam Rahim</strong></p>\n" +
                            "<p>Berikut ini berbagai macam proses yang akan dialami dan dilakukan oleh bayi :</p>\n" +
                            "<p>- Rambut di atas kepala mulai tumbuh dengan baik</p>\n" +
                            "<p>- Jika jenis kelaminnya laki-laki, maka janin mengalami penurunan buah zakar dan turun mendekati ginjal. Penurunan itu melalui pangkal paha dan mulai berjalan ke arah skrotum.</p>\n" +
                            "<p>- Jika perempuan, maka klitoris dari perempuan tersebut akan menonjol. Labianya belum tumbuh dengan sempurna sampai mendekati kelahiran.</p>\n" +
                            "<p>- Kepala bayi semakin membesar dan otaknya juga semakin berkembang.</p>\n" +
                            "<p>- Janin bisa bereaksi terhadap sentuhan jari ibunya di atas perut dan suara yang dia dengar.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Saat fase ini, ibu hamil akan susah tidur karena bayi sangat aktif terutama bayi laki-laki. Ibu hamil akan merasakan rambut janin, jika janin itu menempel pada dinding perutnya. Benjolan-benjolan di perut juga akan semakin terasa dan sering.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin di usia minggu ke-29 akan memiliki berat lebih dari 1 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin saat usia memasuki 19 minggu dia memiliki panjang 39 cm. Di ukur dari atas kepala sampai dengan bokong.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Organ yang terbentuk lebih sempurna adalah kepala dan otak. Selain itu ada clitoris dan skrotum yang mulai berkembang dan muncul meski belum sempurna.</p>";
                    tv_info.setText(Html.fromHtml(minggu29));
                } else if (week == 30) {
                    String minggu30 = "<h2>Perkembangan Janin Minggu Ke- 30 (7 Bulan)</h2>\n" +
                            "<p>Janin mengalami perkembangan secara pesat terutama di bagian paru-paru. Bayi yang dilahirkan kurang dari usia 30 minggu banyak yang mengalami masalah di paru-parunya sebab saat janin berusia 30 minggu paru-paru hampir terbentuk sempurna.</p>\n" +
                            "<p><strong>1. Proses yang Terjadi di Dalam Rahim</strong></p>\n" +
                            "<p>Proses yang dialami oleh janin di dalam rahim adalah :</p>\n" +
                            "<p>- Janin bisa melacak datangnya sumber cahaya yang dia lihat.</p>\n" +
                            "<p>- Jika perut disinari, janin bisa menggerakkan kepalanya mengikuti kemana cahaya tersebut bergerak.</p>\n" +
                            "<p>- Bayi juga memiliki perkembangan visual.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu sering merasakan sesak nafas, hal itu dikarenakan bayi atau janin mulai menekan diafragma sang ibu. Ibu hamil seolah-olah kekurangan udara, untuk mengatasinya ibu hamil bisa berjalan-jalan ringan atau melakukan&nbsp;senam hamil, untuk mengatur pernafasannya. Bagi kehamilan kedua dan ketiga, ibu hamil akan merasakan janinnya bergerak ke arah panggul dan membuat pernafasan lebih mudah dan lega.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin dalam usia ini memiliki berat 1,3 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin sekitar 39 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Organ yang terbentuk pada tubuh janin adalah paru-paru dan sistem pencernaannya yang mendekati sempurna.</p>";
                    tv_info.setText(Html.fromHtml(minggu30));
                } else if (week == 31) {
                    String minggu31 = "<h2>Perkembangan Janin Usia 31 Minggu (7 Bulan)</h2>\n" +
                            "<p>Janin pada usia ini akan memiliki berbagai macam proses dan perkembangan. Baik itu pergerakan tubuhnya maupun dengan organ-organnya.</p>\n" +
                            "<p><strong>1. Perkembangan di Dalam Rahim</strong></p>\n" +
                            "<p>Janin di dalam rahim akan mengalami berbagai macam proses. Jari kaki dan tangannya terus bergerak. Pergerakan bayi pun semakin meningkat. Bayi bisa menggeliat dan menendang. Banyak pertumbuhan yang masih harus dia lewatkan atau lalui.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Janin di dalam tubuh akan menggeliat sehingga ibu hamil akan merasakannya. Janin juga menendang, ibu hamil akan merasakannya. Dalam seminggu berat badan ibu hamil bisa naik sebanyak 450 gram. Hal itu merupakan hal normal.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki berat 1,5 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang 41 cm. Panjang tersebut merupakan panjang proporsional bagi janin di dalam rahim.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Tangan terutama lengan dan kaki akan terus tumbuh. Jari tangan dan kaki semakin meningkat. Tubuh janin akan menyesuaikan dengan kepala bayi.</p>";
                    tv_info.setText(Html.fromHtml(minggu31));
                } else if (week == 32) {
                    String minggu32 = "<h2>Perkembangan Janin Usia 32 Minggu (8 Bulan)</h2>\n" +
                            "<p>Proses yang akan dilalui janin adalah berikut ini :</p>\n" +
                            "<p><strong>1. Perkembangan Bayi</strong></p>\n" +
                            "<p>Janin dalam tahap ini memiliki rambut yang tumbuh. Pada bayi yang rambutnya lebat, bayi akan suka mengelus-eluskan rambutnya pada dinding perut ibunya. Bayi yang berambut halus rambut hanya bisa muncul beberapa helai saja.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan mendapatkan berat badan yang lebih, yaitu lebih dari 450 gram selama seminggu. Benjolan di perut juga semakin besar dibandingkan sebelumnya.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin akan memiliki berat 1,7 kg</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Janin akan memiliki panjang sekitar 42 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Dalam tahap ini, janin akan mengalami pertumbuhan organ diantaranya adalah sebagai berikut :</p>\n" +
                            "<p>- Kulit bayi akan lebih tebal dibandingkan minggu sebelumnya, kulitnya juga lembut dan halus.</p>\n" +
                            "<p>- Laki-laki akan mengalami perkembangan organ berupa buah zakar turun dari perut menuju skrotum. Namun pada kelahiran bayi laki-laki tertentu, testis tidak akan turun sampai bayi berumur satu tahun.</p>\n" +
                            "<p>- Untuk janin perempuan, labia mulai berkembang sedikit demi sedikit.</p>";
                    tv_info.setText(Html.fromHtml(minggu32));
                } else if (week == 33) {
                    String minggu33 = "<h2>Perkembangan Janin Usia 33 Minggu (8 Bulan)</h2>\n" +
                            "<p>Janin pada usia 33 minggu sudah bersiap-siap lahir, sehingga posisinya sudah mendekati panggul.</p>\n" +
                            "<p><strong>1. Perkembangan Janin</strong></p>\n" +
                            "<p>Janin akan mengalami dan melakukan hal-hal di bawah ini :</p>\n" +
                            "<p>- Janin suka berputar-putar dan posisinya suka terbalik-balik.</p>\n" +
                            "<p>- Kepala janin sering menunjuk ke bawah atau berada di bawah.</p>\n" +
                            "<p>- Bidan akan melakukan pengawasan terhadap pergerakan dan perputaran bayi dalam beberapa minggu selanjutnya.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan kepala bayi berada di panggul sedangkan ibu yang sudah berkali-kali menghadapi kehamilan, hal itu akan terjadi seminggu sebelum ibu hamil tersebut menghadapi persalinan. Ibu hamil juga akan merasakan bengkak di kedua kakinya. Ibu hamil juga merasakan dehidrasi yang tinggi. Frekuensi buang air kecil pun semakin bertambah.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin akan memiliki ukuran 2,2 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin di dalam rahim adalah 44 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ yang Terbentuk</strong></p>\n" +
                            "<p>Berikut ini berbagai macam organ yang mengalami perkembangan dan pembentukan :</p>\n" +
                            "<p>- Tengkorak bayi terus tumbuh meski masih lentur.</p>\n" +
                            "<p>- Pelat tengkorak saling menyatu satu sama lain.</p>\n" +
                            "<p>- Tulang-tulang tubuh janin mulai mengeras.</p>\n" +
                            "<p>- Pembentukan lemak mulai sempurna dan menumpuk pada lapisan bawah kulit.</p>";
                    tv_info.setText(Html.fromHtml(minggu33));
                } else if (week == 34) {
                    String minggu34 = "<h2>Perkembangan Janin Usia 34 Minggu ( #8 Bulan )</h2>\n" +
                            "<p>Janin akan mengalami berbagai macam perkembangan. Berikut ini berbagai macam perkembangan yang dialami oleh bayi usia 34 minggu.</p>\n" +
                            "<p><strong>1. Proses Perkembangan Janin Usia 34 Minggu</strong></p>\n" +
                            "<p>Pada saat usia 34 minggu janin bisa mendengarkan semua perkataan ibunya. Hal itu dikarenakan organ pendengaran janin hampir terbentuk dengan sempurna.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil saat usia kandungannya 34 minggu, dia akan suka mengobrol pada janin yang ada di dalam perutnya. Ibu hamil juga akan merasakan kesemutan dengan daerah panggul mereka. Hal itu dikarenakan panggul akan longgar dan siap untuk melakukan persalinan. Kesemutan berbeda dengan sakit. Jika panggul sakit konsultasikan dengan bidan&nbsp;atau dokter tempat ibu hamil periksa.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin pada saat ini memiliki berat 2,2 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang sekitar 45 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Terbentuk</strong></p>\n" +
                            "<p>Janin pada usia ini akan memiliki organ pendengaran yang hampir sempurna. Paru-paru hampir sempurna dan syaraf pusat janin belum terbentuk dengan baik. Bagi kelahiran prematur, janin dengan usia 34 minggu bisa bertahan di luar rahim sebanyak 99 persen, namun sementara waktu diletakkan di ruang inkubator.</p>";
                    tv_info.setText(Html.fromHtml(minggu34));
                } else if (week == 35) {
                    String minggu35 = "<h2>Perkembangan Janin Usia 35 Minggu (8 Bulan)</h2>\n" +
                            "<p>Berbagai macam proses akan dilakukan oleh bayi. Proses tersebut adalah berikut ini :</p>\n" +
                            "<p><strong>1. Proses di Dalam Rahim</strong></p>\n" +
                            "<p>Janin akan menggeliat terutama saat bangun tidur, janin juga akan mengembangkan berbagai hal yang selama ini dia lakukan selama di dalam rahim. Misalnya saja menggeliat lebih sering, menendang lebih sering.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan hal-hal di bawah ini :</p>\n" +
                            "<p>- Siku janin akan menonjol di perut ibu hamil.</p>\n" +
                            "<p>- Ibu hamil mengalami kelonjakan berat badan 11 kg sampai dengan 13,6 kg.</p>\n" +
                            "<p>- Pusar ibu hamil bisa menyembul keluar dan terlihat lebih besar dan lebar.</p>\n" +
                            "<p>- Sesak nafas dan gangguan pencernaan karena rahim bersama janin tepat berada di bawah tulang rusuk.</p>\n" +
                            "<p>- Merangkak bisa membuat ibu hamil memiliki nafas lega.</p>\n" +
                            "<p>- Mendapatkan tekanan di kandung kemih sehingga sering buang air kecil.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran seberat 2,4 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang 46 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Janin memiliki perkembangan organ seperti dibawah ini :</p>\n" +
                            "<p>- Kuku kaki dan kuku tangan telah lengkap terisi</p>\n" +
                            "<p>- Ginjal berkembang dan menghasilkan urin</p>\n" +
                            "<p>- Hati juga mengeluarkan limbah atau bersekresi</p>";
                    tv_info.setText(Html.fromHtml(minggu35));
                } else if (week == 36) {
                    String minggu36 = "<h2>Perkembangan Janin Usia 36 Minggu (9 Bulan)</h2>\n" +
                            "<p>Usia 9 bulan adalah masa yang dinanti-nanti. Sebab kelahiran malaikat kecil sudah hampir tiba. Ibu hamil banyak yang nerves dan merasa khawatir saat kandungannya memasuki usia ini.</p>\n" +
                            "<p><strong>1. Proses Perkembangan Janin</strong></p>\n" +
                            "<p>Di dalam rahim, janin sudah memposisikan kepalanya di dekat panggul sang ibu.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan hal-hal yang tidak nyaman namun normal. Rasa itu adalah :</p>\n" +
                            "<p>- Tekanan pada perut meningkat karena janin bertahap menurun sedikit demi sedikit.</p>\n" +
                            "<p>- Ibu hamil merasakan tidak nyaman saat berjalan.</p>\n" +
                            "<p>- Ibu hamil merasakan bayi akan rontok ke bawah karena bayi terasa di bawah perut mereka.</p>\n" +
                            "<p>- Ibu hamil sering pergi ke toilet.</p>\n" +
                            "<p>- Banyak ibu hamil yang melahirkan di usia kandungan tepat 9 bulan.</p>\n" +
                            "<p>- Payudara ibu hamil seperti bocor dan mengeluarkan cairan.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran 28 gram atau 2,8 kg</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin sekitar 47 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Paru-paru, ginjal dan hati bekerja secara sempurna. Mengeluarkan urin dan sekresi.</p>";
                    tv_info.setText(Html.fromHtml(minggu36));
                } else if (week == 37) {
                    String minggu37 = "<h2>Perkembangan&nbsp; Janin Usia 37 Minggu (9 Bulan)</h2>\n" +
                            "<p><strong>1. Proses Yang Terjadi Di Dalam Rahim</strong></p>\n" +
                            "<p>Janin akan memiliki banyak rambut namun ada juga bayi yang dilahirkan tanpa rambut sama sekali. Bulu-bulu halus juga mulai muncul. Substansi berwarna putih juga akan menutupi tubuh janin. Janin bisa menelan lanugo dan menyimpannya di dalam perut mereka. Bayi mengeluarkan limbah berupa mekonium yang berwarna kehitaman.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil banyak yang khawatir dengan persalinan mereka. Tidur tidak nyenyak, tidur telentang juga akan merasakan sesak nafas.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran seberat 2,9 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin akan sekitar 48,6 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Yang Terbentuk</strong></p>\n" +
                            "<p>Organ janin yang terbentuk adalah selaput keputihan penutup tubuh bayi.</p>";
                    tv_info.setText(Html.fromHtml(minggu37));
                } else if (week == 38) {
                    String minggu38 = "<h2>Perkembangan Janin Minggu Ke-38 (9 Bulan)</h2>\n" +
                            "<p><strong>1. Perkembangan Bayi di Dalam Rahim</strong></p>\n" +
                            "<p>Bayi akan terus membangun kelenjar lemak di dalam tubuhnya. Janin juga aktif berkedip bahkan bergerak di dekat panggul, membuat ibu hamil merasakan kesemutan lebih sering di daerah panggul.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hmil</strong></p>\n" +
                            "<p>Ibu hamil akan merasakan kesemutan. Ibu hamil juga merasakan perut bawah terasa berat untuk dibawa kemanapun.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin memiliki ukuran seberat 3 kg bahkan sampai 3,2 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin akan memiliki panjang sekitar 39 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Janin Yang Terbentuk</strong></p>\n" +
                            "<p>Paru-paru sudah sepenuhnya sempurna. Banyak juga yang ibu hamil melahirkan di usia 9 bulan lebih 3 minggu.</p>";
                    tv_info.setText(Html.fromHtml(minggu38));
                } else if (week == 39) {
                    String minggu39 = "<h2>Perkembangan Janin Usia 39 Minggu ( #9 Bulan )</h2>\n" +
                            "<p>Sebenarnya orang Indonesia memiliki waktu atau masa kelahiran sekitar 9 bulan lebih 11 hari, 9 bulan lebih 2 minggu. Namun orang luar dan sedikitnya orang Indonesia mengalami kehamilan 39 minggu sampai dengan usia 40 minggu.</p>\n" +
                            "<p><strong>1. Perkembangan Janin di Dalam Rahim</strong></p>\n" +
                            "<p>Janin tidak berpindah tempat, kepala tetap berada di sekitar panggul. Namun ibu hamil dengan banyak ketuban bisa membuat janinnya berpindah-pindah posisi. Bayi bisa memiliki minyak, minyak itu bisa melindungi kulitnya. Jaringan kulit dan lapisan kulit telah sempurna.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil dengan usia kandungan ini dan belum merasakan tanda-tanda kelahiran akan khawatir. Kesemutan, pegal dan perut bawah semakin terasa berat.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Ukuran janin saat minggu ini 3,4 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Janin memiliki panjang 50 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Terbentuk</strong></p>\n" +
                            "<p>Organ yang terbentuk adalah kelenjar minyak. Lapisan kulit luar dan rambut janin lebih menebal. Usia minggu ini tengkorak bayi belum menyatu.</p>";
                    tv_info.setText(Html.fromHtml(minggu39));
                } else if (week == 40) {
                    String minggu40 = "<h2>Perkembangan Janin Usia 40 Minggu (10 Bulan)</h2>\n" +
                            "<p>Minggu ke-40 adalah tahap terakhir kehamilan. Jika ibu hamil belum merasakan tanda-tanda kelahiran, periksakan diri ke dokter dan bidan.</p>\n" +
                            "<p><strong>1. Proses Perkembangan Janin Si Dalam Rahim</strong></p>\n" +
                            "<p>Janin terus membentuk kelenjar minyak dan juga membentuk zat vernix caseosa yang bisa melindungi kulit tubuh janin. Oleh sebab itu bayi yang baru dilahirkan akan berkulit putih, namun saat keluar dari rahim selama seminggu zat putih itu akan hilang. Naluri janin bisa menemukan ibu jari dan menghisapnya.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Yang Dirasakan Ibu Hamil</strong></p>\n" +
                            "<p>Ibu hamil saat usia kehamilan ini ada yang mengalami kontraksi dan air ketuban pecah, punggung pegal dan perut mulas. Hal itu menandakan ibu hamil akan segera melahirkan, jika belum ada tanda itu, ibu hamil harus segera di induksi.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Ukuran Janin</strong></p>\n" +
                            "<p>Janin bisa mencapai ukuran 3,8 kg.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Panjang Janin</strong></p>\n" +
                            "<p>Panjang janin sekitar 51 cm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>5. Organ Terbentuk</strong></p>\n" +
                            "<p>Cairan ketuban telah terbentuk sempurna, selain itu organ-organ yang lainnya juga telah sempurna. Semua indera janin juga sudah lengkap sehingga dia siap untuk dilahirkan di dunia ini.</p>";
                    tv_info.setText(Html.fromHtml(minggu40));
                }else if (week > 40) {
                    String mingguover = "<h2>Usia Kehamilan Normal Tidak Melebihi 40 Minggu</h2>";

                    tv_info.setText(Html.fromHtml(mingguover));
                }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private static long daysBetween(Calendar now, Calendar date1) {
        long lama = 0;
        Calendar tanggal = (Calendar) now.clone();
        while (tanggal.before(date1)) {
            tanggal.add(Calendar.DAY_OF_MONTH, 1);
            lama++;
    }
        return lama;
    }


}