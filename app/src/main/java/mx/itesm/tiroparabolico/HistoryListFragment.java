package mx.itesm.tiroparabolico;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class HistoryListFragment extends ListFragment {

    String [] listaNombreLaunch = {"Tiro1", "Tiro2"};
    ArrayList<Launch> listaLaunch;
    ArrayAdapter<String> adapterLaunch;
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
        adapterLaunch = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,
                listaNombreLaunch);
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
