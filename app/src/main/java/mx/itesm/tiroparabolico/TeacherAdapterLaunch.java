package mx.itesm.tiroparabolico;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

/**
 * Created by jorgeemiliorubiobarboza on 03/05/17.
 */

public class TeacherAdapterLaunch extends FirebaseIndexListAdapter<Launch> {

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
        tvStudent.setText(l.getUserName());
        tvDate.setText(DateUtils.getRelativeDateTimeString(mActivity, l.getTimestamp(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0));
        tvValues.setText(l.getV0() + " m/s   " + l.getTheta() + "°   " + l.getY0() + " m");
    }
}
