package com.frame.huxh.evenbusdemo.evenbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public class BaseEvenBusActivity extends AppCompatActivity {
    public EventBus mEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
    }
}
