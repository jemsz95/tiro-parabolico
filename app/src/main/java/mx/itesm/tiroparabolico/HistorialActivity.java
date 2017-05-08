package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by jorgeemiliorubiobarboza on 06/05/17.
 */

public class HistorialActivity extends AppCompatActivity
        implements DatosFragment.OnGraphDataChangeListener,
        HistoryListFragmentProfessor.OnLaunchSelectedListener {

    private GraphFragment graphFragment;
    private HistoryListFragmentProfessor historyListFragment;
    private FirebaseAuth firebaseAuth;

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
        getMenuInflater().inflate(R.menu.menu_filtro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.FiltroAlumnos_action) {
            historyListFragment.filter(2);
        }
        if (id == R.id.filtroFecha_action){
            historyListFragment.filter(1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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
        historyListFragment = (HistoryListFragmentProfessor) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_history);
    }
}
