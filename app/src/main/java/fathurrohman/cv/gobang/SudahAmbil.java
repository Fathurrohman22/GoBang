package fathurrohman.cv.gobang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SudahAmbil extends AppCompatActivity {

    ImageView silang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudah_ambil);

        silang = findViewById(R.id.btnClose);

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SudahAmbil.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
