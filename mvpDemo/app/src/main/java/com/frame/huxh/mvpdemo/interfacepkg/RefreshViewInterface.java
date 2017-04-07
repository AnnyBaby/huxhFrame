package com.frame.huxh.mvpdemo.interfacepkg;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */

public interface RefreshViewInterface<T> {
    public void   showArticles(List<T> list);
    public  void  showLoading();
    public  void  hideLoading();

}
