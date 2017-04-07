package mx.itesm.tiroparabolico;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.DrawableRes;

/**
 * Created by jisus on 06/04/2017.
 */

public class Credito {
    private String nombre;
    private String matricula;
    private int fotoRes;

    public Credito(String nombre,String matricula, @DrawableRes int fotoRes){
        this.nombre = nombre;
        this.matricula = matricula;
        this.fotoRes = fotoRes;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @DrawableRes
    public int getFotoRes() {
        return fotoRes;
    }

    public void setFotoRes( @DrawableRes int fotoRes){
        this.fotoRes = fotoRes;
    }

}
