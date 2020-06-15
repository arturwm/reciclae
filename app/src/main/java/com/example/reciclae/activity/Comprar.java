package com.example.reciclae.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.reciclae.R;
import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.model.Produto;

import java.util.List;

public class Comprar extends AppCompatActivity {

    private String email;
    private ListView lvComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        email = getIntent().getStringExtra("EMAIL");
        lvComprar = findViewById(R.id.lvComprar);

        List<Produto> produtos = AppDatabase.getInstance(getApplicationContext()).produtoDao().getAll();

        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, produtos);

        lvComprar.setAdapter(adapter);
    }

    public void voltar(View view) {
        Intent mainMenu = new Intent(Comprar.this, MainMenu.class);
        mainMenu.putExtra("EMAIL", email);
        startActivity(mainMenu);
        finish();
    }
}
