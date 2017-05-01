package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    Button btnRegistro;
    EditText etUsuario;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.button_login);
        btnRegistro=(Button)findViewById(R.id.button_registro);
        etUsuario = (EditText) findViewById(R.id.editText_password);
        etPassword = (EditText) findViewById(R.id.editText_mail);
        etUsuario.setText("");
        etPassword.setText("");
        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_registro:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;

            case R.id.button_login: {
                if(etUsuario.getText().toString().trim().length() > 0 && etPassword.getText().toString().trim().length() > 0)
                {
                    Intent intent = new Intent(this, InstruccionesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Ingrese TODOS los datos", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}






