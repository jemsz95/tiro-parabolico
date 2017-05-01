package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SimulatorActivity extends AppCompatActivity
        implements DatosFragment.OnGraphDataChangeListener,
        HistoryListFragment.OnLaunchSelectedListener {

    GraphFragment graphFragment;
    DatosFragment datosFragment;
    HistoryListFragment historyListFragment;
    private FirebaseAuth firebaseAuth;






    boolean landscape;

    @Override
    public void onGraphDataChange(Launch launch) {
        graphFragment.clearLaunches();

        graphFragment.addLaunch(launch);
        graphFragment.graph();
    }

    @Override
    public void onLaunchSelected(Launch l) {
        graphFragment.addLaunch(l);
        graphFragment.graph();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simulador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.creditos_action) {
            Intent i = new Intent(this, CreditosActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
        }
        if (id == R.id.instrucciones_action){
            Intent i = new Intent(this, InstruccionesActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
        }
        if(id == R.id.logout_action){
            firebaseAuth.signOut();
            //closing activity
            finish();


            startActivity(new Intent(this, LoginActivity.class));


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        graphFragment = (GraphFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_graph);
        datosFragment = (DatosFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_data);

        landscape = datosFragment == null;

        if(landscape) {
            historyListFragment = (HistoryListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_history);
        }
    }
}
