package com.example.reciclae.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.R;
import com.example.reciclae.model.Cliente;

public class MainActivity extends AppCompatActivity {

    private TextView tituloLogin;
    private EditText emailLogin, senhaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailLogin = findViewById(R.id.emailLogin);
        senhaLogin = findViewById(R.id.senhaLogin);
    }

    public void entrarLogin(View view) {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        String email = emailLogin.getText().toString();
        String senha = senhaLogin.getText().toString();

        Cliente cliente = db.clienteDao().findByEmailAndSenha(email, senha);

        if(cliente != null) {
            Intent mainMenu = new Intent(MainActivity.this, MainMenu.class);
            mainMenu.putExtra("EMAIL", email);
            startActivity(mainMenu);
            finish();
        } else {
            Toast.makeText(this, "Erro ao efetuar login!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarLogin(View view) {
        Intent cadastrar = new Intent(MainActivity.this, Cadastrar.class);
        startActivity(cadastrar);
        Toast.makeText(this, "Efetue o cadastro!", Toast.LENGTH_SHORT).show();
    }

}
