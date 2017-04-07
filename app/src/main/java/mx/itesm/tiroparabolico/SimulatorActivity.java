package mx.itesm.tiroparabolico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SimulatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);

        GraphFragment graphFragment = (GraphFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_graph);

        graphFragment.setY0(0);
        graphFragment.setTheta(45);
        graphFragment.setV0(12);

        graphFragment.graph();
    }
}
