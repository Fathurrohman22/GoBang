package fathurrohman.cv.gobang;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import fathurrohman.cv.gobang.DataHandler.API;


/**
 * A simple {@link Fragment} subclass.
 */
public class Beranda extends Fragment {
    EditText kode;
    Button cari;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_beranda, container, false);
        kode = v.findViewById(R.id.etKodeTilang);
        cari = v.findViewById(R.id.btnCari);


//        new Bantuan(getActivity()).swal_basic("awww");

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkKode(kode.getText().toString());
                SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading ...");
                pDialog.setCancelable(true);
                pDialog.show();
//                new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
//                        .setTitleText("Loading ...")
//                        .showCancelButton(false)
//                        .setContentText("Tunggu beberapa saat")
//                        .setCancelable(false)
//                        .show();

//                new SweetAlertDialog(Beranda.this, SweetAlertDialog.PROGRESS_TYPE) {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        checkKode(kode.getText().toString());
//                        sDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
//                        sDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                        sDialog.setTitleText("Loading ...");
//                        sDialog.showCancelButton(false);
//                        sDialog.setContentText("Tunggu beberapa saat");
//                        sDialog.setCancelable(false);
//                        sDialog.show();
//                    }
//                };
            }
        });

        return v;
    }

    private void checkKode(final String no_reg_tilang) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                API.URL_CEK_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            int response_code = object.getInt("respon_code");
                            if (response_code == 404) {
                                new Bantuan(getActivity()).swal_error(object.getString("respon_mess"));
                            } else if (response_code == 302) {
                                JSONObject data = object.getJSONObject("data");
//                               new Bantuan(getActivity()).swal_error(data.getString("nama_terpidana"));
                                Intent i = new Intent(getActivity(), DetailData.class);
                                i.putExtra("no_reg_tilang", data.getString("no_reg_tilang"));
                                i.putExtra("nama_terpidana", data.getString("nama_terpidana"));
                                i.putExtra("alamat_terpidana", data.getString("alamat_terpidana"));
                                i.putExtra("nomor_briva", data.getString("nomor_briva"));
                                i.putExtra("tgl_penitipan", data.getString("tgl_penitipan"));
                                i.putExtra("jumlah_penitipan", data.getString("jumlah_penitipan"));
                                i.putExtra("tgl_putusan", data.getString("tgl_putusan"));
                                i.putExtra("denda", data.getString("denda"));
                                i.putExtra("biaya_perkara", data.getString("biaya_perkara"));
                                i.putExtra("posisi", data.getString("posisi"));
                                i.putExtra("created_at", data.getString("created_at"));
                                i.putExtra("updated_at", data.getString("updated_at"));

                                startActivity(i);
                            }
                        } catch (JSONException e) {
                            new Bantuan(getActivity()).swal_error(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new Bantuan(getActivity()).swal_error("err :" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("screet_key", API.KEY);
                params.put("no_reg_tilang", no_reg_tilang);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
