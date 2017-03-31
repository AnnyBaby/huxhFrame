package com.frame.huxh.rxandroiddemo.rx_simpleObserve;

/**
 * Created by Administrator on 2017/3/29.
 */

public class MyTest {
    public static  void main(String[] args) throws Exception{
        SimpleObservable observable = new SimpleObservable();
        SimpleObserver observer = new SimpleObserver(observable);
        observable.setData(1);
        observable.setData(2);
        observable.setData(3);
        observable.setData(3);
        observable.setData(4);
//        observer.update(observable,null);
    }
}
