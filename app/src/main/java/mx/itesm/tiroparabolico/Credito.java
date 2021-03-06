package mx.itesm.tiroparabolico;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * Autor: Racket
 * Creación: 6 de Abril 2017
 * Última modificación: 15 de Mayo 2017
 * Descripción: Contiene las propiedades de un crédito que se mostrará en la página de créditos
 */
public class Credito implements Serializable {
    private String nombre;
    private String matricula;
    private String mail;
    private int fotoRes;

    public Credito(String nombre,String matricula, String mail, @DrawableRes int fotoRes){
        this.nombre = nombre;
        this.matricula = matricula;
        this.mail = mail;
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

    public String getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

}
