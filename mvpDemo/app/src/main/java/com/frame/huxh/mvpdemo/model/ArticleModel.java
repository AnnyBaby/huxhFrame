package com.frame.huxh.mvpdemo.model;

import com.frame.huxh.mvpdemo.HttpService;
import com.frame.huxh.mvpdemo.bean.ActicleBean;
import com.frame.huxh.mvpdemo.interfacepkg.IacticleModel;

import org.reactivestreams.Subscriber;

/**
 * Created by Administrator on 2017/4/6.
 */

public class ArticleModel implements IacticleModel {

    private HttpService mHttpService = new HttpService();

    //获取文章，也是我们的业务逻辑
    public void requestArticleData(Subscriber<ActicleBean> subscriber){
        mHttpService.requsetArticleList(subscriber);
    }
}
