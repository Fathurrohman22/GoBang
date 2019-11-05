package fathurrohman.cv.gobang;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cari extends Fragment {
    CardView cari, lacak, pgm, carapakai, carabayar, tentang;


    public Cari() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cari, container, false);

        cari = v.findViewById(R.id.cari_card);
        lacak = v.findViewById(R.id.tracking_card);
        pgm = v.findViewById(R.id.pgm_card);
//        carapakai = v.findViewById(R.id.carapakai_card);
//        carabayar = v.findViewById(R.id.carabayar_card);
        tentang = v.findViewById(R.id.tentang_card);

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Beranda.class);
                startActivity(i);
            }
        });

        lacak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Lacak.class);
                startActivity(i);
            }
        });

        pgm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PosGiro.class);
                startActivity(i);
            }
        });

//        carapakai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), CaraPakai.class);
//                startActivity(i);
//            }
//        });
//
//        carabayar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), CaraBayar.class);
//                startActivity(i);
//            }
//        });

        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), About.class);
                getContext().startActivity(i);
            }
        });
        return v;
    }

}
