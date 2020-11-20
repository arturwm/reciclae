package com.example.reciclae.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.R;
import com.example.reciclae.model.Cliente;
import com.example.reciclae.model.Endereco;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainMenu extends AppCompatActivity {

    private TextView tituloMainMenu, enderecoMainMenu;
    private String email;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tituloMainMenu = findViewById(R.id.tituloMainMenu);
        enderecoMainMenu = findViewById(R.id.enderecoMainMenu);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        tituloMainMenu.setText("Bem-vindo, "+user.getDisplayName());

    }

    public void vender(View view) {
        Intent venderActivity = new Intent(MainMenu.this, Vender.class);
        startActivity(venderActivity);
        finish();
    }

    public void comprar(View view) {
        Intent comprarActivity = new Intent(MainMenu.this, Comprar.class);
        startActivity(comprarActivity);
        finish();
    }

    public void perfil(View view) {
        Intent comprarActivity = new Intent(MainMenu.this, Perfil.class);
        startActivity(comprarActivity);
        finish();
    }

    public void sair(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sair");
        builder.setMessage("Deseja mesmo sair?");
        builder.setNegativeButton("NÃO", null);
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAuth.signOut();
                Intent sairMenu = new Intent(MainMenu.this, MainActivity.class);
                startActivity(sairMenu);
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
