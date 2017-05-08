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

    TeacherAdapterLaunch adapterLaunch;
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
                .getReference("teachers/" + user.getUid() + "/class")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        classId = (String) dataSnapshot.getValue();

                        Query launchesReference = Database.getInstance().getReference("/launches");
                        DatabaseReference classMembersRef = Database.getInstance().getReference("class_launch/" + classId);
                        adapterLaunch = new TeacherAdapterLaunch(getActivity(), R.layout.row_teacher, classMembersRef, launchesReference);
                        setListAdapter(adapterLaunch);
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
                    + "must implement HistoryListFragmentProfessor.OnItemSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Launch launch = adapterLaunch.getItem(position);
        launchListener.onLaunchSelected(launch);
    }

    public interface OnLaunchSelectedListener {
        void onLaunchSelected(Launch l);
    }

    public void filter(int value){
        if(value == 1) {
            Query launchesReference = Database.getInstance().getReference("/launches");
            DatabaseReference classMembersRef = Database.getInstance().getReference().child("class_member/" + classId);
            adapterLaunch = new TeacherAdapterLaunch(getActivity(), R.layout.row_teacher, classMembersRef, launchesReference);
        }
        if(value==2){
            Query launchesReference = Database.getInstance().getReference("/launches");
            DatabaseReference classMembersRef = Database.getInstance().getReference("class_member/" + classId);
            adapterLaunch = new TeacherAdapterLaunch(getActivity(), R.layout.row_teacher, classMembersRef, launchesReference);
        }
    }
}

