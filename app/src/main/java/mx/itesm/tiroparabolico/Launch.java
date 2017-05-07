package mx.itesm.tiroparabolico;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Launch implements Serializable {
    private static final String DEBUG_TAG = "Launch";

    // Gravity calculations
    private static final double GRAVITY = 9.81;
    private static final double TWICE_GRAVITY = 2 * GRAVITY;
    private static final double HALF_GRAVITY = 0.5 * GRAVITY;

    //Stored
    private String id;
    private String userId;
    private Double y0 = 0d;
    private Double theta = 0d;
    private Double v0 = 0d;
    private Boolean favorite = false;
    private String userName;

    //Setted
    private int resolution = 100;

    //Calculated
    private double flightTime;
    private double maxHeight;
    private double distance;
    private LineDataSet lineDataSet;

    private Calendar calendar = new GregorianCalendar();
    private SimpleDateFormat df;

    //Control
    private boolean calculated = false;

    public Launch() {}

    public Launch(int resolution) {
        this.resolution = resolution;
    }

    public Launch(String id, String userId, double y0, double theta, double v0, boolean favorite, Calendar calendar) {
        this(id, userId, y0, theta, v0, favorite, 100,calendar);
    }

    public Launch(String id, String userId, double y0, double theta, double v0, boolean favorite, int resolution, Calendar calendar) {
        this.id = id;
        this.userId = userId;
        this.y0 = y0;
        this.theta = theta;
        this.v0 = v0;
        this.favorite = favorite;
        this.resolution = resolution;
        this.calendar = calendar;
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

        lineDataSet = new LineDataSet(entries, "Tiro");

        calculated = true;
    }

    // Getters //
    @Exclude
    public String getId() {
        return id;
    }

    @PropertyName("author")
    public String getUserId() {
        return userId;
    }

    @PropertyName("height")
    public double getY0() {
        return y0;
    }

    @PropertyName("angle")
    public double getTheta() {
        return theta;
    }

    @PropertyName("velocity")
    public double getV0() {
        return v0;
    }

    @PropertyName("timestamp")
    public long getTimestamp() {
        return calendar.getTimeInMillis();
    }

    @PropertyName("favorite")
    public boolean isFavorite() {
        return favorite;
    }

    @PropertyName("author_name")
    public String getUserName() {
        return userName;
    }

    //*Get de fecha
    @Exclude
    public String getFormattedDate(){
        calendar = Calendar.getInstance();
        df = new SimpleDateFormat("dd-MMM-yyyy");

        return df.format(calendar.getTime());
    }

    // getter de calendar
    @Exclude
    public Calendar getCalendar(){
        return calendar;
    }

    @Exclude
    public int getResolution() {
        return resolution;
    }

    @Exclude
    public double getFlightTime() {
        return flightTime;
    }

    @Exclude
    public double getMaxHeight() {
        return maxHeight;
    }

    @Exclude
    public double getDistance() {
        return distance;
    }

    @Exclude
    public LineDataSet getLineDataSet() {
        return lineDataSet;
    }

    @Exclude
    public boolean isCalculated() {
        return calculated;
    }

    // Setters //
    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public void setFormattedDate(String formattedDate) throws ParseException {
        calendar.setTime(df.parse(formattedDate));
    }

    @PropertyName("author")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @PropertyName("height")
    public void setY0(double y0) {
        this.y0 = y0;
        calculated = false;
    }

    @PropertyName("angle")
    public void setTheta(double theta) {
        this.theta = theta;
        calculated = false;
    }

    @PropertyName("velocity")
    public void setV0(double v0) {
        this.v0 = v0;
        calculated = false;
    }

    @PropertyName("favorite")
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @PropertyName("timestamp")
    public void setTimestamp(long timestamp) {
        calendar.setTimeInMillis(timestamp);
    }

    @PropertyName("author_name")
    public void setUserName(String userName) {
        if(userName != null) {
            this.userName = userName;
        }

        Log.d(DEBUG_TAG, "User name for " + id + " is null");
    }

    @Exclude
    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    @Exclude
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Launch) {
            Launch l = (Launch) obj;
            return l.getId() == id;
        }

        return false;
    }
}
