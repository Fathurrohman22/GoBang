package fathurrohman.cv.gobang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailData extends AppCompatActivity {

    Button lanjut, ulang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        lanjut = findViewById(R.id.btnLanjut);
        ulang = findViewById(R.id.btnUlangi);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanjut = new Intent(DetailData.this, Pemesanan.class);
                startActivity(lanjut);
            }
        });

        ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ulang = new Intent(DetailData.this, MainActivity.class);
                ulang.putExtra("Beranda", 0);
                startActivity(ulang);
            }
        });
    }
}
