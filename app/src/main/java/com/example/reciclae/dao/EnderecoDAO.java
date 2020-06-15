package com.example.reciclae.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.reciclae.model.Endereco;

import java.util.List;

@Dao
public interface EnderecoDAO {
    @Query("SELECT * FROM endereco")
    List<Endereco> getAll();

    @Query("SELECT * FROM endereco WHERE idE IN (:ids)")
    List<Endereco> loadAllByIds(int[] ids);

    @Query("SELECT * FROM endereco WHERE fk_idc LIKE :idc LIMIT 1")
    Endereco findByName(int idc);

    @Query("SELECT idE, cep, rua, cidade, estado, fk_idc FROM endereco WHERE cep LIKE :cep")
    Endereco findByCep(String cep);

    @Insert
    void insertAllEnderecos(Endereco... enderecos);

    @Delete
    void delete(Endereco endereco);
}
