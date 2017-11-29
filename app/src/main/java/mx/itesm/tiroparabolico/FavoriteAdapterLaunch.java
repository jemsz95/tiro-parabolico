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

/**
 * Autor: Racket
 * Creación: 14 de Mayo 2017
 * Última modificación: 28 de Noviembre 2017
 * Descipción: Adaptador de Launch que filtra por favoritos
 */
public class FavoriteAdapterLaunch extends FirebaseListAdapter<Launch> {
    private ItemSelector selector;
    private Activity mActivity;

    public FavoriteAdapterLaunch(Activity activity, @LayoutRes int modelLayout,
                                 Query keysRef, DatabaseReference valuesRef) {
        super(new FirebaseListOptions.Builder<Launch>()
                .setIndexedQuery(keysRef, valuesRef, new LaunchSnapshotParser())
                .setLayout(modelLayout)
                .build()
        );

        mActivity = activity;
    }

    public void setItemSelector(ItemSelector selector) {
        this.selector = selector;
    }

    @Override
    protected void populateView(View v, final Launch l, final int position ){
        TextView tvStudent = v.findViewById(R.id.text_author);
        TextView tvDate = v.findViewById(R.id.text_date);
        TextView tvData = v.findViewById(R.id.text_values);
        ImageView ibFavorite = v.findViewById(R.id.button_favorite);
        ImageView ibVisible = v.findViewById(R.id.image_Visibility);

        tvStudent.setText(l.getUserName());
        tvDate.setText(DateUtils.getRelativeDateTimeString(mActivity, l.getTimestamp(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0));
        tvData.setText(l.getV0() + " m/s   " + l.getTheta() + "°   " + l.getY0() + " m");
        ibFavorite.setImageResource(l.isFavorite() ? R.drawable.ic_star_gold : R.drawable.ic_star_unpressed);

        if(selector != null) {
            ibVisible.setVisibility(selector.isSelected(l.getId()) ? View.VISIBLE : View.INVISIBLE);
        }

        ibFavorite.setOnClickListener(new StarClickListener(l, position));
    }

    private class StarClickListener implements View.OnClickListener {
        private int position;
        private Launch l;

        StarClickListener(Launch l, int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            DatabaseReference launchRef = getRef(position);

            launchRef.child("favorite")
                    .setValue(!l.isFavorite());

            // This is an ugly hack to obtain the class code of the current user
            // TODO: Move to application state
            String classCode = ((SimulatorActivity) mActivity).userClassCode;

            // Save to class_launch when students stars a launch
            // Save to user_favorites when a user stars a launch
            if(!l.isFavorite()) {
                // The user is starring the launch
                Database.getInstance()
                        .getReference("class_launch/" + classCode + "/" + launchRef.getKey())
                        .setValue(true);

                Database.getInstance()
                        .getReference("user_favorites/" + l.getUserId() + "/" + launchRef.getKey())
                        .setValue(true);
            } else {
                // The user is removing the star
                Database.getInstance()
                        .getReference("class_launch/" + classCode + "/" + launchRef.getKey())
                        .setValue(null);

                Database.getInstance()
                        .getReference("user_favorites/" + l.getUserId() + "/" + launchRef.getKey())
                        .setValue(null);
            }
        }
    }
}