package mx.itesm.tiroparabolico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DatosFragment extends Fragment implements View.OnClickListener{
    private static final String DEBUG_TAG = "TAG_FRAGMENT_DATOS";

    private TextView tvAltura;
    private TextView tvAlcance;
    private TextView tvTiempo;
    private TextView tvVeloIni;
    private TextView tvAngulo;
    private EditText etAltura;
    private EditText etAlcance;
    private EditText etTiempo;
    private EditText etVeloIni;
    private EditText etAngulo;
    private Button btnSimular;
    private ImageView btnInfo;

    private OnGraphDataChangeListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(DEBUG_TAG, "onCreate() has been called");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_datos, container, false);

        tvAltura = (TextView) view.findViewById(R.id.text_Altura);
        tvAlcance = (TextView) view.findViewById(R.id.text_Alcance);
        tvTiempo = (TextView) view.findViewById(R.id.text_Tiempo);
        tvVeloIni = (TextView) view.findViewById(R.id.text_Velocidad);
        tvAngulo = (TextView) view.findViewById(R.id.text_Angulo);
        etAltura = (EditText) view.findViewById(R.id.edit_Altura);
        etAlcance = (EditText) view.findViewById(R.id.edit_Alcance);
        etTiempo = (EditText) view.findViewById(R.id.edit_Tiempo);
        etVeloIni = (EditText) view.findViewById(R.id.edit_Velocidad);
        etAngulo = (EditText) view.findViewById(R.id.edit_Angulo);
        btnSimular = (Button) view.findViewById(R.id.button_Simular);
        btnInfo = (ImageView) view.findViewById(R.id.button_info);

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

    public void onClick(View v)
    {

    }

    public void setOnGraphDataChangeListener(OnGraphDataChangeListener listener) {
        this.listener = listener;
    }

    public interface OnGraphDataChangeListener {
        void onAngleChange(double theta);
        void onSpeedChange(double speed);
        void onHeightChange(double height);
    }
}
