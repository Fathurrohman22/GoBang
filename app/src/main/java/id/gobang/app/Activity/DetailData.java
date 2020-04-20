package id.gobang.app.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import id.gobang.app.Helper.Bantuan;
import id.gobang.app.R;

public class DetailData extends AppCompatActivity {

    Button lanjut;
    ImageView kembali;
    TextView id_tilang, nama, alamat, denda, sidang, barangbukti, nomorpolisi;
    private Context context = DetailData.this;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        id_tilang = findViewById(R.id.tvidTilang);
        nama = findViewById(R.id.tvNama);
        alamat = findViewById(R.id.tvAlamat);
        denda = findViewById(R.id.tvDenda);
        sidang = findViewById(R.id.tvTglSidang);
        barangbukti = findViewById(R.id.tvBarangBukti);
        nomorpolisi = findViewById(R.id.tvNoPolisi);

        int totalDenda = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("denda"))) + Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("biaya_perkara")));
        id_tilang.setText(getIntent().getStringExtra("no_reg_tilang"));
        nama.setText(getIntent().getStringExtra("nama_terpidana"));
        alamat.setText(getIntent().getStringExtra("alamat_terpidana"));
        denda.setText("Rp " + new Bantuan(context).formatHarga(String.valueOf(totalDenda)));
        sidang.setText(getIntent().getStringExtra("tgl_putusan"));
        barangbukti.setText(getIntent().getStringExtra("barang_bukti"));
        nomorpolisi.setText(getIntent().getStringExtra("nomor_polisi"));

        lanjut = findViewById(R.id.btnLanjut);
        kembali = findViewById(R.id.btnClose);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanjut = new Intent(DetailData.this, Pemesanan.class);
                lanjut.putExtra("no_reg_tilang", getIntent().getStringExtra("no_reg_tilang"));
                lanjut.putExtra("denda", getIntent().getStringExtra("denda"));
                lanjut.putExtra("biaya_perkara", getIntent().getStringExtra("biaya_perkara"));

                startActivity(lanjut);
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
