package mx.itesm.tiroparabolico;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {
    private static final String DEBUG_TAG = "TAG_FRAGMENT_DATOS";

    // UI elements
    private LineChart chart;

    // Graph parameters
    List<Launch> launches = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(DEBUG_TAG, "onCreate() has been called");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(DEBUG_TAG, "onActivityCreated() has been called.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        chart = (LineChart) view.findViewById(R.id.grafo);

        chart.setPinchZoom(true);

        AxisBase xAxis = chart.getXAxis();
        AxisBase yAxis = chart.getAxisLeft();

        xAxis.setAxisMinimum(0);
        yAxis.setAxisMinimum(0);

        xAxis.setGranularity(1);
        yAxis.setGranularity(1);

        xAxis.setGranularityEnabled(true);
        yAxis.setGranularityEnabled(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(DEBUG_TAG, "onStart() has been called.");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(DEBUG_TAG, "onResume() has been called.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(DEBUG_TAG, "onPause() has been called.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(DEBUG_TAG, "onStop() has been called.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(DEBUG_TAG, "onDestroyView() has been called.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(DEBUG_TAG, "onDestroy() has been called.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(DEBUG_TAG, "onDetach() has been called.");
    }

    public void addLaunch(Launch launch) {
        launches.add(launch);
    }

    public void clearLaunches() {
        launches.clear();
    }

    public List<Launch> getLaunches() {
        return launches;
    }

    public void graph() {
        List<ILineDataSet> dataSetList = new ArrayList<>();

        for(Launch l : launches) {
            l.calculate();
            dataSetList.add(l.getLineDataSet());
        }

        chart.setData(new LineData(dataSetList));
        chart.invalidate();
    }
}
