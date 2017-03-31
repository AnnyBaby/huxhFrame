package com.frame.huxh.rxandroid20demo.rxjava;

import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;


/**
 * Created by Administrator on 2017/3/31.
 */

public class RxUtils {

    //Flowable类实现了“活动流”模式，并提供了工厂方法
    public static Flowable<String> createFlowable() {
        return Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("hi,Rxjava2.0");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
    }

    public static Flowable<Object> createFlowable(Object o) {
        return Flowable.just(o);
    }

    public static Flowable<Object> createFlowableMap(Object o) {
        return Flowable.just(o)
                .map(new Function<Object, Object>() {

                    @Override
                    public Object apply(@NonNull Object o) throws Exception {
                        return o + "+哈哈";
                    }
                }).map(new Function<Object, Object>() {
                    @Override
                    public Object apply(@NonNull Object o) throws Exception {
                        return o + "+好了";
                    }
                });
    }

    //List发射出来的一个一个的元素发射出去
    public static Flowable<Integer> createFlowableFlatMap(List<Integer> list) {
        return Flowable.just(list)
                .flatMap(new Function<List, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(@NonNull List list) throws Exception {
                        return Flowable.fromIterable(list);
                    }
                });
    }

   //想要订阅者只能收到大于6的数据 filter 是用于过滤数据的，返回false表示拦截此数据。
    public static Flowable<Integer> createFlowablefilter(final List<Integer> list) {
        return Flowable.just(list)
                .flatMap(new Function<List<Integer>, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(@NonNull List<Integer> integers) throws Exception {
                        return Flowable.fromIterable(list);
                    }
                }).filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer.intValue() > 6;
                    }
                });
    }


    //take 用于指定订阅者最多收到多少数据。
    public static Flowable<Integer> createFlowableTake(final List<Integer> list) {
        return Flowable.just(list)
                .flatMap(new Function<List<Integer>, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(@NonNull List<Integer> integers) throws Exception {
                        return Flowable.fromIterable(integers);
                    }
                }).take(2);
    }

    //如果我们想在订阅者接收到数据前干点事情，比如记录日志:
    public static Flowable<Integer> createFlowableDoOnNext(final List<Integer> list) {
//        return Flowable.just(list).doOnNext(new Consumer<List<Integer>>() {
//                    @Override
//                    public void accept(@NonNull List<Integer> integers) throws Exception {
//                        Log.i("DoOnNext" , integers.toString()+"");
//                    }
//                }).flatMap(new Function<List<Integer>, Publisher<Integer>>() {
//                    @Override
//                    public Publisher<Integer> apply(@NonNull List<Integer> integers) throws Exception {
//                        return Flowable.fromIterable(integers);
//                    }
//                });


        return  Flowable.fromIterable(list).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.i("DoOnNext" , integer.toString()+"");
            }
        });
    }

}
