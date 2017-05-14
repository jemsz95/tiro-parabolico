package mx.itesm.tiroparabolico;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
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
        implements View.OnClickListener, TextWatcher {

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

    private ValueEventListener classEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            classExists = dataSnapshot.exists();
            classChecked = true;

            // If the progress dialog is showing the user was trying to register
            if(progressDialog.isShowing()) {
                // Hide the progress dialog and retry
                progressDialog.hide();
                userLogin();
            }
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
        etCodigo.addTextChangedListener(this);
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
        String classCode = etCodigo.getText().toString().trim();

        etName.setError(null);
        etSecondName.setError(null);
        etMail.setError(null);
        etPassword.setError(null);
        etCodigo.setError(null);

        if (TextUtils.isEmpty(firstName)) {
            etName.setError("Ingrese su nombre");
            etName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(secondName)) {
            etSecondName.setError("Ingrese su apellido");
            etSecondName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etMail.setError("Ingrese su correo electrónico");
            etMail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Ingrese su contraseña");
            etPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            etPassword.setError("La contraseña debe de ser mínimo de 6 caracteres");
            etPassword.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(classCode)) {
            etCodigo.setError("Ingrese un código de clase");
            etCodigo.requestFocus();
            return;
        }

        if (classChecked) {
            int checkedRadioBtn = radioGroup.getCheckedRadioButtonId();

            if (checkedRadioBtn == R.id.radioBtn_maestro) {
                if (classExists) {
                    etCodigo.setError("Este salón ya existe, elige otro código");
                    return;
                } else {
                    etCodigo.setError(null);
                }
            } else if (checkedRadioBtn == R.id.radioBtn_alumno) {
                if (!classExists) {
                    etCodigo.setError("Este salón no existe");
                    return;
                } else {
                    etCodigo.setError(null);
                }
            }
        } else {
            progressDialog.setMessage("Revisando clases disponibles...");
            progressDialog.show();
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            userLogin();
                            Toast.makeText(RegisterActivity.this, "El usuario fue registrado correctamente.", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.hide();

                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                etMail.setError("Correo incorrecto");
                                etMail.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e) {
                                etMail.setError("Usuario ya registrado");
                                etMail.requestFocus();
                            } catch (Exception e) {
                                Log.d(DEBUG_TAG, e.toString());
                            }
                        }
                    }
                });

        progressDialog.setMessage("Registrando Usuario...");
        progressDialog.show();
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

                            Intent i = new Intent(RegisterActivity.this, SimulatorActivity.class);
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
                break;
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        classChecked = false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Do nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Check new class code
        if(s != null) {
            classRef = databaseReference.child("classes/" + s);
            classRef.addListenerForSingleValueEvent(classEventListener);
        }
    }
}
