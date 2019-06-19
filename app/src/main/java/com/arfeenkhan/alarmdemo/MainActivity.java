package com.arfeenkhan.alarmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioButton rdinotification, rdiToast;
    Button btnOneTime, btnTwoTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rdinotification = findViewById(R.id.rdiNotification);
        rdiToast = findViewById(R.id.rdiToast);
        btnOneTime = findViewById(R.id.btnontTime);
        btnTwoTime = findViewById(R.id.btnTwoTime);

        btnOneTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rdinotification.isChecked())
                    startAlarm(true, false);
                else
                    startAlarm(false, false);
            }
        });

        btnTwoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rdinotification.isChecked())
                    startAlarm(true, true);
                else
                    startAlarm(false, false);

            }
        });
    }

    private void startAlarm(boolean isNotification, boolean isRepeating) {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;
        if (isNotification) {
            myIntent = new Intent(MainActivity.this, AlarmToastReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
        } else {
            myIntent = new Intent(MainActivity.this, AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
        }

        if (isRepeating)
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, pendingIntent);
        else
            manager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
    }
}
