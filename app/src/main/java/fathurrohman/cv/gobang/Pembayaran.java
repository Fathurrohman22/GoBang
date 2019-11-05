package fathurrohman.cv.gobang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import cn.iwgang.countdownview.CountdownView;

public class Pembayaran extends AppCompatActivity {

    Button lanjut;
    ImageView silang;

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
