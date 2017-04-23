package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InstruccionesActivity extends AppCompatActivity  {

    private ImageView ivLogo;
    private TextView tvTitulo;
    private TextView tvTextInstruct;
    private ImageView ivInstruct;
    private int index = 0;
    private GestureDetectorCompat detector;
    private Instruccion[] instruccion = new Instruccion[] {
            new Instruccion("Coloca la altura inicial", R.drawable.instrucciones),
            new Instruccion("Ingresa los valores iniciales", R.drawable.instrucciones2),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);

        tvTitulo = (TextView) findViewById(R.id.text_InstruccionesTitulo);
        ivLogo = (ImageView) findViewById(R.id.image_LogoPrepaNet);
        tvTextInstruct = (TextView) findViewById(R.id.text_TextInstruct);
        ivInstruct = (ImageView)findViewById(R.id.imageView_foto);

        ivLogo.setImageResource(R.drawable.logoprepanetsolo);

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

}
