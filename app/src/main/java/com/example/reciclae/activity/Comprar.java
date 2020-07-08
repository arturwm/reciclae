package com.example.reciclae.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.reciclae.R;
import com.example.reciclae.dao.ClienteDAO;
import com.example.reciclae.database.AppDatabase;
import com.example.reciclae.model.Cliente;
import com.example.reciclae.model.Produto;

import java.util.List;

public class Comprar extends AppCompatActivity {

    private String email;
    private ListView lvComprar;
    private List MyDataObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        email = getIntent().getStringExtra("EMAIL");
        lvComprar = findViewById(R.id.lvComprar);

        List<Produto> produtos = AppDatabase.getInstance(getApplicationContext()).produtoDao().getAll();

        final ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, produtos);

        lvComprar.setAdapter(adapter);

        final AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        lvComprar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(Comprar.this);
                adb.setTitle("Comprar");
                adb.setMessage("Deseja comprar o item " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Comprar", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //MyDataObject.remove(positionToRemove);
                        db.produtoDao().delete(adapter.getItem(positionToRemove));
                        adapter.remove(adapter.getItem(positionToRemove));
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });
    }


    public void voltar(View view) {
        Intent mainMenu = new Intent(Comprar.this, MainMenu.class);
        mainMenu.putExtra("EMAIL", email);
        startActivity(mainMenu);
        finish();
    }
}
