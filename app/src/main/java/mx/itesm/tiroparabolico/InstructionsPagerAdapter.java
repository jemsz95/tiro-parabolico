package mx.itesm.tiroparabolico;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by javier on 5/15/17.
 */

public class InstructionsPagerAdapter extends FragmentStatePagerAdapter {

    private List<Instruccion> instructions;

    InstructionsPagerAdapter(FragmentManager fm, List<Instruccion> instructions) {
        super(fm);
        this.instructions = instructions;
    }

    @Override
    public Fragment getItem(int position) {
        // Recylce framents to reduce memory footprint and processing
        InstructionFragment fragment = new InstructionFragment();
        fragment.setInstruction(instructions.get(position));

        return fragment;
    }

    @Override
    public int getCount() {
        return instructions.size();
    }
}
