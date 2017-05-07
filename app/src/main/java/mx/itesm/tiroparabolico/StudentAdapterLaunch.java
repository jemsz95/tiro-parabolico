package mx.itesm.tiroparabolico;

import android.app.Activity;
import android.renderscript.Double2;
import android.support.annotation.LayoutRes;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;

/**
 * Created by jorgeemiliorubiobarboza on 03/05/17.
 */
/*
*   clase que apoya para adquirir todos los launch's de un usuario de firebase
*   mediante firebase  IndexListAdapter
*   */


public class StudentAdapterLaunch extends FirebaseListAdapter<Launch> {

    public StudentAdapterLaunch(Activity activity, @LayoutRes int modelLayout, Query ref){
        super(activity, Launch.class, modelLayout, ref);
    }

    @Override
    protected void populateView(View v, final Launch l, final int position ){
        TextView tvStudent = (TextView) v.findViewById(R.id.text_author);
        TextView tvDate = (TextView) v.findViewById(R.id.text_date);
        ImageView ibFavorite = (ImageView) v.findViewById(R.id.button_favorite);

        tvStudent.setText(l.getUserName());
        tvDate.setText(DateUtils.getRelativeDateTimeString(mActivity, l.getTimestamp(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0));

        ibFavorite.setImageResource(l.isFavorite() ? R.drawable.ic_star_24dp : R.drawable.ic_star_border_24dp);

        ibFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRef(position).child("favorite")
                        .setValue(!l.isFavorite());
            }
        });
    }
}

