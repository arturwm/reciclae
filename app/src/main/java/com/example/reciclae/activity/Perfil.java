package com.example.reciclae.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reciclae.R;
import com.example.reciclae.model.Cliente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Perfil extends AppCompatActivity {

    private EditText nome, telefone, documento, email, senha, cep, rua, numero, complemento, bairro, cidade;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;


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
        db = FirebaseFirestore.getInstance();
        carregarUsuario();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void carregarUsuario() {
        FirebaseUser user = mAuth.getCurrentUser();

        DocumentReference cliente = db.collection("cliente").document(user.getUid());

        cliente.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();
                            if (doc.exists()) {
                                Cliente pessoa = doc.toObject(Cliente.class);
                                telefone.setText(pessoa.getTelefone());
                                documento.setText(pessoa.getDocumento());
                                nome.setText(pessoa.getNome());
                                senha.setText("********");
                                rua.setText(pessoa.getRua());
                                numero.setText(pessoa.getNumero());
                                cep.setText(pessoa.getCep());
                                complemento.setText(pessoa.getComplemento());
                                bairro.setText(pessoa.getBairro());
                                cidade.setText(pessoa.getCidade());
                            }
                        }
                    }
                });
        email.setText(user.getEmail());
        email.setEnabled(false);
        senha.setEnabled(false);

    }

    public void voltar(View view) {
        Intent mainMenu = new Intent(Perfil.this, MainMenu.class);
        startActivity(mainMenu);
        finish();
    }

    public void alterar(View view) {

        final String nomeString = nome.getText().toString();
        final String telefoneString = telefone.getText().toString();
        final String documentoString = documento.getText().toString();
        final String complementoString = complemento.getText().toString();
        final String cepString = cep.getText().toString();
        final String ruaString = rua.getText().toString();
        final String numeroString = numero.getText().toString();
        final String bairroString = bairro.getText().toString();
        final String cidadeString = cidade.getText().toString();


        if (cepString.matches("") || ruaString.matches("") || numeroString.matches("") || bairroString.matches("") || cidadeString.matches("")) {
            Toast.makeText(this, "Dados incompletos!", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseUser user = mAuth.getCurrentUser();

            Map<String, Object> dados = new HashMap<>();
            dados.put("nome", nomeString);
            dados.put("telefone", telefoneString);
            dados.put("documento", documentoString);
            dados.put("complemento", complementoString);
            dados.put("cep", cepString);
            dados.put("rua", ruaString);
            dados.put("numero", numeroString);
            dados.put("bairro", bairroString);
            dados.put("cidade", cidadeString);


            db.collection("cliente").document(user.getUid())
                    .set(dados)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Perfil.this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Perfil.this, "FALHA AO ATUALZIAR", Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }

}

