package com.frame.huxh.rxandroiddemo.rx_simpleObserve;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2017/3/29.
 * 观察者
 */

public class SimpleObserver implements Observer {

    public SimpleObserver(SimpleObservable observable){
        observable.addObserver(this);
    }
    //当被观察者发生改变的时候，就会被触发
    @Override
    public void update(Observable observable, Object arg) {
      System.out.println("data is changed:"+((SimpleObservable)observable).getData());
    }
}
