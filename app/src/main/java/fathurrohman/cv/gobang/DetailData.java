package fathurrohman.cv.gobang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailData extends AppCompatActivity {

    Button lanjut;
    ImageView kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        lanjut = findViewById(R.id.btnLanjut);
        kembali = findViewById(R.id.btnClose);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanjut = new Intent(DetailData.this, Pemesanan.class);
                startActivity(lanjut);
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(DetailData.this, MainActivity.class);
                startActivity(kembali);
            }
        });
    }
}
