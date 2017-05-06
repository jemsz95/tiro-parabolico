package mx.itesm.tiroparabolico;

import com.google.firebase.database.PropertyName;

/**
 * Created by jisus on 03/05/2017.
 */

public class clase {
    public String name;
    public String teacher;



    public clase(){
    }

    public clase(String teacher,String name) {
        this.name = name;
        this.teacher = teacher;

    }
}
