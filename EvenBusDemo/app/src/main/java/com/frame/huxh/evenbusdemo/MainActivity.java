package com.frame.huxh.evenbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.frame.huxh.evenbusdemo.evenbus.MainEvenBusActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_test1)
    Button mBtnTest1;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    private EventBus mEventBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test1)
    public void  test(){
        startActivity(new Intent(this, MainEvenBusActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
    }
}
