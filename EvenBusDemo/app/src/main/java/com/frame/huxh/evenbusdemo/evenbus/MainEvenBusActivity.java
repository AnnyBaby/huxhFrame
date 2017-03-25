package com.frame.huxh.evenbusdemo.evenbus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frame.huxh.evenbusdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainEvenBusActivity extends BaseEvenBusActivity {


    @BindView(R.id.btn_test1)
    Button mBtnTest1;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    private EventBus mEventBus;

    @BindView(R.id.text_content)
    TextView mTextContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evenbus_activity_main);
        ButterKnife.bind(this);
        //下面这一条的效果是完全一样的
    }


    //在onStart调用register后，执行消息
    @Subscribe
    public void onEventMainThread(MessageEvent event){
//        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
        if(event.type == 0){
            mTextContent.setText(event.message);
        }
    }

    @OnClick(R.id.btn_test1)
    public void  test(){
        startActivity(new Intent(this, TestEvenBusActivity.class));
    }

}
