package mx.itesm.tiroparabolico;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by javier on 5/15/17.
 */

public class CreditsPagerAdapter extends FragmentStatePagerAdapter {

    List<Credito> credits;

    CreditsPagerAdapter(FragmentManager fm, List<Credito> credits) {
        super(fm);
        this.credits = credits;
    }

    @Override
    public Fragment getItem(int position) {
        return CreditFragment.newInstance(credits.get(position));
    }

    @Override
    public int getCount() {
        return credits.size();
    }
}
