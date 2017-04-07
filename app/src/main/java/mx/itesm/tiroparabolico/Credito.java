package mx.itesm.tiroparabolico;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by jisus on 06/04/2017.
 */

public class Credito {
    private String nombre;
    private String matricula;
    private Bitmap foto;

    public Credito(){super();}

    public Credito(String nombre,String matricula, Bitmap foto){
        this.nombre=nombre;
        this.matricula=matricula;
        this.foto=foto;
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


    public Bitmap getFoto() {return foto;}

    public void setFoto(Bitmap foto){this.foto = foto;}



}
