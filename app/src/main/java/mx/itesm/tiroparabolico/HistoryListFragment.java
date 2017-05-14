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

import java.util.HashSet;
import java.util.Set;

public class HistoryListFragment extends ListFragment implements ItemSelector {
    private Set<String> selectedItems = new HashSet<>();
    private StudentAdapterLaunch adapterLaunch;
    private OnLaunchSelectedListener launchListener;
    private FavoriteAdapterLaunch favoriteAdapterLaunch;

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
        Query favoritesRef = Database.getInstance().getReference("/user_favorites/" + user.getUid());

        adapterLaunch = new StudentAdapterLaunch(getActivity(), R.layout.row, launchesReference);
        favoriteAdapterLaunch = new FavoriteAdapterLaunch(getActivity(), R.layout.row, favoritesRef, launchesReference);

        adapterLaunch.setItemSelector(this);
        favoriteAdapterLaunch.setItemSelector(this);

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
        boolean selected;

        selected = selectedItems.contains(adapterLaunch.getRef(position).getKey());

        if(selected) {
            selectedItems.remove(adapterLaunch.getRef(position).getKey());
        } else {
            selectedItems.add(adapterLaunch.getRef(position).getKey());
        }

        ibVisible.setVisibility(selected ? View.INVISIBLE : View.VISIBLE);

        launchListener.onLaunchSelected(launch);
    }

    public void toggleFilterFavorites() {
        if(isFilteringFavorites()) {
            setListAdapter(adapterLaunch);
        } else {
            setListAdapter(favoriteAdapterLaunch);
        }
    }

    public boolean isFilteringFavorites() {
        return getListAdapter().equals(favoriteAdapterLaunch);
    }

    public interface OnLaunchSelectedListener {
        void onLaunchSelected(Launch l);
    }

    @Override
    public boolean isSelected(String id) {
        return selectedItems.contains(id);
    }
}
