package mx.itesm.tiroparabolico;

/**
 * Autor: Racket
 * Creación: 6 de Mayo 2017
 * Última modificación: 7 de Mayo 2017
 * Descripción: Contiene las propiedades de un salón de clases registrado en la base de datos
 */
public class Clase {
    public String name;
    public String teacher;



    public Clase() {

    }

    public Clase(String teacher,String name) {
        this.name = name;
        this.teacher = teacher;

    }
}
