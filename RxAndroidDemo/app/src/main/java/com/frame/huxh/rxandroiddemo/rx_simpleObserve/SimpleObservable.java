package com.frame.huxh.rxandroiddemo.rx_simpleObserve;

import java.util.Observable;

/**
 * Created by Administrator on 2017/3/29.
 * 被观察者
 */

public class SimpleObservable extends Observable {
    private int data = 0 ;

    public int getData() {
        return data;
    }

    public void setData(int data) {

        if(data!= this.data){
            this.data = data;
            setChanged();
            //通知观察者表示状态发生改变
            notifyObservers();
        }
    }
}
