package com.example.plantnany.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    @Query("SELECT * FROM user")
    List<DataModel> getAll();

    @Query("SELECT targetWater FROM user WHERE date= :date")
    float getTargetWaterDB(long date);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(DataModel users);

}
