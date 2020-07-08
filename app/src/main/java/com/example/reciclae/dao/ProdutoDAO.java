package com.example.reciclae.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.reciclae.model.Produto;

import java.util.List;

@Dao
public interface ProdutoDAO {
    @Query("SELECT * FROM produto")
    List<Produto> getAll();

    @Query("SELECT * FROM produto WHERE idProduto IN (:ids)")
    List<Produto> loadAllByIds(int[] ids);

//    @Query("SELECT * FROM produto WHERE fk_idc LIKE :idc LIMIT 1")
//    Produto findByName(int idc);

    @Insert
    void insertAllProdutos(Produto... produtos);

    @Delete
    void delete(Produto produtos);

//    @Query("DELETE FROM produto WHERE quantidade LIKE :qtd AND valor LIKE :vl AND vendedor LIKE :seller")
//    void deletarComWhere(String qtd, double vl, String seller);
}
