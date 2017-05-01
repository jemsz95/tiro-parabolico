package mx.itesm.tiroparabolico;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jorgeemiliorubiobarboza on 23/04/17.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvName;
    private TextView tvSecondName;
    private TextView tvPasswordRegister;
    private EditText etName;
    private EditText etSecondName;
    private EditText etPassword;
    private EditText etCodigo;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);


        tvTitle = (TextView) findViewById(R.id.text_title_instructions);
        tvName = (TextView) findViewById(R.id.text_NameRegister);
        tvSecondName = (TextView) findViewById(R.id.text_SecondNameRegister);
        tvPasswordRegister = (TextView) findViewById(R.id.text_Password);

        etName = (EditText) findViewById(R.id.edit_FirstName);
        etSecondName =(EditText) findViewById(R.id.edit_LastName);
        etPassword = (EditText) findViewById(R.id.edit_PasswordRegister);
        etCodigo = (EditText) findViewById(R.id.edit_Class);
        btnRegister = (Button) findViewById(R.id.button_RegisterReg);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_RegisterReg:
                finish();
                break;

        }
    }



}
