package com.example.reciclae.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.reciclae.DAO.ClienteDAO;
import com.example.reciclae.model.Cliente;

@Database(entities = {Cliente.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClienteDAO clienteDao();
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
