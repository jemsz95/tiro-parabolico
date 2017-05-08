package mx.itesm.tiroparabolico;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by jorgeemiliorubiobarboza on 23/04/17.
 */

public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnFocusChangeListener, RadioGroup.OnCheckedChangeListener {

    private static final String DEBUG_TAG = "RegisterActivity";

    private EditText etName;
    private EditText etSecondName;
    private EditText etPassword;
    private RadioButton radioBtnAlumno;
    private RadioButton radioBtnMaestro;
    private RadioGroup radioGroup;
    private EditText etMail;
    private EditText etCodigo;
    private Button btnRegister;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DatabaseReference classRef;
    private boolean classExists = false;
    private boolean classChecked = false;
    private boolean classValid = false;

    private ValueEventListener classEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            classExists = dataSnapshot.exists();
            classChecked = true;

            validateClassCode();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d(DEBUG_TAG, databaseError.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Inicia Fierbase auth
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = Database.getInstance().getReference();

        // Bind UI elements
        radioBtnAlumno = (RadioButton) findViewById(R.id.radioBtn_alumno);
        radioBtnMaestro = (RadioButton) findViewById(R.id.radioBtn_maestro);
        etName = (EditText) findViewById(R.id.edit_FirstName);
        etSecondName = (EditText) findViewById(R.id.edit_LastName);
        etMail = (EditText) findViewById(R.id.edit_EMail);
        etPassword = (EditText) findViewById(R.id.edit_PasswordRegister);
        etCodigo = (EditText) findViewById(R.id.edit_Class);
        btnRegister = (Button) findViewById(R.id.button_RegisterReg);
        radioGroup = (RadioGroup) findViewById(R.id.radGrupo);

        // Set listeners
        progressDialog = new ProgressDialog(this);
        btnRegister.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        etCodigo.setOnFocusChangeListener(this);
    }

    private void saveUserInformation() {
        String email = etMail.getText().toString().trim();
        String firstName = etName.getText().toString().trim();
        String secondName = etSecondName.getText().toString().trim();
        String clase = etCodigo.getText().toString().trim();

        UserInformation userInformation = new UserInformation(firstName, secondName, clase, email);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Clase klass = new Clase(user.getUid(), clase);

        if (radioBtnAlumno.isChecked()) {
            databaseReference.child("students").child(user.getUid()).setValue(userInformation);
            databaseReference.child("class_member/" + clase + "/" + user.getUid()).setValue(new Boolean(true));
        } else if (radioBtnMaestro.isChecked()) {
            databaseReference.child("teachers").child(user.getUid()).setValue(userInformation);
            databaseReference.child("classes").child(userInformation.classId).setValue(klass);
        }

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userInformation.name + " " + userInformation.lastname)
                .build();

        user.updateProfile(profileUpdates);

        Toast.makeText(this,user.getDisplayName(),Toast.LENGTH_SHORT).show();
    }

    private void registerUser() {
        String email = etMail.getText().toString().trim();
        String firstName = etName.getText().toString().trim();
        String secondName = etSecondName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(secondName)) {
            Toast.makeText(this, "Ingrese su apellido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Ingrese su correo electronico ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Ingrese su contraseña ", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registrando Usuario....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            userLogin();
                            Toast.makeText(RegisterActivity.this, "Usted fue registrado correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            //display some message here
                            Toast.makeText(RegisterActivity.this, "No se pudo registrar intente denuevo porfavor", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void userLogin() {
        String email = etMail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if (task.isSuccessful()) {
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
                validateClassCode();
                if (classValid) {
                    registerUser();
                } else {
                    Toast.makeText(this, "Error de codigo de clase", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private void validateClassCode() {
        if (classChecked) {
            int checkedRadioBtn = radioGroup.getCheckedRadioButtonId();

            if (checkedRadioBtn == R.id.radioBtn_maestro) {
                if (classExists) {
                    etCodigo.setError("Este salón ya existe, elige otro código");
                    classValid = false;
                } else {
                    etCodigo.setError(null);
                    classValid = true;
                }
            } else if (checkedRadioBtn == R.id.radioBtn_alumno) {
                if (!classExists) {
                    etCodigo.setError("Este salón no existe");
                    classValid = false;
                } else {
                    etCodigo.setError(null);
                    classValid = true;
                }
            }
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            String classCode = etCodigo.getText().toString();
            classRef = databaseReference.child("classes/" + classCode);
            classRef.addListenerForSingleValueEvent(classEventListener);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        validateClassCode();
    }
}
