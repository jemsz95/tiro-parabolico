package mx.itesm.tiroparabolico;

import android.support.annotation.DrawableRes;

/**
 * Autor: Racket
 * Creación: 23 de Abril 2017
 * Última modificación: 23 de Abril 2017
 * Descipción: Contiene las propiedades de una instruccion que se mostrará en la página de
 *             intrucciones
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
