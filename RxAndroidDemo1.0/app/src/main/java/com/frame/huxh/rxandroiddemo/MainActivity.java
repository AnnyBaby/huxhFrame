package com.frame.huxh.rxandroiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.frame.huxh.rxandroiddemo.rxAndroid.TextRxAndroidActivity;
import com.frame.huxh.rxandroiddemo.rxJava_Demo.RxJavaActivity;
import com.frame.huxh.rxandroiddemo.rx_simpleObserve.MyTest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    void  setButton1Click(){
        new MyTest();
        Toast.makeText(this,"请单独运行Test测试类",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button2)
    void  setButton2Click(){
        new MyTest();
        Toast.makeText(this,"请单独运行MyTest测试类",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button3)
    void  setButton3Click(){
        Intent intent = new Intent(this, RxJavaActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button4)
    void  setButton4Click(){
        Intent intent = new Intent(this, TextRxAndroidActivity.class);
        startActivity(intent);
    }


}
