package com.example.plantnany;

import android.content.Context;

import com.example.plantnany.database.AppDataBase;
import com.example.plantnany.database.DataEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {

    private static AppRepository instance;
    private final AppDataBase mDatabase;
    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    public List<DataEntity> mList;

    public static AppRepository getInstance(Context context) {
        return instance = new AppRepository(context);

    }

    private AppRepository(Context context) {
        mDatabase = AppDataBase.getInstance(context);
    }


    public List<DataEntity> getAllData() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mList = mDatabase.dataDao().getAll();
            }
        });
        return mList;
    }

    public void insertData(DataEntity dataEntity) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.dataDao().inserData(dataEntity);
            }
        });
    }
}
