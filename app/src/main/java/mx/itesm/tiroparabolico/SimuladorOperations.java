package mx.itesm.tiroparabolico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
    public static final String COLUMN_THETA = "theta";
    public static final String COLUMN_ALTURA = "altura";
    public static final String COLUMN_VELOCIDAD = "velocidad";
    public static final String COLUMN_FAVORITO = "favorito";
    public static final String COLUMN_FECHA = "fecha";


    public SimuladorOperations(Context context){
        dbHelper = ProductHelper.getInstance(context);
    }

    public long addSimulacion(Launch launch) {
        db = dbHelper.getWritableDatabase();
        long newRowID=0;
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TIRO, launch.getId());
            values.put(COLUMN_ID_USUARIO, launch.getUserId());
            values.put(COLUMN_THETA, launch.getTheta());
            values.put(COLUMN_ALTURA, launch.getY0());
            values.put(COLUMN_VELOCIDAD, launch.getV0());
            values.put(COLUMN_FECHA, launch.getFormattedDate());
            values.put(COLUMN_FAVORITO, launch.isFavorite());
            newRowID=db.insert(TABLE_SIMULADOR, null, values);
        } catch (SQLiteException e){
            // Error, db can't be opened
            Log.d(TAG, "Error while trying to add product to database");

        }
        return newRowID;
    }



    public List<Launch> getAllSimulaciones(){
        String query = "SELECT * FROM " + TABLE_SIMULADOR;
        List<Launch> listaDeSimulaciones = new ArrayList<Launch>();
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()){
                while(cursor.isAfterLast() == false) {
                    int TIRO = cursor.getInt(cursor.getColumnIndex(COLUMN_TIRO));
                    int IDUSUARIO = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_USUARIO));
                    String DATE = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA));
                    String FAVORITO = cursor.getString(cursor.getColumnIndex(COLUMN_FAVORITO));
                    double THETA= cursor.getDouble(cursor.getColumnIndex(COLUMN_THETA));
                    double ALTURA= cursor.getDouble(cursor.getColumnIndex(COLUMN_ALTURA));
                    double VELOCIDAD=cursor.getDouble(cursor.getColumnIndex(COLUMN_VELOCIDAD));
                    boolean FAVORITO2;
                    if(FAVORITO=="true"){
                        FAVORITO2=true;
                    }else{
                        FAVORITO2=false;
                    }

                    Launch simulacion = new Launch(TIRO,IDUSUARIO,ALTURA,THETA,VELOCIDAD,FAVORITO2,DATE);
                    listaDeSimulaciones.add(simulacion);
                    cursor.moveToNext();
                }
            }
        } catch (SQLiteException e){
            Log.d(TAG, "Error while trying to get products from database");
        }
        db.close();
        return listaDeSimulaciones;
    }

}
