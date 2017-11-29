package mx.itesm.tiroparabolico;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Autor: Racket
 * Creación: 3 de Mayo 2017
 * Última modificación: 28 de Noviembre 2017
 * Descipción: Busca y despliega el historial de tiros de la base de datos que fueron compartidos
 *             al maestro por parte de sus estudiantes
 */
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

                        DatabaseReference launchesReference = Database.getInstance().getReference("/launches");
                        DatabaseReference classMembersRef = Database.getInstance().getReference("class_launch/" + classId);
                        adapterLaunch = new TeacherAdapterLaunch(getActivity(), R.layout.row_teacher, classMembersRef, launchesReference);
                        setListAdapter(adapterLaunch);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("TAG_CANCEL", "Falló porque no conectó.");
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
        ImageView ibVisible = (ImageView) v.findViewById(R.id.image_Visibility);
        Launch launch = adapterLaunch.getItem(position);
        boolean selected = adapterLaunch.selected.contains(adapterLaunch.getRef(position).getKey());

        if(selected) {
            adapterLaunch.selected.remove(adapterLaunch.getRef(position).getKey());
        } else {
            adapterLaunch.selected.add(adapterLaunch.getRef(position).getKey());
        }

        ibVisible.setVisibility(!selected ? View.VISIBLE : View.INVISIBLE);
        launchListener.onLaunchSelected(launch);
    }

    public interface OnLaunchSelectedListener {
        void onLaunchSelected(Launch l);
    }

    public void filter(int value){
        if(value == 1) {
            DatabaseReference launchesReference = Database.getInstance().getReference("/launches");
            DatabaseReference classMembersRef = Database.getInstance().getReference().child("class_member/" + classId);
            adapterLaunch = new TeacherAdapterLaunch(getActivity(), R.layout.row_teacher, classMembersRef, launchesReference);
        }
        if(value==2){
            DatabaseReference launchesReference = Database.getInstance().getReference("/launches");
            DatabaseReference classMembersRef = Database.getInstance().getReference("class_member/" + classId);
            adapterLaunch = new TeacherAdapterLaunch(getActivity(), R.layout.row_teacher, classMembersRef, launchesReference);
        }
    }
}

