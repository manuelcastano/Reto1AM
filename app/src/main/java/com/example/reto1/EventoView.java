package com.example.reto1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventoView extends RecyclerView.ViewHolder {

    private Evento evento;
    private ImageView imagenNegocio;
    private TextView nombreNegocio, nombreEvento, ubicacion, inicio, finalTV;

    public EventoView(@NonNull View itemView) {
        super(itemView);
        imagenNegocio = itemView.findViewById(R.id.imagenNegocio);
        nombreNegocio = itemView.findViewById(R.id.nombreNegocio);
        nombreEvento = itemView.findViewById(R.id.nombreEvento);
        ubicacion = itemView.findViewById(R.id.ubicacion);
        inicio = itemView.findViewById(R.id.inicio);
        finalTV = itemView.findViewById(R.id.finalTV);
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Evento getEvento() {
        return evento;
    }

    public ImageView getImagenNegocio() {
        return imagenNegocio;
    }

    public void setImagenNegocio(ImageView imagenNegocio) {
        this.imagenNegocio = imagenNegocio;
    }

    public TextView getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(TextView nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public TextView getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(TextView nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public TextView getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(TextView ubicacion) {
        this.ubicacion = ubicacion;
    }

    public TextView getInicio() {
        return inicio;
    }

    public void setInicio(TextView inicio) {
        this.inicio = inicio;
    }

    public TextView getFinalTV() {
        return finalTV;
    }

    public void setFinalTV(TextView finalTV) {
        this.finalTV = finalTV;
    }
}
