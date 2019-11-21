package id.gobang.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.gobang.app.Fragment.About;
import id.gobang.app.Fragment.Beranda;
import id.gobang.app.Fragment.Cari;
import id.gobang.app.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btFavorite, btAccount;
    ImageButton btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new Beranda());
        btHome = findViewById(R.id.btHome);
        btFavorite = findViewById(R.id.btFavorite);
        btAccount = findViewById(R.id.btAccount);

        btHome.setOnClickListener(this);
        btFavorite.setOnClickListener(this);
        btAccount.setOnClickListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btFavorite:
                loadFragment(new Cari());
                break;
            case R.id.btHome:
                loadFragment(new Beranda());
                break;
            case R.id.btAccount:
                loadFragment(new About());
                break;

        }
    }
}
