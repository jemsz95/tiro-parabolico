package mx.itesm.tiroparabolico;

/**
 * Created by jisus on 01/05/2017.
 */

public class UserInformation {
    String name;
    String lastname;
    String clase;
    String email;

    public UserInformation(){

    }

    public UserInformation(String name, String lastname, String clase, String email) {
        this.name = name;
        this.lastname = lastname;
        this.clase = clase;
        this.email = email;
    }

}
