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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private Button btnRegistro;
    private EditText etUsuario;
    private EditText etPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        btnLogin = (Button) findViewById(R.id.button_login);
        btnRegistro=(Button)findViewById(R.id.button_registro);
        etUsuario = (EditText) findViewById(R.id.editText_mail);
        etPassword = (EditText) findViewById(R.id.editText_password);
        etUsuario.setText("");
        etPassword.setText("");
        progressDialog=new ProgressDialog(this);
        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }

    private void userLogin(){
        String email = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Porfavor ingresa tu correo.",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Porfavor ingresa tu contraseña.",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Iniciar sesión....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        //if the task is successfull
                        if(task.isSuccessful()){
                            Intent i = new Intent(LoginActivity.this, SimulatorActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }
                        else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(LoginActivity.this,
                                        "El usuario y/o contraseña son incorrectos",
                                        Toast.LENGTH_SHORT);
                            } catch (FirebaseAuthInvalidUserException e) {
                                Toast.makeText(LoginActivity.this,
                                        "El usuario y/o contraseña son incorrectos",
                                        Toast.LENGTH_SHORT);
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this,
                                        "No se pudo conectar con el servidor. Por favor revise su" +
                                                " conexión a internet y vuelva a intentar.",
                                        Toast.LENGTH_SHORT);
                            }
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






