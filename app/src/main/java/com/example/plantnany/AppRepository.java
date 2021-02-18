package com.example.plantnany;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.plantnany.database.AppDataBase;
import com.example.plantnany.database.DataEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {

    private static AppRepository instance;
    private final AppDataBase mDatabase;
    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    public LiveData<List<DataEntity>> mList;

    public static AppRepository getInstance(Context context) {
        return instance = new AppRepository(context);

    }

    private AppRepository(Context context) {
        mDatabase = AppDataBase.getInstance(context);
        mList = getAllData();

    }


    public LiveData<List<DataEntity>> getAllData() {

        return mDatabase.dataDao().getAll();
    }

    public void insertData(DataEntity dataEntity) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.dataDao().insertData(dataEntity);
            }
        });
    }
}
