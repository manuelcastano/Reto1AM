package com.example.reto1;

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


    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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



        return view;
    }

    public void setListener(OnEditFragment listener) {
        this.listener = listener;
    }

    public interface OnEditFragment{
        void onEditFragment();
    }
}