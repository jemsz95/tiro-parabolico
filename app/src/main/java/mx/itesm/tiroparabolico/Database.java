package mx.itesm.tiroparabolico;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jisus on 03/05/2017.
 */

public final class Database {
    private static FirebaseDatabase db;

    private Database() {
        //Private constructor to avoid instantiating helper class
    }

    //Retrieve the firebase database enabling persistence only once
    public static FirebaseDatabase getInstance() {
        if(db == null) {
            db = FirebaseDatabase.getInstance();
            db.setPersistenceEnabled(true);
        }

        return db;
    }
}
