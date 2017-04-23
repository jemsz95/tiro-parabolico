package mx.itesm.tiroparabolico;

import android.support.annotation.DrawableRes;

/**
 * Created by jorgeemiliorubiobarboza on 14/04/17.
 */

public class Instruccion {
    private String texto;
    private int fotoRes;

    public Instruccion(String texto, @DrawableRes int fotoRes){
        this.texto = texto;
        this.fotoRes = fotoRes;
    }
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @DrawableRes
    public int getFotoRes() {
        return fotoRes;
    }

    public void setFotoRes( @DrawableRes int fotoRes){
        this.fotoRes = fotoRes;
    }
}
