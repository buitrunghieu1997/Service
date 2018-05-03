package com.example.latrodectus.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyBroadcastReceiver receiver;
    Intent intentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentService = new Intent(MainActivity.this, Test2.class);
                startService(intentService);
            }
        });

        findViewById(R.id.button_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentService = new Intent(MainActivity.this, Test2.class);
                intentService.setAction("STOP_SERVICE");
                stopService(intentService);
            }
        });

        IntentFilter filter = new IntentFilter("com.example.latrodectus.service.SHOW_DATA");
        receiver = new MyBroadcastReceiver();

        registerReceiver(receiver, filter);
    }
    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("Data");
            Log.d("Data", data);
        }

    }
}
