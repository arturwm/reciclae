package com.example.reciclae.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = {@ForeignKey(entity = Cliente.class, parentColumns = "idc", childColumns = "fk_idc")})
public class Produto {
    @PrimaryKey(autoGenerate = true)
    public int idProduto;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "quantidade")
    public String quantidade;

    @ColumnInfo(name = "valor")
    public double valor;

    @ColumnInfo(name = "vendedor")
    public String vendedor;

    @ColumnInfo(name = "fk_idc")
    public int fk_idc;

    @Override public String toString() {
        return "Produto: " + nome + "\nQuantidade: " + quantidade + " litros\nValor: R$" + valor + "\nVendedor: " + vendedor;
    }

}
