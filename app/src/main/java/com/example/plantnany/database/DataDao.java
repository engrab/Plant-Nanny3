package com.example.plantnany.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    @Query("SELECT * FROM user")
    LiveData<List<DataEntity>> getAll();

    @Query("SELECT * FROM user")
    DataEntity getWater();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(DataEntity users);

    @Query("UPDATE user SET clover = :clovers WHERE date = :date")
    int insertClover(String date, int clovers);

}
