package mx.itesm.tiroparabolico;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CreditosActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private TextView tvTitulo;
    private TextView tvCuerpo;
    int idImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        tvTitulo = (TextView) findViewById(R.id.text_CreditosTitulo);
        tvCuerpo = (TextView) findViewById(R.id.text_CreditosCuerpo);
        ivLogo = (ImageView) findViewById(R.id.image_LogoPrepaNet);
        idImagen = R.drawable.logoprepanetsolo;

        Bitmap imagen = BitmapFactory.decodeResource(getResources(), idImagen);
        ivLogo.setImageBitmap(imagen);

    }
}
