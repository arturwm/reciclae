package com.example.reciclae.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ClienteComEndereco {
    @Embedded
    public Cliente cliente;
    //@Relation(parentColumn = "idc", entityColumn = "fk_idc")
    //public Endereco endereco;
}
