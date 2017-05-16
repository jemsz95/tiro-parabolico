package mx.itesm.tiroparabolico;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import java.util.HashSet;
import java.util.Set;

/**
 * Autor: Racket
 * Creación: 6 de Mayo 2017
 * Última modificación: 13 de Mayo 2017
 * Descipción: Adapta los datos del servidor para mostrar en lista de profesor
 */
public class TeacherAdapterLaunch extends FirebaseIndexListAdapter<Launch> {

    public Set<String> selected = new HashSet<>();

    public TeacherAdapterLaunch(Activity actual, @LayoutRes int modelLayout, Query keyRef, Query dataRef) {
        super(actual, Launch.class, modelLayout, keyRef, dataRef );
    }

    @Override
    protected Launch parseSnapshot(DataSnapshot snapshot) {
        Launch l = super.parseSnapshot(snapshot);
        l.setId(snapshot.getKey());
        return l;
    }

    @Override
    protected void populateView(View v, Launch l, int position ) {
        TextView tvStudent = (TextView) v.findViewById(R.id.text_author);
        TextView tvDate = (TextView) v.findViewById(R.id.text_date);
        TextView tvValues = (TextView) v.findViewById(R.id.text_valuesProf);
        ImageView ibVisible = (ImageView) v.findViewById(R.id.image_Visibility);
        tvStudent.setText(l.getUserName());
        tvDate.setText(DateUtils.getRelativeDateTimeString(mActivity, l.getTimestamp(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0));
        tvValues.setText(l.getV0() + " m/s   " + l.getTheta() + "°   " + l.getY0() + " m");
        ibVisible.setVisibility(selected.contains(getRef(position).getKey()) ? View.VISIBLE : View.INVISIBLE);
    }
}
