package com.example.plantnany.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.plantnany.R;
import com.example.plantnany.database.AppDataBase;
import com.example.plantnany.database.DataModel;
import com.example.plantnany.sharedpref.SharedPreferencesManager;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment implements View.OnClickListener {

    RelativeLayout screenShot;
    ImageView mAddWater;
    private final AppDataBase appDataBase;
    private DataModel dataModel;
    private Date date = new Date();
    private Executor executor = Executors.newSingleThreadExecutor();
    float targetWaterDB;
    TextView targerWaterInfo;

    public HomeFragment(Context context) {
        appDataBase = AppDataBase.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        onclickListener();
        return view;

    }

    private void onclickListener() {
        screenShot.setOnClickListener(this);
        mAddWater.setOnClickListener(this);
    }

    private void init(View view) {

        screenShot = view.findViewById(R.id.rl_camera);
        mAddWater = view.findViewById(R.id.iv_add_water);
        targerWaterInfo = view.findViewById(R.id.tv_target_water_info);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_camera:
                takeScreenshot();
                break;

            case R.id.iv_add_water:

                executor.execute(new Runnable() {
                    @Override
                    public void run() {

                        dataModel = new DataModel(date.getTime(),
                                SharedPreferencesManager.getInstance(getActivity()).getWeight(),
                                SharedPreferencesManager.getInstance(getActivity()).getTargetWater(), 240);

                        appDataBase.dataDao().insertAll(dataModel);

                        targetWaterDB = appDataBase.dataDao().getTargetWaterDB(date.getTime());
                        targerWaterInfo.setText(String.valueOf(targetWaterDB));
                    }
                });


                break;
        }
    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getActivity().getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(Intent.createChooser(intent, "choose one"));
    }
}