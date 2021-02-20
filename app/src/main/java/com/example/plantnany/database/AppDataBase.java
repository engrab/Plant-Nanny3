package com.example.plantnany.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {DataEntity.class}, version = 6)
public abstract class AppDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "appdatabase.db";


    public abstract DataDao dataDao();
    public static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    static Migration migration = new Migration(5,6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'user' ADD COLUMN 'plant_type' INTEGER NOT NULL DEFAULT 1 ");
        }
    };



    public static AppDataBase getInstance(Context context) {
        if (instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME)
                            .addMigrations(migration)
                            .build();
                }
            }
        }
        return instance;
    }
}
