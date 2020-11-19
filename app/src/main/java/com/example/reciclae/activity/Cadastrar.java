    package com.example.reciclae.activity;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.Toast;

    import com.example.reciclae.R;

    public class Cadastrar extends AppCompatActivity {

        private EditText nomeCompleto, telefone, documento, email, senha;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadastrar);

            nomeCompleto = findViewById(R.id.nomeCompletoCadastrar);
            telefone = findViewById(R.id.telefoneCadastrar);
            documento = findViewById(R.id.documentoCadastrar);
            email = findViewById(R.id.emailCadastrar);
            senha = findViewById(R.id.senhaCadastrar);


        }

        public void confirmarCadastro(View view) {
            String name = nomeCompleto.getText().toString();
            String tel = telefone.getText().toString();
            String doc = documento.getText().toString();
            String mail = email.getText().toString();
            String password = senha.getText().toString();

                if(name.matches("") || tel.matches("") || doc.matches("") || mail.matches("") || password.matches("")){
                    Toast.makeText(this, "Dados incompletos!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent segundoCadastrar = new Intent(Cadastrar.this, SegundoCadastrar.class);
                    segundoCadastrar.putExtra("NOME", name);
                    segundoCadastrar.putExtra("TEL", tel);
                    segundoCadastrar.putExtra("DOC", doc);
                    segundoCadastrar.putExtra("EMAIL", mail);
                    segundoCadastrar.putExtra("SENHA", password);
                    startActivity(segundoCadastrar);
                    finish();
                }

    }

        public void voltar(View view) {
            Intent mainActivity = new Intent(Cadastrar.this, MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
    }
