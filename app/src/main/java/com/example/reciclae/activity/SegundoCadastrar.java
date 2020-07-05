package com.example.reciclae.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.reciclae.R;
import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.model.Cliente;
import com.example.reciclae.model.Endereco;

public class SegundoCadastrar extends AppCompatActivity {

    private EditText rua, numero, complemento, bairro, cidade, cep;
    private String nomeCompleto, usuario, documento, email, senha;
    private Spinner estadoSpinner;
    private boolean estadoSelected = false;

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

        nomeCompleto = getIntent().getStringExtra("NOME");
        usuario = getIntent().getStringExtra("USER");
        documento = getIntent().getStringExtra("DOC");
        email = getIntent().getStringExtra("EMAIL");
        senha = getIntent().getStringExtra("SENHA");

        estadoSpinner = findViewById(R.id.estadoSpinner);
        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(this,
                R.array.estados, android.R.layout.simple_list_item_1);

        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSpinner.setAdapter(adp);
        estadoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                if(position == 0) {
                    onNothingSelected(arg0);
                    estadoSelected = false;
                } else {
                    estadoSelected = true;
                    String ss = estadoSpinner.getSelectedItem().toString();
                    Toast.makeText(getBaseContext(), ss, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                estadoSelected = false;
                Toast.makeText(getBaseContext(), "Por favor, selecione um estado!", Toast.LENGTH_SHORT).show();
            }
        });

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
        String estadoString = estadoSpinner.getSelectedItem().toString();

        Cliente cliente = db.clienteDao().findByEmail(email);
        if(cliente != null){
            Toast.makeText(this, "Cliente já cadastrado!", Toast.LENGTH_SHORT).show();
        } else {
            if(cepString.matches("") || ruaString.matches("") || numeroString.matches("") || bairroString.matches("") || cidadeString.matches("") || estadoSelected == false){
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
                db.enderecoDao().insertAllEnderecos(endereco);

                Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent mainActivity = new Intent(SegundoCadastrar.this, MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        }
    }
}
