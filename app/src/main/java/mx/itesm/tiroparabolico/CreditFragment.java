package mx.itesm.tiroparabolico;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Fragmento que muestra una instancia de un credito
 */
public class CreditFragment extends Fragment {
    // the fragment initialization parameters
    private static final String ARG_CREDIT = "credit";

    private Credito credit;
    private TextView tvMail;
    private TextView tvNombre;
    private TextView tvMat;
    private ImageView ivProfile;


    public CreditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param credit Credito a ser desplegado
     * @return A new instance of fragment CreditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreditFragment newInstance(Credito credit) {
        CreditFragment fragment = new CreditFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CREDIT, credit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            credit = (Credito) getArguments().getSerializable(ARG_CREDIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_credito, container, false);

        tvNombre = (TextView) v.findViewById(R.id.textView_nombre);
        tvMat = (TextView) v.findViewById(R.id.textView_matricula);
        ivProfile = (ImageView) v.findViewById(R.id.imageView_foto);
        tvMail = (TextView) v.findViewById(R.id.textView_Mail);

        tvNombre.setText(credit.getNombre());
        tvMat.setText(credit.getMatricula());
        tvMail.setText(credit.getMail());
        ivProfile.setImageResource(credit.getFotoRes());

        return v;
    }

}
