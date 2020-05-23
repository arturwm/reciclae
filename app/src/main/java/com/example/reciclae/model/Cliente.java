package com.example.reciclae.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cliente {
    @PrimaryKey(autoGenerate = true)
    public int cid;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "usuario")
    public String usuario;

    @ColumnInfo(name = "documento")
    public String documento;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "senha")
    public String senha;

}
