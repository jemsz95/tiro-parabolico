package mx.itesm.tiroparabolico;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class InstruccionesActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private InstructionsPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Instruccion> instructions = new ArrayList<>();

        instructions.add(new Instruccion(getResources().getString(R.string.instruction_3),
                        R.drawable.instruccionescorr3));
        instructions.add(new Instruccion(getResources().getString(R.string.instruction_1),
                        R.drawable.instruccioncorr1));
        instructions.add(new Instruccion(getResources().getString(R.string.instruction_2),
                        R.drawable.instruccioncorr2));
        instructions.add(new Instruccion(getResources().getString(R.string.instruction_4),
                        R.drawable.instruccionescorr4));

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new InstructionsPagerAdapter(getSupportFragmentManager(), instructions);

        viewPager.setAdapter(adapter);
    }
}
