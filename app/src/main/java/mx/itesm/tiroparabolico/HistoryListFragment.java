package mx.itesm.tiroparabolico;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class HistoryListFragment extends ListFragment {

    String [] listaNombreLaunch = {"Tiro1", "Tiro2"};
    ArrayList<Launch> listaLaunch;
    ArrayAdapter<String> adapterLaunch;
    StudentAdapterLaunch adapterLaunch2;
    OnLaunchSelectedListener launchListener;

    public HistoryListFragment() {

    }

    public static HistoryListFragment newInstance() {
        HistoryListFragment historyListFragment = new HistoryListFragment();
        return historyListFragment;
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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference classMemeberRef = Database.getInstance().getReference("/class_member/1234" );
        Query launchesReference = Database.getInstance().getReference("/launches").orderByChild("author").equalTo(user.getUid());

        launchesReference.addValueEventListener(postListener);

        adapterLaunch2 = new StudentAdapterLaunch(getActivity(), android.R.layout.simple_list_item_activated_1, launchesReference);
        setListAdapter(adapterLaunch);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnLaunchSelectedListener){
            launchListener = (OnLaunchSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + "must implement HistoryListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        double angle;
        double speed;
        double height;

        if (position == 0) {
            angle = 20;
            speed = 15;
            height = 2;
        }
        else {
            angle = 10;
            speed = 20;
            height = 0;
        }

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
