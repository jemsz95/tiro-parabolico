package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SimulatorActivity extends AppCompatActivity
        implements DatosFragment.OnGraphDataChangeListener,
        HistoryListFragment.OnLaunchSelectedListener {

    // Ugly hack to obtain class code elsewhere
    // TODO: Move to application state
    public String userClassCode;

    private static final String DEBUG_TAG = "SimulatorActivity";
    private static final int STUDENT = 0;
    private static final int TEACHER = 1;
    private static final int UNKNOWN = 2;

    int userRole = UNKNOWN;
    private GraphFragment graphFragment;
    private DatosFragment datosFragment;
    private HistoryListFragment historyListFragment;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    boolean landscape;

    @Override
    public void onGraphDataChange(Launch launch) {

        DatabaseReference newLaunchRef = Database.getInstance().getReference("launches").push();
        launch.setUserId(currentUser.getUid());
        launch.setUserName(currentUser.getDisplayName());
        newLaunchRef.setValue(launch);
        launch.setId(newLaunchRef.getKey());

        graphFragment.clearLaunches();
        graphFragment.addLaunch(launch);
        graphFragment.graph();
    }

    @Override
    public void onLaunchSelected(Launch l) {
        //Remove launch if exists on graph
        if(!graphFragment.removeLaunch(l)) {
            //Launch did not exist. Add it!
            graphFragment.addLaunch(l);
        }

        graphFragment.graph();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
            switch (userRole) {
                case STUDENT:
                    getMenuInflater().inflate(R.menu.menu_alumno, menu);
                    break;

                case TEACHER:
                    getMenuInflater().inflate(R.menu.menu_maestro, menu);
                    break;
            }

        return super.onCreateOptionsMenu(menu);
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
        if (id == R.id.historal_action) {
            Intent i = new Intent(this, HistorialActivity.class);
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
            Intent i = new Intent(this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        //getting current user
        currentUser = firebaseAuth.getCurrentUser();

        //if the user is not logged in
        //that means current user will return null
        if(currentUser == null){
            Intent i = new Intent(this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return;
        }

        graphFragment = (GraphFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_graph);
        datosFragment = (DatosFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_data);

        landscape = datosFragment == null;

        if(landscape) {
            historyListFragment = (HistoryListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_history);
        }

        Database.getInstance()
                .getReference("teachers/" + currentUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        userRole = dataSnapshot.exists() ? TEACHER : STUDENT;
                        invalidateOptionsMenu();

                        if(userClassCode == null) {
                            userClassCode = (String) dataSnapshot.child("class").getValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(DEBUG_TAG, "Could not connect to teachers");
                    }
                });

        Database.getInstance()
                .getReference("students/" + currentUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(userClassCode == null) {
                            userClassCode = (String) dataSnapshot.child("class").getValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(DEBUG_TAG, "Could not connect to students");
                    }
                });
    }
}
