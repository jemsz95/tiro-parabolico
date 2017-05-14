package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InstruccionesActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitulo;
    private TextView tvTextInstruct;
    private ImageView ivInstruct;
    private int index = 0;
    private GestureDetectorCompat detector;
    private Instruccion[] instruccion = new Instruccion[] {
            new Instruccion("Desliza tu dedo hacia la izquierda o derecha para navegar por las instrucciones.", R.drawable.instruccionescorr3),
            new Instruccion("Ingresa los valores iniciales de velocidad, angulo y altura para la simulación.", R.drawable.instruccioncorr1),
            new Instruccion("Selecciona las gráficas de tus simulaciones anteriores que deseas mostrar en la gráfica.", R.drawable.instruccioncorr2),
            new Instruccion("Pellizca con dos dedos hacia afuera para poder enfocar la gráfica o hacia adentro para desenfocarla.", R.drawable.instruccionescorr4)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitulo = (TextView) findViewById(R.id.text_title_instructions);
        tvTextInstruct = (TextView) findViewById(R.id.text_TextInstruct);
        ivInstruct = (ImageView) findViewById(R.id.imageView_foto);

        tvTextInstruct.setText(instruccion[index].getTexto());
        ivInstruct.setImageResource(instruccion[index].getFotoRes());

        GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                index += (e1.getX() < e2.getX() ? 1 : -1) + instruccion.length;
                index %= instruccion.length;
                tvTextInstruct.setText(instruccion[index].getTexto());
                ivInstruct.setImageResource(instruccion[index].getFotoRes());

                return true;
            }
        };

        detector = new GestureDetectorCompat(this,gestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, SimulatorActivity.class);
        startActivity(i);
    }
}
