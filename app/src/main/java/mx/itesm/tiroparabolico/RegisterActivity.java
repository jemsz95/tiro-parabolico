package mx.itesm.tiroparabolico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

<<<<<<< HEAD
        tvTitle = (TextView) findViewById(R.id.text_RegisterTitle);
=======
        tvTitle = (TextView) findViewById(R.id.text_title_instructions);
>>>>>>> 13046c9c753f9f9ee6ecb552d1ce5250688b6cd3
        tvName = (TextView) findViewById(R.id.text_NameRegister);
        tvSecondName = (TextView) findViewById(R.id.text_SecondNameRegister);
        tvPasswordRegister = (TextView) findViewById(R.id.text_PasswordRegist);
        etName = (EditText) findViewById(R.id.edit_FirstName);
        etSecondName =(EditText) findViewById(R.id.edit_LastName);
        etPassword = (EditText) findViewById(R.id.edit_PasswordRegister);
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
