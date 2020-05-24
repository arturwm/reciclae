package com.example.reciclae.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.R;
import com.example.reciclae.model.Cliente;

public class MainMenu extends AppCompatActivity {

    private TextView tituloMainMenu;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tituloMainMenu = findViewById(R.id.tituloMainMenu);

        email = getIntent().getStringExtra("EMAIL");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Cliente cliente = AppDatabase.getInstance(getApplicationContext()).clienteDao().findByEmail(email);

        String welcome = "\nBem-Vindo "+cliente.nome+"!\n\n";

        tituloMainMenu.setText(welcome);
    }

    public void vender(View view) {
        Intent venderActivity = new Intent(MainMenu.this, Vender.class);
        venderActivity.putExtra("EMAIL", email);
        startActivity(venderActivity);
        finish();
    }

    public void comprar(View view) {
    }
}
