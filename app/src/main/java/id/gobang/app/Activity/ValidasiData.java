package id.gobang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import id.gobang.app.R;

public class ValidasiData extends AppCompatActivity {

    Button lanjut;
    ImageView silang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_data);

        lanjut = findViewById(R.id.btnLanjut);
        silang = findViewById(R.id.btnClose);
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lanjut = new Intent(ValidasiData.this, Pembayaran.class);
                startActivity(lanjut);
            }
        });

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(ValidasiData.this, Pemesanan.class);
                startActivity(kembali);
            }
        });
    }
}
