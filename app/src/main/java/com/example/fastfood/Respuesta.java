package com.example.fastfood;

import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class Respuesta {
    private String cuestion;
    private String respuesta;
    ImageView image;


    public Respuesta(String cuestion, String respuesta) {
        this.cuestion = cuestion;
        this.respuesta = respuesta;

    }

    public String getCuestion() {

        return cuestion;
    }
    public String getRespuesta() {
        return respuesta;
    }

}

