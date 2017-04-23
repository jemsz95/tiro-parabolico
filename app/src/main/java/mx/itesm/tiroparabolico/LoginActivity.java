package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static mx.itesm.tiroparabolico.R.styleable.View;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    Button btnLogin2;
    ImageView btnInfo;
    EditText etUsuario;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.button_login);
        btnInfo = (ImageView) findViewById(R.id.button_info);
        btnLogin2=(Button)findViewById(R.id.button_login2);
        etUsuario = (EditText) findViewById(R.id.editText_mail);
        etPassword = (EditText) findViewById(R.id.editText_password);
        etUsuario.setText("");
        etPassword.setText("");
        btnLogin.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        btnLogin2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                if(etUsuario.getText().toString().trim().length() > 0 && etPassword.getText().toString().trim().length() > 0)
                {
                  Intent i = new Intent(this, InstruccionesActivity.class);
                  i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                  startActivity(i);
                }
                else
                {
                    Toast.makeText(this, "Ingrese TODOS los datos", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button_info: {
                //TODO: Show app info
                break;
            }

            case R.id.button_login2: {
                if(etUsuario.getText().toString().trim().length() > 0 && etPassword.getText().toString().trim().length() > 0)
                {
                    Intent i = new Intent(this, InstruccionesActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(i);
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






