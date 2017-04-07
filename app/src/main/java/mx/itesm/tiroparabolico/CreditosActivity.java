package mx.itesm.tiroparabolico;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import static mx.itesm.tiroparabolico.R.id.image;
import static mx.itesm.tiroparabolico.R.id.image_LogoPrepaNet;

public class CreditosActivity extends AppCompatActivity  {

    private ImageView ivLogo;
    private TextView tvTitulo;
    private TextView tvCuerpo;
    private TextView tvNombre;
    private TextView tvMat;
    private int fotoJesus;
    private int fotoJavier;
    private int fotoNando;
    private int fotoJorge;
    Credito cred;
    Credito cred1;
    Credito cred2;
    Credito cred3;
    int idImagen;
    int idImagen2;
    private ImageView ivProfile;
    private int index=0;
    private GestureDetectorCompat detector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        tvTitulo = (TextView) findViewById(R.id.text_CreditosTitulo);
        ivLogo = (ImageView) findViewById(R.id.image_LogoPrepaNet);
        idImagen = R.drawable.logoprepanetsolo;
        tvNombre= (TextView) findViewById(R.id.textView_nombre);
        tvMat= (TextView) findViewById(R.id.textView_matricula);
        fotoJesus=R.drawable.jesus;
        fotoJavier=R.drawable.javier;
        fotoNando=R.drawable.juan;
        fotoJorge=R.drawable.jorge;

        ivProfile=(ImageView)findViewById(R.id.imageView_foto);



        Bitmap imagen = BitmapFactory.decodeResource(getResources(), idImagen);
        ivLogo.setImageBitmap(imagen);


        //Agregando perfiles
        Bitmap imagenJesus=BitmapFactory.decodeResource(getResources(),fotoJesus);
        cred=new Credito("Jesus Guadiana","A00814770",imagenJesus);

        final Credito[] creditos=new Credito[3];

        creditos[0]=cred;
        creditos[1]=cred1;



        tvNombre.setText(creditos[index].getNombre());
      tvMat.setText(creditos[index].getMatricula());
       ivProfile.setImageBitmap(creditos[index].getFoto());

        GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() == e2.getX()) {
                    return false;
                }
                Bitmap imagenJesus=BitmapFactory.decodeResource(getResources(),fotoJesus);
                Bitmap imagenJavier=BitmapFactory.decodeResource(getResources(),fotoJavier);
                Bitmap imagenNando=BitmapFactory.decodeResource(getResources(),fotoNando);
                Bitmap imagenJorge=BitmapFactory.decodeResource(getResources(),fotoJorge);
                cred=new Credito("Jesus Guadiana","A00814770",imagenJesus);
                cred1=new Credito("Jorge Rubio","A00368770",imagenJorge);
                cred2=new Credito("Juan Ulloa","A00817807",imagenNando);
                cred3=new Credito("Javier Meza","A01244496",imagenJavier);
                final Credito[] creditos=new Credito[4];

                creditos[0]=cred;
                creditos[1]=cred1;
                creditos[2]=cred2;
                creditos[3]=cred3;

                index += (e1.getX() < e2.getX() ? 1 : -1) + creditos.length;
                index %= creditos.length;

                if(index>=0&&index<=3){
                    tvNombre.setText(creditos[index].getNombre());
                    tvMat.setText(creditos[index].getMatricula());
                    ivProfile.setImageBitmap(creditos[index].getFoto());
                }



                return true;
            }
        };

        detector=new GestureDetectorCompat(this,gestureListener);



    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
