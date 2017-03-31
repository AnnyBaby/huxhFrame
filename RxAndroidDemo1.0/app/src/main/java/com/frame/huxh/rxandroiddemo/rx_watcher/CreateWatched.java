package com.frame.huxh.rxandroiddemo.rx_watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CreateWatched implements Watched {
    private List<Watcher> mList = new ArrayList<>();
    @Override
    public void addWatcher(Watcher watcher) {
        mList.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
      mList.remove(watcher);
    }

    @Override
    public void notifyWatcher(String str) {
        for (Watcher watcher:mList) {
            watcher.update(str);
        }
    }
}
