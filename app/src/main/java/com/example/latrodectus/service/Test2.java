package com.example.latrodectus.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Test2 extends Service {
    public Test2() {
    }

    boolean isRunning = true;

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        Log.d("onStartCommand: ", "Success");
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 120 && isRunning; ++i) {
                    try {
                        Thread.sleep(250);
                        Intent intent = new Intent("com.example.latrodectus.service.SHOW_DATA");
                        intent.putExtra("Data", "Data_item_" + i);
                        sendBroadcast(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("onDestroy: ", "Success");
        isRunning = false;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
