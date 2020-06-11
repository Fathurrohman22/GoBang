package id.gobang.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import id.gobang.app.Adapter.SimpleListAdapter;
import id.gobang.app.Helper.API;
import id.gobang.app.Helper.Bantuan;
import id.gobang.app.Model.DesaModel;
import id.gobang.app.Model.KabupatenModel;
import id.gobang.app.Model.KecamatanModel;
import id.gobang.app.Model.ProvinsiModel;
import id.gobang.app.R;

public class Pemesanan extends AppCompatActivity {
    Button lanjut;
    ImageView silang;
    TextView id_tilang;
    EditText nama, detail_alamat, kodepos, nohp;
    private Context context = Pemesanan.this;
    private SimpleListAdapter ProvinsiAdapter;
    private SimpleListAdapter KabupatenAdapter;
    private SimpleListAdapter KecamatanAdapter;
    private SimpleListAdapter DesaAdapter;

    private SearchableSpinner spinner_provinsi;
    private SearchableSpinner spinner_kabupaten;
    private SearchableSpinner spinner_kecamatan;
    private SearchableSpinner spinner_desa;

    private List<ProvinsiModel> listProvinsi = new ArrayList<>();
    private List<KabupatenModel> listKabupaten = new ArrayList<>();
    private List<KecamatanModel> listKecamatan = new ArrayList<>();
    private List<DesaModel> listDesa = new ArrayList<>();

    private final ArrayList<String> provinsi = new ArrayList<>();
    private final ArrayList<String> kabupaten = new ArrayList<>();
    private final ArrayList<String> kecamatan = new ArrayList<>();
    private final ArrayList<String> desa = new ArrayList<>();

    private int nominalPos = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        getListProvinsi();

        lanjut = findViewById(R.id.btnLanjut);
        silang = findViewById(R.id.btnClose);
        id_tilang = findViewById(R.id.tvidTilang);
        nama = findViewById(R.id.etNama);
        detail_alamat = findViewById(R.id.etDetailAlamat);
        kodepos = findViewById(R.id.etKodePos);
        nohp = findViewById(R.id.etNoHP);

        id_tilang.setText(getIntent().getStringExtra("no_reg_tilang"));

        spinner_provinsi = findViewById(R.id.spinner_provinsi);
        spinner_kabupaten = findViewById(R.id.spinner_kabupaten);
        spinner_kecamatan = findViewById(R.id.spinner_kecamatan);
        spinner_desa = findViewById(R.id.spinner_desa);

        spinner_provinsi.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                spinner_provinsi.revealEdit();
                spinner_kabupaten.hideEdit();
                spinner_kecamatan.hideEdit();
                spinner_desa.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });
        spinner_kabupaten.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                spinner_provinsi.hideEdit();
                spinner_kabupaten.revealEdit();
                spinner_kecamatan.hideEdit();
                spinner_desa.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });
        spinner_kecamatan.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                spinner_provinsi.hideEdit();
                spinner_kabupaten.hideEdit();
                spinner_kecamatan.revealEdit();
                spinner_desa.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });
        spinner_desa.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                spinner_provinsi.hideEdit();
                spinner_kabupaten.hideEdit();
                spinner_kecamatan.hideEdit();
                spinner_desa.revealEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanjut();
            }
        });

        silang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!spinner_provinsi.isInsideSearchEditText(event)) {
            spinner_provinsi.hideEdit();
        }

        if (!spinner_kabupaten.isInsideSearchEditText(event)) {
            spinner_kabupaten.hideEdit();
        }

        if (!spinner_kecamatan.isInsideSearchEditText(event)) {
            spinner_kecamatan.hideEdit();
        }

        if (!spinner_desa.isInsideSearchEditText(event)) {
            spinner_desa.hideEdit();
        }
        return super.onTouchEvent(event);
    }

    private void getListProvinsi() {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(context));
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_PROVINSI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listProvinsi.clear();
                        try {
                            JSONObject object = new JSONObject(response);
//                            boolean isError = object.getBoolean("error");
//                            if (!isError) {
                                JSONArray jsonArray = object.getJSONArray("provinsi");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    ProvinsiModel provinsiModel = new ProvinsiModel();
                                    provinsiModel.setId(jsonObject.getString("id"));
                                    provinsiModel.setNama(jsonObject.getString("nama"));
                                    provinsi.add(jsonObject.getString("nama"));
                                    listProvinsi.add(provinsiModel);
                                }

                                ProvinsiAdapter = new SimpleListAdapter(context, provinsi, "Pilih Provinsi");
                                spinner_provinsi.setAdapter(ProvinsiAdapter);
                                spinner_provinsi.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(View view, int position, long id) {
                                        int index = -1;
                                        if (position > 0) {
                                            for (int i = 0; i < listProvinsi.size(); i++) {
                                                if (ProvinsiAdapter.getItem(position).equals(listProvinsi.get(i).getNama())) {
                                                    if (listProvinsi.get(i).getId().equalsIgnoreCase("33") || //Jawa Tengah
                                                            listProvinsi.get(i).getId().equalsIgnoreCase("34")) { // DIY
                                                        nominalPos = 12000;
                                                    } else {
                                                        nominalPos = 20000;
                                                    }
                                                    index = i;
                                                    break;
                                                }
                                            }
                                        }

                                        kabupaten.clear();
                                        KabupatenAdapter = new SimpleListAdapter(context, kabupaten, "Pilih Kabupaten");
                                        spinner_kabupaten.setAdapter(KabupatenAdapter);
                                        spinner_kabupaten.setSelectedItem(0);

                                        kecamatan.clear();
                                        KecamatanAdapter = new SimpleListAdapter(context, kecamatan, "Pilih Kecamatan");
                                        spinner_kecamatan.setAdapter(KecamatanAdapter);
                                        spinner_kecamatan.setSelectedItem(0);

                                        desa.clear();
                                        DesaAdapter = new SimpleListAdapter(context, desa, "Pilih Desa");
                                        spinner_desa.setAdapter(DesaAdapter);
                                        spinner_desa.setSelectedItem(0);

                                        if (index > -1) {
                                            getListKabupaten(listProvinsi.get(index).getId());
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected() {
                                        Toast.makeText(context, "Silahkan pilih provinsi terlebih dahulu", Toast.LENGTH_SHORT).show();
                                    }
                                });

