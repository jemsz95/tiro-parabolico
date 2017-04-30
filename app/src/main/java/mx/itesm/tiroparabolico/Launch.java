package mx.itesm.tiroparabolico;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Launch implements Serializable {
    // Gravity calculations
    private static final double GRAVITY = 9.81;
    private static final double TWICE_GRAVITY = 2 * GRAVITY;
    private static final double HALF_GRAVITY = 0.5 * GRAVITY;

    //Stored
    private long id;
    private long userId;
    private String name;
    private double y0 = 0;
    private double theta = 0;
    private double v0 = 0;
    private boolean favorite = false;





    //Setted
    private int resolution = 100;

    //Calculated
    private double flightTime;
    private double maxHeight;
    private double distance;
    private LineDataSet lineDataSet;

    //*JESUS PARA OBTENER FECHA
    private Calendar c;
    SimpleDateFormat df;
    String formattedDate;

    //Control
    private boolean calculated = false;

    public Launch() {}

    public Launch(int resolution) {
        this.resolution = resolution;
    }

                                                                                             //*Fecha
    public Launch(long id, long userId, double y0, double theta, double v0, boolean favorite,String formattedDate) {
        this(id, userId, y0, theta, v0, favorite, 100,formattedDate);
    }
                                                                                                             //*Fecha
    public Launch(long id, long userId, double y0, double theta, double v0, boolean favorite, int resolution, String formattedDate) {
        this.id = id;
        this.userId = userId;
        this.y0 = y0;
        this.theta = theta;
        this.v0 = v0;
        this.favorite = favorite;
        this.resolution = resolution;
        this.formattedDate=formattedDate;
    }

    // Generates the graph data set from initial launch params
    public void calculate() {
        //Don't recalculate without need
        if(calculated) {
            return;
        }

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

        lineDataSet = new LineDataSet(entries, name);

        calculated = true;
    }

    // Getters //
    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
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
    //*Get de fecha
    public String getFormattedDate(){
        c = Calendar.getInstance();
        df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

        return formattedDate;
    }
    // getter de calendar
    public Calendar getCalendar(){
        return c;
    }

    public boolean isFavorite() {
        return favorite;
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

    public LineDataSet getLineDataSet() {
        return lineDataSet;
    }

    public boolean isCalculated() {
        return calculated;
    }

    // Setters //
    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
        //set de Fecha
    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setY0(double y0) {
        this.y0 = y0;
        calculated = false;
    }

    public void setTheta(double theta) {
        this.theta = theta;
        calculated = false;
    }

    public void setV0(double v0) {
        this.v0 = v0;
        calculated = false;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }
}
