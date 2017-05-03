package mx.itesm.tiroparabolico;


import android.app.ProgressDialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.ProgressBar;


import android.widget.TextView;
import android.widget.Toast;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    private EditText etMail;

    private EditText etCodigo;

    private Button btnRegister;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Inicia Fierbase auth
        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference = Database.getInstance().getReference();


        tvTitle = (TextView) findViewById(R.id.text_title_instructions);
        tvName = (TextView) findViewById(R.id.text_NameRegister);
        tvSecondName = (TextView) findViewById(R.id.text_SecondNameRegister);
        tvPasswordRegister = (TextView) findViewById(R.id.text_Password);
        etName = (EditText) findViewById(R.id.edit_FirstName);
        etSecondName =(EditText) findViewById(R.id.edit_LastName);
        etMail=(EditText) findViewById(R.id.edit_EMail);
        etPassword = (EditText) findViewById(R.id.edit_PasswordRegister);
        etCodigo = (EditText) findViewById(R.id.edit_Class);
        btnRegister = (Button) findViewById(R.id.button_RegisterReg);
        progressDialog=new ProgressDialog(this);
        btnRegister.setOnClickListener(this);
    }



    private void saveUserInformation(){
        String email=etMail.getText().toString().trim();
        String firstName=etName.getText().toString().trim();
        String secondName=etSecondName.getText().toString().trim();
        String clase =etCodigo.getText().toString().trim();

        UserInformation userInformation= new UserInformation(firstName,secondName,clase,email);


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        databaseReference.child("students").child(user.getUid()).setValue(userInformation);


        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userInformation.name)
                .build();

        user.updateProfile(profileUpdates);
    }


    private void registerUser(){
        String email=etMail.getText().toString().trim();
        String firstName=etName.getText().toString().trim();
        String secondName=etSecondName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(firstName)){
            Toast.makeText(this,"Ingrese su nombre",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(secondName)){
            Toast.makeText(this,"Ingrese su apellido",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Ingrese su correo electronico ",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Ingrese su contraseña ",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registrando Usuario....");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here

                            Toast.makeText(RegisterActivity.this, "Usted fue registrado correctamente",Toast.LENGTH_SHORT).show();

                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this, "No se pudo registrar intente denuevo porfavor",Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

    private void userLogin(){
        String email=etMail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();



        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            saveUserInformation();

                            Intent i = new Intent(RegisterActivity.this, InstruccionesActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_RegisterReg:
                registerUser();
                userLogin();
                break;

        }
    }





}
