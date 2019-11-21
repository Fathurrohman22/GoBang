package id.gobang.app.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.iwgang.countdownview.CountdownView;
import id.gobang.app.Adapter.ProductAdapter;
import id.gobang.app.Company;
import id.gobang.app.Product;
import id.gobang.app.R;

public class Pembayaran extends AppCompatActivity {

    Button lanjut;
    ImageView silang;
    TextView virtual, salin, aplikasi;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        CountdownView mCvCountdownView = (CountdownView) findViewById(R.id.countdown);
        mCvCountdownView.start(172800000);
        for (int time = 0; time < 1000; time++) {
            mCvCountdownView.updateShow(time);
        }

        lanjut = findViewById(R.id.btnLanjut);
        silang = findViewById(R.id.btnClose);
        virtual = findViewById(R.id.VirtualAccount);
        salin = findViewById(R.id.SalinRek);
        aplikasi = findViewById(R.id.aplikasiPGM);
        recyclerView = findViewById(R.id.recyclerViewMetode);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        aplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.posindonesia.giropos&hl=en_US"));
                startActivity(i);
            }
        });

        salin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", virtual.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                clipData.getDescription();
                Toast.makeText(Pembayaran.this, "No. Rekening berhasil disalin", Toast.LENGTH_SHORT).show();
            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lanjut = new Intent(Pembayaran.this, ResiKirim.class);
                startActivity(lanjut);
            }
        });

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(Pembayaran.this, Pemesanan.class);
                startActivity(kembali);
            }
        });

        ArrayList<Company> companies = new ArrayList<>();

        ArrayList<Product> kantorPos = new ArrayList<>();
        kantorPos.add(new Product("1. Datang ke loket Kantor Pos terdekat."));
        kantorPos.add(new Product("2. Tunjukan kode Virtual Account ke petugas loket."));
        kantorPos.add(new Product("3. Konfirmasi pembayaran di aplikasi."));

        Company pos = new Company("Kantor Pos", kantorPos);
        companies.add(pos);

        ArrayList<Product> aplikasiPGM = new ArrayList<>();
        aplikasiPGM.add(new Product("1. Buka aplikasi PGM (POSGIRO Mobile."));
        aplikasiPGM.add(new Product("2. Pilih menu Virtual Account."));
        aplikasiPGM.add(new Product("3. Masukkan kode Virtual Account Anda."));
        aplikasiPGM.add(new Product("4. Selesai."));

        Company pgm = new Company("Aplikasi POSGIRO Mobile", aplikasiPGM);
        companies.add(pgm);

        ProductAdapter adapter = new ProductAdapter(companies);
        recyclerView.setAdapter(adapter);
    }

}
