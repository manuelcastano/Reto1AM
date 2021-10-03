package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements PerfilFragment.OnEditFragment {

    private PerfilFragment perfilFragment;
    private PublicacionesFragment publicacionesFragment;
    private MapaFragment mapaFragment;
    private BottomNavigationView navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigator = findViewById(R.id.navigator);

        perfilFragment = PerfilFragment.newInstance();
        publicacionesFragment = PublicacionesFragment.newInstance();
        mapaFragment = MapaFragment.newInstance();

        perfilFragment.setListener(this);


        showFragment(perfilFragment);

        navigator.setOnItemSelectedListener(
                menuItem -> {
                    if(menuItem.getItemId() == R.id.perfilItem){
                        showFragment(perfilFragment);
                    } else if(menuItem.getItemId() == R.id.publicacionesItem){
                        showFragment(publicacionesFragment);
                    } else if(menuItem.getItemId() == R.id.mapaItem){
                        showFragment(mapaFragment);
                    }
                    return true;
                }
        );
    }

    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    @Override
    public void onEditFragment() {

      EditProfileFragment editProfileFragment = EditProfileFragment.newInstance();
      showFragment(editProfileFragment);

    }
}