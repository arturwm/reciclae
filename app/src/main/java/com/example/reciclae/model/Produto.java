package com.example.reciclae.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Produto {

    public String display_vendedor;

    public String id_vendedor;

    public String nome;

    public String qtd;

    public long valor;

    public String id_produto;

    @Override
    public String toString() {
        return "Produto{" +
                "display_vendedor='" + display_vendedor + '\'' +
                ", id_vendedor='" + id_vendedor + '\'' +
                ", nome='" + nome + '\'' +
                ", qtd='" + qtd + '\'' +
                ", valor=" + valor +
                '}';
    }
}
