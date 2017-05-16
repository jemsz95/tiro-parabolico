package mx.itesm.tiroparabolico;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Autor: Racket
 * Creación: 20 de Marzo 2017
 * Última modificación: 15 de Mayo 2017
 * Descipción: Adaptador de Crédito a ViewPager utilizando Fragment
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
