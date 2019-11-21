package id.gobang.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.isapanah.awesomespinner.AwesomeSpinner;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
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
    AwesomeSpinner spinnerProv, spinnerKab, spinnerKec;
    List<String> list;
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
    private List<DesaModel> lisDesa = new ArrayList<>();

    private final ArrayList<String> provinsi = new ArrayList<>();
    private final ArrayList<String> kabupaten = new ArrayList<>();
    private final ArrayList<String> kecamatan = new ArrayList<>();
    private final ArrayList<String> desa = new ArrayList<>();

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

        spinnerProv = findViewById(R.id.my_spinnerProvinsi);
        spinnerKab = findViewById(R.id.my_spinnerKota);
        spinnerKec = findViewById(R.id.my_spinnerKecamatan);

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

        getProvinsi();


        List<String> kategoriKab = new ArrayList<String>();
        kategoriKab.add("Banyumas");
        kategoriKab.add("Bogor");
        kategoriKab.add("Mojokerto");

        ArrayAdapter<String> KabKategori = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kategoriKab);
        spinnerKab.setAdapter(KabKategori);
        spinnerKab.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {

            }
        });
        List<String> kategoriKec = new ArrayList<String>();
        kategoriKec.add("Purwokerto Utara");
        kategoriKec.add("Tasikmalaya");
        kategoriKec.add("Mojokerto");

        ArrayAdapter<String> KecKategori = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kategoriKec);
        spinnerKec.setAdapter(KecKategori);
        spinnerKec.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {

            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!spinner_provinsi.isInsideSearchEditText(event)){
            spinner_provinsi.hideEdit();
        }

        if(!spinner_kabupaten.isInsideSearchEditText(event)){
            spinner_kabupaten.hideEdit();
        }

        if(!spinner_kecamatan.isInsideSearchEditText(event)){
            spinner_kecamatan.hideEdit();
        }

        if(!spinner_desa.isInsideSearchEditText(event)){
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
                            boolean isError = object.getBoolean("error");
                            if(!isError){
                                JSONArray jsonArray = object.getJSONArray("semuaprovinsi");
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
                                        if(position > 0){
                                            for(int i = 0; i < listProvinsi.size() ; i++){
                                                if(ProvinsiAdapter.getItem(position).equals(listProvinsi.get(i).getNama())){
                                                    index = i;
                                                    break;
                                                }
                                            }
                                        }

                                        kabupaten.clear();
                                        kabupaten.add("Pilih Kabupaten");
                                        KabupatenAdapter = new SimpleListAdapter(context, kabupaten, "Pilih Kabupaten");
                                        spinner_kabupaten.setAdapter(KabupatenAdapter);

                                        if(index > -1){
                                            getListKabupaten(listProvinsi.get(index).getId());
                                        }

//                                        Toast.makeText(context, "ID :  " + listProvinsi.get(index).getId() + " : " + listProvinsi.get(index).getNama(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNothingSelected() {
                                        Toast.makeText(context, "Nothing Selected", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                new Bantuan(context).toastLong(object.getString("message"));
                            }
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

    private void getListKabupaten(String ID_PROVINSI){
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
                            boolean isError = object.getBoolean("error");
                            if(!isError){
                                JSONArray jsonArray = object.getJSONArray("kabupatens");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    KabupatenModel kabupatenModel = new KabupatenModel();
                                    kabupatenModel.setId(jsonObject.getString("id"));
                                    kabupatenModel.setId_prov(jsonObject.getString("id_prov"));
                                    kabupatenModel.setNama(jsonObject.getString("nama"));
                                    kabupaten.add(jsonObject.getString("nama"));
                                    listKabupaten.add(kabupatenModel);
                                }

                                KabupatenAdapter = new SimpleListAdapter(context, kabupaten);
                                spinner_kabupaten.setAdapter(KabupatenAdapter);
                                spinner_kabupaten.setOnItemSelectedListener(new OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(View view, int position, long id) {
                                        int index = -1;
                                        for(int i = 0; i < listKabupaten.size() ; i++){
                                            if(KabupatenAdapter.getItem(position).equals(listKabupaten.get(i).getNama())){
                                                index = i;
                                                break;
                                            }
                                        }

                                        Toast.makeText(context, "ID :  " + listKabupaten.get(index).getId() + " : " + listKabupaten.get(index).getNama(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNothingSelected() {
                                        Toast.makeText(context, "Nothing Selected", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                new Bantuan(context).toastLong(object.getString("message"));
                            }
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
        new SweetAlertDialog(Pemesanan.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Peringatan")
                .setContentText("Apakah data yang anda masukkan sudah benar ?")
                .setConfirmText("Benar")
                .setCancelText("Salah")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                        sDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimary));
                        sDialog.setTitleText("Loading ...");
                        sDialog.showCancelButton(false);
                        sDialog.setContentText("Tunggu beberapa saat");
                        sDialog.setCancelable(false);
                        sDialog.show();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
    }

    private void getProvinsi() {
        List<String> kategoriProv = new ArrayList<>();
        kategoriProv.add("Jawa Tengah");
        kategoriProv.add("Jawa Barat");
        kategoriProv.add("Jawa Timur");

        ArrayAdapter<String> ProvKategori = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                kategoriProv);
        spinnerProv.setAdapter(ProvKategori);
        spinnerProv.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {

            }
        });
    }
}
