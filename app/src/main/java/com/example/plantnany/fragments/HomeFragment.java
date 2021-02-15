package com.example.plantnany.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.plantnany.R;
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.database.AppDataBase;
import com.example.plantnany.database.DataEntity;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.example.plantnany.utils.AppUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_STORAGE = 100;
    private static final String TAG = "Mytag";
    RelativeLayout screenShot;
    ImageView mAddWater;
    private final AppDataBase appDataBase;
    private DataEntity dataEntity;
    private Executor executor = Executors.newSingleThreadExecutor();

    TextView targerWaterInfo;
    ViewGroup viewGroup;
    ImageView potsRedirect;
    TextView targetWater;
    TextView inTakeWater;
    int id = 0;
    List<DataEntity> all;
    DataEntity entity;

    String date;
    int intakeWater;


    public HomeFragment(Context context) {
        appDataBase = AppDataBase.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        entity = new DataEntity();
        init(view);
        onclickListener();
        all = new ArrayList<>();


        return view;

    }

    private void waterDialoge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Total Water = " + SharedPreferencesManager.getInstance(getActivity()).getTargetWater());
        builder.setMessage("Taken Water = " + intakeWater);
        builder.setCancelable(true);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getDBData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                all = appDataBase.dataDao().getAll();

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void onclickListener() {
        screenShot.setOnClickListener(this);
        mAddWater.setOnClickListener(this);
        potsRedirect.setOnClickListener(this);
    }

    private void init(View view) {

        screenShot = view.findViewById(R.id.rl_camera);
        mAddWater = view.findViewById(R.id.iv_add_water);
        targerWaterInfo = view.findViewById(R.id.tv_target_water_info);
        viewGroup = view.findViewById(R.id.relativeLayout3);
        potsRedirect = view.findViewById(R.id.iv_pots_redirect);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_pots_redirect:
                MainActivity.navigation.setSelectedItemId(R.id.navigation_pots);
                Toast.makeText(getActivity(), "pots redirect hit", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rl_camera:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    permissionCheck();
                } else {
                    takeScreenshot();
                }
                break;

            case R.id.iv_add_water:

                executor.execute(new Runnable() {
                    @Override
                    public void run() {

                        getDBData();

                        if (all.size() != 0) {

                            if (all.get(all.size() - 1).getDate().equals(AppUtils.getCurrentDate())) {
                                int lastWater = all.get(all.size() - 1).getIntakeWater();
                                dataEntity = new DataEntity(AppUtils.getCurrentDate(), 240 + lastWater);
                            } else {
                                dataEntity = new DataEntity(AppUtils.getCurrentDate(), 240);
                            }

                        } else {
                            dataEntity = new DataEntity(AppUtils.getCurrentDate(), 240);
                        }
                        appDataBase.dataDao().insertAll(dataEntity);


                    }
                });

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!all.isEmpty()) {
                            date = all.get(all.size() - 1).getDate();
                            intakeWater = all.get(all.size() - 1).getIntakeWater();

                            waterDialoge();
                        }
                    }
                }, 1000);


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

            shareScreenShot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }


    private void shareScreenShot(File fileScreenShot) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(fileScreenShot);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Share via"));
        }
    }

    public void permissionCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE);
            } else {
                takeScreenshot();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {

                Log.d(TAG, "onRequestPermissionsResult: ");
            } else {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}