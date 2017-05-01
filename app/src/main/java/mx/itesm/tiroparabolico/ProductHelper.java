package mx.itesm.tiroparabolico;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jisus on 23/04/2017.
 */

public class ProductHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SimuladorDB.db";
    public static final String TABLE_SIMULADOR = "simulaciones";
    public static final String COLUMN_TIRO = "_id";
    public static final String COLUMN_ID_USUARIO = "usario_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_THETA = "theta";
    public static final String COLUMN_ALTURA = "altura";
    public static final String COLUMN_VELOCIDAD = "velocidad";
    public static final String COLUMN_FAVORITO = "favorito";


    private static ProductHelper productHelperInstance;

    public static synchronized ProductHelper getInstance(Context context){
        if (productHelperInstance == null){
            productHelperInstance = new ProductHelper(context.getApplicationContext());
        }
        return productHelperInstance;
    }

    private ProductHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        String CREATE_SIMULADOR_TABLE = "CREATE TABLE " +
                TABLE_SIMULADOR +
                "(" +
                COLUMN_TIRO + " INTEGER PRIMARY KEY," +
                COLUMN_ID_USUARIO + " INTEGER," +
                COLUMN_FAVORITO + " INTEGER," +
                COLUMN_NOMBRE + " STRING," +
                COLUMN_THETA + " REAL," +
                COLUMN_ALTURA + " REAL," +
                COLUMN_VELOCIDAD + " REAL," + ");";
        Log.i("Producthelper onCreate", CREATE_SIMULADOR_TABLE);
        arg0.execSQL(CREATE_SIMULADOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int newVersion, int oldVersion) {
        if (newVersion != oldVersion) {
            Log.w("Producthelper onUpgrade",
                    "Upgrading database from version " +
                            oldVersion +
                            " to " + newVersion +
                            ", which will destroy all old data");

            arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_SIMULADOR);
            onCreate(arg0);
        }
    }
}
