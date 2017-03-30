package com.frame.huxh.rxandroiddemo.rxJava_Demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/30.
 */

public class LoginUtils {
   OkHttpClient mOkHttpClient;

    public LoginUtils() {
        mOkHttpClient = new OkHttpClient();
    }

    public Observable<CommonBean> Login(final String url, final Map<String,String> map){
        return Observable.create(new Observable.OnSubscribe<CommonBean>() {
            @Override
            public void call(final Subscriber<? super CommonBean> subscriber) {
                if(!subscriber.isUnsubscribed()){
                    FormBody.Builder  builder = new FormBody.Builder();
                    if(map!=null&&!map.isEmpty()){
                        for(Map.Entry<String,String> entry:map.entrySet()){
                            builder.add(entry.getKey(),entry.getValue());
                        }
                    }
                    RequestBody requestBody = builder.build();
                    Request request = new Request.Builder().url(url).post(requestBody).build();
                    mOkHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                              if(response.isSuccessful()){
                                  String result = response.body().string();
                                  TypeToken typeToken = new TypeToken< List<CommonBean>>(){};
                                  List<CommonBean> list = new Gson().fromJson(result,typeToken.getType());
                                  if(list.size()>0){
                                      subscriber.onNext(list.get(0));
                                  }
                              }
                            subscriber.onCompleted();//访问结束
                        }
                    });
                }


            }
        });
    }
//    private
}
