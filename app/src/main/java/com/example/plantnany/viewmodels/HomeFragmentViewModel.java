package com.example.plantnany.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.plantnany.AppRepository;
import com.example.plantnany.database.DataEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {

    public List<DataEntity> mListEntity = new ArrayList<>();
    private AppRepository mRepository;
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());

    }

    public void getAllData() {
         mListEntity = mRepository.getAllData();
    }

    public void inserData(DataEntity dataEntity) {
        mRepository.insertData(dataEntity);
    }
}
