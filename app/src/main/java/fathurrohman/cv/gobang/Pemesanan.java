package fathurrohman.cv.gobang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Pemesanan extends AppCompatActivity {

    Button lanjut;
    ImageView silang;
    Spinner spinnerProv, spinnerKab, spinnerKec;
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

        spinnerProv = findViewById(R.id.customSpinnerProvinsi);
        daftarProvinsi();
        spinnerKab = findViewById(R.id.customSpinnerKabupaten);
        daftarKabupaten();
        spinnerKec = findViewById(R.id.customSpinnerKecamatan);
        daftarKecamatan();
        ArrayAdapter<String> customSpinAdap = new ArrayAdapter<>(Pemesanan.this, android.R.layout.simple_spinner_item, list);
        customSpinAdap.setDropDownViewResource(R.layout.custom_dropdown);

        spinnerProv.setAdapter(customSpinAdap);
        spinnerProv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Snackbar.make(view, value, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKab.setAdapter(customSpinAdap);
        spinnerKab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Snackbar.make(view, value, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKec.setAdapter(customSpinAdap);
        spinnerKec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Snackbar.make(view, value, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void daftarKecamatan() {
        list = new ArrayList<>();
        list.add("Banyumas");
        list.add("Tambelang");
        list.add("Banyuwangi");
    }

    private void daftarKabupaten() {
        list = new ArrayList<>();
        list.add("Banyumas");
        list.add("Bekasi");
        list.add("Banyuwangi");
    }

    private void daftarProvinsi() {
        list = new ArrayList<>();
        list.add("Jawa Tengah");
        list.add("Jawa Barat");
        list.add("Jawa Timur");
    }
}
