package com.example.reto1;

import android.net.Uri;

import com.google.android.gms.maps.model.Marker;

public class Evento {

    private String compañia;
    private String nombreEvento;
    private String inicio;
    private String fin;
    private Marker ubicacion;
    private Uri uri;

    public Evento(String compañia, String nombreEvento, String inicio, String fin, Marker ubicacion, Uri uri) {
        this.compañia = compañia;
        this.nombreEvento = nombreEvento;
        this.inicio = inicio;
        this.fin = fin;
        this.ubicacion = ubicacion;
        this.uri = uri;
    }

    public Evento() {
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Marker getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Marker ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
