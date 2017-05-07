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
        DatabaseReference classMemeberRef = Database.getInstance().getReference("/class_member/1234" );
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Query launchesReference = Database.getInstance().getReference("/launches").orderByChild("author").equalTo(user.getUid()).orderByChild("timestamp");
        adapterLaunch2 = new StudentAdapterLaunch(getActivity(), R.layout.row, launchesReference);
        setListAdapter(adapterLaunch2);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnLaunchSelectedListener){
            launchListener = (OnLaunchSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + "must implement HistoryListFragment.OnItemSelectedListener");
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
