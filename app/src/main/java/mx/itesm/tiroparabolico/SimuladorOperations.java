package mx.itesm.tiroparabolico;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by jisus on 23/04/2017.
 */

public class SimuladorOperations {

    private SQLiteDatabase db;
    private ProductHelper dbHelper;
    List<Launch> listaLaunch = new ArrayList<Launch>();
    public static final String TABLE_SIMULADOR = "simulaciones";
    public static final String COLUMN_TIRO = "_id";
    public static final String COLUMN_ID_USUARIO = "user_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_THETA = "theta";
    public static final String COLUMN_ALTURA = "altura";
    public static final String COLUMN_VELOCIDAD = "velocidad";
    public static final String COLUMN_FAVORITO = "favorito";


    public SimuladorOperations(Context context){
        dbHelper = ProductHelper.getInstance(context);
    }

    public void addSimulacion(Launch launch) {
        db = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TIRO, launch.getTiro());
            values.put(COLUMN_ID_USUARIO, launch.getIdUsuario());
            values.put(COLUMN_NOMBRE, launch.getNombre());
            values.put(COLUMN_THETA, launch.getTheta());
            values.put(COLUMN_ALTURA, launch.getAltura());
            values.put(COLUMN_VELOCIDAD, launch.getVelocidad());
            values.put(COLUMN_FAVORITO, launch.getFavorito());
            db.insert(TABLE_SIMULADOR, null, values);
        } catch (SQLiteException e){
            // Error, db can't be opened
            Log.d(TAG, "Error while trying to add product to database");
        }
    }

}
