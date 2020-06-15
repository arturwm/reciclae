package com.example.reciclae.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.R;
import com.example.reciclae.model.Cliente;
import com.example.reciclae.model.Endereco;

public class MainMenu extends AppCompatActivity {

    private TextView tituloMainMenu, enderecoMainMenu;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tituloMainMenu = findViewById(R.id.tituloMainMenu);
        enderecoMainMenu = findViewById(R.id.enderecoMainMenu);

        email = getIntent().getStringExtra("EMAIL");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Cliente cliente = AppDatabase.getInstance(getApplicationContext()).clienteDao().findByEmail(email);

        //int idc = cliente.idc;

        //Endereco endereco = AppDatabase.getInstance(getApplicationContext()).clienteDao().findByIdc(idc);

        String txtWelcome = "\nBem-Vindo "+cliente.nome+"!\n\n";
        //String txtEndereco = "\nSeu endereço é: "+endereco.rua+", "+endereco.numero+"!\n\n";

        tituloMainMenu.setText(txtWelcome);
        //enderecoMainMenu.setText(txtEndereco);
    }

    public void vender(View view) {
        Intent venderActivity = new Intent(MainMenu.this, Vender.class);
        venderActivity.putExtra("EMAIL", email);
        startActivity(venderActivity);
        finish();
    }

    public void comprar(View view) {
        Intent comprarActivity = new Intent(MainMenu.this, Comprar.class);
        comprarActivity.putExtra("EMAIL", email);
        startActivity(comprarActivity);
        finish();
    }
}
