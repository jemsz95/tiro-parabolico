package mx.itesm.tiroparabolico;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.HashSet;
import java.util.Set;

/**
 * Autor: Racket
 * Creación: 6 de Mayo 2017
 * Última modificación: 28 de Noviembre 2017
 * Descipción: Adapta los datos del servidor para mostrar en lista de profesor
 */
public class TeacherAdapterLaunch extends FirebaseListAdapter<Launch> {

    public Set<String> selected = new HashSet<>();
    private Activity mActivity;

    public TeacherAdapterLaunch(Activity actual, @LayoutRes int modelLayout, Query keyRef, DatabaseReference dataRef) {
        super(new FirebaseListOptions.Builder<Launch>()
                .setIndexedQuery(keyRef, dataRef, new LaunchSnapshotParser())
                .setLayout(modelLayout)
                .build()
        );

        mActivity = actual;
    }

    @Override
    protected void populateView(View v, Launch l, int position ) {
        TextView tvStudent = v.findViewById(R.id.text_author);
        TextView tvDate = v.findViewById(R.id.text_date);
        TextView tvValues = v.findViewById(R.id.text_valuesProf);
        ImageView ibVisible = v.findViewById(R.id.image_Visibility);
        tvStudent.setText(l.getUserName());
        tvDate.setText(DateUtils.getRelativeDateTimeString(mActivity, l.getTimestamp(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0));
        tvValues.setText(l.getV0() + " m/s   " + l.getTheta() + "°   " + l.getY0() + " m");
        ibVisible.setVisibility(selected.contains(getRef(position).getKey()) ? View.VISIBLE : View.INVISIBLE);
    }
}