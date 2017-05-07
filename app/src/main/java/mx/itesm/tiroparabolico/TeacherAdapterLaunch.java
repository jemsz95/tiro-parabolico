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
        //TODO
    }
}
