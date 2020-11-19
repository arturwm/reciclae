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
import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.model.Cliente;
import com.example.reciclae.model.Produto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Vender extends AppCompatActivity {

    private EditText nomeProduto, qtdProduto, valorProduto;
    private String nome, qtd;
    private double valor;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender);

        mAuth = FirebaseAuth.getInstance();

        nomeProduto = findViewById(R.id.nomeProduto);
        qtdProduto = findViewById(R.id.qtdProduto);
        valorProduto = findViewById(R.id.valorProduto);

    }

    @Override
    protected void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    public void cadastrarVenda(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        nome = nomeProduto.getText().toString();
        qtd = qtdProduto.getText().toString();
        valor = Double.parseDouble(valorProduto.getText().toString());

        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("qtd", qtd);
        dados.put("valor", valor);
        dados.put("id_vendedor", user.getUid());
        dados.put("display_vendedor", user.getDisplayName());

        db.collection("produto").document(user.getUid())
                .set(dados)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Vender.this, "SUCESSO", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Vender.this, "FALHA AO CADASTRAR", Toast.LENGTH_SHORT).show();
                    }
                });
        updateUI(user);
    }

    private void updateUI(FirebaseUser user) {
        Intent mainMenu = new Intent(Vender.this, MainMenu.class);
        startActivity(mainMenu);
        Toast.makeText(this, "Venda cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void voltar(View view) {
        Intent mainMenu = new Intent(Vender.this, MainMenu.class);
        startActivity(mainMenu);
        finish();
    }
}
