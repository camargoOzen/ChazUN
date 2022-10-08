package com.example.chazun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;
import java.util.Objects;


public class SignUpActivity extends AppCompatActivity {
    //Varibles
    private EditText emailText, passwordText, rePasswordText;
    private CheckBox storeBox, termBox;
    private ImageButton backBtn;
    private Button signUpBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Se inicializan las variables buscando cada elemento por el id
        this.emailText = findViewById(R.id.emailSignUp);
        this.passwordText = findViewById(R.id.passwordSignUp);
        this.rePasswordText = findViewById(R.id.rePasswordSignUp);
        this.storeBox = findViewById(R.id.storeCheckBox);
        this.termBox = findViewById(R.id.termCheckBox);
        this.backBtn = findViewById(R.id.backUpBtn);
        this.signUpBtn = findViewById(R.id.signUpBtn);
        this.mAuth = FirebaseAuth.getInstance();
        try {
            this.signUpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Se toman las cadenas de caracteres que estan en los editText
                    String email = emailText.getText().toString().trim().toLowerCase(Locale.ROOT);
                    String password = passwordText.getText().toString().trim();
                    String rePassword = rePasswordText.getText().toString().trim();
                    //Se comprueba que los campos obligatorios contengan información
                    if (email.isEmpty() || password.isEmpty() || rePassword.isEmpty() || !termBox.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!password.equals(rePassword)) {
                        rePasswordText.setError("Las contraseñas no coinciden");
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent back = new Intent(getApplicationContext(),SignInActivity.class);
                                startActivity(back);
                                finish();
                                Toast.makeText(getApplicationContext(),"¡Registro exitoso!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(getApplicationContext(),"Error registro",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }
        this.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(back);
                finish();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) mAuth.signOut();
    }
}