package com.example.reciclae.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reciclae.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetarSenha extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetar_senha);
        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.emailReset);

    }

    public void voltarSenha(View view) {
        Intent mainActivity = new Intent(ResetarSenha.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    public void ResetSenha(View view) {
        final String email = emailText.getText().toString();

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetarSenha.this, "Email Enviado", Toast.LENGTH_SHORT).show();
                                                }
                    }
                });

    }
}


