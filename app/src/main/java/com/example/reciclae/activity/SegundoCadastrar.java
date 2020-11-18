        package com.example.reciclae.activity;

        import androidx.annotation.NonNull;
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
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.firestore.FirebaseFirestore;

        import java.util.HashMap;
        import java.util.Map;

        public class SegundoCadastrar extends AppCompatActivity {

            private EditText rua, numero, complemento, bairro, cidade, cep;
            private String nomeCompleto, usuario, documento, email, senha;
            private Spinner estadoSpinner;
            private boolean estadoSelected = false;
            private FirebaseAuth mAuth;
            private FirebaseFirestore db;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_segundo_cadastrar);

                mAuth = FirebaseAuth.getInstance();

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

                String cepString = cep.getText().toString();
                String ruaString = rua.getText().toString();
                String numeroString = numero.getText().toString();
                String complementoString = complemento.getText().toString();
                String bairroString = bairro.getText().toString();
                String cidadeString = cidade.getText().toString();
                String estadoString = estadoSpinner.getSelectedItem().toString();


                    if(cepString.matches("") || ruaString.matches("") || numeroString.matches("") || bairroString.matches("") || cidadeString.matches("") || estadoSelected == false){
                        Toast.makeText(this, "Dados incompletos!", Toast.LENGTH_SHORT).show();
                    } else {
                     /* cliente = new Cliente();
                        cliente.nome = nomeCompleto;
                        cliente.usuario = usuario;
                        cliente.documento = documento;
                        cliente.email = email;
                        cliente.senha = senha;
                        db.clienteDao().insertAllClientes(cliente);

                        int idc = cliente.idc;

                     endereco.cep = cepString;
                        endereco.rua = ruaString;
                        endereco.numero = numeroString;
                        endereco.complemento = complementoString;
                        endereco.bairro = bairroString;
                        endereco.cidade = cidadeString;
                        endereco.estado = estadoString;
                        endereco.fk_idc = idc;
                        db.enderecoDao().insertAllEnderecos(endereco); */

                        mAuth.createUserWithEmailAndPassword(email,senha)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            Map<String, Object> dados = new HashMap<>();
                                            dados.put("nome", nomeCompleto);
                                            dados.put("usuario", usuario);
                                            dados.put("documento", documento);

                                            db.collection("cliente").document(user.getUid())
                                                    .set(dados)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(SegundoCadastrar.this, "SUCESSO", Toast.LENGTH_SHORT).show();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(SegundoCadastrar.this, "FALHA AO CADASTRAR", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                            updateUI(user);
                                        }else {
                                            Toast.makeText(SegundoCadastrar.this, "FALHA AO CADASTRAR", Toast.LENGTH_SHORT).show();
                                            updateUI(null);
                                        }
                                    }
                                });
                    }



                    }

            private void updateUI(FirebaseUser user) {
                if(user != null){
                    Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent mainActivity = new Intent(SegundoCadastrar.this, MainActivity.class);
                    startActivity(mainActivity);
                    finish();
                }

            }

                }


