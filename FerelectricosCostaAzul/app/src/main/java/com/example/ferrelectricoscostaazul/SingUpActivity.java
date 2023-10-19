package com.example.ferrelectricoscostaazul;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import kotlin.Pair;

public class SingUpActivity extends AppCompatActivity {

    TextView nuevoUsuario, bienvenidoLabel, continuarLabel;
    ImageView singUpImageView;
    TextInputLayout usuarioSingUpTextField, contrasenaTextField;
    MaterialButton inicioSesion;

    TextInputLayout emailEditTex, passwordEditTex, confirmPasswordEditTex;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        singUpImageView = findViewById(R.id.singUpImageView);
        bienvenidoLabel = findViewById(R.id.bienvenidolabel);
        continuarLabel = findViewById(R.id.continuarLabel);
        usuarioSingUpTextField = findViewById(R.id.usuarioSingUpTextField);
        contrasenaTextField = findViewById(R.id.contrasenaTextField);
        inicioSesion = findViewById(R.id.inicioSesion);
        nuevoUsuario = findViewById(R.id.nuevoUsuario);

        emailEditTex = findViewById(R.id.emailEditText);
        passwordEditTex = findViewById(R.id.passwordEditText);
        confirmPasswordEditTex = findViewById(R.id.confirmPasswordEditText);

        nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transitionBack();
            }
        });

        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate();
            }
        });
        mAuth = FirebaseAuth.getInstance();


    }
    public void  validate(){

        String email = emailEditTex.getEditText().toString().trim();
        String password = passwordEditTex.getEditText().toString().trim();
        String confirmPassword = confirmPasswordEditTex.getEditText().toString().trim();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            emailEditTex.setError("Correo No Valido");
            return;
        }else{
            emailEditTex.setError(null);
        }
        
        if(password.isEmpty() || password.length() < 8){
            passwordEditTex.setError("Minimo 8 caracteres");
            return;
        } else if (!Pattern.compile("[0-9]").matcher(password).find()) {

            passwordEditTex.setError("Usa al menos un numero");
            return;
        }else{
            passwordEditTex.setError(null);
        }

        if(!confirmPassword.equals(password)){
            confirmPasswordEditTex.setError("Las contraseñas deben ser iguales");
            return;
        }else{
            registrar(email,password);
        }

    }

    public void registrar(String email, String password){

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Intent intent = new Intent(SingUpActivity.this, ComprasClienteActivity.class);
                            startActivity(intent);
                            finish();
                        }else{

                            Toast.makeText(SingUpActivity.this,"Fallo en Registrase",Toast.LENGTH_LONG);
                        }
                    }
                });

    }

    public void onBackPresesed(){
        transitionBack();
    }
    public void transitionBack(){
        Intent intent = new Intent(SingUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();


    }
}