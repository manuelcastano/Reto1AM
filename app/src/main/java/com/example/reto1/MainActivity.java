package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.reto1.databinding.FragmentPerfilBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements PerfilFragment.OnEditFragment,PublicacionesFragment.OnAddPublicacion,EditProfileFragment.OnProfile, AddPublicacionFragment.OnEvento {

    private PerfilFragment perfilFragment;
    private PublicacionesFragment publicacionesFragment;
    private MapaFragment mapaFragment;
    private EditProfileFragment editProfileFragment;
    private BottomNavigationView navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigator = findViewById(R.id.navigator);
        editProfileFragment = EditProfileFragment.newInstance();
        perfilFragment = PerfilFragment.newInstance();
        publicacionesFragment = PublicacionesFragment.newInstance();
        mapaFragment = MapaFragment.newInstance();

        perfilFragment.setListener(this);
        editProfileFragment.setListener(this);
        publicacionesFragment.setListener(this);
        publicacionesFragment.setListenerToAddPublicaciones(this);

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
    public void onEditFragment(Profile profile) {
        editProfileFragment.setProfile(profile);
        showFragment(editProfileFragment);
    }

    @Override
    public void addPublicacion(AddPublicacionFragment fragment) {
        showFragment(fragment);
    }

    @Override
    public void onProfile(Profile profile) {
        perfilFragment.setProfile(profile);
        showFragment(perfilFragment);
    }

    @Override
    protected void onResume() {
        SharedPreferences preferences = getSharedPreferences("MainActivity",MODE_PRIVATE);
        String json =  preferences.getString("profile","NO_OBJ");
        if(!json.equals("NO_OBJ")){
            Gson gson = new Gson();
            Toast.makeText(this,json,Toast.LENGTH_LONG).show();
             Profile profile = gson.fromJson(json,Profile.class);
             perfilFragment.setProfile(profile);


             /*perfilFragment.getBinding().titlePro.setText(profile.getTitle());
             perfilFragment.getBinding().descriptionPro.setText(profile.getDescription());
             if(profile.getUri()!= null) {
                 Uri uri = Uri.parse(profile.getUri());
                 perfilFragment.getBinding().imageProfile.setImageURI(uri);
             }*/


        }


        super.onResume();
    }

    @Override
    protected void onPause() {
        Profile profile = perfilFragment.getProfile();

        Gson gson = new Gson();
        String json = gson.toJson(profile);
        //Toast.makeText(this,">>>>"+json,Toast.LENGTH_LONG).show();
        //Almacenamos en el localStorage =SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MainActivity",MODE_PRIVATE);
        preferences.edit().putString("profile",json).apply();

        super.onPause();
    }

    @Override
    public void onEvento(Evento evento) {
        evento.setCompañia(perfilFragment.getProfile().getTitle());
        String uri = perfilFragment.getProfile().getUri();
        Uri ur = Uri.parse(uri);
        evento.setUri(ur);
        publicacionesFragment.addEvento(evento);
        showFragment(publicacionesFragment);
    }
}