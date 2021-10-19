package com.example.reto1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto1.databinding.FragmentPerfilBinding;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {


    private FragmentPerfilBinding binding;
    private OnEditFragment listener;
    private static PerfilFragment perfilFragment;

    //State
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public PerfilFragment() {
        // Required empty public constructor
        profile= new Profile("Negocio por defecto", "Inserte aquí una descripción apropiada para su negocio. El autor de las publicaciones que haga en el fragmento “publicaciones” será negocio que se registre en este fragment", null);
    }

    public static PerfilFragment newInstance() {

        if(perfilFragment == null){
            perfilFragment = new PerfilFragment();
        }
        Bundle args = new Bundle();
        perfilFragment.setArguments(args);
        return perfilFragment;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void onResume() {
        binding.titlePro.setText(profile.getTitle());
        binding.descriptionPro.setText(profile.getDescription());

        if(profile.getUri()!= null) {
            Uri uri = Uri.parse(profile.getUri());
            Log.e("Hola:", uri.toString());
            binding.imageProfile.setImageURI(uri);
        }
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPerfilBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.editBtn.setOnClickListener(v->{


            listener.onEditFragment(profile);


        });

       //  recreamos el fragemento
        binding.titlePro.setText(profile.getTitle());
        binding.descriptionPro.setText(profile.getDescription());

        /*if(profile.getFile()!= null) {
            Bitmap bitmap = BitmapFactory.decodeFile(profile.getFile().getPath());
            binding.imageProfile.setImageBitmap(bitmap);
        }*/
        if (profile.getUri() != null){
            String uri = profile.getUri();
            Uri ur = Uri.parse(uri);
            binding.imageProfile.setImageURI(ur);
        }



        return view;
    }

    public void setListener(OnEditFragment listener) {
        this.listener = listener;
    }

    public interface OnEditFragment{
        void onEditFragment(Profile profile);
    }

    public FragmentPerfilBinding getBinding() {
        return binding;
    }
}