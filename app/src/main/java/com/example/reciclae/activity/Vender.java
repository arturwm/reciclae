package com.example.reciclae.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reciclae.R;

public class Vender extends AppCompatActivity {

    private TextView tituloVender;
    private EditText nomeProduto, qtdProduto, valorProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender);

        tituloVender = findViewById(R.id.tituloVender);
        nomeProduto = findViewById(R.id.nomeProduto);
        qtdProduto = findViewById(R.id.qtdProduto);
        valorProduto = findViewById(R.id.valorProduto);
    }

    public void cadastrarVenda(View view) {
        Toast.makeText(this, "Venda cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
        Intent mainMenu = new Intent(Vender.this, MainMenu.class);
        startActivity(mainMenu);
        finish();
    }
}
