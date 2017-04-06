package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InstruccionesActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivLogo;
    private TextView tvTitulo;
    private TextView tvCuerpo;
    private Button btnSkip;
    int idImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);


        tvTitulo = (TextView) findViewById(R.id.text_InstruccionesTitulo);
        tvCuerpo = (TextView) findViewById(R.id.text_InstruccionesCuerpo);
        ivLogo = (ImageView) findViewById(R.id.image_LogoPrepaNet);
        btnSkip = (Button) findViewById(R.id.button_skip);
        idImagen = R.drawable.logoprepanetsolo;

        Bitmap imagen = BitmapFactory.decodeResource(getResources(), idImagen);
        ivLogo.setImageBitmap(imagen);

        btnSkip.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_skip:
                Intent i = new Intent(this, SimuladoActivity.class);
                startActivity(i);
                break;


        }
    }
}
