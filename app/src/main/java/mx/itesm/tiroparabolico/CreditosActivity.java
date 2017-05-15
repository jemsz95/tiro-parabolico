package mx.itesm.tiroparabolico;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CreditosActivity extends AppCompatActivity  {

    private ViewPager pager;
    private CreditsPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pager = (ViewPager) findViewById(R.id.pager);

        ArrayList<Credito> credits = new ArrayList<>();

        credits.add(new Credito("Jesús Guadiana","A00814770", "jisus130@hotmail.com", R.drawable.jesus));
        credits.add(new Credito("Jorge Rubio","A00368770", "jorge.rubiobarboza96@gmail.com", R.drawable.jorge));
        credits.add(new Credito("Juan Ulloa","A00817807", "juan.fernando.ulloa@gmail.com", R.drawable.juan));
        credits.add(new Credito("Javier Meza","A01244496", "javenmeza@gmail.com", R.drawable.javier));

        adapter = new CreditsPagerAdapter(getSupportFragmentManager(), credits);

        pager.setAdapter(adapter);
    }

}
