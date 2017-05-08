package mx.itesm.tiroparabolico;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;

import java.util.HashSet;
import java.util.Set;


public class HistoryListFragment extends ListFragment {

    StudentAdapterLaunch adapterLaunch;
    OnLaunchSelectedListener launchListener;
    Set<Integer> selectedPos;

    public HistoryListFragment() {

    }

    public static HistoryListFragment newInstance() {
        HistoryListFragment historyListFragment = new HistoryListFragment();
        return historyListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Query launchesReference = Database.getInstance().getReference("/launches").orderByChild("author").equalTo(user.getUid());

        adapterLaunch = new StudentAdapterLaunch(getActivity(), R.layout.row, launchesReference);
        selectedPos = new HashSet<>();

        setListAdapter(adapterLaunch);
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
        Launch launch = adapterLaunch.getItem(position);
        launchListener.onLaunchSelected(launch);

        if(selectedPos.contains(position)) {
            int c = ResourcesCompat.getColor(getResources(), R.color.list_default_color, null);
            v.setBackgroundColor(c);
            selectedPos.remove(position);
        } else {
            int c = ResourcesCompat.getColor(getResources(), R.color.list_pressed_color, null);
            v.setBackgroundColor(c);
            selectedPos.add(position);
        }
    }

    public interface OnLaunchSelectedListener {
        void onLaunchSelected(Launch l);
    }
}
