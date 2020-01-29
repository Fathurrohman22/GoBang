package id.gobang.app.Activity;

import android.annotation.SuppressLint;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import cn.iwgang.countdownview.CountdownView;
import id.gobang.app.Adapter.Company;
import id.gobang.app.Adapter.ProductAdapter;
import id.gobang.app.Helper.Bantuan;
import id.gobang.app.Model.Product;
import id.gobang.app.R;

public class Pembayaran extends AppCompatActivity {

    Button lanjut;
    Button btnDownloadPGM;
    ImageView silang;
    TextView virtual, salin, aplikasi, tvTotalPembayaran;
    TextView tvNominalDendaTilang;
    TextView tvNominalAntar;
    TextView tvBiayaAdministrasi;
    RecyclerView recyclerView;
    ImageView btnCopyVA;
    ImageView btnCopyNominal;
    private Context context = Pembayaran.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        init();
        ArrayList<Company> companies = new ArrayList<>();

        ArrayList<Product> kantorPos = new ArrayList<>();
        kantorPos.add(new Product("1. Datang ke loket Kantor Pos terdekat dengan membawa barang bukti tilang."));
        kantorPos.add(new Product("2. Tunjukan kode Virtual Account ke petugas loket."));
        kantorPos.add(new Product("3. Konfirmasi pembayaran di aplikasi."));

        Company pos = new Company("Kantor Pos", kantorPos);
        companies.add(pos);

        ArrayList<Product> aplikasiPGM = new ArrayList<>();
        aplikasiPGM.add(new Product("1. Buka aplikasi PGM (POSGIRO Mobile)."));
        aplikasiPGM.add(new Product("2. Pilih menu Virtual Account."));
        aplikasiPGM.add(new Product("3. Masukkan kode Virtual Account Anda."));
        aplikasiPGM.add(new Product("4. Selesai."));

        Company pgm = new Company("Aplikasi POSGIRO Mobile", aplikasiPGM);
        companies.add(pgm);

        ProductAdapter adapter = new ProductAdapter(companies);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        lanjut = findViewById(R.id.btnLanjut);
        silang = findViewById(R.id.btnClose);
        virtual = findViewById(R.id.VirtualAccount);
        salin = findViewById(R.id.SalinRek);
        aplikasi = findViewById(R.id.aplikasiPGM);
        btnDownloadPGM = findViewById(R.id.btnDownloadPGM);
        btnCopyVA = findViewById(R.id.btnCopyVA);
        btnCopyNominal = findViewById(R.id.btnCopyNominal);

        tvNominalDendaTilang = findViewById(R.id.tvNominalDendaTilang);
        tvNominalAntar = findViewById(R.id.tvNominalAntar);
        tvBiayaAdministrasi = findViewById(R.id.tvBiayaAdministrasi);

        tvTotalPembayaran = findViewById(R.id.tvTotalPembayaran);
        CountdownView mCvCountdownView = findViewById(R.id.countdown);

        recyclerView = findViewById(R.id.recyclerViewMetode);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        virtual.setText(getIntent().getStringExtra("no_va"));
        tvTotalPembayaran.setText("Rp " + new Bantuan(context)
                .formatHarga(
                        String.valueOf(
                                Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("denda_tilang"))) +
                                Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("biaya_antar"))) +
                                Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("biaya_administrasi")))
                        )
                )
        );

        tvNominalDendaTilang.setText("Rp " + new Bantuan(context).formatHarga(getIntent().getStringExtra("denda_tilang")));
        tvNominalAntar.setText("Rp " + new Bantuan(context).formatHarga(getIntent().getStringExtra("biaya_antar")));
        tvBiayaAdministrasi.setText("Rp " + new Bantuan(context).formatHarga(getIntent().getStringExtra("biaya_administrasi")));


        String expired = getIntent().getStringExtra("waktu_expired");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_expired;
        Date date_now = new Date();
        try {
            date_expired = format.parse(expired);
            long time = date_expired.getTime() - date_now.getTime();
            mCvCountdownView.start(time);
            for (int i = 0; i < 1000; i++) {
                mCvCountdownView.updateShow(time);
            }
        } catch (ParseException e) {
            new Bantuan(context).toastLong(e.getMessage());
            e.printStackTrace();
        }

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
                Toast.makeText(Pembayaran.this, "Nomor virtual account berhasil disalin", Toast.LENGTH_SHORT).show();
            }
        });

        btnCopyVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("noVA", virtual.getText().toString());
                if (clipboardManager != null) {
                    clipboardManager.setPrimaryClip(clipData);
                }
                clipData.getDescription();
                new Bantuan(context).toastLong("Nomor Virtual Account Berhasil Disalin");
            }
        });

        btnCopyNominal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("totalPembayaran", String.valueOf(
                        Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("denda_tilang"))) +
                                Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("biaya_antar"))) +
                                Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("biaya_administrasi")))
                ));
                if (clipboardManager != null) {
                    clipboardManager.setPrimaryClip(clipData);
                }
                clipData.getDescription();
                new Bantuan(context).toastLong("Total Pembayaran Berhasil Disalin");
            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        });

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        });

        if (!new Bantuan(context).isAppInstalled("com.posindonesia.giropos")) {
            btnDownloadPGM.setText("DOWNLOAD POS GIRO MOBILE");
        } else {
            btnDownloadPGM.setText("BUKA POS GIRO MOBILE");
        }

        btnDownloadPGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (new Bantuan(context).isAppInstalled("com.posindonesia.giropos")) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.posindonesia.giropos");
                    if (launchIntent != null) {
                        startActivity(launchIntent);
                    } else {
                        new Bantuan(context).toastLong("Terjadi Kesalahan");
                    }
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=com.posindonesia.giropos&hl=en_US"));
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }
}
