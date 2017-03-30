package com.frame.huxh.rxandroiddemo.rxAndroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.frame.huxh.rxandroiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextRxAndroidActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.button5)
    Button mButton5;
    @BindView(R.id.button6)
    Button mButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_rx_android);
        ButterKnife.bind(this);
    }


    public  void  onClickButton(View view){
        RxUtils.createObservable(); Toast.makeText(this,"请查看日志 RxUtils",Toast.LENGTH_SHORT).show();

    }
    public  void  onClickButton2(View view){
        RxUtils.createPrint2();
        RxUtils.createObservable(); Toast.makeText(this,"请查看日志 RxUtils",Toast.LENGTH_SHORT).show();


    }

    @OnClick(R.id.button3)
    public  void  setButton3(){
        RxUtils.filter();
        RxUtils.createObservable(); Toast.makeText(this,"请查看日志 RxUtils",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button4)
    public  void  setButton4(){
        RxUtils.from();
        RxUtils.createObservable(); Toast.makeText(this,"请查看日志 RxUtils",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button5)
    public  void  setButton5(){
        RxUtils.just();
        RxUtils.createObservable(); Toast.makeText(this,"请查看日志 RxUtils",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button6)
    public  void  setButton6(){
        RxUtils.rang();
        RxUtils.createObservable(); Toast.makeText(this,"请查看日志 RxUtils",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button7)
    public  void  setButton7(){
        RxUtils.interval();
        RxUtils.createObservable(); Toast.makeText(this,"请查看日志 RxUtils",Toast.LENGTH_SHORT).show();
    }
}
