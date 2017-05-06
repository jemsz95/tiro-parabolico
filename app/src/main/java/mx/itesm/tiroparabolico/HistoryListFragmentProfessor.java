package mx.itesm.tiroparabolico;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by jorgeemiliorubiobarboza on 03/05/17.
 */

public class HistoryListFragmentProfessor extends ListFragment {

    String [] listaNombreLaunch = {"Tiro1", "Tiro2"};
    ArrayList<Launch> listaLaunch;
    ArrayAdapter<DatabaseReference> adapterLaunch;
    AdapterLaunch adapterLaunch2;
    HistoryListFragment.OnLaunchSelectedListener launchListener;


    public HistoryListFragmentProfessor() {

    }

    public static HistoryListFragmentProfessor newInstance() {
        HistoryListFragmentProfessor historyListFragmentProfessor = new HistoryListFragmentProfessor();
        return historyListFragmentProfessor;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Launch object and use the values to update the UI
                Launch launch = dataSnapshot.getValue(Launch.class);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Launch failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        DatabaseReference classMemeberRef = FirebaseDatabase.getInstance().getReference("/class_member/1234" );
        DatabaseReference launchesReference = FirebaseDatabase.getInstance().getReference("/launches");
        launchesReference.addValueEventListener(postListener);

        adapterLaunch2 = new AdapterLaunch(getActivity(), android.R.layout.simple_list_item_activated_1,classMemeberRef,
                launchesReference);
        setListAdapter(adapterLaunch2);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof HistoryListFragment.OnLaunchSelectedListener){
            launchListener = (HistoryListFragment.OnLaunchSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + "must implement HistoryListFragment.OnItemSelectedListener");
        }
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        double angle = 20;
        double speed = 15;
        double height = 2;

        Launch launch = new Launch();

        launch.setV0(speed);
        launch.setTheta(angle);
        launch.setY0(height);
        launch.calculate();

        launchListener.onLaunchSelected(launch);
    }

    public interface OnLaunchSelectedListener {
        public void onLaunchSelected(Launch l);
    }
}

