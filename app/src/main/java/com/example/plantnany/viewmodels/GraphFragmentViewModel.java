package com.example.plantnany.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.plantnany.AppRepository;
import com.example.plantnany.database.DataEntity;

import java.util.ArrayList;
import java.util.List;

public class GraphFragmentViewModel extends AndroidViewModel {

    public List<DataEntity> mList;
    private AppRepository mRepository;
    public GraphFragmentViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mList = new ArrayList<>();
    }

    public void getAllData() {
        mList = mRepository.getAllData();
    }
}
