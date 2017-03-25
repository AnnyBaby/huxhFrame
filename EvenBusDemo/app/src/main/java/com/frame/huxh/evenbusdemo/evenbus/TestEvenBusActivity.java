package com.frame.huxh.evenbusdemo.evenbus;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frame.huxh.evenbusdemo.FragmentAdapter;
import com.frame.huxh.evenbusdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestEvenBusActivity extends FragmentActivity {

    @BindView(R.id.btn_receive)
    Button mBtnReceive;
    @BindView(R.id.text_content)
    TextView mTextContent;
    EventBus mEventBus;
    @BindView(R.id.testListViewPaper)
    ViewPager mTestListViewPaper;
    @BindView(R.id.start_srvice_btn)
    Button mStartSrviceBtn;
    @BindView(R.id.unregist_srvice_btn)
    Button mUnregistSrviceBtn;
    @BindView(R.id.service_message)
    TextView mServiceMessage;
    @BindView(R.id.activity_test_even_bus)
    LinearLayout mActivityTestEvenBus;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private ServiceConnection mServicConnection;
    private Intent mServiceIntent;
    private TestChangeUIReceiver mTestChangeUIReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_even_bus);
        ButterKnife.bind(this);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
        initFrament();
        initReceiver();
    }

    @OnClick(R.id.btn_receive)
    public void setBtnReceive() {
        mEventBus.post(new MessageEvent("我是从TestEvenBusActivity来的消息", 0));
    }

    @OnClick(R.id.send_meaasge_service_btn)
    public void sendMessage() {
        mEventBus.post(new MessageEvent("我是从TestEvenBusActivity-->TestEvenBusService来的消息,通过TestChangeUIReceiver广播刷新UI", 1));
    }

    @OnClick(R.id.start_srvice_btn)
    public void startSrviceBtn() {
        mServiceIntent = new Intent(this, TestEvenBusService.class);
        startService(mServiceIntent);
        mServicConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(mServiceIntent, mServicConnection, 0);
    }


    @OnClick(R.id.unregist_srvice_btn)
    public void unRegistService() {
        unbindService(mServicConnection);
//        unRegistService();
        stopService(mServiceIntent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, priority = 90)
    public void onEvenOtherMessage(OtherMessageEven even) {
        if(even.getType() ==0){
            mTextContent.setText(even.getMessage());
        }else if(even.getType() ==1){
                mServiceMessage.setText(even.getMessage());
        }
    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("HUXH_TEXT_EVEN_BUS");
        mTestChangeUIReceiver = new TestChangeUIReceiver();
        registerReceiver(mTestChangeUIReceiver, filter);
    }


    private void initFrament() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentList.add(new TestEvenBusFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, mFragmentList);
        mTestListViewPaper.setAdapter(fragmentAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
        unregisterReceiver(mTestChangeUIReceiver);
    }

    class TestChangeUIReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String messge = intent.getStringExtra("message");
            mTextContent.setText(messge);
        }
    }


}


