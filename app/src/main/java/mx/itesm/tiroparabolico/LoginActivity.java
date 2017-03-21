package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static mx.itesm.tiroparabolico.R.styleable.View;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.button_login);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
                break;
        }
    }
}






