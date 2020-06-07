package com.example.reciclae.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = {@ForeignKey(entity = Cliente.class, parentColumns = "idc", childColumns = "fk_idc")})
public class Endereco {
    @PrimaryKey(autoGenerate = true)
    public int idE;

    @ColumnInfo(name = "cep")
    public String cep;

    @ColumnInfo(name = "rua")
    public String rua;

    @ColumnInfo(name = "numero")
    public String numero;

    @ColumnInfo(name = "complemento")
    public String complemento;

    @ColumnInfo(name = "bairro")
    public String bairro;

    @ColumnInfo(name = "cidade")
    public String cidade;

    @ColumnInfo(name = "estado")
    public String estado;

    public int fk_idc;
}
