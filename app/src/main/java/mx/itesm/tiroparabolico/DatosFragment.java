package mx.itesm.tiroparabolico;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DatosFragment extends Fragment implements View.OnClickListener{
    private static final String DEBUG_TAG = "TAG_FRAGMENT_DATOS";

    private TextView tvAltura;
    private TextView tvAlcance;
    private TextView tvTiempo;
    private EditText etVelocidad;
    private EditText etAngulo;
    private Button btnSimular;
    private ImageButton btnInfo;

    private OnGraphDataChangeListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(DEBUG_TAG, "onCreate() has been called");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set OnGraphDataChangeListener using parent activity
        if(getActivity() instanceof OnGraphDataChangeListener) {
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
        btnSimular = (Button) view.findViewById(R.id.button_Simular);
        btnInfo = (ImageButton) view.findViewById(R.id.button_info);

        btnSimular.setOnClickListener(this);
        btnInfo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
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
        switch(v.getId()) {
            case R.id.button_info:
                // TODO: Replace with instructions
                Toast.makeText(getContext(), "Info button press", Toast.LENGTH_SHORT)
                        .show();
                break;

            case R.id.button_Simular :
                if(etAngulo.getText().toString().trim().length() > 0 && etVelocidad.getText().toString().trim().length() > 0)
                {
                    if(Double.parseDouble(etAngulo.getText().toString()) >= -90 &&
                            Double.parseDouble(etAngulo.getText().toString()) <= 90 &&
                            Double.parseDouble(etVelocidad.getText().toString()) >= 0) {
                        onSimulateClick();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Los datos son invalidos",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "No se han llenado los datos completos",
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

        Launch l = new Launch();

        l.setV0(speed);
        l.setTheta(angle);

        listener.onGraphDataChange(l);
    }

    public interface OnGraphDataChangeListener {
        void onGraphDataChange(Launch launch);
    }
}
