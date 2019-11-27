package id.gobang.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
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
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import id.gobang.app.Activity.DetailData;
import id.gobang.app.Activity.Pembayaran;
import id.gobang.app.Activity.StatusBarangBukti;
import id.gobang.app.Helper.API;
import id.gobang.app.Helper.Bantuan;
import id.gobang.app.R;


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

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog pDialog = new Bantuan(getActivity()).swal_loading("Proses pengecekan data");
                pDialog.show();
                checkKode(kode.getText().toString(), pDialog);
            }
        });

        return v;
    }

    private void checkKode(final String no_reg_tilang, final SweetAlertDialog pDialog) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                API.URL_CEK_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismissWithAnimation();
                        try {
                            JSONObject object = new JSONObject(response);
                            int response_code = object.getInt("respon_code");
                            if (response_code == 404) {
                                new Bantuan(getActivity()).swal_error(object.getString("respon_mess"));
                            } else if (response_code == 302 || response_code == 417) {
                                new Bantuan(getActivity()).toastLong(object.getString("respon_mess"));
                                JSONObject data = object.getJSONObject("data");
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
                            } else if (response_code == 402) {
                                JSONObject data = object.getJSONObject("data");
                                new Bantuan(getActivity()).toastLong(object.getString("respon_mess"));
                                Intent intent = new Intent(getActivity(), Pembayaran.class);
                                intent.putExtra("waktu_expired", data.getString("waktu_expired"));
                                intent.putExtra("no_va", data.getString("kode_inst") + data.getString("no_va"));
                                intent.putExtra("denda_tilang", String.valueOf(Integer.parseInt(data.getString("nominal_denda")) +
                                        Integer.parseInt(data.getString("nominal_perkara"))));
                                intent.putExtra("biaya_antar", data.getString("nominal_pos"));
                                intent.putExtra("biaya_administrasi", data.getString("nominal_gobang"));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else if (response_code == 200) {
                                JSONObject data = object.getJSONObject("data");
                                new Bantuan(getActivity()).toastLong(object.getString("respon_mess"));
                                Intent intent = new Intent(getActivity(), StatusBarangBukti.class);
                                intent.putExtra("id_tilang", data.getString("no_reg_tilang"));
                                intent.putExtra("nama_penerima", data.getString("nama_penerima"));
                                intent.putExtra("refnumber", data.getString("refnumber"));
                                intent.putExtra("no_resi", data.getString("no_resi"));
                                startActivity(intent);
                            } else {
                                new Bantuan(getActivity()).swal_error(object.getString("respon_mess"));
                            }
                        } catch (JSONException e) {
                            pDialog.dismissWithAnimation();
                            new Bantuan(getActivity()).swal_error(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismissWithAnimation();
                new Bantuan(getActivity()).swal_error("err :" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("no_reg_tilang", no_reg_tilang);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<>();
                String creds = String.format("%s:%s", API.USERNAME, API.PASSWORD);
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
