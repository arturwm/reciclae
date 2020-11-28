package com.example.reciclae.custom;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.reciclae.R;
import com.example.reciclae.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<Produto> produtos = new ArrayList<>();
    Activity act;

    public CustomAdapter(Context context, List<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    public CustomAdapter(List<Produto> produtos, Activity act) {
        this.produtos = produtos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView nome;
        TextView valor;
        TextView vendedor;

        Produto p = produtos.get(position);

        View view = act.getLayoutInflater().inflate(R.layout.list_view_produtos, parent, false);

        //if(convertView == null){
         //   convertView = LayoutInflater.from(context).inflate(R.layout.list_view_produtos, parent, false);
       // }

        nome = view.findViewById(R.id.txtNome);
        valor = view.findViewById(R.id.txtValor);
        vendedor = view.findViewById(R.id.txtVendedor);

        nome.setText("Nome: " + p.nome);
        valor.setText("Valor: " + String.valueOf(p.valor));
        vendedor.setText("Vendedor: " + p.display_vendedor);

        return view;
    }

    public void remove(int posicao){
        produtos.remove(posicao);
    }
}
