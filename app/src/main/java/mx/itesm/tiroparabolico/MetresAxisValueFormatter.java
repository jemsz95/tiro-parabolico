package mx.itesm.tiroparabolico;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by javier on 5/14/17.
 */

public class MetresAxisValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return Float.toString(value) + "m";
    }
}
