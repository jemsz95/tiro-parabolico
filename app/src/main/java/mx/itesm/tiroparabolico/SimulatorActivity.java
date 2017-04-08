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
    public void onAngleChange(double theta) {
        graphFragment.setTheta(theta);
    }

    @Override
    public void onSpeedChange(double speed) {
        graphFragment.setV0(speed);
    }

    @Override
    public void onHeightChange(double height) {
        graphFragment.setY0(height);
    }
}
