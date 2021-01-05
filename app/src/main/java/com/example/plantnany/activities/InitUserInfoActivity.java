package com.example.plantnany.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.plantnany.R;
import com.example.plantnany.utils.AdsUtility;
import com.example.plantnany.utils.AppUtils;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;

public class InitUserInfoActivity extends AppCompatActivity {


    private static final String TAG = "InitUserInfoActivity";
    public boolean doubleBackToExitPressedOnce;
    public SharedPreferences sharedPref;
    public long sleepingTime;
    public long wakeupTime;
    public String weight = "";
    public String workTime = "";



    @Override
    protected void onStart() {
        super.onStart();
        sharedPref = getSharedPreferences(AppUtils.USERS_SHARED_PREF, MODE_PRIVATE);

        if (sharedPref.getBoolean(AppUtils.FIRST_RUN_KEY, true)) {
            Log.d(TAG, "onStart: First Time");
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(8192);
        }

        setContentView(R.layout.activity_init_user_info);
        AdsUtility.InterstitialAdmob(this);
        SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.USERS_SHARED_PREF, AppUtils.PRIVATE_MODE);
        sharedPref = sharedPreferences;
        wakeupTime = sharedPref.getLong(AppUtils.WAKEUP_TIME, 1558323000000L);
        sleepingTime = sharedPref.getLong(AppUtils.SLEEPING_TIME_KEY, 1558369800000L);
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.etWakeUpTime);
        EditText editText = textInputLayout.getEditText();

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(wakeupTime);
                TimePickerDialog mTimePicker = new TimePickerDialog(InitUserInfoActivity.this, new TimePickerDialog.OnTimeSetListener() {


                    public final void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Calendar time = Calendar.getInstance();
                        time.set(11, selectedHour);
                        time.set(12, selectedMinute);
                        wakeupTime = time.getTimeInMillis();
                        TextInputLayout textInputLayout = findViewById(R.id.etWakeUpTime);
                        EditText editText = textInputLayout.getEditText();
                        if (editText == null) {

                        }
                        Object[] objArr = {Integer.valueOf(selectedHour), Integer.valueOf(selectedMinute)};
                        String format = String.format("%02d:%02d", Arrays.copyOf(objArr, objArr.length));
                        editText.setText(format);
                    }
                }, calendar.get(11), calendar.get(12), false);
                mTimePicker.setTitle("Select Wakeup Time");
                mTimePicker.show();
            }
        });
        TextInputLayout textInputLayout2 = (TextInputLayout) findViewById(R.id.etSleepTime);
        EditText editText2 = textInputLayout2.getEditText();

        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(sleepingTime);
                TimePickerDialog mTimePicker = new TimePickerDialog(InitUserInfoActivity.this, new TimePickerDialog.OnTimeSetListener() {


                    public final void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Calendar time = Calendar.getInstance();
                        time.set(11, selectedHour);
                        time.set(12, selectedMinute);

                        sleepingTime = time.getTimeInMillis();
                        TextInputLayout textInputLayout = findViewById(R.id.etSleepTime);
                        EditText editText = textInputLayout.getEditText();

                        Object[] objArr = {Integer.valueOf(selectedHour), Integer.valueOf(selectedMinute)};
                        String format = String.format("%02d:%02d", Arrays.copyOf(objArr, objArr.length));
                        editText.setText(format);
                    }
                }, calendar.get(11), calendar.get(12), false);
                mTimePicker.setTitle("Select Sleeping Time");
                mTimePicker.show();
            }
        });
        findViewById(R.id.btnContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object systemService = getSystemService(INPUT_METHOD_SERVICE);
                if (systemService != null) {
                    ConstraintLayout constraintLayout = findViewById(R.id.init_user_info_parent_layout);
                    ((InputMethodManager) systemService).hideSoftInputFromWindow(constraintLayout.getWindowToken(), 0);
                    TextInputLayout textInputLayout = findViewById(R.id.etWeight);
                    EditText editText = textInputLayout.getEditText();
                    if (editText == null) {
                    }
                    weight = editText.getText().toString();
                    TextInputLayout textInputLayout2 = findViewById(R.id.etWorkTime);
                    EditText editText2 = textInputLayout2.getEditText();
                    if (editText2 == null) {
                    }
                    workTime = editText2.getText().toString();
                    if (TextUtils.isEmpty(weight)) {
                        Snackbar.make(v, "Please input your weight", BaseTransientBottomBar.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(weight) > 200 || Integer.parseInt(weight) < 20) {
                        Snackbar.make(v, "Please input a valid weight", BaseTransientBottomBar.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(workTime)) {
                        Snackbar.make(v, "Please input your workout time", BaseTransientBottomBar.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(workTime) > 500 || Integer.parseInt(workTime) < 0) {
                        Snackbar.make(v, "Please input a valid workout time", BaseTransientBottomBar.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences.Editor editor = getSharedPreferences(AppUtils.USERS_SHARED_PREF, MODE_PRIVATE).edit();
                        editor.putInt(AppUtils.WEIGHT_KEY, Integer.parseInt(weight));
                        editor.putInt(AppUtils.WORK_TIME_KEY, Integer.parseInt(workTime));
                        editor.putLong(AppUtils.WAKEUP_TIME, wakeupTime);
                        editor.putLong(AppUtils.SLEEPING_TIME_KEY, sleepingTime);
                        editor.putBoolean(AppUtils.FIRST_RUN_KEY, false);
                        double totalIntake = AppUtils.calculateIntake(Integer.parseInt(weight), Integer.parseInt(workTime));
                        DecimalFormat df = new DecimalFormat("#");
                        df.setRoundingMode(RoundingMode.CEILING);
                        String total_intake = AppUtils.TOTAL_INTAKE;
                        String format = df.format(totalIntake);
                        editor.putInt(total_intake, Integer.parseInt(format));
                        editor.apply();
                        startActivity(new Intent(InitUserInfoActivity.this, MainActivity.class));
                        finish();
                    }
                    AdsUtility.showInterstitialAds();
                    return;
                }

            }
        });
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        doubleBackToExitPressedOnce = true;
        Window window = getWindow();
        Snackbar.make(window.getDecorView(), "Please click BACK again to exit", BaseTransientBottomBar.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);
    }
}