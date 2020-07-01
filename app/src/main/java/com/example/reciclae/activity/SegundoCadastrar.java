package com.example.reciclae.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reciclae.R;
import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.model.Cliente;
import com.example.reciclae.model.Endereco;

public class SegundoCadastrar extends AppCompatActivity {

    private EditText rua, numero, complemento, bairro, cidade, estado, cep;
    private String nomeCompleto, usuario, documento, email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo_cadastrar);

        cep = findViewById(R.id.cepCadastrar);
        rua = findViewById(R.id.ruaCadastrar);
        numero = findViewById(R.id.numeroCadastrar);
        complemento = findViewById(R.id.complementoCadastrar);
        bairro = findViewById(R.id.bairroCadastrar);
        cidade = findViewById(R.id.cidadeCadastrar);
        estado = findViewById(R.id.estadoCadastrar);

        nomeCompleto = getIntent().getStringExtra("NOME");
        usuario = getIntent().getStringExtra("USER");
        documento = getIntent().getStringExtra("DOC");
        email = getIntent().getStringExtra("EMAIL");
        senha = getIntent().getStringExtra("SENHA");

    }

    public void voltar(View view) {
        Intent voltarcadastro = new Intent(SegundoCadastrar.this, Cadastrar.class);
        startActivity(voltarcadastro);
        finish();
    }

    public void confirmarCadastro(View view) {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        String cepString = cep.getText().toString();
        String ruaString = rua.getText().toString();
        String numeroString = numero.getText().toString();
        String complementoString = complemento.getText().toString();
        String bairroString = bairro.getText().toString();
        String cidadeString = cidade.getText().toString();
        String estadoString = estado.getText().toString();

        Cliente cliente = db.clienteDao().findByEmail(email);
        if(cliente != null){
            Toast.makeText(this, "Cliente já cadastrado!", Toast.LENGTH_SHORT).show();
        } else {
            if(cepString.matches("") || ruaString.matches("") || numeroString.matches("") || bairroString.matches("") || cidadeString.matches("") || estadoString.matches("")){
                Toast.makeText(this, "Dados incompletos!", Toast.LENGTH_SHORT).show();
            } else {
                cliente = new Cliente();
                cliente.nome = nomeCompleto;
                cliente.usuario = usuario;
                cliente.documento = documento;
                cliente.email = email;
                cliente.senha = senha;
                db.clienteDao().insertAllClientes(cliente);

                int idc = cliente.idc;

                Endereco endereco = new Endereco();
                endereco.cep = cepString;
                endereco.rua = ruaString;
                endereco.numero = numeroString;
                endereco.complemento = complementoString;
                endereco.bairro = bairroString;
                endereco.cidade = cidadeString;
                endereco.estado = estadoString;
                endereco.fk_idc = idc;
                //db.clienteDao().insertAllEnderecos(endereco);

                Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent mainActivity = new Intent(SegundoCadastrar.this, MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        }
    }
}
