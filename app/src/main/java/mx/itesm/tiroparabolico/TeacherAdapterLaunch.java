package mx.itesm.tiroparabolico;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.annotation.LayoutRes;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by jorgeemiliorubiobarboza on 03/05/17.
 */

public class TeacherAdapterLaunch extends FirebaseIndexListAdapter<Launch>  {

    public TeacherAdapterLaunch(Activity actual, @LayoutRes int modelLayout, Query keyRef, Query dataRef){
        super(actual, Launch.class, modelLayout, keyRef, dataRef );
    }

    @Override
    protected void populateView(View v, Launch l, int position ){
        TextView tvStudent = (TextView) v.findViewById(R.id.text_studentHistory);
        TextView tvAngle = (TextView) v.findViewById(R.id.text_angleHistory);
        TextView tvVelocity = (TextView) v.findViewById(R.id.text_velocityHistory);
        TextView tvHeight = (TextView) v.findViewById(R.id.text_heightHistory);

        tvStudent.setText(l.getUserName());
        tvAngle.setText(Double.toString(l.getTheta()));
        tvVelocity.setText(Double.toString(l.getV0()));
        tvHeight.setText(Double.toString(l.getY0()));
    }
}
