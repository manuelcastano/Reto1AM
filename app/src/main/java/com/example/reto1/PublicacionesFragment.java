package com.example.reto1;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublicacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicacionesFragment extends Fragment {

    private Button addBtn;
    private ConstraintLayout noPublicaciones;
    private OnAddPublicacion listener;
    private AddPublicacionFragment fragment;

    public PublicacionesFragment() {
        fragment = AddPublicacionFragment.newInstance();
    }

    public static PublicacionesFragment newInstance() {
        PublicacionesFragment fragment = new PublicacionesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publicaciones, container, false);
        addBtn = view.findViewById(R.id.addBtn);
        noPublicaciones = view.findViewById(R.id.noPublicaciones);

        addBtn.setOnClickListener(
                (v)->{
                    listener.addPublicacion(fragment);
                }
        );

        return view;
    }

    public interface OnAddPublicacion{
        public void addPublicacion(AddPublicacionFragment fragment);
    }

    public void setListener(OnAddPublicacion listener) {
        this.listener = listener;
    }
}