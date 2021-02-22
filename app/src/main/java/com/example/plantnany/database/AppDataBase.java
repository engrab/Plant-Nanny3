package com.example.plantnany.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {DataEntity.class}, version = 9)
public abstract class AppDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "appdatabase.db";


    public abstract DataDao dataDao();
    public static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    static Migration migration = new Migration(6,7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'user' ADD COLUMN 'complete' INTEGER NOT NULL DEFAULT 0 ");
        }
    };
    static Migration migrationSeed = new Migration(7,8) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'user' ADD COLUMN 'seed' INTEGER NOT NULL DEFAULT 1 ");
        }
    };
    static Migration migrationClover = new Migration(8,9) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'user' ADD COLUMN 'clover' INTEGER NOT NULL DEFAULT 1 ");
        }
    };



    public static AppDataBase getInstance(Context context) {
        if (instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME)
                            .addMigrations(migration, migrationSeed, migrationClover)
                            .build();
                }
            }
        }
        return instance;
    }
}
