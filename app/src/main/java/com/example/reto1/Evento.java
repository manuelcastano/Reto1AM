package com.example.reto1;


import java.lang.String;
import com.google.android.gms.maps.model.Marker;

public class Evento {

    private String compañia;
    private String nombreEvento;
    private String inicio;
    private String fin;
    private String ubicacion;
    private String uri;

    public Evento(String compañia, String nombreEvento, String inicio, String fin, String ubicacion, String uri) {
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
