package fathurrohman.cv.gobang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.isapanah.awesomespinner.AwesomeSpinner;

import java.util.ArrayList;
import java.util.List;

public class Pemesanan extends AppCompatActivity {

    Button lanjut;
    ImageView silang;
    AwesomeSpinner spinnerProv, spinnerKab, spinnerKec;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        lanjut = findViewById(R.id.btnLanjut);
        silang = findViewById(R.id.btnClose);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanjut = new Intent(Pemesanan.this, Pembayaran.class);
                startActivity(lanjut);
                finish();
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
