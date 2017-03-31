package com.frame.huxh.rxandroiddemo.rxAndroid;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/29.
 */

public class RxUtils {
    private static  String TAG = RxUtils.class.getSimpleName();


    /**
     * 使用create方式
     */
    public static  void  createObservable(){
        //定义观察者
        Observable observable = Observable.create(new OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("huxh1");
                subscriber.onNext("huxh2");
                subscriber.onNext("huxh3");
                subscriber.onNext(dowmLoadData());
                subscriber.onCompleted();
            }
        });
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,"result-->"+s);
            }
        };
       observable.subscribe(subscriber);//关联被观察者
    }

    private static  String dowmLoadData(){
        return "JSON DATA";
    }


    /**
     * create 第二种方式
     */
    public static void createPrint2(){
        Observable.create(new OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
              if(!subscriber.isUnsubscribed()){
                  for(int i = 0 ; i < 10 ;i++){
                      subscriber.onNext(i);
                  }
                  subscriber.onCompleted();
              }
            }
        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG,"result-->"+o);
            }
        });
    }

    /***
     * 使用在被观察者，返回的对象一般都是数值类型
     */
    public static void  from(){
        Integer[] items = new Integer[]{1,2,3,4,5,6};
        Observable observable = Observable.from(items);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG,"result--->"+o.toString());
            }
        });
    }

    /**
     * 处理数组集合
     */
    public static void  just(){
        Integer[] items1 = new Integer[]{1,2,3,4,5,6};
        Integer[] items2 = new Integer[]{7,8,9,10,11};
        Observable observable = Observable.just(items1,items2);
        observable.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,e.getMessage());
            }

            @Override
            public void onNext(Integer[] o) {
                for (int i:o) {
                    Log.i(TAG,"result-->"+i);
                }

            }
        });
    }

    /**
     * 指定某一时刻进行数据发送
     */
    public static void interval(){
        Integer[] items = {1,2,3,4,5,6,7,8,9,10};
        Observable obserable = Observable.interval(1,1, TimeUnit.SECONDS);
        obserable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG,o.toString());
            }
        });
    }
    /**
     * 使用范围数据，指定输出数据的范围
     */
    public  static void  rang(){
        Observable observale  = Observable.range(1,40);
        observale.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                Log.i(TAG,"result-->"+o);
            }
        });
    }

    /**
     * 使用过滤功能
     */

    public static  void  filter(){
        Observable observable = Observable.just(1,2,3,4,5,6,8);
        observable.filter(new Func1<Integer,Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer<5;
            }


        }).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer o) {
               Log.i(TAG,"result--->"+o.toString());
            }
        });
    }



}
