package com.example.reciclae.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reciclae.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView tituloLogin;
    private EditText emailLogin, senhaLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailLogin = findViewById(R.id.emailLogin);
        senhaLogin = findViewById(R.id.senhaLogin);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent dashboard = new Intent(MainActivity.this, MainMenu.class);
            startActivity(dashboard);
            finish();
        }
    }

    public void entrarLogin(View view) {
        String login = emailLogin.getText().toString();
        String senha = senhaLogin.getText().toString();

        if (login.matches("") || senha.matches("")) {
            Toast.makeText(this, "Preencher usuario e senha!", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(login, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Toast.makeText(MainActivity.this, "Falha ao autenticar", Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }
    }

    public void cadastrarLogin(View view) {
        Intent cadastrar = new Intent(MainActivity.this, Cadastrar.class);
        startActivity(cadastrar);
        Toast.makeText(this, "Efetue o cadastro!", Toast.LENGTH_SHORT).show();
    }

}
