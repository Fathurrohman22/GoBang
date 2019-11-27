package id.gobang.app.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.gobang.app.Helper.Bantuan;
import id.gobang.app.R;

public class StatusBarangBukti extends AppCompatActivity {

    ImageView silang;
    TextView resi, salin;
    TextView tvNoRef;
    TextView tvidTilang;
    TextView tvNama;
    Button lacak, selesai;
private Context context = StatusBarangBukti.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resi_kirim);

        silang = findViewById(R.id.btnClose);
        resi = findViewById(R.id.tvResi);
        salin = findViewById(R.id.SalinResi);
        lacak = findViewById(R.id.btnLacak);
        tvNoRef = findViewById(R.id.tvNoRef);
        tvidTilang = findViewById(R.id.tvidTilang);
        tvNama = findViewById(R.id.tvNama);

        init();

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lacak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StatusBarangBukti.this, Lacak.class);
                startActivity(i);
            }
        });

        salin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", resi.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                clipData.getDescription();
                Toast.makeText(StatusBarangBukti.this, "No. Resi berhasil disalin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        tvidTilang.setText(getIntent().getStringExtra("id_tilang"));
        tvNama.setText(getIntent().getStringExtra("nama_penerima"));
        tvNoRef.setText(getIntent().getStringExtra("refnumber"));
        tvNoRef.setText(getIntent().getStringExtra("refnumber"));
        resi.setText(new Bantuan(context).isNullOrEmpty(getIntent().getStringExtra("no_resi")) ? "Proses Input No Resi" : getIntent().getStringExtra("no_resi"));
    }
}
