package mx.itesm.tiroparabolico;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Query;

public class HistoryListFragment extends ListFragment {

    StudentAdapterLaunch adapterLaunch;
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Query launchesReference = Database.getInstance().getReference("/launches").orderByChild("author").equalTo(user.getUid());

        adapterLaunch = new StudentAdapterLaunch(getActivity(), R.layout.row, launchesReference);
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
}
