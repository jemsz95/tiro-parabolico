package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static mx.itesm.tiroparabolico.R.styleable.View;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    Button btnLogin2;
    ImageView btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.button_login);
        btnInfo = (ImageView) findViewById(R.id.button_info);
        btnLogin2=(Button)findViewById(R.id.button_login2);

        btnLogin.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        btnLogin2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login: {
                Intent i = new Intent(this, InstruccionesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                break;
            }

            case R.id.button_info: {
                //TODO: Show app info
                break;
            }

            case R.id.button_login2: {
                Intent i = new Intent(this, InstruccionesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                break;
            }
        }
    }
}






