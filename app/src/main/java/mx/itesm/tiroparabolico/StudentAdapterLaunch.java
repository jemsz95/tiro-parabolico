package mx.itesm.tiroparabolico;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.firebase.ui.database.FirebaseIndexListAdapter;
import com.google.firebase.database.Query;

/**
 * Created by jorgeemiliorubiobarboza on 03/05/17.
 */


/*
*
*   clase que apoya para adquirir todos los launch's de un usuario de firebase
*   mediante firebase  IndexListAdapter
*   */

public class StudentAdapterLaunch extends FirebaseIndexListAdapter<Launch> {

    public StudentAdapterLaunch(Activity actual, @LayoutRes int modelLayout, Query keyRef, Query dataRef){

        super(actual, Launch.class, modelLayout, keyRef, dataRef );
    }

    @Override
    protected void populateView(View v, Launch l, int position ){

    }
}

