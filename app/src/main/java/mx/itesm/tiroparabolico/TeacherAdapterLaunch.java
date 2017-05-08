package mx.itesm.tiroparabolico;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.google.firebase.database.Query;

/**
 * Created by jorgeemiliorubiobarboza on 03/05/17.
 */

public class TeacherAdapterLaunch extends FirebaseIndexListAdapter<Launch> {

    public TeacherAdapterLaunch(Activity actual, @LayoutRes int modelLayout, Query keyRef, Query dataRef) {
        super(actual, Launch.class, modelLayout, keyRef, dataRef );
    }



    @Override
    protected void populateView(View v, Launch l, int position ) {
        TextView tvStudent = (TextView) v.findViewById(R.id.text_author);
        TextView tvDate = (TextView) v.findViewById(R.id.text_date);

        tvStudent.setText(l.getUserName());
        tvDate.setText(DateUtils.getRelativeDateTimeString(mActivity, l.getTimestamp(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0));
    }
}
