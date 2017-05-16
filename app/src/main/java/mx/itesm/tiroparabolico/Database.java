package mx.itesm.tiroparabolico;

import com.google.firebase.database.FirebaseDatabase;


/**
 * Autor: Racket
 * Creación: 20 de Marzo 2017
 * Última modificación: 15 de Mayo 2017
 * Descipción: Clase estatica que contiene la referencia a la base de datos con la
 *             configuración actual.
 *             Toda clase que busque escribir o leer a la base de datos debe accesar
 *             a ella mediante ésta.
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
