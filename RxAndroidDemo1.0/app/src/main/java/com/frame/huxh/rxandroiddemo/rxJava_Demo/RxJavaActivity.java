package com.frame.huxh.rxandroiddemo.rxJava_Demo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.frame.huxh.rxandroiddemo.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.login)
    Button mLogin;
    private String path = "http://r3.ykimg.com/05410408554F7C7D6A0A4504566FD13A";
    private String TAG = RxJavaActivity.class.getSimpleName();

    @BindView(R.id.dowmloadImage)
    Button mDowmloadImage;
    private LoginUtils mLoginUtils;
    private ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);
        mProgressBar = new ProgressDialog(this);
        mProgressBar.setTitle("login......");
    }

    @OnClick(R.id.dowmloadImage)
    void setDowmloadImageOnClick() {
        DownLoadImageUtils loadImageUtils = new DownLoadImageUtils();
        loadImageUtils.dowmLoadImage(path).subscribe(new Subscriber<byte[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                mImage.setImageBitmap(bitmap);
            }
        });
    }


    @OnClick(R.id.login)
    void  loginClick(){
        Map<String,String> map = new HashMap<>();
        map.put("userId",mName.getText().toString().trim());
        map.put("password",mPassword.getText().toString().trim());
        map.put("browerType","android");
        mLoginUtils = new LoginUtils();
        mProgressBar.show();
        mLoginUtils.Login("http://www.ysydgj.com/tp/userlogin.php",map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonBean>() {
            @Override
            public void onCompleted() {
                mProgressBar.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                   Log.i(TAG,e.getMessage());
            }

            @Override
            public void onNext(CommonBean commonBean) {

                Toast.makeText(RxJavaActivity.this,commonBean.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
