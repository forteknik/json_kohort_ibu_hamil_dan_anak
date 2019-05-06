package com.forteknik.kohort.Menu;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.NumberPicker;

import com.forteknik.kohort.R;

public class TipsMakananIbuHamil extends Activity {

    TextView isi, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_makanan_ibu_hamil);


        final TextView tv = (TextView) findViewById(R.id.tv);
        title = (TextView) findViewById(R.id.title);
        isi = (TextView) findViewById(R.id.isi);
        isi.setMovementMethod(new ScrollingMovementMethod());
        final NumberPicker np = (NumberPicker) findViewById(R.id.np);
        np.setMinValue(1);
        np.setMaxValue(8);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldBulan, int newBulan){

                //Display the newly selected number from picker
                tv.setText("Makanan Sehat Ibu Hamil " + newBulan+ " Bulan");

                if (newBulan <= 1){
                    String bulan1 = "<h3>Berikut ini adalah langkah yang dapat anda lakukan untuk memilih makanan yang tepat bagi kehamilan di bulan pertama :</h3>\n" +
                            "<p><strong>1. Folid Acid</strong></p>\n" +
                            "<p>Pada bulan pertama kehamilan, umumnya baru disadari oleh ibu hamil setelah kehamilan 2-3 minggu. Sehingga sangat penting untuk segera menyesuaikan kebutuhan nutrisi ibu hamil. Salah satu yang diperlukan oleh ibu hamil di bulan pertama adalah folid acid. Folid acid terdapat pada kembang kol, brokoli, jagung, wortel, kacang kedelai, jambu, alpukat dan gandum.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>2. Asam Folat</strong></p>\n" +
                            "<p>Makanan yang sangat diperlukan pada bulan ke 1. Sayuran hijau memang baik karena mengandung asam folat.Salah satu sayur hijau yang tinggi asam folat adalah bayam. Asam folat memiliki peran di awal kehamilan yaitu untuk mencegah kecacatan tabung saraf. Bahkan kandungan asam folat akan mengurangi risiko cacat lahir otak dan juga sumsung tulang belakang. Lengkapi kebutuhan asam folat denga mencukupi 600 mcg per hari. Bahkan asam folat sangat baik untuk dikonsumsi 3 bulan sebelum kehamilan. Bahkan di 4 minggu pembentukan janin pertama. Konsumsi makanan yang kaya asam folat yaitu kacang merah, kuning telur, sereal, susu dan sayuran hijau.</p>\n" +
                            "<div class=\"code-block code-block-4\">&nbsp;</div>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>3. Zat besi</strong></p>\n" +
                            "<p>Tidak kalah penting untuk ibu hamil di bulan pertama untuk melengkapi zat besi. Pada awal kehamilan zat besi bermanfaat untuk membentuk sel darah merah bagi ibu hamil dan janin. Sehingga ibu hamil menyiapkan diri untuk adaptasi dengan usia kehamilan selanjutnya dan mengurangi risiko lemah,letih dan juga lesu.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p><strong>4. Kalsium</strong></p>\n" +
                            "<p>Pada minggu ke 4 memiliki kebutuhan kalsium yang tinggi. Kalsium memiliki manfaat untuk membentuk tulang janin. Janin yang kekurangan kalsium maka akan mengambil cadangan kalsium dari ibu sehingga ibu berisiko tinggi mengalami keropos tulang.&nbsp; &nbsp;</p>\n" +
                            "<p>Dengan demikian sangat penting untuk ibu hamil menjaga kehamilan di satu bulan kehamilan. Untuk memberikan kebutuhan nutrisi anda dapat mempersiapkan sebelum ibu hamil untuk menjaga kondisi ibu sebelum terjadi pembuahan .</p>";
                    isi.setText(Html.fromHtml(bulan1));
                }else if (newBulan <= 2){
                    String bulan2 = "<p><strong>1. Asam folat</strong></p>\n" +
                            "<p>Asam folat memang penting untuk ibu hamil. Di awal anda merencanakan kehamilan. Kandungan asam folat ada pada sayuran hijau , hati sapi, alpukat dan jeruk. Dengan asam folat yang tercukupi maka akan membantu dalam sistem saraf pusat dan juga darah janin. Bahkan asam folat yang tercukupi maka akan mengurangi risiko bayi lahir cacat.</p>\n" +
                            "<p><strong>2. Zat Besi</strong></p>\n" +
                            "<p>Pada bulan ke dua kehamilan, anda membutuhkan zat besi. Zat besi yang kurang pada ibu hamil maka akan menganggu siklus pelepasan sel telur pada ibu hamil. Zat besi terdapat pada sayuran hijau, daging merah, kuning telur, jeruk dan hati.</p>\n" +
                            "<p><strong>3. Zat Seng</strong></p>\n" +
                            "<p>Taukah anda bahwa zat seng juga dapat membantu dalam produksi materi generatik ketika terjadinya pembuahan. Adapun makanan yang mengandung zat besi diantaranya adalah , yaitu kacang-kacangan, biji-bijian, telur dan daging.</p>\n" +
                            "<div class=\"code-block code-block-4\">&nbsp;</div>\n" +
                            "<p><strong>4. Vitamin C</strong></p>\n" +
                            "<p>Lengkapi konsumsi makanan anda dengan kandungan vitamin C.Vitamin C dapat membantu pembentukan jaringan tubuh janin selain itu dengan kandungan vitamin C yang tercukupi maka akan membantu dalam penyerapan besi yang maksimal dan mengurangi risiko preeklampsia. Adapun sumber vitamin C yang baik untuk ibu hamil di bulan ke dua yaitu jeruk, kiwi,&nbsp; brokoli dan strowberry.</p>\n" +
                            "<p><strong>5. Vitamin A, B , D dan E</strong></p>\n" +
                            "<p>Tidak kalah penting yaitu vitamin A, B, D dan E yang dapat membantu tumbuh kembang dalam membentuk sel darah baru , pembentukan gigi , tulang dan metabolisme. Oleh karena itu lengkapi semua vitamin dari buah dan sayur yang bervariasi warna.</p>\n" +
                            "<p><strong>6. Karbohidrat</strong></p>\n" +
                            "<p>Tidak kalah penting adalah kandungan karbohidrat. Pada bulan ke dua pertumbuhan janin sangat pesat sehingga kebutuhan energi ibu hamil harus tetap terjaga. Anda dapat mengatur asupan karbohidrat meskipun morning sickness mengganggu.</p>";
                    isi.setText(Html.fromHtml(bulan2));
                }else if (newBulan <= 3){
                    String bulan3 = "<p><strong>1. Kacang Almond</strong></p>\n" +
                            "<p>Kacang almond mengandung vitamin E, protein dan omega 3. Kandungan vitamin E yang ada di dalam kacang almond akan meningkatkan jumlah darah di dalam tubuh, vitamin E menyiapkan tempat janin dan menyiapkan makan selama di dalam kandungan. Kekurangan vitamin E akan mengakibatkan bahaya pada janin dan ibu hamil. Anda dapat mengkonsumsi vitamin E dalam jumlah yang cukup sesuai dosis setiap harinya. (Artikel terkait:&nbsp;<a href=\"https://bidanku.com/panduan-makanan-sehat-untuk-ibu-hamil-muda\">Makanan ibu hamil</a>)&nbsp;</p>\n" +
                            "<p><strong>2. Asparagus</strong></p>\n" +
                            "<p>Ibu hamil membutuhkan vitamin D untuk dapat mencerna kalsium. Selain itu vitamin D akan membantu ibu hamil dalam mengatasi gangguan kehamilan yang seringkali dirasakan di akhir bulan ke 3 kehamilan, yaitu insomnia. Disarankan anda mengkonsumsi asparagus yang megandung vitamin D membantu menjaga kesehatan anda dan anak anda. Selain itu aroma asparagus dapat mengatasi morning sickness.</p>\n" +
                            "<p><strong>3. Bayam</strong></p>\n" +
                            "<p>Ibu hamil memerlukan bahan makanan yang mengandung zat besi. Salah satunya berasal dari sayuran hijau yaitu bayam. Zat besi dapat membantu memproduksi sel darah merah dan menjaga aliran darah ke rahim. Selain itu kandungan zat besi dapat menurunkan risiko anemia yang seringkali dialami oleh ibu hamil.</p>\n" +
                            "<div class=\"code-block code-block-4\">&nbsp;</div>\n" +
                            "<p><strong>4. Salmon</strong></p>\n" +
                            "<p>Ikan salmon mengandung berbagai macam gizi. Kandungan gizi yang utama yang terdapat di dalam ikan salmon adalah asam lemak omega 3, kalsium dan vitamin D. Ikan salmon juga merupakan jenis ikan yang aman di konsumsi ibu hamil karena kandungan merkuri yang rendah bahkan kadarnya mendekati tidak ada. Selain itu salmon sangat kecil menimbulkan alergi.</p>\n" +
                            "<p><strong>5. Telur</strong></p>\n" +
                            "<p>Pada bulan ke 3 kehamilan, anda dapat meningkatkan konsumsi telur. Ingat proses memasak telur harus benar-benar matang diantaranya adalah dengan digoreng, rebus, orak-orak atau yang lainnya. Terpenting jangan mengkonsumsi telur setengah matang atau telur mentah karena bakterinya tidak baik untuk tumbuh kembang janin.</p>\n" +
                            "<p><strong>6. Sayuran dengan berbagai warna</strong></p>\n" +
                            "<p>Sayuran memiliki manfaat untuk mengurangi morning sickness. Morning sickness sangat menggangu ibu hamil sehingga mengurangi nafsu makan. Dengan sayuran hijau maka akan mengurangi gejala morning sickness yang sering ibu alami di bulan ke 2-4 kehamilan. Selain itu sayur hijau mengandung mineral yang dibutuhkan oleh tubuh ibu hamil dan janin.</p>";
                    isi.setText(Html.fromHtml(bulan3));
                }else if (newBulan <= 4){
                    String bulan4 = "<p><strong>1. Perkaya dengan kandungan serat</strong></p>\n" +
                            "<p>Pada awal bulan ke 4 anda dapat mengkonsumsi lebih banyak serat. Karena kondisi fisik yang semakin berubah di bulan ke 4 seringkali menimbulkan keluhan. Untuk mengurangi keluhan selama kehamilan yang berhubungan dengan pencernaan maka anda dapat mengkonsumsi serat untuk mengurangi risiko sembelit. Anda dapat mengkonsumsi makanan yang kaya serat seperti jeruk, kacang-kacangan, semangka atau brokoli</p>\n" +
                            "<p><strong>2. Kalsium</strong></p>\n" +
                            "<p>Makanan yang mengandung kalsium memang sangat dibutuhkan. Bahkan kalsium sudah sangat dibutuhkan di awal kehamilan. Peranan kalsium sangat penting dalam tumbuh kembang tulang rawan pada janin. Anda dapat menyediakan kalsium yang cukup dengan mengkonsumsi keju, susu dan sayuran yang berdaun hijau. Hal ini untuk mengurangi resiko kalsium yang terambil di dalam tubuh ibu.</p>\n" +
                            "<div class=\"code-block code-block-4\">&nbsp;</div>\n" +
                            "<p><strong>3. Protein</strong></p>\n" +
                            "<p>Selama perkembangan janin di dalam kandungan anda membutuhkan protein. Protein selalu dibutuhkan sehingga setiap anda mengkonsumsi makanan pastikan dilengkapi dengan protein nabati atau protein hewani. Anda dapat memilih bahan makanan yang terkandung di sayuran, buah-buaham, suu atau telur. Anda sediakan protein yang berkecukupan sehingga dapat proses pembelahan sel. (Baca juga:&nbsp;<a href=\"https://bidanku.com/panduan-makanan-sehat-untuk-ibu-hamil-muda\">Makanan ibu hamil</a>)</p>\n" +
                            "<p><strong>4. Rendah lemak</strong></p>\n" +
                            "<p>Berat badan akan mulai meningkat pada kehamilan bulan ke 4. Sehingga asupan lemak pada ibu hamil harus dikontrol. Anda dapat mengkonsumsi makanan yang rendah lemak sebagai menu harian anda. Pilihlah susu yang rendah lemak dan daging tanpa lemak yang baik untuk kesehatan sehingga baik di bulan ke 4 kehamilan anda.<br /><strong>5. Zat Besi</strong></p>\n" +
                            "<p>Ibu hamil mudah terserang anemia sehingga anda harus dapat menjaga asupan zat besi di dalam tubuh. Asupan zat besi sangat penting untuk membentuk darah dan membantu nutrisi yang cukup selama janin di dalam kandungan. Anda dapat mengkonsumsi makanan yang kaya akan kandungan zat besi diantaranya adalah kurma, pisang, atau labu.</p>";
                    isi.setText(Html.fromHtml(bulan4));
                }else if (newBulan <= 5){
                    String bulan5 = "<p><strong>1. Kalsium dan vitamin D</strong></p>\n" +
                            "<p>Kandungan kalsium yang terdapat di beberapa menu makananan sangat baik dikonsumsi oleh ibu hamil. Kalsium akan dibutuhkan oleh ibu hamil di awal pemebentukan tulang dan gigi. Dengan kalsium yang cukup maka ibu hamil akan terhindar dari osteoporosis, sehingga mengurangi risiko terjadinya kalsium yang diambil dati tulang.Anda dapat mengkonsumsi susu di bulan ke 5 kehamilan tanpa merasakan morning sickness karena pada bulan ke 5 kehamilan sudah ibu hamil tidak mengeluhkan mual dan muntah. Selain kalsium, lengkapi dengan vitamin D yang akan membantu dalam penyerapan kalsium secara maksimal. Vitamin D bisa didapatkan dari susu, hati ikan atau kuning telur. (Artikel terkait:&nbsp;<a href=\"https://bidanku.com/panduan-makanan-sehat-untuk-ibu-hamil-muda\">Makanan ibu hamil</a>)</p>\n" +
                            "<p><strong>2. Omega 3</strong></p>\n" +
                            "<div class=\"code-block code-block-4\">&nbsp;</div>\n" +
                            "<p>Konsumsi juga omega 3 yang akan mendukung perkembangan otak sehingga dapat menunjang janin selama di dalam kandungan. Omega 3 bisa anda dapatkan di berbagai macam bahan makanan seperti ikan salmon, makarel, sardin, telur, sayuran hijau atau kacang kedelai. Anda dapat mempersiapkan kecukupan omega 3 dari bulan ke 1 kehamilan anda.</p>\n" +
                            "<p><strong>3. Vitamin A</strong></p>\n" +
                            "<p>Vitamin A memiliki fungsi untuk penglihatan, pertumbuhan kulit dan tulang. Selain itu vitamin A memiliki fungsi dalam menunjang pertumbuhan janin dan untuk imunitas pada bayi. Konsumsi vitamin A dengan jumlah yang wajar dan tidak berlebihan. Anda dapat mendapatkan vitamin A dari bahan makanan seperti wortel, bayam, tomat pepaya, apel atau buah naga.</p>\n" +
                            "<p><strong>4. Zat Besi dan vitamin C</strong></p>\n" +
                            "<p>Anda dapat mengkonsumsi zat besi yang terdapat di dalam daging, ikan atau hati. Zat besi memiliki fungsi untuk membentuk sel darah merah sehingga mengurangi resiko terkena anemia. Lengkapi pula dengan vitamin C yang akan membantu dalam penyerapan zat besi dan membnatu untuk melindungi jaringan dan membantu anda memberikan antioksidan.</p>";
                    isi.setText(Html.fromHtml(bulan5));
                }else if (newBulan <= 6){
                    String bulan6 = "<h4>1. Ikan salmon</h4>\n" +
                            "<p>Kandungan ikan salmon yang terdiri dari omega 3 berfungsi untuk kesehatan janin di dalam kandungan. Perhatikan pula pilihan ikan laut yang anda konsumsi jangan sampai banyak mengandung merkuri. Merkuri akan menggangu perkembangan saraf bayi sehingga mengkonsumsi salmon selain memiliki kandungan omega 3 yang baik termasuk dalam ikan laut yang rendah merkuri. Anda dapat mengkonsumsinya dengan porsi yang sesuai kebutuhan, 12 ons per minggu.</p>\n" +
                            "<h4>2. Telur</h4>\n" +
                            "<p>Telur mengandung kalori bahkan di dalam kandungannya banyak mengandung vitamin, protein dan mineral. Telur merupakan makanan untuk ibu hamil yang mendorong pertumbuhan janin dan kaya akan kandungan kolin. Ibu hamil yang mengkonsumsi telur sebaiknya hindari mengkonsumsi mentah atau setengah matang.</p>\n" +
                            "<h4>3. Buncis</h4>\n" +
                            "<p>Buncis adalah salah satu jenis kacang-kacangan yang baik dikonsumsi ibu hamil. Buncis mengandung protein dan serat yang lebih banyak dibandingkan dengan jenis kacang-kacangan lainnya. Kandungan protein nya akan membantu dalam menjaga jannin begitu pula serat yang dibutuhkan ibu hamil dalam melancarkan pencernaan. Ibu hamil di bulan ke 6 seringkali mengeluhkan gangguan pada pencernaan sehingga sebaiknya anda konsumsi makanan yang banyak mengandung serat.</p>\n" +
                            "<div class=\"code-block code-block-4\">&nbsp;</div>\n" +
                            "<h4>4. Ubi jalar</h4>\n" +
                            "<p>Ubi jalar yang berwarna orange mengandung karatenoid yang banyak memberikan vitamin A. Seain itu ubi jalar membantu perkembangan janin dan juga mencukupi kebutuhan nutrisi ibu hamil diantaranya folat dan vitamin C. Ubi jalar dapat anda konsumsi direbus&nbsp; atau dijadikan tambahan untuk sayur bening.</p>\n" +
                            "<h4>5. Daging tanpa lemak</h4>\n" +
                            "<p>Memperhatikan berat badan tubuh anda selama kehamilan bukan berarti tidak mengkonsumsi makanan akan tetapi anda membatasi dengan pilihan yang tepat. Lengkapi kebutuhan gizi harian anda dari daging, pilihlah daging tanpa lemak. Daging tanpa lemak baik untuk ibu hamil.</p>\n" +
                            "<h4>6. Sayuran Hijau</h4>\n" +
                            "<p>Anda dapat mengkonsumsi sayuran hijau yang sangat baik untuk kesehatan ibu dan janin. Bahkan bagi ibu yang sudah terbiasa mengkonsumsi sayuran akan membuat bayi anda mudah menerima sayuran di masa pemberian makanan pendamping ASI.</p>";
                    isi.setText(Html.fromHtml(bulan6));
                }else if (newBulan <= 7){
                    String bulan7 = "<h4>1. Kalori</h4>\n" +
                            "<p>Pada trimester ke tiga ibu hamil membutuhkan kalori. Sehingga untuk memenuhi kebutuhan kalori ibu hamil dapat mengkonsumsi makanan yang mengandung karbohidrat dalam porsi sesuai yang dibutuhkan.Beberapa makanan yang mengandung kalori seperti kentang, singkong, umbi atau nasi.Penghitungan kalori yang tepat sesuai dengan porsi yang dibutuhkan oleh tubuh akan membantu dalam mengontrol berat badan ibu hamil dan mengurngi keluhan selama kehamilan di bulan ke 7.</p>\n" +
                            "<h4>2. Vitamin B</h4>\n" +
                            "<p>Vitamin B dibutuhkan oleh tubuh terutama adalah vitamin B6 yang akan membantu dalam proses kimia dengan bantuan enzim. Selain itu Vitamin B6 akan membantu dalam proses metabolisme. Anda dapat melengkapi kebutuhan vitamin B6 dari bahan makanan seperti ikan, ayam atau daging sapi.</p>\n" +
                            "<h4>3. Yodium</h4>\n" +
                            "<p>Yodium akan membantu dalam pembentukan sel baru dikarenakan kandungan senyawa tiroksin yang akan membantu untuk mengurangi resiko tubuh bayi kerdil.Meskipun dalam jumlah yang sedikit, anda tetap harus memperhatikan asupan yang mengandung yodium seperti dari ikan laut atau garam dapur beryodium. Meskipun demikian anda tetap harus memperhatikan asupannya jangan terlalu berlebihan. (Artikel menarik lainnya:&nbsp;<a href=\"https://bidanku.com/panduan-makanan-sehat-untuk-ibu-hamil-muda\">Makanan ibu hamil</a>)</p>\n" +
                            "<div class=\"code-block code-block-4\">&nbsp;</div>\n" +
                            "<h4>4. Kalsium</h4>\n" +
                            "<p>Kalsium akan membantu ibu menjaga kesehatan tulang selama kehamilan termasuk ketika memasuki bulan ke 7 kehamilan, bobot tubuh ibu hamil akan semakin berat, kekurangan kalsium akan menyebabkan mengikis tulang ibu ketika janin membutuhkan kalsium untuk pertumbuhan. Anda dapat mengkonsumsi susu yang mengandung protein dan kalsium yang baik untuk ibu hamil di bulan ke 7. Dengan membiasakan ibu hamil minum susu akan membantu antibodi yang baik untuk ibu hamil.</p>\n" +
                            "<h4>5. Buah-Buahan</h4>\n" +
                            "<p>Semua buah-buahan baik untuk kesehatan. Manfaat yang di dapatkan oleh ibu hamil yang mengkonsumsi pepaya adalah dapat mengurangi kondisi heartburn. Kondisi heartburn adalah kondisi dimana ibu hamil mengalami panas pada bagian perut. Pepaya akan membantu proses pencernaan sehinga mengurangi asam hidrolarat lambung.</p>";
                    isi.setText(Html.fromHtml(bulan7));
                }else if (newBulan <= 8){
                    String bulan8= "<p><strong>1. Nasi Merah dan Umbi-umbian</strong><br />Pada usia kehamilan 8 bulan ibu hamil harus dapat menjaga asupan nutrisi.Salah satunya sumber kalori, ibu hamil harus tetap memperhatikan kalori untuk plasenta dan pertumbuhan janin. Ibu hamil dapat memperoleh asupan kalori dari kentang, singkong, kacang-kacangan, beras dan umbi-umbian. Disarankan ibu hamil mengkonsumsi beras merah karena selain memiliki kandungan serat yang biasanya dialami oleh ibu hamil menjelang persalinan adalah sembelit.</p>\n" +
                            "<p><strong>2. Sayuran warna orange dan hijau</strong><br />Sayuran dengan warna orange memiliki kandungan vitamin A yang lebih banyak sehingga dapat membantu dalam imunitas, perkembangan embrio dan pertumbuhan. Dengan mengkonsumsi vitamin A yang cukup maka akan mengurangi risiko bayi lahir prematur dan bayi lahir dengan berat badan yang rendah. Selain sayurana yang berwarna orange dan hijau anda dapat melengkapi kebutuhan vitamin A dari hati ayam, kuning telur dan susu.Variasikan menu harian anda dengan mengkonsumsi sayuran hijau.</p>\n" +
                            "<p><strong>3. Kacang Kedelai</strong><br />Kacang kedelai memiliki kandungan vitamin B yang bermanfaat untuk ibu hamil di bulan ke 8. Vitamin B1 dan Vitamin B2 akan membantu dalam proses metabolisme sedangkan untuk vitamin B12 dan vitamin B6 akan membantu dalam pembentukan sel darah merah dan DHA. Sedangkan untuk B6 akan membantu dalam proses metabolisme yang berperan dalam sel saraf. Anda dapat melengkapi kebutuhan Vitamin B6 selain dari kacang kedelai yaitu dari ikan, susu atau gandum. (Artikel terkait: Makanan untuk ibu hamil)</p>\n" +
                            "<p><br /><strong>4. Jeruk dan Brokoli</strong><br />Kandungan vitamin C yang terdapat di dalam sayur dan buah di atas dapat membantu untuk memberikan antioksidan dan melindungi kerusakan jaringan. Selain itu vitamin C sangat baik untuk penyerapan zat besi sehingga membantu ibu di kehamilan 8 bulan untuk mempersiapkan persalinan tanpa resiko anemia. Selain Jeruk dan brokoli anda dapat mengonsumsi tomat atau strawberry yang memberikan kandungan vitamin C.</p>\n" +
                            "<p><strong>5. Air</strong><br />Dehidrasi akan mengakibatkan gangguan kesehatan untuk ibu hamil sehingga yang dapat anda lakukan di bulan ke 8 salah satunya dengan menjaga asupan cairan yang dapat mengatur suhu tubuh dan mengatur proses metabolisme sehingga dapat menjaga kesehatan mempersiapkan menjelang persalinan.</p>\n" +
                            "<p>Dengan demikian bagi anda yang sudah memasuki kehamilan di bulan ke 8 sangat penting untuk menjaga asupan nutrisi dan cairan sehingga membantu untuk mengurangi risiko masalah kesehatan menjelang persalinan.</p>";
                    isi.setText(Html.fromHtml(bulan8));
                }else {
                    isi.setText("Silahkan Scrool Angka Sesuai Usia Kehamilan Anda");
                }

            }
        });



    }
}