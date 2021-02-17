package com.example.plantnany.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.plantnany.database.DataEntity;
import com.example.plantnany.database.DateConverter;
import com.example.plantnany.databinding.FragmentGraphBinding;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.example.plantnany.viewmodels.GraphFragmentViewModel;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GraphFragment extends Fragment {

    private FragmentGraphBinding binding;
    private GraphFragmentViewModel mViewModel;
    private Context mContext;
    private List<DataEntity> mDataList = new ArrayList<>();
    DataEntity mEntity;
    private Date currentDate = new Date();
    private static final String TAG = "GraphFragment";
    private List<BarEntry> entries = new ArrayList<>();


    public GraphFragment(Context context) {
        mContext = context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentGraphBinding.inflate(inflater, container, false);
        initViewModel();
        getAllData();

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWaterInfo();
        setGraph();
    }

    private void setGraph() {
        int targetWater = SharedPreferencesManager.getInstance(getActivity()).getTargetWater();
        for (int i = 0 ; i <= mDataList.size() -1; i++){
            int intakeWater = mDataList.get(i).getIntakeWater();
            int percent = (intakeWater *100) / targetWater;
            entries.add(new BarEntry((float) i, percent));
        }
        if (!entries.isEmpty()){
            BarDataSet barDataSet = new BarDataSet(entries, "Drink Chart");
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(16f);

            BarData barData = new BarData(barDataSet);
            binding.barChart.setFitBars(false);
            binding.barChart.setData(barData);
            binding.barChart.getDescription().setText("Drink Chart");
            binding.barChart.animate();
        }
    }

    private void getAllData() {
        mViewModel.getAllData();
        mDataList = mViewModel.mList;

    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(getActivity()).get(GraphFragmentViewModel.class);
    }

    @Override
    public void onResume() {


        super.onResume();
    }


    private void getWaterInfo() {

        getAllData();

        int targetWater = SharedPreferencesManager.getInstance(getActivity()).getTargetWater();
        binding.tvTargetIntake.setText(String.valueOf(targetWater));
        if (mDataList.size() != 0) {

            String date = mDataList.get(mDataList.size() - 1).getDate();
            if (date.equals(DateConverter.dateToString(currentDate.getTime()))) {
                int intakeWater = mDataList.get(mDataList.size() - 1).getIntakeWater();
                int progress = (intakeWater * 100) / targetWater;
                binding.waterLevelView.setProgressValue(progress);
                binding.waterLevelView.setCenterTitle(progress + "%");
                int remainWater = targetWater - intakeWater;
                if (remainWater >= 0) {

                    binding.tvRemainingIntake.setText(String.valueOf(remainWater));
                } else {
                    Toast.makeText(mContext, "Excess water dangerious", Toast.LENGTH_SHORT).show();
                    binding.tvRemainingIntake.setText(String.valueOf(0));
                }
            } else {
                binding.waterLevelView.setProgressValue(0);
                binding.waterLevelView.setCenterTitle(0 + "%");
                binding.tvRemainingIntake.setText(0 + "ml");

            }
        } else {
            Toast.makeText(mContext, "Please take some Water", Toast.LENGTH_SHORT).show();
            binding.waterLevelView.setProgressValue(0);
            binding.waterLevelView.setCenterTitle(0 + "%");
            binding.tvRemainingIntake.setText(0 + "ml");
        }

    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}