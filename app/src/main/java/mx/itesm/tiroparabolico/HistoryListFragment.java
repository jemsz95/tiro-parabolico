package mx.itesm.tiroparabolico;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashSet;
import java.util.Set;

/**
 * Autor: Racket
 * Creación: 20 de Marzo 2017
 * Última modificación: 28 de Noviembre 2017
 * Descipción: Busca, filtra y despliega el historial de tiros de la base de datos
 */
public class HistoryListFragment extends ListFragment implements ItemSelector {
    private Set<String> selectedItems = new HashSet<>();
    private StudentAdapterLaunch adapterLaunch;
    private OnLaunchSelectedListener launchListener;
    private FavoriteAdapterLaunch favoriteAdapterLaunch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference launchesReference = Database.getInstance().getReference("/launches");
        DatabaseReference favoritesRef = Database.getInstance().getReference("/user_favorites/" + user.getUid());

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
        BaseAdapter currentAdapter = isFilteringFavorites() ? favoriteAdapterLaunch : adapterLaunch;
        Launch launch = (Launch) currentAdapter.getItem(position);
        boolean selected;
        selected = selectedItems.contains(launch.getId());

        if(selected) {
            selectedItems.remove(launch.getId());
        } else {
            selectedItems.add(launch.getId());
        }

        currentAdapter.notifyDataSetChanged();
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
