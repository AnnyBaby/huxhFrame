package com.frame.huxh.rxandroiddemo.rx_watcher;

/**
 * Created by Administrator on 2017/3/29.
 */

public class Test {
   public static  void main(String[] args) throws Exception{
       //被观察者
       CreateWatched watched = new CreateWatched();

       //观察者
       CreateWatcher watcher1 = new CreateWatcher();
       CreateWatcher watcher2 = new CreateWatcher();
       CreateWatcher watcher3 = new CreateWatcher();

       watched.addWatcher(watcher1);
       watched.addWatcher(watcher2);
       watched.addWatcher(watcher3);
       watched.notifyWatcher("我需要输出了\n");
   }
}
