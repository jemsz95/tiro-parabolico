package mx.itesm.tiroparabolico;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

/**
 * Created by jorgeemiliorubiobarboza on 03/05/17.
 */
/*
*   clase que apoya para adquirir todos los launch's de un usuario de firebase
*   mediante firebase
*   */


public class StudentAdapterLaunch extends FirebaseListAdapter<Launch> {

    public StudentAdapterLaunch(Activity activity, @LayoutRes int modelLayout, Query ref){
        super(activity, Launch.class, modelLayout, ref);
    }

    @Override
    protected void populateView(View v, final Launch l, final int position ){
        TextView tvStudent = (TextView) v.findViewById(R.id.text_author);
        TextView tvDate = (TextView) v.findViewById(R.id.text_date);
        TextView tvData = (TextView) v.findViewById(R.id.text_values);
        ImageView ibFavorite = (ImageView) v.findViewById(R.id.button_favorite);

        tvStudent.setText(l.getUserName());
        tvDate.setText(DateUtils.getRelativeDateTimeString(mActivity, l.getTimestamp(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0));
        tvData.setText(l.getV0() + " m/s   " + l.getTheta() + "°   " + l.getY0() + " m");
        ibFavorite.setImageResource(l.isFavorite() ? R.drawable.ic_star_gold_24dp : R.drawable.ic_star_grey_24dp);

        ibFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference launchRef = getRef(position);

                launchRef.child("favorite")
                        .setValue(!l.isFavorite());

                // This is an ugly hack to obtain the class code of the current user
                // TODO: Move to application state
                String classCode = ((SimulatorActivity) mActivity).userClassCode;

                // Save to class_launch when students stars a launch
                if(!l.isFavorite()) {
                    // The user is starring the launch
                    Database.getInstance()
                            .getReference("class_launch/" + classCode + "/" + launchRef.getKey())
                            .setValue(true);
                } else {
                    // The user is removing the star
                    Database.getInstance()
                            .getReference("class_launch/" + classCode + "/" + launchRef.getKey())
                            .setValue(null);
                }
            }
        });
    }
}

