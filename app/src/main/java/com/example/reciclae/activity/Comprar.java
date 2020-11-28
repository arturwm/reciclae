package com.example.reciclae.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reciclae.R;
import com.example.reciclae.custom.CustomAdapter;
import com.example.reciclae.model.Produto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comprar extends AppCompatActivity {

    ListView lvComprar;
    private List MyDataObject;
    List<Produto> produtosGlobal = new ArrayList<>();

    private CustomAdapter adapter;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        lvComprar = findViewById(R.id.lvComprar);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        final FirebaseUser user = mAuth.getCurrentUser();

        final CollectionReference produtos = db.collection("produto");

        produtos
                .whereEqualTo("status", "aberto")
                .whereNotEqualTo("id_vendedor", user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        map.put(doc.getId(), doc.toObject(Produto.class));
                    }
                    realizarCompra(map);
                }
            }
        });


        lvComprar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long id) {
                final Produto p = produtosGlobal.get(i);
                AlertDialog.Builder adb = new AlertDialog.Builder(Comprar.this);
                adb.setTitle("Comprar");
                adb.setMessage("Deseja comprar o item " + p.nome);
                final int positionToRemove = i;
                adb.setNegativeButton("Cancelar", null);
                adb.setPositiveButton("Comprar", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Map<String, Object> dados = new HashMap<>();
                        dados.put("id_produto", p.id_produto);
                        dados.put("display_vendedor", p.display_vendedor);
                        dados.put("id_vendedor", p.id_vendedor);
                        dados.put("nome", p.nome);
                        dados.put("qtd", p.qtd);
                        dados.put("valor", p.valor);
                        dados.put("id_comprador", user.getUid());
                        dados.put("display_comprador", user.getDisplayName());
                        dados.put("status", "fechado");

                        db.collection("produto")
                                .document(p.id_produto)
                                .set(dados)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Comprar.this, "SUCESSO", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Comprar.this, "FALHA AO CADASTRAR", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        updateUI(user);

                        adapter.remove(positionToRemove);


                    }});
                adb.show();

            }
        });

    }

    private void realizarCompra(Map<String, Object> map) {

        final List<Produto> produtos = new ArrayList<>();
        produtosGlobal = produtos;

        for(Map.Entry<String, Object> produtoM : map.entrySet()){
            Produto produto = (Produto) produtoM.getValue();
            produto.id_produto = produtoM.getKey();
            produtos.add(produto);
        }

        adapter = new CustomAdapter(produtos, this);

        lvComprar.setAdapter(adapter);

    }



    public void voltar(View view) {
        Intent mainMenu = new Intent(Comprar.this, MainMenu.class);
        startActivity(mainMenu);
        finish();
    }


    private void updateUI(FirebaseUser user) {
        Intent mainMenu = new Intent(Comprar.this, MainMenu.class);
        startActivity(mainMenu);
        Toast.makeText(this, "Compra realizada com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
