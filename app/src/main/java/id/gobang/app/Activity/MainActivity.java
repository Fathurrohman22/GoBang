package id.gobang.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import id.gobang.app.Fragment.About;
import id.gobang.app.Fragment.Beranda;
import id.gobang.app.Fragment.Cari;
import id.gobang.app.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btFavorite, btAccount;
    ImageButton btHome;

    private SpaceNavigationView spaceNavigationView;

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

        spaceNavigationView = findViewById(R.id.spaceNavigationView);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Beranda", R.drawable.home));
        spaceNavigationView.addSpaceItem(new SpaceItem("Tentang", R.drawable.ic_aboout));
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                loadFragment(new Beranda());
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if(itemIndex == 0){
                    loadFragment(new Cari());
                } else if(itemIndex == 1){
                    loadFragment(new About());
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                if(itemIndex == 0){
                    loadFragment(new Cari());
                } else if(itemIndex == 1){

                    loadFragment(new About());
                }
            }
        });
        loadFragment(new Cari());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
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
