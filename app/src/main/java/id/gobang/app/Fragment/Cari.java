package id.gobang.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.gobang.app.Activity.Lacak;
import id.gobang.app.Activity.PosGiro;
import id.gobang.app.Activity.Tentang;
import id.gobang.app.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cari extends Fragment {
    LinearLayout cari, lacak, pgm, carapakai, carabayar, tentang;


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
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Beranda beranda = new Beranda();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_cari, beranda).addToBackStack(null).commit();
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
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
//                new Bantuan(getActivity()).toastLong("awww");
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
                Intent i = new Intent(getActivity(), Tentang.class);
                getContext().startActivity(i);
            }
        });
        return v;
    }

}
