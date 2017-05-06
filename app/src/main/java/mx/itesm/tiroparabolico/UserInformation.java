package mx.itesm.tiroparabolico;

import com.google.firebase.database.PropertyName;

/**
 * Created by jisus on 01/05/2017.
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
