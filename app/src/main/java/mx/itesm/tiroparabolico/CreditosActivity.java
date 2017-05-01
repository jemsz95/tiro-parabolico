package mx.itesm.tiroparabolico;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class CreditosActivity extends AppCompatActivity  {

    private ImageView ivLogo;
    private TextView tvTitulo;
    private TextView tvMail;
    private TextView tvNombre;
    private TextView tvMat;
    private ImageView ivProfile;
    private int index = 0;
    private GestureDetectorCompat detector;
    private Credito[] creditos = new Credito[] {
            new Credito("Jesus Guadiana","A00814770", "jisus130@hotmail.com", R.drawable.jesus),
            new Credito("Jorge Rubio","A00368770", "hola", R.drawable.jorge),
            new Credito("Juan Ulloa","A00817807", "juan.fernando.ulloa@gmail.com", R.drawable.juan),
            new Credito("Javier Meza","A01244496", "adios", R.drawable.javier)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        tvTitulo = (TextView) findViewById(R.id.text_CreditosTitulo);
        ivLogo = (ImageView) findViewById(R.id.image_LogoPrepaNet);
        tvNombre = (TextView) findViewById(R.id.textView_nombre);
        tvMat = (TextView) findViewById(R.id.textView_matricula);
        ivProfile = (ImageView)findViewById(R.id.imageView_foto);
        tvMail = (TextView) findViewById(R.id.textView_Mail);

        ivLogo.setImageResource(R.drawable.logoprepanetsolo);
        tvNombre.setText(creditos[index].getNombre());
        tvMat.setText(creditos[index].getMatricula());
        tvMail.setText(creditos[index].getMail());
        ivProfile.setImageResource(creditos[index].getFotoRes());

        GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                index += (e1.getX() < e2.getX() ? 1 : -1) + creditos.length;
                index %= creditos.length;

                tvNombre.setText(creditos[index].getNombre());
                tvMat.setText(creditos[index].getMatricula());
                tvMail.setText(creditos[index].getMail());
                ivProfile.setImageResource(creditos[index].getFotoRes());

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
