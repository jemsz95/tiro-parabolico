package mx.itesm.tiroparabolico;

import android.app.Activity;
import android.renderscript.Double2;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;

/**
 * Created by jorgeemiliorubiobarboza on 03/05/17.
 */
/*
*
*   clase que apoya para adquirir todos los launch's de un usuario de firebase
*   mediante firebase  IndexListAdapter
*   */


public class StudentAdapterLaunch extends FirebaseListAdapter<Launch> {

    public StudentAdapterLaunch(Activity activity, @LayoutRes int modelLayout, Query ref){
        super(activity, Launch.class, modelLayout, ref);
    }

    @Override
    protected void populateView(View v, Launch l, int position ){
        TextView tvStudent = (TextView) v.findViewById(R.id.text_studentHistory);
        TextView tvAngle = (TextView) v.findViewById(R.id.text_angleHistory);
        TextView tvVelocity = (TextView) v.findViewById(R.id.text_velocityHistory);
        TextView tvHeight = (TextView) v.findViewById(R.id.text_heightHistory);

        if (l != null) {
            tvStudent.setText(l.getUserName());
            tvAngle.setText(Double.toString(l.getTheta()));
            tvVelocity.setText(Double.toString(l.getV0()));
            tvHeight.setText(Double.toString(l.getY0()));
        }
    }
}

