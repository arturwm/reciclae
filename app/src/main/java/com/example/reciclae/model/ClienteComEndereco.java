package com.example.reciclae.model;

import androidx.room.Entity;
import androidx.room.Relation;

@Entity
public class ClienteComEndereco {
    public Cliente cliente;

    @Relation(
            parentColumn = "id_cliente",
            entityColumn =  "fk_id_cliente"
    )

    public Endereco endereco;
}
