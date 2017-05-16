package mx.itesm.tiroparabolico;

import com.google.firebase.database.PropertyName;

/**
 * Autor: Racket
 * Creación: 1 de Mayo 2017
 * Última modificación: 3 de Mayo 2017
 * Descripción: Contiene las propiedades de un usuario que se almacena en la base de datos
 */
public class UserInformation {
    public String name;
    public String lastname;
    @PropertyName("class")
    public String classId;
    public String email;


    public UserInformation(){
    }

    public UserInformation(String name, String lastname, String classId, String email) {
        this.name = name;
        this.lastname = lastname;
        this.classId = classId;
        this.email = email;
    }

}
