package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
        // Remove launch from list
        if(!graphFragment.removeLaunch(l)) {
            // Launch was not in list. Add it!
            graphFragment.addLaunch(l);
        }

        graphFragment.graph();
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        graphFragment = (GraphFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_graph);
        historyListFragment = (HistoryListFragmentProfessor) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_history);
    }
}
