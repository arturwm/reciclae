package com.example.reciclae.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.R;
import com.example.reciclae.model.Cliente;

public class Cadastrar extends AppCompatActivity {

    private EditText nomeCompleto, usuario, documento, email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        nomeCompleto = findViewById(R.id.nomeCompletoCadastrar);
        usuario = findViewById(R.id.usuarioCadastrar);
        documento = findViewById(R.id.documentoCadastrar);
        email = findViewById(R.id.emailCadastrar);
        senha = findViewById(R.id.senhaCadastrar);

    }

    public void confirmarCadastro(View view) {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        String name = nomeCompleto.getText().toString();
        String user = usuario.getText().toString();
        String doc = documento.getText().toString();
        String mail = email.getText().toString();
        String password = senha.getText().toString();

        Cliente cliente = db.clienteDao().findByEmail(mail);
        if(cliente != null){
            Toast.makeText(this, "Cliente já cadastrado!", Toast.LENGTH_SHORT).show();
        } else {
            if(name.matches("") || user.matches("") || doc.matches("") || mail.matches("") || password.matches("")){
                Toast.makeText(this, "Dados incompletos!", Toast.LENGTH_SHORT).show();
            } else {
                Intent segundoCadastrar = new Intent(Cadastrar.this, SegundoCadastrar.class);
                segundoCadastrar.putExtra("NOME", name);
                segundoCadastrar.putExtra("USER", user);
                segundoCadastrar.putExtra("DOC", doc);
                segundoCadastrar.putExtra("EMAIL", mail);
                segundoCadastrar.putExtra("SENHA", password);
                startActivity(segundoCadastrar);
                finish();
            }
        }
    }

    public void voltar(View view) {
        Intent mainActivity = new Intent(Cadastrar.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
