package com.example.reciclae.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.reciclae.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Perfil extends AppCompatActivity {

    private EditText nome, telefone, documento, email, senha, cep, rua, numero, complemento, bairro, cidade;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private CollectionReference cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nome = findViewById(R.id.nomePerfil);
        telefone = findViewById(R.id.telefonePerfil);
        documento = findViewById(R.id.documentoPerfil);
        email = findViewById(R.id.emailPerfil);
        senha = findViewById(R.id.senhaPerfil);
        cep = findViewById(R.id.cepPerfil);
        rua = findViewById(R.id.ruaPerfil);
        numero = findViewById(R.id.numeroPerfil);
        complemento = findViewById(R.id.complementoPerfil);
        bairro = findViewById(R.id.bairroPerfil);
        cidade = findViewById(R.id.cidadePerfil);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        nome.setText(user.getDisplayName());
        telefone.setText(user.getPhoneNumber());
        documento.setText("");
        email.setText(user.getEmail());
        senha.setText("");
        cep.setText("");
        rua.setText("");
        numero.setText("");
        complemento.setText("");
        bairro.setText("");
        cidade.setText("");

    }

    public void voltar(View view) {
        Intent mainMenu = new Intent(Perfil.this, MainMenu.class);
        startActivity(mainMenu);
        finish();
    }

    public void alterar(View view) {
    }
}