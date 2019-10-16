package fathurrohman.cv.gobang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Pemesanan extends AppCompatActivity {

    Button lanjut;
    ImageView silang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        lanjut = findViewById(R.id.btnLanjut);
        silang = findViewById(R.id.btnClose);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanjut = new Intent(Pemesanan.this, ValidasiData.class);
                startActivity(lanjut);
            }
        });


        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(Pemesanan.this, DetailData.class);
                startActivity(kembali);
            }
        });
    }
}
