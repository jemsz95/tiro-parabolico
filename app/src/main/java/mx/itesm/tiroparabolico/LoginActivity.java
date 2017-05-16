package mx.itesm.tiroparabolico;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

/**
 * Autor: Racket
 * Creación: 20 de Marzo 2017
 * Última modificación: 14 de Mayo 2017
 * Descipción: Captura datos de inicio de sesión y autentifica con el servidor
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String DEBUG_TAG = "LoginActivity";

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
            etUsuario.setError(getResources().getString(R.string.email_empty_error));
            etUsuario.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)){
            etPassword.setError(getResources().getString(R.string.pass_empty_error));
            etPassword.requestFocus();
            return;
        }

        progressDialog.setMessage(getResources().getString(R.string.logging_in));
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
                                        getResources().getString(R.string.email_password_incorrect),
                                        Toast.LENGTH_SHORT)
                                        .show();
                            } catch (FirebaseAuthInvalidUserException e) {
                                Toast.makeText(LoginActivity.this,
                                        getResources().getString(R.string.email_password_incorrect),
                                        Toast.LENGTH_SHORT)
                                        .show();
                            } catch (FirebaseNetworkException e) {
                                Toast.makeText(LoginActivity.this,
                                        getResources().getString(R.string.connection_error),
                                        Toast.LENGTH_SHORT)
                                        .show();
                            } catch (Exception e) {
                                Log.d(DEBUG_TAG, e.toString());
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






