package mx.itesm.tiroparabolico;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jisus on 03/05/2017.
 */

public final class Database {
    private static FirebaseDatabase db;

    public static FirebaseDatabase getInstance() {
        if(db == null) {
            db = FirebaseDatabase.getInstance();
            db.setPersistenceEnabled(true);
        }

        return db;
    }
}
