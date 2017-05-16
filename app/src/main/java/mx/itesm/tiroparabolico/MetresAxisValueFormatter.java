package mx.itesm.tiroparabolico;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Autor: Racket
 * Creación: 14 de Mayo 2017
 * Última modificación: 14 de Mayo 2017
 * Descipción: Formatea los contadores de los ejes para desplegar la unidad de medida
 */
public class MetresAxisValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return Float.toString(value) + "m";
    }
}
