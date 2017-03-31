package com.frame.huxh.rxandroid20demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.frame.huxh.rxandroid20demo.rxjava.RxUtils;
import com.frame.huxh.rxandroid20demo.utils.ToastUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

//你可以在 Publisher 中查询数据库或者从网络上获取数据，然后在 Subscriber 中显示。
//Publisher 不只有一种，事实上 Flowable 和 Processor 所有的子类都属于 Publisher。
//在数据发射途中，你可以利用操作符对数据进行变换。
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.getFlowable_just)
    Button mGetFlowableJust;
    @BindView(R.id.getFlowable_map)
    Button mGetFlowableMap;
    @BindView(R.id.getFlowable_flatmap)
    Button mGetFlowableFlatmap;
    @BindView(R.id.getFlowable_filter)
    Button mGetFlowableFilter;
    @BindView(R.id.getFlowable_take)
    Button mGetFlowableTake;
    @BindView(R.id.getFlowable_doOnNext)
    Button mGetFlowableDoOnNext;
    @BindView(R.id.querydata)
    Button mQuerydata;
    private String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.getFlowable)
    Button mGetFlowable;

    private List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
    }

    @OnClick({R.id.getFlowable_just, R.id.getFlowable_map, R.id.getFlowable, R.id.getFlowable_flatmap, R.id.getFlowable_filter, R.id.getFlowable_take, R.id.getFlowable_doOnNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getFlowable:
                RxUtils.createFlowable().subscribe(new Subscriber<String>() {
                    //，在onSubscribe中，我们需要调用request去请求资源，参数就是要请求的数量，一般如果不限制请求数量，
                    // 可以写成Long.MAX_VALUE。如果你不调用request，Subscriber的onNext和onComplete方法将不会被调用。
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.i(TAG, "Subscription");
                        s.request(Long.MAX_VALUE);
                    }

                    //onNext方法里面传入的参数就是Flowable中发射出来的。
                    @Override
                    public void onNext(String s) {
                        ToastUtils.toast(MainActivity.this, s.toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.i(TAG, t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "onComplete");
                    }
                });
                break;
            case R.id.getFlowable_just:
                RxUtils.createFlowable("你好").subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        ToastUtils.toast(MainActivity.this, o.toString());
                    }
                });
                break;
            case R.id.getFlowable_map:
                //map()操作符
                RxUtils.createFlowableMap("开始").subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        ToastUtils.toast(MainActivity.this, o.toString());
                    }
                });
                break;

            case R.id.getFlowable_flatmap:
                RxUtils.createFlowableFlatMap(list).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        ToastUtils.toast(MainActivity.this, integer.toString());
                    }
                });
                break;
            case R.id.getFlowable_filter:
                RxUtils.createFlowablefilter(list).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        ToastUtils.toast(MainActivity.this, integer.toString());
                    }
                });
                break;
            case R.id.getFlowable_take:
                RxUtils.createFlowableTake(list).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        ToastUtils.toast(MainActivity.this, integer.toString());
                    }
                });
                break;
            case R.id.getFlowable_doOnNext:
                RxUtils.createFlowableDoOnNext(list).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        ToastUtils.toast(MainActivity.this, integer.toString());
                    }
                });
                break;

        }

    }

    @OnClick(R.id.querydata)
    public void onViewClicked() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 添加RxJava2的适配器支持
                .build();
        BaiDuService service = retrofit.create(BaiDuService.class);
        service.getText()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(ResponseBody s) {
                        Toast.makeText(MainActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
                        try {
                            System.out.println(s.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(MainActivity.this, "获取失败，请检查网络是否畅通", Toast.LENGTH_SHORT).show();
                        Log.e(TAG,t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("任务结束");
                    }
                });
    }
}
