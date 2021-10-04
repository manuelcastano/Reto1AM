package com.example.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reto1.databinding.FragmentPerfilEditBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    private FragmentPerfilEditBinding binding;
    private OnProfile listener;



    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentPerfilEditBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.editInfoBtn.setOnClickListener(v->{

            String title = binding.titleNeg.getText().toString();
            String description = binding.descripcionText.getText().toString();
            Profile profile = new Profile(title,description);
            listener.onProfile(profile);



        });

        return view;
    }

    public void setListener(OnProfile listener) {
        this.listener = listener;
    }

    public interface OnProfile{
        void onProfile(Profile profile);
    }


}