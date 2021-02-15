package com.example.plantnany.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {DataEntity.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "appdatabase.db";


    public abstract DataDao dataDao();
    public static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public static AppDataBase getInstance(Context context) {
        if (instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
