package mx.itesm.tiroparabolico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SimuladoActivity extends AppCompatActivity {

    private double yInitial;
    private double yFinal;
    private double xFinal;
    private double shotAngle;
    private double speedInitial;
    private EditText etAngle;
    private EditText etSpeed;
    private double constGravity=9.8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulado);

        etSpeed = (EditText) findViewById(R.id.edit_Velocidad);
        etAngle = (EditText) findViewById(R.id.edit_Angulo);
    }

    //funcion para calcular el alcance final, en base al aungulo y velocidad inical del tiro
    private double finalReach(double speedInitial, double shotAngle){
        double radians = 2 * (Math.toRadians(shotAngle));
        xFinal = ((Math.pow(speedInitial,2.0)) * Math.sin(radians))/(constGravity);
        return xFinal;
    }

    //funcion para calcular la altura final
    private double finalHeight(double speedInitial, double shotAngle, double xFinal){
        double t=0.0;
        double radians = (Math.toRadians(shotAngle));
        t = (xFinal)/(speedInitial * Math.cos(radians));

        yFinal = (speedInitial * Math.sin(radians))*t;

        return  yFinal;
    }
}
