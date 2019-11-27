package id.gobang.app.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.gobang.app.Adapter.Company;
import id.gobang.app.Adapter.ProductAdapter;
import id.gobang.app.Model.Product;
import id.gobang.app.R;

public class Tentang extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        recyclerView = findViewById(R.id.recyclerViewTentang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Company> companies = new ArrayList<>();

        ArrayList<Product> tentang = new ArrayList<>();
        tentang.add(new Product("Gobang adalah suatu aplikasi yang memudahkan masyarakat dalam pengambilan barang bukti tilang. Aplikasi ini merupakan aplikasi resmi yang dikeluarkan oleh Kejaksaan Negeri yang bekerja sama dengan Kantor Pos. Dengan menggunakan aplikasi ini, masyarakat dapat menghemat waktunya tanpa harus mengantre ambil bukti tilang."));

        Company ttg = new Company("Apa itu GOBANG?", tentang);
        companies.add(ttg);

        ArrayList<Product> carapenggunaan = new ArrayList<>();
        carapenggunaan.add(new Product("1. Buka aplikasi GOBANG"));
        carapenggunaan.add(new Product("2. Masukkan id tilang Anda."));
        carapenggunaan.add(new Product("3. Lalu klik tombol cari, aplikasi GOBANG akan menampilkan data tilang."));
        carapenggunaan.add(new Product("4. Kemudian, masukkan data diri Anda dan alamat pengiriman Anda."));
        carapenggunaan.add(new Product("5. Lakukan pembayaran sesuai dengan total biaya yang tetera pada aplikasi, bisa melalui loket pembayaran di kantor pos, ataupun melalui aplikasi Posgiro Mobile"));
        carapenggunaan.add(new Product("6. Apabila pembayaran sudah dilakukan, maka akan muncul nomor resi pengiriman (1x24jam) setelah proses pembayaran"));

        Company pakai = new Company("Cara Penggunaan", carapenggunaan);
        companies.add(pakai);

        ArrayList<Product> keunggulan = new ArrayList<>();
        keunggulan.add(new Product("1. Mudah digunakan."));
        keunggulan.add(new Product("2. Hemat waktu."));
        keunggulan.add(new Product("3. Tidak perlu antre mengambil barang bukti."));

        Company unggul = new Company("Keunggulan", keunggulan);
        companies.add(unggul);

        ProductAdapter adapter = new ProductAdapter(companies);
        recyclerView.setAdapter(adapter);
    }
}
