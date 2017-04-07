package com.frame.huxh.mvpdemo.presenter;

import com.frame.huxh.mvpdemo.interfacepkg.RefreshViewInterface;

/**
 * Created by Administrator on 2017/4/6.
 */

public class ArticlePresenter {

     RefreshViewInterface  mActicleInterface;


    public ArticlePresenter(RefreshViewInterface acticleInterface) {
        mActicleInterface = acticleInterface;
    }

    public void fetchArticles(){


    }


}
