package mx.itesm.tiroparabolico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SimulatorActivity extends AppCompatActivity implements DatosFragment.OnGraphDataChangeListener {

    GraphFragment graphFragment;
    DatosFragment datosFragment;

    boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);

        graphFragment = (GraphFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_graph);
        datosFragment = (DatosFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_data);

        isLandscape = datosFragment == null;
    }

    @Override
    public void onGraphDataChange(double angle, double speed) {
        graphFragment.setTheta(angle);
        graphFragment.setV0(speed);
    }
}
