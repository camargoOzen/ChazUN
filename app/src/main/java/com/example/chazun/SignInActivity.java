package com.example.chazun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class SignInActivity extends AppCompatActivity {
    private EditText emailText, passwordText;
    private Button signInBtn;
    private CheckedTextView forgotPassword, signUp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.emailText = findViewById(R.id.emailSignIn);
        this.passwordText = findViewById(R.id.passwordSignIn);
        this.signInBtn = findViewById(R.id.signInBtn);
        this.forgotPassword = findViewById(R.id.forgotPassBtn);
        this.signUp = findViewById(R.id.signUpActivityBtn);
        this.mAuth = FirebaseAuth.getInstance();
        try {
            this.signInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = emailText.getText().toString().trim().toLowerCase(Locale.ROOT);
                    String password = passwordText.getText().toString().trim();
                    if (email.isEmpty() && password.isEmpty()) {
                        Toast.makeText(getApplicationContext(),"Rellene todos los campos",Toast.LENGTH_SHORT).show();
                    } else {
                        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(),MainNavigationDrawerActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña incorrecto",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
        this.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            mAuth.signOut();
        }
    }
}