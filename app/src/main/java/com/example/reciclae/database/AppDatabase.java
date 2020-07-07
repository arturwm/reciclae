package com.example.reciclae.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.reciclae.dao.ClienteDAO;
import com.example.reciclae.dao.EnderecoDAO;
import com.example.reciclae.dao.ProdutoDAO;
import com.example.reciclae.model.Cliente;
import com.example.reciclae.model.Endereco;
import com.example.reciclae.model.Produto;

@Database(entities = {Cliente.class, Endereco.class, Produto.class}, version = 10)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClienteDAO clienteDao();
    public abstract EnderecoDAO enderecoDao();
    public abstract ProdutoDAO produtoDao();
    public static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context ctx) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class){
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx, AppDatabase.class, "room-reciclae")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
