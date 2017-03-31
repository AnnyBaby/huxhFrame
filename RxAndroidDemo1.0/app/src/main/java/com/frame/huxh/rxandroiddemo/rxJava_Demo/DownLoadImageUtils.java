package com.frame.huxh.rxandroiddemo.rxJava_Demo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/30.
 */

public class DownLoadImageUtils {
    private static OkHttpClient mOkHttpClient;

    public DownLoadImageUtils() {
        mOkHttpClient = new OkHttpClient();
    }

    public  Observable<byte[]> dowmLoadImage(final String path){
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                if(!subscriber.isUnsubscribed()){
                    final Request request = new Request.Builder().url(path).build();
                    mOkHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if(response.isSuccessful()){
                                byte[] data = response.body().bytes();
                                if(data!=null){
                                    subscriber.onNext(data);
                                }
                            }

                            subscriber.onCompleted();
                        }
                    });
                }

            }
        });
     }
}
