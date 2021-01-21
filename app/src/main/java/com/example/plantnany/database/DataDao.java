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

    @Query("SELECT * FROM user WHERE date IN (:userIds)")
    List<DataModel> loadAllByIds(int[] userIds);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(DataModel... users);

}
