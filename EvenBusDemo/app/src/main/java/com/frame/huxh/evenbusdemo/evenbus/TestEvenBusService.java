package com.frame.huxh.evenbusdemo.evenbus;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TestEvenBusService extends Service {
    private static final String ACTION = "HUXH_TEXT_EVEN_BUS";
    private Intent mIntent;
    private int count;
    private Timer mTimer;

    public TestEvenBusService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
     return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        mIntent = new Intent(ACTION);
//        mIntent.setAction(ACTION);
        mTimer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                EventBus.getDefault().post(new OtherMessageEven(null,new Date(System.currentTimeMillis())+"",1));
            }
        };
        mTimer.schedule(task, 1000, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Subscribe
    public void onEvenMessage(MessageEvent event){
        if(event.type ==1){
            mIntent.putExtra("message",event.message);
            sendBroadcast(mIntent);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mTimer.cancel();
    }
}
