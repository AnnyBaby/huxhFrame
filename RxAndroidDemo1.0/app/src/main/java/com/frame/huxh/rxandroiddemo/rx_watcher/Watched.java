package com.frame.huxh.rxandroiddemo.rx_watcher;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface Watched {
    public  void addWatcher(Watcher watcher);
    public  void removeWatcher(Watcher watcher);
    public  void notifyWatcher(String str);
}
