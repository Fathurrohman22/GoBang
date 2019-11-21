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

import id.gobang.app.R;

public class ResiKirim extends AppCompatActivity {

    ImageView silang;
    TextView resi, salin;
    Button lacak, selesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resi_kirim);

        silang = findViewById(R.id.btnClose);
        resi = findViewById(R.id.tvResi);
        salin = findViewById(R.id.SalinResi);
        lacak = findViewById(R.id.btnLacak);

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(ResiKirim.this, Pembayaran.class);
                startActivity(kembali);
            }
        });

        lacak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResiKirim.this, Lacak.class);
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
                Toast.makeText(ResiKirim.this, "No. Resi berhasil disalin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
