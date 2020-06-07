package com.example.reciclae.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.reciclae.model.Cliente;
import com.example.reciclae.model.ClienteComEndereco;

import java.util.List;

@Dao
public interface ClienteDAO {
    @Query("SELECT * FROM cliente")
    List<Cliente> getAll();

    @Query("SELECT * FROM cliente WHERE idc IN (:userIds)")
    List<Cliente> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM cliente WHERE nome LIKE :name LIMIT 1")
    Cliente findByName(String name);

    @Query("SELECT * FROM cliente WHERE email LIKE :email AND senha LIKE :senha")
    Cliente findByEmailAndSenha(String email, String senha);

    @Query("SELECT * FROM cliente WHERE email LIKE :email")
    Cliente findByEmail(String email);

    @Insert
    void insertAll(Cliente... clientes);

    @Delete
    void delete(Cliente cliente);

    @Transaction
    @Query("SELECT * FROM Cliente")
    public List<ClienteComEndereco> getClienteComEndereco();
}
