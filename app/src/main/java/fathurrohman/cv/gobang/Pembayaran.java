package fathurrohman.cv.gobang;

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

import cn.iwgang.countdownview.CountdownView;

public class Pembayaran extends AppCompatActivity {

    Button lanjut;
    ImageView silang;
    TextView virtual, salin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        CountdownView mCvCountdownView = (CountdownView) findViewById(R.id.countdown);
        mCvCountdownView.start(86400000);
        for (int time = 0; time < 1000; time++) {
            mCvCountdownView.updateShow(time);
        }

        lanjut = findViewById(R.id.btnLanjut);
        silang = findViewById(R.id.btnClose);
        virtual = findViewById(R.id.VirtualAccount);
        salin = findViewById(R.id.SalinRek);

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
    }

}
