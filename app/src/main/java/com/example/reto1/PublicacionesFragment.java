package com.example.reto1;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublicacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicacionesFragment extends Fragment {

    private Button addBtn, addBtn2;
    private ConstraintLayout noPublicaciones;
    private OnAddPublicacion listener;
    private AddPublicacionFragment fragment;
    private RecyclerView publicacionesRecycler;
    private LinearLayoutManager manager;
    private EventoAdapter adapter;

    public PublicacionesFragment() {
        adapter = new EventoAdapter();
        fragment = AddPublicacionFragment.newInstance();
    }

    public static PublicacionesFragment newInstance() {
        PublicacionesFragment fragment = new PublicacionesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public EventoAdapter getAdapter() {
        return adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publicaciones, container, false);
        addBtn = view.findViewById(R.id.addBtn);
        addBtn2 = view.findViewById(R.id.addBtn2);
        noPublicaciones = view.findViewById(R.id.noPublicaciones);

        publicacionesRecycler = view.findViewById(R.id.publicacionesRecycler);
        manager = new LinearLayoutManager(view.getContext());
        publicacionesRecycler.setLayoutManager(manager);
        publicacionesRecycler.setAdapter(adapter);
        publicacionesRecycler.setHasFixedSize(true);

        addBtn.setOnClickListener(
                (v)->{
                    listener.addPublicacion(fragment);
                }
        );
        addBtn2.setOnClickListener(
                (v)->{
                    listener.addPublicacion(fragment);
                }
        );

        if(adapter.getItemCount() > 0){
            noPublicaciones.setVisibility(View.INVISIBLE);
            addBtn2.setVisibility(View.VISIBLE);
            addBtn.setVisibility(View.INVISIBLE);
            publicacionesRecycler.setVisibility(View.VISIBLE);
        }
        return view;
    }

    public void addEvento(Evento evento) {
        adapter.addEvento(evento);
    }

    public interface OnAddPublicacion{
        public void addPublicacion(AddPublicacionFragment fragment);
    }

    public void setListener(OnAddPublicacion listener) {
        this.listener = listener;
    }

    public void setListenerToAddPublicaciones(AddPublicacionFragment.OnEvento listener){
        fragment.setListener(listener);
    }

    public ArrayList<Evento> getEventos(){
        return adapter.getEventos();
    }
}