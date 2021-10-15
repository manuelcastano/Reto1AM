package com.example.reto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventoAdapter extends RecyclerView.Adapter<EventoView>{

    private ArrayList<Evento> eventos;

    public EventoAdapter(){
        eventos = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventoView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.eventorow, parent, false);
        EventoView skeleton = new EventoView(row);
        return skeleton;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoView skeleton, int position) {
        Evento evento = eventos.get(position);
        skeleton.setEvento(evento);
        skeleton.getImagenNegocio().setImageURI(evento.getUri());
        skeleton.getNombreEvento().setText(evento.getNombreEvento());
        skeleton.getNombreNegocio().setText(evento.getCompañia());
        skeleton.getUbicacion().setText(evento.getUbicacion());
        skeleton.getInicio().setText(evento.getInicio());
        skeleton.getFinalTV().setText(evento.getFin());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public void addEvento(Evento evento) {
        eventos.add(evento);
        notifyItemInserted(eventos.size()-1);
    }
}
