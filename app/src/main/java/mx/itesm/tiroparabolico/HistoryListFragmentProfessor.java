package mx.itesm.tiroparabolico;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by jorgeemiliorubiobarboza on 03/05/17.
 */

//Clase para desplegar el historial de los estudiantes para el maestro

public class HistoryListFragmentProfessor extends ListFragment {

    TeacherAdapterLaunch adapterLaunch2;
    HistoryListFragmentProfessor.OnLaunchSelectedListener launchListener;
    String classId;

    public HistoryListFragmentProfessor() {

    }

    public static HistoryListFragmentProfessor newInstance() {
        HistoryListFragmentProfessor historyListFragmentProfessor = new HistoryListFragmentProfessor();
        return historyListFragmentProfessor;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Database.getInstance()
                .getReference("teacher/" + user.getUid() + "/class")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        classId = (String) dataSnapshot.getValue();

                        Query launchesReference = Database.getInstance().getReference("/launches").orderByChild("timestamp");
                        DatabaseReference classMembersRef = Database.getInstance().getReference().child("class_member/" + classId);
                        adapterLaunch2 = new TeacherAdapterLaunch(getActivity(), R.layout.row, classMembersRef, launchesReference);
                        setListAdapter(adapterLaunch2);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("OMNOM", "Fallo porque no conecto");
                    }
                });
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof HistoryListFragmentProfessor.OnLaunchSelectedListener){
            launchListener = (HistoryListFragmentProfessor.OnLaunchSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + "must implement HistoryListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Launch launch = adapterLaunch2.getItem(position);
        launchListener.onLaunchSelected(launch);
    }

    public interface OnLaunchSelectedListener {
        public void onLaunchSelected(Launch l);
    }
}

