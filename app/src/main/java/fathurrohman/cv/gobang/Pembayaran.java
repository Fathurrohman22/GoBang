package fathurrohman.cv.gobang;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Pembayaran extends AppCompatActivity {

    Button lanjut;
    ImageView silang;
    RadioGroup metode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        lanjut = findViewById(R.id.btnLanjut);
        silang = findViewById(R.id.btnClose);
        metode = findViewById(R.id.rgBayar);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lanjut = new Intent(Pembayaran.this, Barcode.class);
                startActivity(lanjut);
                checkBayar();
            }
        });

        metode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("event.getAction()", event.getAction() + "");
                Log.e("event.keyCode()", keyCode + "");
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    checkBayar();
                    return true;
                }
                return false;
            }
        });

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(Pembayaran.this, ValidasiData.class);
                startActivity(kembali);
            }
        });
    }

    private void checkBayar() {
        String Metode = metode.toString();
        if (TextUtils.isEmpty(Metode)) {
            new Bantuan(Pembayaran.this).swal_warning("Pilih Metode Pembayaran");
//        }else if("D221098".equalsIgnoreCase(Kode)){
//            new Bantuan(getActivity()).swal_sukses("Data Ditemukan");
//            Intent i = new Intent(getActivity(), DetailData.class);
//            startActivity(i);
        } else {
            new Bantuan(Pembayaran.this).swal_sukses("Selesai");
        }
    }

}