//                            } else {
//                                new Bantuan(context).toastLong(object.getString("message"));
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            new Bantuan(context).toastLong(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new Bantuan(context).swal_error("err :" + error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    private void getListKabupaten(String ID_PROVINSI) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(context));
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_KABUPATEN(ID_PROVINSI),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listKabupaten.clear();
                        kabupaten.clear();
                        try {
                            JSONObject object = new JSONObject(response);
//                            boolean isError = object.getBoolean("error");
//                            if (!isError) {
                                JSONArray jsonArray = object.getJSONArray("kota_kabupaten");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    KabupatenModel kabupatenModel = new KabupatenModel();
                                    kabupatenModel.setId(jsonObject.getString("id"));
                                    kabupatenModel.setId_prov(jsonObject.getString("id_provinsi"));
                                    kabupatenModel.setNama(jsonObject.getString("nama"));
                                    kabupaten.add(jsonObject.getString("nama"));
                                    listKabupaten.add(kabupatenModel);
                                }

                                KabupatenAdapter = new SimpleListAdapter(context, kabupaten, "Pilih Kabupaten");
                                spinner_kabupaten.setAdapter(KabupatenAdapter);
                                spinner_kabupaten.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(View view, int position, long id) {
                                        int index = -1;
                                        if (position > 0) {
                                            for (int i = 0; i < listKabupaten.size(); i++) {
                                                if (KabupatenAdapter.getItem(position).equals(listKabupaten.get(i).getNama())) {
                                                    index = i;
                                                    break;
                                                }
                                            }
                                        }

                                        kecamatan.clear();
                                        KecamatanAdapter = new SimpleListAdapter(context, kecamatan, "Pilih Kecamatan");
                                        spinner_kecamatan.setAdapter(KecamatanAdapter);
                                        spinner_kecamatan.setSelectedItem(0);

                                        desa.clear();
                                        DesaAdapter = new SimpleListAdapter(context, desa, "Pilih Desa");
                                        spinner_desa.setAdapter(DesaAdapter);
                                        spinner_desa.setSelectedItem(0);

                                        if (index > -1) {
                                            getListKecamatan(listKabupaten.get(index).getId());
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected() {
                                        Toast.makeText(context, "Silahkan pilih kabupaten terlebih dahulu", Toast.LENGTH_SHORT).show();
                                    }
                                });

//                            } else {
//                                new Bantuan(context).toastLong(object.getString("message"));
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            new Bantuan(context).toastLong(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new Bantuan(context).swal_error("err :" + error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    private void getListKecamatan(String ID_KABUPATEN) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(context));
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_KECAMATAN(ID_KABUPATEN),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listKecamatan.clear();
                        kecamatan.clear();
                        try {
                            JSONObject object = new JSONObject(response);
//                            boolean isError = object.getBoolean("error");
//                            if (!isError) {
                                JSONArray jsonArray = object.getJSONArray("kecamatan");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    KecamatanModel kecamatanModel = new KecamatanModel();
                                    kecamatanModel.setId(jsonObject.getString("id"));
                                    kecamatanModel.setId_kabupaten(jsonObject.getString("id_kota"));
                                    kecamatanModel.setNama(jsonObject.getString("nama"));
                                    kecamatan.add(jsonObject.getString("nama"));
                                    listKecamatan.add(kecamatanModel);
                                }

                                KecamatanAdapter = new SimpleListAdapter(context, kecamatan, "Pilih Kecamatan");
                                spinner_kecamatan.setAdapter(KecamatanAdapter);
                                spinner_kecamatan.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(View view, int position, long id) {
                                        int index = -1;
                                        if (position > 0) {
                                            for (int i = 0; i < listKecamatan.size(); i++) {
                                                if (KecamatanAdapter.getItem(position).equals(listKecamatan.get(i).getNama())) {
                                                    index = i;
                                                    break;
                                                }
                                            }
                                        }

                                        desa.clear();
                                        DesaAdapter = new SimpleListAdapter(context, desa, "Pilih Desa");
                                        spinner_desa.setAdapter(DesaAdapter);
                                        spinner_desa.setSelectedItem(0);

                                        if (index > -1) {
                                            getListDesa(listKecamatan.get(index).getId());
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected() {
                                        Toast.makeText(context, "Silahkan pilih kecamatan terlebih dahulu", Toast.LENGTH_SHORT).show();
                                    }
                                });

//                            } else {
//                                new Bantuan(context).toastLong(object.getString("message"));
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            new Bantuan(context).toastLong(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new Bantuan(context).swal_error("err :" + error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    private void getListDesa(String ID_DESA) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(context));
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_DESA(ID_DESA),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listDesa.clear();
                        desa.clear();
                        try {
                            JSONObject object = new JSONObject(response);
//                            boolean isError = object.getBoolean("error");
//                            if (!isError) {
                                JSONArray jsonArray = object.getJSONArray("kelurahan");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    DesaModel desaModel = new DesaModel();
                                    desaModel.setId(jsonObject.getString("id"));
                                    desaModel.setId_kecamatan(jsonObject.getString("id_kecamatan"));
                                    desaModel.setNama(jsonObject.getString("nama"));
                                    desa.add(jsonObject.getString("nama"));
                                    listDesa.add(desaModel);
                                }

                                DesaAdapter = new SimpleListAdapter(context, desa, "Pilih Desa");
                                spinner_desa.setAdapter(DesaAdapter);
                                spinner_desa.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(View view, int position, long id) {
                                        int index = -1;
                                        if (position > 0) {
                                            for (int i = 0; i < listDesa.size(); i++) {
                                                if (DesaAdapter.getItem(position).equals(listDesa.get(i).getNama())) {
                                                    index = i;
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected() {
                                        Toast.makeText(context, "Silahkan pilih desa terlebih dahulu", Toast.LENGTH_SHORT).show();
                                    }
                                });

//                            } else {
//                                new Bantuan(context).toastLong(object.getString("message"));
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            new Bantuan(context).toastLong(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new Bantuan(context).swal_error("err :" + error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    private void lanjut() {
        if (
                TextUtils.isEmpty(id_tilang.getText().toString()) ||
                        TextUtils.isEmpty(nama.getText().toString()) ||
                        spinner_provinsi.getSelectedItem() == null ||
                        spinner_kabupaten.getSelectedItem() == null ||
                        spinner_kecamatan.getSelectedItem() == null ||
                        spinner_desa.getSelectedItem() == null ||
                        TextUtils.isEmpty(detail_alamat.getText().toString()) ||
                        TextUtils.isEmpty(kodepos.getText().toString()) ||
                        TextUtils.isEmpty(nohp.getText().toString())
        ) {
            new Bantuan(context).swal_error("Masih ada data yang kosong, silahkan lengkapi semua data terlebih dahulu");
        } else {
            SweetAlertDialog alertDialog = new SweetAlertDialog(Pemesanan.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Peringatan")
                    .setContentText("Apakah data yang anda masukkan sudah benar ?")
                    .setConfirmText("Lanjut")
                    .setCancelText("Ulangi")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(final SweetAlertDialog sDialog) {
                            sDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                            sDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimary));
                            sDialog.setTitleText("Loading ...");
                            sDialog.showCancelButton(false);
                            sDialog.setContentText("Tunggu beberapa saat");
                            sDialog.setCancelable(false);
                            sDialog.show();

                            RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(context));
                            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                                    API.URL_PERMINTAAN,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            sDialog.dismissWithAnimation();
                                            try {
                                                JSONObject object = new JSONObject(response);
                                                int response_code = object.getInt("respon_code");
                                                if (response_code == 201) { //sukses
                                                    JSONObject data = object.getJSONObject("data");
                                                    new Bantuan(context).toastLong(object.getString("respon_mess"));
                                                    Intent intent = new Intent(context, Pembayaran.class);
                                                    intent.putExtra("waktu_expired", data.getString("waktu_expired"));
                                                    intent.putExtra("no_va", data.getString("kode_inst") + data.getString("no_va"));
                                                    intent.putExtra("denda_tilang", String.valueOf(Integer.parseInt(data.getString("nominal_denda")) +
                                                            Integer.parseInt(data.getString("nominal_perkara"))));
                                                    intent.putExtra("biaya_antar", data.getString("nominal_pos"));
                                                    intent.putExtra("biaya_administrasi", data.getString("nominal_gobang"));
                                                    intent.putExtra("nama_terpidana", data.getString("nama_terpidana"));
                                                    intent.putExtra("no_reg_tilang", data.getString("no_reg_tilang"));
                                                    intent.putExtra("barang_bukti", data.getString("barang_bukti"));
                                                    intent.putExtra("nomor_polisi", data.getString("nomor_polisi"));
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                } else {
                                                    new Bantuan(context).swal_error(object.getString("respon_mess"));
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                new Bantuan(context).swal_error(e.getMessage());
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    sDialog.dismissWithAnimation();
                                    new Bantuan(context).swal_error("err :" + error.toString());
                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("no_reg_tilang", id_tilang.getText().toString());
                                    params.put("nama_penerima", nama.getText().toString());
                                    params.put("alamat_antar", "Ds. " + spinner_desa.getSelectedItem().toString() + ", Kec." +
                                            spinner_kecamatan.getSelectedItem().toString() + ", " +
                                            spinner_kabupaten.getSelectedItem().toString() + ", " +
                                            spinner_provinsi.getSelectedItem().toString());
                                    params.put("detail_alamat", detail_alamat.getText().toString());
                                    params.put("kode_pos", kodepos.getText().toString());
                                    params.put("nomer_hp", nohp.getText().toString());
                                    params.put("nominal_denda", getIntent().getStringExtra("denda"));
                                    params.put("nominal_perkara", getIntent().getStringExtra("biaya_perkara"));
                                    params.put("nominal_pos", String.valueOf(nominalPos));
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

//                            new Bantuan(context).alertDialogDebugging(
//                                    "Nomor Tilang : " + id_tilang.getText().toString() + "\n" +
//                                            "Nama Penerima : " + nama.getText().toString() + "\n" +
//                                            "Provinsi : " + spinner_provinsi.getSelectedItem().toString() + "\n" +
//                                            "Kabupaten : " + spinner_kabupaten.getSelectedItem().toString() + "\n" +
//                                            "Kecamatan : " + spinner_kecamatan.getSelectedItem().toString() + "\n" +
//                                            "Desa : " + spinner_desa.getSelectedItem().toString() + "\n" +
//                                            "Detail Alamat : " + detail_alamat.getText().toString() + "\n" +
//                                            "Kode Pos : " + kodepos.getText().toString() + "\n" +
//                                            "Nomer HP : " + nohp.getText().toString() + "\n" +
//                                            "Alamat Antar : " + "Ds. " + spinner_desa.getSelectedItem().toString() + ", Kec." +
//                                            spinner_kecamatan.getSelectedItem().toString() + ", " +
//                                            spinner_kabupaten.getSelectedItem().toString() + ", " +
//                                            spinner_provinsi.getSelectedItem().toString() + "\n" +
//                                            "Nominal Denda : " + getIntent().getStringExtra("denda") + "\n" +
//                                            "Nominal Perkara : " + getIntent().getStringExtra("biaya_perkara") + "\n" +
//                                            "Nominal Pos : " + nominalPos
//                            );
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
            alertDialog.show();

            Button confirmButton = alertDialog.findViewById(R.id.confirm_button);
            Button cancelButton = alertDialog.findViewById(R.id.cancel_button);
            confirmButton.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            cancelButton.setBackgroundColor(context.getResources().getColor(R.color.gray_muda));
        }
    }

}
