package fathurrohman.cv.gobang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pemesanan extends AppCompatActivity {

    Button lanjut, ulang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        lanjut = findViewById(R.id.btnLanjut);
        ulang = findViewById(R.id.btnUlangi);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanjut = new Intent(Pemesanan.this, ValidasiData.class);
                startActivity(lanjut);
            }
        });

        ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ulang = new Intent(Pemesanan.this, DetailData.class);
                startActivity(ulang);
            }
        });
    }
}
