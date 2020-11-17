package com.example.reciclae.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.R;
import com.example.reciclae.model.Cliente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Cadastrar extends AppCompatActivity {

    private EditText nomeCompleto, usuario, documento, email, senha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        //nomeCompleto = findViewById(R.id.nomeCompletoCadastrar);
        //usuario = findViewById(R.id.usuarioCadastrar);
        //documento = findViewById(R.id.documentoCadastrar);
        email = findViewById(R.id.emailCadastrar);
        senha = findViewById(R.id.senhaCadastrar);

        mAuth = FirebaseAuth.getInstance();
    }

    public void confirmarCadastro(View view) {
        //String name = nomeCompleto.getText().toString();
        //String user = usuario.getText().toString();
        //String doc = documento.getText().toString();
        String mail = email.getText().toString();
        String password = senha.getText().toString();

        mAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }else {
                            Toast.makeText(Cadastrar.this, "FALHA AO CADASTRAR", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            Intent proximo = new Intent(Cadastrar.this,MainMenu.class);
            startActivity(proximo);
            finish();
    }

}

    public void voltar(View view) {
        Intent mainActivity = new Intent(Cadastrar.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
