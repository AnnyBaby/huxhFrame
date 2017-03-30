package com.frame.huxh.rxandroiddemo.rx_watcher;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CreateWatcher implements Watcher {
    @Override
    public void update(String str) {
      System.out.print(str);
    }
}
