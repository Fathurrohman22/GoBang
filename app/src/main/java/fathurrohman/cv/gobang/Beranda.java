package fathurrohman.cv.gobang;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Beranda extends Fragment {
    EditText kode;
    Button cari;


    public Beranda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_beranda, container, false);
        kode = v.findViewById(R.id.etKodeTilang);
        cari = v.findViewById(R.id.btnCari);

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkKode();
            }
        });

        kode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                Log.e("event.getAction()", event.getAction() + "");
                Log.e("event.keyCode()", keyCode + "");
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    checkKode();
                    return true;
                }
                return false;
            }
        });

        return v;
    }

    private void checkKode() {
        String Kode  = kode.getText().toString().trim();
        if(TextUtils.isEmpty(Kode)){
                new Bantuan(getActivity()).swal_warning("Masukkan Kode Tilang");
        }else if("D221098".equalsIgnoreCase(Kode)){
            Intent i = new Intent(getActivity(), DetailData.class);
            startActivity(i);
        }else {
            new Bantuan(getActivity()).swal_error("Data Tidak Ditemukan");
        }
    }

}
