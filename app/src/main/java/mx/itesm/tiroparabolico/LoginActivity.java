package mx.itesm.tiroparabolico;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    Button btnRegistro;
    EditText etUsuario;
    EditText etPassword;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        //si el objeto getcurrentuser no es null
        //significa que el usuario ya esta loggeado

        if(firebaseAuth.getCurrentUser()!=null){
            //cierra la actividad
            finish();
            //abre instricciones activity
            startActivity(new Intent(getApplicationContext(),InstruccionesActivity.class));
        }

        btnLogin = (Button) findViewById(R.id.button_login);
        btnRegistro=(Button)findViewById(R.id.button_registro);
        etUsuario = (EditText) findViewById(R.id.editText_password);
        etPassword = (EditText) findViewById(R.id.editText_mail);
        etUsuario.setText("");
        etPassword.setText("");
        progressDialog=new ProgressDialog(this);
        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }

    private void userLogin(){
        String email=etUsuario.getText().toString().trim();
        String password=etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Porfavor ingresa tu email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Porfavor ingresa tu password",Toast.LENGTH_LONG).show();
            return;

    } progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            Intent intent = new Intent(getApplicationContext(), InstruccionesActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), ":C", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_registro:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;

            case R.id.button_login: {
              userLogin();
                break;
            }
        }
    }
}






