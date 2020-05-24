package com.example.reciclae.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Endereco {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_endereco")
    public int ide;

    @ColumnInfo(name = "rua")
    public String rua;

    @ColumnInfo(name = "numero")
    public String numero;

    @ColumnInfo(name = "cidade")
    public String cidade;

    @ColumnInfo(name = "complemento")
    public String complemento;

    @ColumnInfo(name = "uf")
    public String uf;

    @ColumnInfo(name = "bairro")
    public String bairro;

    @ColumnInfo(name = "cep")
    public String cep;

    public int fk_id_cliente;
}
