package com.example.plantnany.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.plantnany.AppRepository;
import com.example.plantnany.database.DataEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentViewModel extends AndroidViewModel {

    public LiveData<List<DataEntity>> mListEntity;
    private AppRepository mRepository;
    public FragmentViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mListEntity = mRepository.mList;
    }

    public LiveData<List<DataEntity>> getAllData() {
         return mRepository.getAllData();
    }

    public void insertData(DataEntity dataEntity) {
        mRepository.insertData(dataEntity);
    }


    public void insertClover(String toDate, int clovers) {
        mRepository.insertClover(toDate, clovers);
    }
}
