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

import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {
    private static final String DEBUG_TAG = "TAG_FRAGMENT_DATOS";

    // Gravity calculations
    private static final double GRAVITY = 9.81;
    private static final double TWICE_GRAVITY = 2 * GRAVITY;
    private static final double HALF_GRAVITY = 0.5 * GRAVITY;

    // UI elements
    private LineChart chart;

    // Graph parameters
    private String name;
    private int resolution = 100;
    private double flightTime = 0;
    private double maxHeight = 0;
    private double distance = 0;
    private double y0 = 0;
    private double theta = 0;
    private double v0 = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(DEBUG_TAG, "onCreate() has been called");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        name = getResources().getString(R.string.trajectory);

        Log.d(DEBUG_TAG, "onActivityCreated() has been called.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        chart = (LineChart) view.findViewById(R.id.grafo);

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

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setV0(double v0) {
        this.v0 = v0;
    }

    public int getResolution() {
        return resolution;
    }

    public double getFlightTime() {
        return flightTime;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public double getDistance() {
        return distance;
    }

    public double getY0() {
        return y0;
    }

    public double getTheta() {
        return theta;
    }

    public double getV0() {
        return v0;
    }

    public void graph() {
        generateDataSet();
        chart.invalidate();
    }

    // Generates the graph data set from initial launch params
    // TODO: Move this behavior to separate class
    private void generateDataSet() {
        // Data entries
        List<Entry> entries = new ArrayList<>();

        // Intermediate values
        double thetaRads = Math.toRadians(theta);
        double vX = v0 * Math.cos(thetaRads);
        double vY = v0 * Math.sin(thetaRads);
        double twiceGY0 = TWICE_GRAVITY * y0;
        double t = 0;

        // Other useful calculations
        flightTime = (vY + Math.sqrt((vY * vY) + twiceGY0)) / GRAVITY;
        maxHeight = ((vY * vY) / TWICE_GRAVITY) + y0;
        distance = vX * flightTime;

        // Calculate step of graph
        double step = flightTime / resolution;

        // Generate data set
        for(int i = 0; i < resolution; i++) {
            float x = (float) (vX * t);
            float y = (float) ((vY * t) + y0 - (HALF_GRAVITY * t * t));

            Entry entry = new Entry(x, y);

            t += step;
            entries.add(entry);
        }

        LineData data = new LineData(new LineDataSet(entries, name));

        chart.setData(data);
    }
}
