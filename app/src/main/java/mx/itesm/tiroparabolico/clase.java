package mx.itesm.tiroparabolico;

import com.google.firebase.database.PropertyName;

/**
 * Created by jisus on 03/05/2017.
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
