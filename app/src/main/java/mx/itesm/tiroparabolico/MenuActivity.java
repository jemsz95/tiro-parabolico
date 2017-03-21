package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSimulator;
    Button btnInstructions;
    Button btnCredits;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnSimulator = (Button) findViewById(R.id.button_simulador);
        btnInstructions = (Button) findViewById(R.id.button_instruciones);
        btnCredits = (Button) findViewById(R.id.button_creditos);
        btnLogOut = (Button) findViewById(R.id.button_logout);

        btnSimulator.setOnClickListener(this);
        btnInstructions.setOnClickListener(this);
        btnCredits.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.button_simulador:
                i = new Intent(this, SimuladoActivity.class);
                startActivity(i);
                break;

            case R.id.button_instruciones:
                i = new Intent(this, InstruccionesActivity.class);
                startActivity(i);
                break;

            case R.id.button_creditos:
                i = new Intent(this, CreditosActivity.class);
                startActivity(i);
                break;

            case R.id.button_logout:
                //TODO: Log out the user
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
