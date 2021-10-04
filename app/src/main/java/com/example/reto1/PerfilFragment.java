package com.example.reto1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reto1.databinding.FragmentPerfilBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {


    private FragmentPerfilBinding binding;
    private OnEditFragment listener;

    //State
    private Profile profile;



    public PerfilFragment() {
        // Required empty public constructor
        profile= new Profile("Negocio por defecto","Inserte aquí una descripción apropiada para su negocio. El autor de las publicaciones que haga en el fragmento “publicaciones” será negocio que se registre en este fragment",null);
    }

    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPerfilBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.editBtn.setOnClickListener(v->{

            listener.onEditFragment();

        });

       //  recreamos el fragemento
        binding.titlePro.setText(profile.getTitle());
        binding.descriptionPro.setText(profile.getDescription());

        /*if(profile.getFile()!= null) {
            Bitmap bitmap = BitmapFactory.decodeFile(profile.getFile().getPath());
            binding.imageProfile.setImageBitmap(bitmap);
        }*/
        if (profile.getUri() != null){
            binding.imageProfile.setImageURI(profile.getUri());
        }

        return view;
    }

    public void setListener(OnEditFragment listener) {
        this.listener = listener;
    }

    public interface OnEditFragment{
        void onEditFragment();
    }
}