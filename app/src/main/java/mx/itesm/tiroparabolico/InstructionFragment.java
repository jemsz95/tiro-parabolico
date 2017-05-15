package mx.itesm.tiroparabolico;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by javier on 5/15/17.
 */

public class InstructionFragment extends Fragment {
    private TextView tvTextInstruct;
    private ImageView ivInstruct;
    private Instruccion instruction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instrucciones, container, false);

        tvTextInstruct = (TextView) view.findViewById(R.id.text_TextInstruct);
        ivInstruct = (ImageView) view.findViewById(R.id.imageView_foto);

        if(instruction != null) {
            tvTextInstruct.setText(instruction.getTexto());
            ivInstruct.setImageResource(instruction.getFotoRes());
        }

        return view;
    }

    public void setInstruction(Instruccion in) {
        instruction = in;

        if(getView() != null) {
            tvTextInstruct.setText(instruction.getTexto());
            ivInstruct.setImageResource(instruction.getFotoRes());
        }
    }

    public Instruccion getInstruction() {
        return instruction;
    }
}
