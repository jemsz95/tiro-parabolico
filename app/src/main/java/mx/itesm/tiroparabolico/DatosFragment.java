package mx.itesm.tiroparabolico;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class DatosFragment extends Fragment implements View.OnClickListener {
    private static final String DEBUG_TAG = "TAG_FRAGMENT_DATOS";

    private TextView tvAltura;
    private TextView tvAlcance;
    private TextView tvTiempo;
    private EditText etVelocidad;
    private EditText etAngulo;
    private EditText etAltura;
    private Button btnSimular;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private OnGraphDataChangeListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference = Database.getInstance().getReference();
        Log.d(DEBUG_TAG, "onCreate() has been called");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set OnGraphDataChangeListener using parent activity
        if (getActivity() instanceof OnGraphDataChangeListener) {
            listener = (OnGraphDataChangeListener) getActivity();
        } else {
            throw new RuntimeException("Parent class "
                    + getActivity().toString()
                    + "should implement OnGraphDataChangeListener"
            );
        }

        Log.d(DEBUG_TAG, "onActivityCreated() has been called.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos, container, false);

        tvAltura = (TextView) view.findViewById(R.id.text_altura);
        tvAlcance = (TextView) view.findViewById(R.id.text_alcance);
        tvTiempo = (TextView) view.findViewById(R.id.text_tiempo);
        etAngulo = (EditText) view.findViewById(R.id.edit_angulo);
        etVelocidad = (EditText) view.findViewById(R.id.edit_velocidad);
        etAltura = (EditText) view.findViewById(R.id.edit_altura);
        btnSimular = (Button) view.findViewById(R.id.button_Simular);

        btnSimular.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        etVelocidad.setText(null);
        etAltura.setText(null);
        etAngulo.setText(null);
        Log.d(DEBUG_TAG, "onStart() has been called.");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(DEBUG_TAG, "onResume() has been called.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(DEBUG_TAG, "onPause() has been called.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(DEBUG_TAG, "onStop() has been called.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(DEBUG_TAG, "onDestroyView() has been called.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(DEBUG_TAG, "onDestroy() has been called.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(DEBUG_TAG, "onDetach() has been called.");
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_Simular:
                if (etAngulo.getText().toString().trim().length() > 0 && etVelocidad.getText().toString().trim().length() > 0
                        && etAltura.getText().toString().trim().length() > 0) {
                    if (Double.parseDouble(etAngulo.getText().toString()) >= -90 &&
                            Double.parseDouble(etAngulo.getText().toString()) <= 90 &&
                            Double.parseDouble(etVelocidad.getText().toString()) >= 0 &&
                            Double.parseDouble(etAltura.getText().toString()) >= 0) {
                        onSimulateClick();
                    } else {
                        Toast.makeText(getActivity(), "Los datos son inválidos.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "No se han llenado todos los campos.",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void setReach(double reach) {
        tvAlcance.setText(Double.toString(reach) + "m");
    }

    public void setTime(double seconds) {
        tvTiempo.setText(Double.toString(seconds) + "s");
    }

    public void setHeight(double height) {
        tvAltura.setText(Double.toString(height) + "m");
    }

    protected void onSimulateClick() {
        double angle = Double.parseDouble(etAngulo.getText().toString());
        double speed = Double.parseDouble(etVelocidad.getText().toString());
        double height = Double.parseDouble(etAltura.getText().toString());
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Launch l = new Launch();

        l.setV0(speed);
        l.setTheta(angle);
        l.setY0(height);
        l.calculate();

        listener.onGraphDataChange(l);

        tvAlcance.setText(String.format("%1$.2f", l.getDistance()) + " m");
        if(height == 0){
            tvAltura.setText(height + " m");
        }
        else{
            tvAltura.setText("-" + height + "m");
        }
        tvTiempo.setText(String.format("%1$.2f", l.getFlightTime()) + " s");
    }

    public interface OnGraphDataChangeListener {
        void onGraphDataChange(Launch launch);
    }
}
