package fathurrohman.cv.gobang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.isapanah.awesomespinner.AwesomeSpinner;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Pemesanan extends AppCompatActivity {

    Button lanjut;
    ImageView silang;
    TextView id_tilang;
    EditText nama, detail_alamat, kodepos, nohp;
    AwesomeSpinner spinnerProv, spinnerKab, spinnerKec;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        lanjut = findViewById(R.id.btnLanjut);
        silang = findViewById(R.id.btnClose);
        id_tilang = findViewById(R.id.tvidTilang);
        nama = findViewById(R.id.etNama);
        detail_alamat = findViewById(R.id.etDetailAlamat);
        kodepos = findViewById(R.id.etKodePos);
        nohp = findViewById(R.id.etNoHP);
        id_tilang.setText(getIntent().getStringExtra("no_reg_tilang"));

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Pemesanan.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Peringatan!")
                        .setContentText("Apakah data yang Anda masukkan sudah benar?")
                        .setConfirmText("Benar")
                        .setCancelText("Salah")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                sDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                sDialog.setTitleText("Loading ...");
                                sDialog.showCancelButton(false);
                                sDialog.setContentText("Tunggu beberapa saat");
                                sDialog.setCancelable(false);
                                sDialog.show();
                            }
                        })
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        }).show();
//                Intent lanjut = new Intent(Pemesanan.this, Pembayaran.class);
//                startActivity(lanjut);
//                finish();
            }
        });


        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(Pemesanan.this, DetailData.class);
                startActivity(kembali);
                finish();
            }
        });

        spinnerProv = findViewById(R.id.my_spinnerProvinsi);
        spinnerKab = findViewById(R.id.my_spinnerKota);
        spinnerKec = findViewById(R.id.my_spinnerKecamatan);

        List<String> kategoriProv = new ArrayList<String>();
        kategoriProv.add("Jawa Tengah");
        kategoriProv.add("Jawa Barat");
        kategoriProv.add("Jawa Timur");

        ArrayAdapter<String> ProvKategori = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kategoriProv);
        spinnerProv.setAdapter(ProvKategori);
        spinnerProv.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {

            }
        });
        List<String> kategoriKab = new ArrayList<String>();
        kategoriKab.add("Banyumas");
        kategoriKab.add("Bogor");
        kategoriKab.add("Mojokerto");

        ArrayAdapter<String> KabKategori = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kategoriKab);
        spinnerKab.setAdapter(KabKategori);
        spinnerKab.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {

            }
        });
        List<String> kategoriKec = new ArrayList<String>();
        kategoriKec.add("Purwokerto Utara");
        kategoriKec.add("Tasikmalaya");
        kategoriKec.add("Mojokerto");

        ArrayAdapter<String> KecKategori = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kategoriKec);
        spinnerKec.setAdapter(KecKategori);
        spinnerKec.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {

            }
        });


    }
}
