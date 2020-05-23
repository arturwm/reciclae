package com.example.reciclae;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    }

    public void comprar(View view) {
    }
}
